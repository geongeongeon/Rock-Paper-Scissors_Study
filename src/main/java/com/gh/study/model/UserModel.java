package com.gh.study.model;

public class UserModel {
    int userNo;
    String userId;
    String userPw;
    String userNickname;

    public UserModel(int userNo, String userId, String userPw, String userNickname) {
        this.userNo = userNo;
        this.userId = userId;
        this.userPw = userPw;
        this.userNickname = userNickname;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    @Override
    public String toString() {
        return String.format("%d | %s | %s | %s", userNo, userId, userPw, userNickname);
    }
}
