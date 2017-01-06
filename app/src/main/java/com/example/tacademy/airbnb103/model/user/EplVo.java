package com.example.tacademy.airbnb103.model.user;

/**
 * tbl_eplList 테이블 정보에 대응
 */

public class EplVo {
    int ranking; //INT(10) NULL DEFAULT NULL COMMENT '순위',
    String name; //VARCHAR(50) NOT NULL COMMENT '팀명',
    int count; //INT(10) NULL DEFAULT NULL COMMENT '경기수',
    int winpoint; //INT(10) NULL DEFAULT NULL COMMENT '승점',
    int win; //INT(10) NULL DEFAULT NULL COMMENT '승',
    int draw; //INT(10) NULL DEFAULT NULL COMMENT '무',
    int lose; //INT(10) NULL DEFAULT NULL COMMENT '패',
    int score; //INT(10) NULL DEFAULT NULL COMMENT '득점',
    int mscore; //INT(10) NULL DEFAULT NULL COMMENT '실점',
    int gap; //INT(10) NULL DEFAULT NULL COMMENT '득실차',
    String symbol;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getGap() {
        return gap;
    }

    public void setGap(int gap) {
        this.gap = gap;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getWinpoint() {
        return winpoint;
    }

    public void setWinpoint(int winpoint) {
        this.winpoint = winpoint;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getLose() {
        return lose;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getMscore() {
        return mscore;
    }

    public void setMscore(int mscore) {
        this.mscore = mscore;
    }

}
