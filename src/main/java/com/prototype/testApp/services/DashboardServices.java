package com.prototype.testApp.services;

import com.prototype.testApp.domain.Card;
import com.prototype.testApp.domain.Line;
import com.prototype.testApp.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class DashboardServices {
    private final Storage storage;

    @Autowired
    public DashboardServices(Storage storage) {
        this.storage = storage;
    }

    public Collection<Line> loadData() {
        return storage.loadData();
    }

    public void saveLine(Line line) {
        storage.saveLine(line);
    }


}
