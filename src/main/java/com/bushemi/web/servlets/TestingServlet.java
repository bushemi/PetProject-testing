package com.bushemi.web.servlets;

import com.bushemi.model.QuestionForSessionDto;
import com.bushemi.model.TestForSessionDto;
import com.bushemi.service.implementations.QuestionServiceImpl;
import com.bushemi.service.implementations.TestServiceImpl;
import com.bushemi.service.interfaces.QuestionService;
import com.bushemi.service.interfaces.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static java.util.Objects.nonNull;

public class TestingServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger("TestingServlet");
    private final TestService testService = new TestServiceImpl();
    private final QuestionService questionService = new QuestionServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        LOG.info("user  with session id = {}", session.getId());
        String testIdString = req.getPathInfo();
        LOG.info("test id = {}", testIdString);
        Long testId = null;
        if (nonNull(testIdString)) {
            testId = Long.parseLong(testIdString.substring(1));
        }
        if (nonNull(testId)) {
            TestForSessionDto testById = testService.findTestById(testId);
            LocalDateTime localDateTime = LocalDateTime.now().plusSeconds(testById.getSecondsToComplete());
            long time = convertToDateViaInstant(localDateTime).getTime();
            List<QuestionForSessionDto> questionsByTestId = questionService.findQuestionsByTestId(testId);

            session.setAttribute("startTime", LocalDateTime.now());
            session.setAttribute("finalTime", time);
            session.setAttribute("testId", testId);
            session.setAttribute("questions", questionsByTestId);
            session.setAttribute("currentQuestion", 0);
            session.setAttribute("countRightAnswers", 0);
            session.setAttribute("countWrongAnswers", 0);
            resp.sendRedirect("/testing");
        }
    }

    private Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return Date
                .from(dateToConvert.atZone(ZoneId.systemDefault())
                        .toInstant());
    }
}
