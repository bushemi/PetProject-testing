package com.bushemi.web.servlets;

import com.bushemi.dao.entity.PassedTest;
import com.bushemi.model.OptionForSessionDto;
import com.bushemi.model.QuestionForSessionDto;
import com.bushemi.service.implementations.PassedTestsServiceImpl;
import com.bushemi.service.interfaces.PassedTestsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.nonNull;

public class QuestionServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger("QuestionServlet");
    private final PassedTestsService passedTestsService = new PassedTestsServiceImpl();
    private static final String COUNT_RIGHT_ANSWERS = "countRightAnswers";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        LOG.info("user with session id = {}", session.getId());
        List<QuestionForSessionDto> questions = (ArrayList<QuestionForSessionDto>) session.getAttribute("questions");
        Integer currentQuestion = (Integer) session.getAttribute("currentQuestion");
        if (nonNull(questions) && nonNull(currentQuestion)) {
            QuestionForSessionDto questionForSessionDto = questions.get(0);
            LOG.info("put question {} into session = {}", questionForSessionDto, session.getId());
            session.setAttribute("question", questionForSessionDto);
            resp.sendRedirect("/question");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        LOG.info("user with session id = {}", session.getId());
        List<QuestionForSessionDto> questions = (ArrayList<QuestionForSessionDto>) session.getAttribute("questions");
        Integer currentQuestion = (Integer) session.getAttribute("currentQuestion");
        validateResults(session);
        if (nonNull(questions) && nonNull(currentQuestion)) {
            currentQuestion++;
            if (currentQuestion < questions.size()) {
                QuestionForSessionDto questionForSessionDto = questions.get(currentQuestion);
                session.setAttribute("question", questionForSessionDto);
                session.setAttribute("currentQuestion", currentQuestion);
                resp.sendRedirect("/question");
            } else {
                int countRightAnswers = (int) session.getAttribute(COUNT_RIGHT_ANSWERS);
                long testId = (long) session.getAttribute("testId");
                long userId = (long) session.getAttribute("userId");
                session.setAttribute("question", null);
                session.setAttribute("currentQuestion", null);
                session.setAttribute("questions", null);
                LocalDateTime startTime = (LocalDateTime) session.getAttribute("startTime");
                long spentSeconds = ChronoUnit.SECONDS.between(startTime, LocalDateTime.now());
                LOG.info("test completed by user with id = {}. Spent time {} for test {}", userId, spentSeconds, userId);
                session.setAttribute("spentTime", spentSeconds);
                passedTestsService.save(new PassedTest(null, testId, userId, countRightAnswers, (int) spentSeconds));
                resp.sendRedirect("/testResult");
            }
        }
    }

    private void validateResults(HttpSession session) {
        String textFromRequest = (String) session.getAttribute("requestBody");
        String[] answers = textFromRequest.split("&");
        List<Integer> indexesOfAnswers = Stream.of(answers)
                .map(answer -> answer.split("="))
                .map(answer -> answer[0])
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        QuestionForSessionDto question = (QuestionForSessionDto) session.getAttribute("question");
        List<OptionForSessionDto> options = question.getOptions();
        int countRightAnswers = (int) session.getAttribute(COUNT_RIGHT_ANSWERS);
        for (OptionForSessionDto option : options) {
            if (indexesOfAnswers.contains(option.getId().intValue()) && option.isCorrect()) {
                countRightAnswers++;
            }
        }
        session.setAttribute(COUNT_RIGHT_ANSWERS, countRightAnswers);
    }
}
