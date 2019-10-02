package com.bushemi.web.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

public class LogOutFilter implements Filter {
    private static final Logger LOG = LoggerFactory.getLogger("LogOutFilter");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String uri = httpRequest.getRequestURI();
        if (uri.endsWith("login")) {
            HttpSession session = httpRequest.getSession(true);
            if (nonNull(session.getAttribute("role"))) {
                LOG.info("log out by session id {}", session.getId());
                session.setAttribute("role", null);
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
