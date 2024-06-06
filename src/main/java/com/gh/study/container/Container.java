package com.gh.study.container;

import com.gh.study.Rq;
import com.gh.study.game_controller.*;
import com.gh.study.user_controller.*;
import com.gh.study.model.UserModel;
import com.gh.study.view.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Container {
    public static Scanner scanner;
    public static Random random;
    public static Rq rq;
    //뷰
    public static GameView gameView;
    public static UserView userView;
    //게임 컨트롤러
    public static StartController startController;
    public static RankingController rankingController;
    public static ExitController exitController;
    //유저 컨트롤러
    public static JoinController joinController;
    public static ListController listController;
    public static LoginController loginController;
    public static LogoutController logoutController;
    public static ModifyController modifyController;
    //맵
    public static Map<Integer, UserModel> userModelMap;
    public static Map<String, UserModel> userIdMap;
    public static Map<String, UserModel> userNicknameMap;

    static {
        scanner = new Scanner(System.in);
        random = new Random();
        rq = new Rq();
        //뷰
        gameView = new GameView();
        userView = new UserView();
        //게임 컨트롤러
        startController = new StartController();
        rankingController = new RankingController();
        exitController = new ExitController();
        //유저 컨트롤러
        joinController = new JoinController();
        listController = new ListController();
        loginController = new LoginController();
        logoutController = new LogoutController();
        modifyController = new ModifyController();
        //맵
        userModelMap = new HashMap<>();
        userIdMap = new HashMap<>();
        userNicknameMap = new HashMap<>();
    }
}
