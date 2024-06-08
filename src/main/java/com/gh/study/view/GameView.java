package com.gh.study.view;

import com.gh.study.model.GameModel;
import com.gh.study.model.UserModel;
import com.gh.study.session.UserSession;

import java.util.List;

import static com.gh.study.container.Container.*;

public class GameView {
    public static int score;

    public void gameStart() {
        if(!UserSession.getIsLogin()) {
            System.out.println("===== login first! =====");
            return;
        }

        System.out.println("===== start! =====");

        while(true) {
            System.out.print("you) ");
            String userChoice = scanner.nextLine();

            if(userChoice.equals("rock") || userChoice.equals("scissor") || userChoice.equals("paper")) {
                String[] gameResult = startController.gameResult(userChoice);

                System.out.println("computer) " + gameResult[0]);
                System.out.printf("===== %s! =====\n", gameResult[1]);

                if(gameResult[1].equals("lose")) {
                    while(true) {
                        System.out.print("/save or /restart) ");
                        String userChoiceAfterLose = scanner.nextLine();

                        if(userChoiceAfterLose.equals("/save")) {
                            System.out.println("===== save! =====");
                            startController.saveScore(score);
                            score = 0;
                            return;
                        } else if(userChoiceAfterLose.equals("/restart")) {
                            System.out.println("===== restart! =====");
                            score = 0;
                            break;
                        } else {
                            System.out.println("===== unknown choice! =====");
                        }
                    }
                }
            } else if(userChoice.equals("/stop")) {
                System.out.println("===== stop! =====");
                return;
            } else {
                System.out.println("===== unknown choice! =====");
            }
        }
    }

    public void gameRanking() {
        System.out.println("===== ranking! =====");
        List<GameModel> gameList = rankingController.getGameInfo();
        for(GameModel game : gameList) {
            System.out.println(game.toString());
        }
        System.out.println("===== testNickname4! =====");
        for(GameModel game : gameNicknameMap.get("testNickname4")) {
            System.out.println(game);
        }

    }

    public void gameExit() {
        System.out.print("===== exit! =====");
        exitController.gameExit();
    }
}
