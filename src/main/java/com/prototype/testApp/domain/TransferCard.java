package com.prototype.testApp.domain;

public class TransferCard {
   private Long card;
   private Long fromLane;
   private Long toLane;

    public Long getCard() {
        return card;
    }

    public void setCard(Long card) {
        this.card = card;
    }

    public Long getFromLane() {
        return fromLane;
    }

    public void setFromLane(Long fromLane) {
        this.fromLane = fromLane;
    }

    public Long getToLane() {
        return toLane;
    }

    public void setToLane(Long toLane) {
        this.toLane = toLane;
    }
}
