package com.prototype.testApp.storage;

import com.prototype.testApp.domain.Card;
import com.prototype.testApp.domain.Line;
import com.prototype.testApp.domain.TransferCard;

import java.util.Collection;

public interface Storage {
    Collection<Line> loadData();

    Line saveLine(Line line);

    Card saveCard(Card card);

    void transferCard(TransferCard card);
}
