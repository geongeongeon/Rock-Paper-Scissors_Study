package com.gh.study.game_controller;

import com.gh.study.model.GameModel;

import java.util.ArrayList;
import java.util.List;

import static com.gh.study.container.Container.gameModelMap;

public class RankingController {
    public List<GameModel> getGameInfo() {
        List<GameModel> gameList = new ArrayList<>();
        for(Integer gameNo : gameModelMap.keySet()) {
            gameList.add(gameModelMap.get(gameNo));
        }
        return gameList;
    }
}
