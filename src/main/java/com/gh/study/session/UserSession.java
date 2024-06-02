package com.gh.study.session;

import com.gh.study.model.UserModel;

public class UserSession {
    private static boolean isLogin;
    private static UserModel loginUser;

    public static boolean getIsLogin() {
        return isLogin;
    }

    public static void setIsLogin(boolean isLogin) {
        UserSession.isLogin = isLogin;
    }

    public static UserModel getLoginUser() {
        return loginUser;
    }

    public static void setLoginUser(UserModel loginUser) {
        UserSession.loginUser = loginUser;
    }
}
