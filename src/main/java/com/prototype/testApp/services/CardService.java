package com.prototype.testApp.services;

import com.prototype.testApp.domain.Card;
import com.prototype.testApp.storage.CardsRepository;
import com.prototype.testApp.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    @Autowired
    private CardsRepository cardsRepository;

    public void transferCard(Long card, Long fromLane, Long toLane) {
        Card updateCard = cardsRepository.getOne(card);
        updateCard.setLine(toLane);
        cardsRepository.save(updateCard);
    }

    public void saveCard(Card card) {
        if (card.getId()==null) {
            Card save = cardsRepository.save(card);
            card.setId(save.getId());
        }else {
            Card updateCard = cardsRepository.getOne(card.getId());
            updateCard.setTitle(card.getTitle());
            cardsRepository.save(updateCard);
        }
    }

    public void removeCard(Card card) {
        cardsRepository.delete(card);
    }
}
