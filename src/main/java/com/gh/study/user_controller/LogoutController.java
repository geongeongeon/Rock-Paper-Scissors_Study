package com.gh.study.user_controller;

import com.gh.study.session.UserSession;

public class LogoutController {
    public void setUserSession() {
        UserSession.setIsLogin(false);
        UserSession.setLoginUser(null);
    }
}
