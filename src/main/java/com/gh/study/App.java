package com.gh.study;

import com.gh.study.factory.TestDataFactory;
import com.gh.study.session.UserSession;

import static com.gh.study.container.Container.*;

public class App {
    private static int userNextNo;
    private static int gameNextNo;

    public static int getUserNextNo() {
        return userNextNo;
    }

    public static void setUserNextNo(int userNextNo) {
        App.userNextNo = userNextNo;
    }

    public static int getGameNextNo() {
        return gameNextNo;
    }

    public static void setGameNextNo(int gameNextNo) {
        App.gameNextNo = gameNextNo;
    }

    public App() {
        loadTestData();
        UserSession.setLoginUser(userModelMap.get(4)); //자동으로 key가 4인 회원에 로그인
    }

    private void loadTestData() {
        TestDataFactory testDataFactory = new TestDataFactory();
        //게임데이터 생성
        testDataFactory.makeTestGame();
        gameNextNo = testDataFactory.getGameLastNo() + 1;
        //유저데이터 생성
        testDataFactory.makeTestUser();
        userNextNo = testDataFactory.getUserLastNo() + 1;
    }

    public void run() {
        while(true) {
            System.out.print("command) ");
            String cmd = scanner.nextLine();
            rq.setCommand(cmd);
            action();
        }
    }

    private void action() {
        if(rq.getUrlPath().equals("/app/start")) {
            gameView.gameStart();
        } else if(rq.getUrlPath().equals("/app/ranking")) {
            gameView.gameRanking();
        } else if(rq.getUrlPath().equals("/usr/join")) {
            userView.userJoin();
        } else if(rq.getUrlPath().equals("/usr/login")) {
            userView.userLogin();
        } else if(rq.getUrlPath().equals("/usr/whoami")) {
            userView.userWhoami();
        } else if(rq.getUrlPath().equals("/usr/modify")) {
            userView.userModify();
        } else if(rq.getUrlPath().equals("/usr/logout")) {
            userView.userLogout();
        } else if(rq.getUrlPath().equals("/usr/list")) {
            userView.userList();
        } else if(rq.getUrlPath().equals("/app/exit")) {
            gameView.gameExit();
        } else {
            System.out.println("===== unknown command! =====");
        }
    }
}