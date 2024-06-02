package com.gh.study;

import com.gh.study.factory.TestDataFactory;
import com.gh.study.session.UserSession;

import static com.gh.study.container.Container.*;

public class App {
    private static int userNextNo;

    public static int getUserNextNo() {
        return userNextNo;
    }

    public static void setUserNextNo(int userNextNo) {
        App.userNextNo = userNextNo;
    }

    public App() {
        loadTestData();

        UserSession.setIsLogin(true); //로그인 상태
        UserSession.setLoginUser(userModelMap.get(4)); //로그인한 유저 정보
    }

    private void loadTestData() {
       TestDataFactory testDataFactory = new TestDataFactory();
       testDataFactory.makeTestUser();
       userNextNo = testDataFactory.getUserLastNo() + 1;
    }

    public void run() {
        while(true) {
            System.out.print("command) ");
            String cmd = scanner.nextLine();

            action(cmd);
        }
    }

    private void action(String cmd) {
        if(cmd.equals("/app/start")) {
            gameView.gameStart();
        } else if(cmd.equals("/app/ranking")) {
            gameView.gameRanking();
        } else if(cmd.equals("/usr/join")) {
            userView.userJoin();
        } else if(cmd.equals("/usr/login")) {
            userView.userLogin();
        } else if(cmd.equals("/usr/whoami")) {
            userView.userWhoami();
        } else if(cmd.split("\\?", 2)[0].equals("/usr/modify")) {
            userView.userModify(cmd);
        } else if(cmd.equals("/usr/logout")) {
            userView.userLogout();
        } else if(cmd.equals("/usr/list")) {
            userView.userList();
        } else if(cmd.equals("/app/exit")) {
            gameView.gameExit();
        } else {
            System.out.println("===== unknown command! =====");
        }
    }
}