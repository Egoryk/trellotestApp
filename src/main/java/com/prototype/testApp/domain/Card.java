package com.prototype.testApp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Card implements Serializable{
    @JsonProperty("_id")
    private Long id;
    private String title;
    @JsonProperty("lane")
    private Long line;


    public Card() {
    }

    public Card(Long laneId, String title) {
        this.title=title;
        this.line =laneId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getLine() {
        return line;
    }

    public void setLine(Long line) {
        this.line = line;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
