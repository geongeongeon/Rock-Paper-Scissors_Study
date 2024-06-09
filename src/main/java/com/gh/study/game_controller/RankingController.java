package com.gh.study.game_controller;

import com.gh.study.model.GameModel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.gh.study.container.Container.gameModelMap;

public class RankingController {
    List<GameModel> gameList = new ArrayList<>();
    public List<GameModel> getGameInfo() {
        for(Integer gameNo : gameModelMap.keySet()) {
            gameList.add(gameModelMap.get(gameNo));
        }
        sortRanking();
        return gameList;
    }

    private void sortRanking() {
        gameList.sort(Comparator.comparingInt(GameModel :: getPlayScore).reversed());
    }
}
