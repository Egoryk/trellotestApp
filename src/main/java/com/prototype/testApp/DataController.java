package com.prototype.testApp;

import com.prototype.testApp.domain.Card;
import com.prototype.testApp.domain.Line;
import com.prototype.testApp.domain.TransferCard;
import com.prototype.testApp.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class DataController {
    @Autowired
    private Storage storage;

    @GetMapping("/lane")
    public Collection<Line> getLanes() {
        return storage.loadData();
    }

    @PostMapping("/lane")
    public ResponseEntity<Line> saveLane(@RequestBody Line line) {
        storage.saveLine(line);
        return ResponseEntity.ok(line);
    }

    @PostMapping("/card")
    public ResponseEntity<Card> saveCard(@RequestBody Card card) {
      storage.saveCard(card);
        return ResponseEntity.ok(card);
    }

    @PostMapping("/transferCard")
    public ResponseEntity<TransferCard> transferCard(@RequestBody TransferCard card) {
        storage.transferCard(card);
        return ResponseEntity.ok(card);
    }
}
