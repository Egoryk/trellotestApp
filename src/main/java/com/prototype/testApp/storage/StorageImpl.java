package com.prototype.testApp.storage;


import com.prototype.testApp.domain.Card;
import com.prototype.testApp.domain.Line;
import com.prototype.testApp.domain.TransferCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class StorageImpl implements Storage {

    @Autowired
    private JdbcTemplate template;

    private Map<Long,Line> cacheMap= Collections.synchronizedMap(new HashMap<>());

    @Override
    public Collection<Line> loadData() {
        if (cacheMap.isEmpty()){
            template.query("select * from lines",rs->{
                while(rs.next()){
                    Line line = new Line();
                    line.setId(rs.getLong("id"));
                    line.setTitle(rs.getString("title"));
                    line.setCards(loadCards(line.getId()));
                    cacheMap.put(line.getId(),line);
                }
            });
        }
        return cacheMap.values();
    }

    @Override
    public Line saveLine(Line line) {
        KeyHolder holder = new GeneratedKeyHolder();
        template.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement("insert INTO lines (title) VALUES (?)", new String[]{"id"});
                    ps.setString(1, line.getTitle());
                    return ps;
                }, holder);
        line.setId(holder.getKey().longValue());
        line.setCards(new ArrayList<>());
        cacheMap.put(line.getId(),line);
        return line;
    }

    @Override
    public Card saveCard(Card card) {
        KeyHolder holder = new GeneratedKeyHolder();
        template.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement("insert INTO cards (title,line) VALUES (?,?)", new String[]{"id"});
                    ps.setString(1, card.getTitle());
                    ps.setLong(2, card.getLine());
                    return ps;
                }, holder);
        card.setId(holder.getKey().longValue());
        cacheMap.get(card.getLine()).getCards().add(card);
        return card;
    }

    @Override
    public void transferCard(TransferCard card) {
        template.update("update cards set line=? where id =?",card.getToLane(),card.getCard());
        List<Card> fromCards = cacheMap.get(card.getFromLane()).getCards();
        List<Card> toCards = cacheMap.get(card.getToLane()).getCards();
        Optional<Card> cardOptional = fromCards.stream()
                .filter(c -> c.getId().equals(card.getCard()))
                .findAny();
        if (cardOptional.isPresent()) {
            Card findCard = cardOptional.get();

            fromCards.remove(findCard);
            findCard.setLine(card.getToLane());
            toCards.add(findCard);
        }
    }

    private List<Card> loadCards(Long id) {
        return template.query("select * from cards WHERE line =?",rs->{
            ArrayList<Card> cards = new ArrayList<>();
            while(rs.next()){
                Card card = new Card();
                card.setId(rs.getLong("id"));
                card.setTitle(rs.getString("title"));
                card.setLine(id);
                cards.add(card);
            }
            return cards;
        },id);
    }
}
