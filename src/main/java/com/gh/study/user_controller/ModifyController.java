package com.gh.study.user_controller;

import com.gh.study.session.UserSession;

import java.util.Map;

public class ModifyController {
    public boolean checkContainsKeyIdAndPw(Map<String, String> checkIdPwMap) {
        boolean hasKey_id = false;
        boolean hasKey_pw = false;
        for(String key : checkIdPwMap.keySet()) {
            if(key.equals("id")) {
                hasKey_id = true;
            } else if(key.equals("pw")) {
                hasKey_pw = true;
            }
        }
        if(hasKey_id && hasKey_pw) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkEqualIdAndPw(Map<String, String> checkIdPwMap) {
        if(checkIdPwMap.get("id").equals(UserSession.getLoginUser().getUserId()) &&
                checkIdPwMap.get("pw").equals(UserSession.getLoginUser().getUserPw())) {
            return true;
        }
        return false;
    }
}
