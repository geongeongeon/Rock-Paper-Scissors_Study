package com.gh.study.model;

import javax.swing.text.DateFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GameModel {
    int playNo;
    String playNickname;
    int playScore;
    LocalDateTime playTime;

    public GameModel(int playNo, String playNickname, int playScore, LocalDateTime playTime) {
        this.playNo = playNo;
        this.playNickname = playNickname;
        this.playScore = playScore;
        this.playTime = playTime;
    }

    public int getPlayNo() {
        return playNo;
    }

    public void setPlayNo(int playNo) {
        this.playNo = playNo;
    }

    public String getPlayNickname() {
        return playNickname;
    }

    public void setPlayNickname(String playNickname) {
        this.playNickname = playNickname;
    }

    public int getPlayScore() {
        return playScore;
    }

    public void setPlayScore(int playScore) {
        this.playScore = playScore;
    }

    public LocalDateTime getPlayTime() {
        return playTime;
    }

    public void setPlayTime(LocalDateTime playTime) {
        this.playTime = playTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String formattedPlayTime = playTime.format(formatter);

        return String.format("%d | %s | %d | %s", playNo, playNickname, playScore, formattedPlayTime);
    }
}
