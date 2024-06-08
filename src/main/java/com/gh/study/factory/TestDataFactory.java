package com.gh.study.factory;

import com.gh.study.model.GameModel;
import com.gh.study.model.UserModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.gh.study.container.Container.*;

public class TestDataFactory {
    private int gameLastNo;
    private int userLastNo;

    public int getGameLastNo() {
        return gameLastNo;
    }

    public int getUserLastNo() {
        return userLastNo;
    }

    public void makeTestGame() {
        for (int i = 1; i <= 4; i++) {
            GameModel gameModel1 = new GameModel(++gameLastNo, "testNickname" + i, i * 1000, LocalDateTime.now());
            gameModelMap.put(gameLastNo, gameModel1);
            List<GameModel> playNicknameGameModelList1 = gameNicknameMap.getOrDefault(gameModel1.getPlayNickname(), new ArrayList<>());
            playNicknameGameModelList1.add(gameModel1);
            gameNicknameMap.put(gameModel1.getPlayNickname(), playNicknameGameModelList1);

            GameModel gameModel2 = new GameModel(++gameLastNo, "testNickname" + i, i * 2000, LocalDateTime.now());
            gameModelMap.put(gameLastNo, gameModel2);
            List<GameModel> playNicknameGameModelList2 = gameNicknameMap.getOrDefault(gameModel2.getPlayNickname(), new ArrayList<>());
            playNicknameGameModelList2.add(gameModel2);
            gameNicknameMap.put(gameModel2.getPlayNickname(), playNicknameGameModelList2);
        }
    }

    public void makeTestUser() {
        for (int i = 1; i <= 9; i++) {
            UserModel userModel = new UserModel(++userLastNo, "testId" + i, "testPw" + i, "testNickname" + i);
            userModelMap.put(userLastNo, userModel);
            userIdMap.put(userModel.getUserId(), userModel);
            userNicknameMap.put(userModel.getUserNickname(), userModel);
        }
    }
}
