package com.bushemi.web.filters;

import com.bushemi.service.implementations.UrlSecurityServiceImpl;
import com.bushemi.service.interfaces.UrlSecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityFilter implements Filter {
    private static final Logger LOG = LoggerFactory.getLogger("SecurityFilter");
    private final UrlSecurityService urlSecurityService = new UrlSecurityServiceImpl();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String role = (String) httpRequest.getSession(true).getAttribute("role");
        String uri = httpRequest.getRequestURI();
        if (!urlSecurityService.doesRoleHasAccessToUrl(role, uri, httpRequest.getMethod())) {
            LOG.info("this uri {} is forbidden fo this role {}", uri, role);
            httpResponse.sendRedirect("/404");
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
