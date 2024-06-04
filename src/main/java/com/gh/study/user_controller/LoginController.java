package com.gh.study.user_controller;

import com.gh.study.model.UserModel;
import com.gh.study.session.UserSession;

import static com.gh.study.container.Container.userModelMap;

public class LoginController {
    public Integer checkHasIdAndGetKey(String userId) {
        for (Integer userNo : userModelMap.keySet()) {
            UserModel user = userModelMap.get(userNo);
            if (userId.equals(user.getUserId())) {
                return userNo;
            }
        }
        return null;
    }

    public boolean checkEqualPw(String userPw, String checkPw) {
        if(userPw.equals(checkPw)) {
            return true;
        } else {
            return false;
        }
    }

    public void changeSession(Integer key_loginUser) {
        UserSession.setLoginUser(userModelMap.get(key_loginUser));
    }

    public String getKeyLoginUserPw(Integer key_loginUser) {
        UserModel user = userModelMap.get(key_loginUser);
        return user.getUserPw();
    }
}
