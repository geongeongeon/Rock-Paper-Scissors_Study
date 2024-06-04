package com.gh.study.user_controller;

import com.gh.study.model.UserModel;

import java.util.ArrayList;
import java.util.List;

import static com.gh.study.container.Container.userModelMap;

public class ListController {
    public List<UserModel> getUserInfo() {
        List<UserModel> userList = new ArrayList<>();
        for(Integer userNo : userModelMap.keySet()) {
            userList.add(userModelMap.get(userNo));
        }
        return userList;
    }
}
