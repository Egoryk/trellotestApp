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
        if (card.getId()==null) {
            storage.saveCard(card);
        }else storage.updateCard(card);
    }


    public void removeCard(Card card) {
        storage.removeCard(card);
    }
}
