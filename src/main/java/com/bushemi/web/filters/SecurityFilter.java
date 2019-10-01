package com.bushemi.web.filters;

import com.bushemi.service.implementations.UrlSecurityServiceImpl;
import com.bushemi.service.interfaces.UrlSecurityService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityFilter implements Filter {
    private final UrlSecurityService urlSecurityService = new UrlSecurityServiceImpl();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String role = httpRequest.getParameter("role");
        String url = httpRequest.getRequestURL().toString();
        if (!urlSecurityService.doesRoleHasAccessToUrl(role, url, httpRequest.getMethod())) {
            httpResponse.sendRedirect("/404");
        }
    }

    @Override
    public void destroy() {

    }
}
