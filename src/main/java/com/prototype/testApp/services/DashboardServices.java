package com.prototype.testApp.services;

import com.prototype.testApp.domain.Line;
import com.prototype.testApp.storage.CardsRepository;
import com.prototype.testApp.storage.LineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class DashboardServices {
    private final LineRepository lineRepository;

    @Autowired
    public DashboardServices(LineRepository lineRepository) {
        this.lineRepository = lineRepository;
    }


    public Collection<Line> loadData() {
        return (List<Line>) lineRepository.findAll();
    }

    public void saveLine(Line line) {
        Line save = lineRepository.save(line);
    }


}
