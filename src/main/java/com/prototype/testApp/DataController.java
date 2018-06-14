package com.prototype.testApp;

import com.prototype.testApp.domain.Card;
import com.prototype.testApp.domain.Line;
import com.prototype.testApp.dto.TransferCard;
import com.prototype.testApp.services.CardService;
import com.prototype.testApp.services.DashboardServices;
import com.prototype.testApp.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class DataController {
    @Autowired
    private DashboardServices dashboardServices;
    @Autowired
    private CardService cardService;

    @GetMapping("/lane")
    public Collection<Line> getLanes() {
        return dashboardServices.loadData();
    }

    @PostMapping("/lane")
    public ResponseEntity<Line> saveLane(@RequestBody Line line) {
        dashboardServices.saveLine(line);
        return ResponseEntity.ok(line);
    }

    @PostMapping("/card")
    public ResponseEntity<Card> saveCard(@RequestBody Card card) {
        cardService.saveCard(card);
        return ResponseEntity.ok(card);
    }

    @PostMapping("/transferCard")
    public ResponseEntity<TransferCard> transferCard(@RequestBody TransferCard card) {
        cardService.transferCard(card.getCard(),card.getFromLane(),card.getToLane());
        return ResponseEntity.ok(card);
    }

    @PostMapping("/deleteCard")
    public ResponseEntity removeCard(@RequestBody Long cardId) {
        cardService.removeCard(cardId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/updateCard")
    public ResponseEntity<Card>udateCard(@RequestBody Card card) {
        cardService.updateCard(card);
        return ResponseEntity.ok(card);
    }
}
