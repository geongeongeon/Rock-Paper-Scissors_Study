package com.gh.study.session;

import com.gh.study.model.UserModel;

public class UserSession {
    private static boolean isLogin;
    private static UserModel loginUser;

    static {
        isLogin = false;
        loginUser = null;
    }

    public static boolean getIsLogin() {
        return isLogin;
    }

    public static UserModel getLoginUser() {
        return loginUser;
    }

    public static void setLoginUser(UserModel loginUser) {
        UserSession.loginUser = loginUser;
        if(UserSession.loginUser != null) {
            UserSession.isLogin = true;
        } else {
            UserSession.isLogin = false;
        }
    }
}
