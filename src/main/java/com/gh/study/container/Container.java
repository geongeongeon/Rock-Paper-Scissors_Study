package com.gh.study.container;

import com.gh.study.controller.GameController;
import com.gh.study.controller.UserController;
import com.gh.study.model.UserModel;
import com.gh.study.view.GameView;
import com.gh.study.view.UserView;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Container {
    public static Scanner scanner;
    public static Random random;
    public static GameView gameView;
    public static UserView userView;
    public static GameController gameController;
    public static UserController userController;
    public static Map<Integer, UserModel> userModelMap;

    static {
        scanner = new Scanner(System.in);
        random = new Random();
        gameView = new GameView();
        userView = new UserView();
        gameController = new GameController();
        userController = new UserController();
        userModelMap = new HashMap<>();
    }
}
