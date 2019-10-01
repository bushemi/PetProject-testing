package com.bushemi.service.interfaces;

public interface UrlSecurityService {

    boolean doesRoleHasAccessToUrl(String roleName, String url, String method);
}
