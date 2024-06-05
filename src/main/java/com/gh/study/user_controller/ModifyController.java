package com.gh.study.user_controller;

import com.gh.study.model.UserModel;
import com.gh.study.session.UserSession;

import java.util.Map;

import static com.gh.study.container.Container.userModelMap;

public class ModifyController {
    public boolean checkContainsKeyIdAndPw(Map<String, String> checkIdPwMap) {
        return checkIdPwMap.containsKey("id") && checkIdPwMap.containsKey("pw");
    }

    public boolean checkEqualIdAndPw(Map<String, String> checkIdPwMap) {
        UserModel loginUser = UserSession.getLoginUser();
        return checkIdPwMap.get("id").equals(loginUser.getUserId()) &&
                checkIdPwMap.get("pw").equals(loginUser.getUserPw());
    }

    public String checkWhatChange(String whatChange) {
        switch (whatChange) {
            case "id":
                return "id";
            case "pw":
                return "pw";
            case "nickname":
                return "nickname";
            default:
                return null;
        }
    }

    public boolean checkEqualPwAndNewPw(String newPassword, String checkNewPassword) {
        return newPassword.equals(checkNewPassword);
    }

    public void changeUserPw(String newPassword) {
        UserSession.getLoginUser().setUserPw(newPassword);
    }

    public boolean checkHasNickname(String newNickname) {
        for(Integer userNo : userModelMap.keySet()) {
            UserModel user = userModelMap.get(userNo);
            if(newNickname.equals(user.getUserNickname())) {
                return true;
            }
        }
        return false;
    }

    public void changeUserNickname(String newNickname) {
        UserSession.getLoginUser().setUserNickname(newNickname);
    }
}
