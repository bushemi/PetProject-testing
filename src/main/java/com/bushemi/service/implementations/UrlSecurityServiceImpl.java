package com.bushemi.service.implementations;

import com.bushemi.service.interfaces.UrlSecurityService;

import java.util.*;

import static java.util.Objects.isNull;

public class UrlSecurityServiceImpl implements UrlSecurityService {
    private static final String GET = "get";
    private static final String POST = "post";
    private static final String PUT = "put";
    private static final String DELETE = "delete";
    private Map<String, List<String>> freeLinksToStudent = new HashMap<>();
    private Map<String, List<String>> freeLinksToAnyone = new HashMap<>();
    private Map<String, List<String>> freeLinksToAdmin = new HashMap<>();

    public UrlSecurityServiceImpl() {
        initFreeLinksToAnyone();
        initFreeLinksToStudent();
        initFreeLinksToAdmin();
    }

    private void initFreeLinksToAnyone() {
        List<String> emptyList = Collections.emptyList();
        freeLinksToAnyone.put(GET, Arrays.asList("/404", "/index", "/start", "/login", "/authentication"));
        freeLinksToAnyone.put(POST, Arrays.asList("/users"));
        freeLinksToAnyone.put(PUT, emptyList);
        freeLinksToAnyone.put(DELETE, emptyList);
    }

    private void initFreeLinksToStudent() {
        List<String> emptyList = Collections.emptyList();
        freeLinksToStudent.put(GET, Arrays.asList("/404", "/index", "/start", "/login", "/authentication", "/userProfile"
                , "/testResult", "/question", "/testing", "/allTests", "/navigation"));
        freeLinksToStudent.put(POST, Arrays.asList("/users"));
        freeLinksToStudent.put(PUT, emptyList);
        freeLinksToStudent.put(DELETE, emptyList);
    }

    private void initFreeLinksToAdmin() {
        List<String> emptyList = Collections.emptyList();
        freeLinksToAdmin.put(GET, Arrays.asList("/404", "/index", "/start", "/login", "/authentication", "/userProfile"
                , "/testResult", "/question", "/testing", "/allTests", "/navigation", "/allUsers", "/addTest", "/editTest", "/addQuestion"));
        freeLinksToAdmin.put(POST, Arrays.asList("/users"));
        freeLinksToAdmin.put(PUT, emptyList);
        freeLinksToAdmin.put(DELETE, emptyList);
    }

    @Override
    public boolean doesRoleHasAccessToUrl(String roleName, String url, String method) {
        if (isNull(roleName)) {
            return validateUrl(freeLinksToAnyone, url, method);
        }
        if (roleName.equalsIgnoreCase("admin")) {
            return validateUrl(freeLinksToAdmin, url, method);
        }
        if (roleName.equalsIgnoreCase("student")) {
            return validateUrl(freeLinksToStudent, url, method);
        }

        return false;
    }

    private boolean validateUrl(Map<String, List<String>> urlMap, String url, String method) {
        for (String openUrl : urlMap.get(method.toLowerCase())) {
            if (url.contains(openUrl)) {
                return true;
            }
        }
        return false;
    }

}
