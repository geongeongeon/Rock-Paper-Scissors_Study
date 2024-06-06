package com.gh.study.view;

import static com.gh.study.container.Container.*;

public class GameView {
    public static int score;
    public void gameStart() {
        System.out.println("===== start! =====");

        while(true) {
            System.out.print("you) ");
            String userChoice = scanner.nextLine();

            if(userChoice.equals("rock") || userChoice.equals("scissor") || userChoice.equals("paper")) {
                String[] gameResult = startController.gameResult(userChoice);

                System.out.println("computer) " + gameResult[0]);
                System.out.printf("===== %s! =====\n", gameResult[1]);

                if(gameResult[1].equals("lose")) {
                    System.out.print("/home or /restart) ");
                    String userChoiceAfterLose = scanner.nextLine();

                    if(userChoiceAfterLose.equals("/home")) {
                        return;
                    } else if(userChoiceAfterLose.equals("/restart")) {
                        System.out.println(score);
                        score = 0;
                    } else {
                        System.out.println("===== unknown choice! =====");
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
    }

    public void gameExit() {
        System.out.print("===== exit! =====");
        exitController.gameExit();
    }
}
