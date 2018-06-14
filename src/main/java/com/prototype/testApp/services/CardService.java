package com.prototype.testApp.services;

import com.prototype.testApp.domain.Card;
import com.prototype.testApp.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    @Autowired
    private Storage storage;

    public void transferCard(Long card, Long fromLane, Long toLane) {
        storage.transferCard(card,fromLane,toLane);
    }

    public void saveCard(Card card) {
        storage.saveCard(card);
    }

    public void updateCard(Card card) {
        storage.updateCard(card);
    }

    public void removeCard(Long cardId) {
        storage.removeCard(cardId);
    }
}
