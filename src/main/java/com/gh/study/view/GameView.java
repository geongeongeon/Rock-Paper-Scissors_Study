package com.gh.study.view;

import com.gh.study.model.GameModel;
import com.gh.study.session.UserSession;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

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
                System.out.println("===== " + gameResult[1] + " =====");

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
            } else if(userChoice.equals("/home")) {
                System.out.println("===== home! =====");
                return;
            } else {
                System.out.println("===== unknown choice! =====");
            }
        }
    }

    public void gameRanking() {
        try {
            Map<String, String> checkNicknameMap = rq.getParams();
            if(!checkNicknameMap.isEmpty()) {
                String nicknameParam = checkNicknameMap.get("nickname");
                System.out.println("===== show score : " + nicknameParam + " =====");
                List<GameModel> sortedList = new ArrayList<>();
                for(GameModel sort : gameNicknameMap.get(nicknameParam)) {
                    sortedList.add(sort);
                }
                sortedList.sort(Comparator.comparingInt(GameModel :: getPlayScore).reversed());
                for(GameModel game : sortedList) {
                    System.out.println(game);
                }
            } else {
                System.out.println("===== ranking! =====");
                List<GameModel> gameList = rankingController.getGameInfo();
                for(GameModel game : gameList) {
                    System.out.println(game.toString());
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("===== need a NICKNAME! =====");
        } catch (NullPointerException e) {
            System.out.println("===== not found data! =====");
        }
    }

    public void gameExit() {
        System.out.print("===== exit! =====");
        exitController.gameExit();
    }
}
