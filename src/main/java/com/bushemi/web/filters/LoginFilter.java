package com.bushemi.web.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;

public class LoginFilter implements Filter {
    private static final Logger LOG = LoggerFactory.getLogger("LoginFilter");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(true);
        String textFromRequest = textFromRequest(httpRequest);
        if (!textFromRequest.isEmpty()) {
            session.setAttribute("requestBody", textFromRequest);
        }
        String url = httpRequest.getRequestURL().toString();
        LOG.info("url = {}. Session id = {}", url, session.getId());
        if (url.endsWith("users") && !textFromRequest.contains("isNewUser=true")) {
            httpResponse.sendRedirect("/authentication");
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void destroy() {

    }

    private String textFromRequest(HttpServletRequest req) throws IOException {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = req.getReader()) {
            reader.lines().forEach(builder::append);
        }
        LOG.info("result of textFromRequest method = {}", builder);
        return builder.toString().trim();
    }
}
