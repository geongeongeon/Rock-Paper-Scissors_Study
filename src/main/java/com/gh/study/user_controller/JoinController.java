package com.gh.study.user_controller;

import com.gh.study.App;
import com.gh.study.model.UserModel;

import static com.gh.study.container.Container.userModelMap;

public class JoinController {
    public boolean checkHasId(String userId) {
        for(Integer userNo : userModelMap.keySet()) {
            UserModel user = userModelMap.get(userNo);
            if(userId.equals(user.getUserId())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkEqualPw(String userPw, String check_userPw) {
        if(userPw.equals(check_userPw)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkHasNickname(String userNickname) {
        for(Integer userNo : userModelMap.keySet()) {
            UserModel user = userModelMap.get(userNo);
            if(userNickname.equals(user.getUserNickname())) {
                return true;
            }
        }
        return false;
    }

    public void join(String userId, String userPw, String userNickname) {
        int userNextNo = App.getUserNextNo();

        UserModel userModel = new UserModel(userNextNo, userId, userPw, userNickname);
        userModelMap.put(userNextNo, userModel);

        App.setUserNextNo(++userNextNo);
    }
}
