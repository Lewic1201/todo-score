package com.lewic.todoscore.vo;

import lombok.Data;

@Data
public class ScoreInfoVo {
    private Integer score;
    private Integer totalScore;
    private String rate;

    public ScoreInfoVo() {
    }

    public ScoreInfoVo(Integer score, Integer totalScore) {
        this.score = score;
        this.totalScore = totalScore;
        if (totalScore != null && totalScore != 0) {
            this.rate = (score * 100 / totalScore) + "%";
        } else {
            this.rate = "100%";
        }
    }
}
