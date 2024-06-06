package com.gh.study.user_controller;

import com.gh.study.App;
import com.gh.study.model.UserModel;

import static com.gh.study.container.Container.*;

public class JoinController {
    public boolean checkHasId(String userId) {
        return userIdMap.containsKey(userId);
    }

    public boolean checkEqualPw(String userPw, String check_userPw) {
        return userPw.equals(check_userPw);
    }

    public boolean checkHasNickname(String userNickname) {
       return userNicknameMap.containsKey(userNickname);
    }

    public void join(String userId, String userPw, String userNickname) {
        int userNextNo = App.getUserNextNo();

        UserModel userModel = new UserModel(userNextNo, userId, userPw, userNickname);
        userModelMap.put(userNextNo, userModel);
        userIdMap.put(userModel.getUserId(), userModel);
        userNicknameMap.put(userModel.getUserNickname(), userModel);

        App.setUserNextNo(++userNextNo);
    }
}
