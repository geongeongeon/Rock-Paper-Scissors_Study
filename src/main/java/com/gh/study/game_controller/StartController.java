package com.gh.study.game_controller;

import com.gh.study.view.GameView;

import static com.gh.study.container.Container.gameView;
import static com.gh.study.container.Container.random;

public class StartController {
    public String[] gameResult(String userChoice) {
        String computerChoice = getComputerChoice();
        String gameResult = getGameResult(userChoice, computerChoice);

        return new String[] {computerChoice, gameResult};
    }

    private String getComputerChoice() {
        int randomNumber = random.nextInt(3) + 1;
        switch(randomNumber) {
            case 1 :
                return "rock";
            case 2 :
                return "scissor";
            case 3 :
                return "paper";
            default :
                throw new IllegalStateException("error :: randomNumber = " + randomNumber);
        }
    }

    private String getGameResult(String userChoice, String computerChoice) {
        if((userChoice.equals("rock") && computerChoice.equals("scissor")) ||
                (userChoice.equals("scissor") && computerChoice.equals("paper")) ||
                (userChoice.equals("paper") && computerChoice.equals("rock"))) {
            GameView.score += 100;
            return "win";
        } else if(userChoice.equals(computerChoice)) {
            GameView.score += 30;
            return "draw";
        } else {
            return "lose";
        }
    }
}
