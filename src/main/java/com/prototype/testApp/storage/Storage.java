package com.prototype.testApp.storage;

import com.prototype.testApp.domain.Card;
import com.prototype.testApp.domain.Line;

import java.util.Collection;

public interface Storage {
    Collection<Line> loadData();

    Line saveLine(Line line);

    Card saveCard(Card card);

    void updateCard(Card card);

    void removeCard(Card card);

    void transferCard(Long cardId,Long fromLane,Long toLane);
}
