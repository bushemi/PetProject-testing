package com.bushemi.web.servlets;

import com.bushemi.model.TestForTestsPage;
import com.bushemi.service.implementations.TestServiceImpl;
import com.bushemi.service.interfaces.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class TestsServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger("TestsServlet");
    private final TestService testService = new TestServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOG.info("TestsServlet.doGet");
        HttpSession session = req.getSession();

        List<TestForTestsPage> testsForTestsPages = testService.findAllTests();
        session.setAttribute("tests", testsForTestsPages);
        resp.sendRedirect("allTests");
    }

}
