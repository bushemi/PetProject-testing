package com.bushemi.web.servlets;

import com.bushemi.converters.TestParser;
import com.bushemi.dao.entity.Test;
import com.bushemi.model.PassedTestForSessionDto;
import com.bushemi.model.TestForTestsPage;
import com.bushemi.service.implementations.PassedTestsServiceImpl;
import com.bushemi.service.implementations.TestServiceImpl;
import com.bushemi.service.interfaces.PassedTestsService;
import com.bushemi.service.interfaces.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestsServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger("TestsServlet");
    private final TestService testService = new TestServiceImpl();
    private final PassedTestsService passedTestsService = new PassedTestsServiceImpl();
    private final TestParser testParser = new TestParser();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOG.info("TestsServlet.doGet");
        HttpSession session = req.getSession();
        List<TestForTestsPage> testsForTestsPages = testService.findAllTests();
        long userId = (long) session.getAttribute("userId");
        enhanceTestsWithPassedTestInformation(testsForTestsPages, userId);
        session.setAttribute("tests", testsForTestsPages);
        resp.sendRedirect("allTests");
    }

    private void enhanceTestsWithPassedTestInformation(List<TestForTestsPage> testsForTestsPages, long userId) {
        Map<Long, PassedTestForSessionDto> allByUserId = passedTestsService.findAllByUserId(userId);
        Set<Long> testIds = allByUserId.keySet();
        testsForTestsPages.stream()
                .filter(test -> testIds.contains(test.getId()))
                .peek(test -> test.setCorrectAnswers(allByUserId.get(test.getId()).getCorrectAnswers()))
                .forEach(test -> test.setSpentTime(allByUserId.get(test.getId()).getSpentTime()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        String requestBody = (String) session.getAttribute("requestBody");
        Test test = testParser.fromString(requestBody);
        Long savedId = testService.save(test);
        resp.setStatus(HttpServletResponse.SC_OK);
        session.setAttribute("editTestId", savedId);
    }
}
