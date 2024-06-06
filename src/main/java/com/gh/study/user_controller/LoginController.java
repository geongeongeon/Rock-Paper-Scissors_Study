package com.gh.study.user_controller;

import com.gh.study.model.UserModel;
import com.gh.study.session.UserSession;

import static com.gh.study.container.Container.userModelMap;

public class LoginController {
    public Integer checkHasIdAndGetKey(String userId) {
        for(Integer userNo : userModelMap.keySet()) { //userModelMap의 모든 키들을 하나씩 조회.
            UserModel user = userModelMap.get(userNo); //userModelMap의 각 키에 들어있는 UserModel을 조회.
            if (user.getUserId().equals(userId)) { //각 키에서 userId를 가져온 후, 매개변수로 받은 userId와 같은지 확인.
                return userNo; //조건문이 참이면 userNo를 반환함
            }
        }
        return null;
    }

    public boolean checkEqualPw(String userPw, String checkPw) {
        return userPw.equals(checkPw);
    }

    public void changeSession(Integer key_loginUser) {
        UserSession.setLoginUser(userModelMap.get(key_loginUser));
    }

    public String getKeyLoginUserPw(Integer key_loginUser) {
        UserModel user = userModelMap.get(key_loginUser);
        return user.getUserPw();
    }
}
