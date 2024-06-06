package com.gh.study.game_controller;

import com.gh.study.App;
import com.gh.study.model.GameModel;
import com.gh.study.session.UserSession;
import com.gh.study.view.GameView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.gh.study.container.Container.*;
import static com.gh.study.container.Container.gameNicknameMap;

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
            GameView.score += 1000;
            return "win";
        } else if(userChoice.equals(computerChoice)) {
            GameView.score += 300;
            return "draw";
        } else {
            return "lose";
        }
    }

    public void saveScore(int score) {
        int gameNextNo = App.getGameNextNo();
        String playNickname = getPlayUserNickname();
        LocalDateTime playTime = LocalDateTime.now();

        GameModel gameModel = new GameModel(gameNextNo, playNickname, score, playTime);
        gameModelMap.put(gameNextNo, gameModel);

        List<GameModel> playNicknameGameModelList = gameNicknameMap.getOrDefault(playNickname, new ArrayList<>());
        gameNicknameMap.put(playNickname, playNicknameGameModelList);

        App.setGameNextNo(++gameNextNo);
    }

    private String getPlayUserNickname() {
        return UserSession.getLoginUser().getUserNickname();
    }
}
