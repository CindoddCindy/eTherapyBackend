package com.etherapy.etherapyproject.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "game")
public class Game extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String content;

    @NotNull
    private String answA;

    @NotNull
    private String answB;

    @NotNull
    private String answC;

    @NotNull
    private String answD;


    public Game() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnswA() {
        return answA;
    }

    public void setAnswA(String answA) {
        this.answA = answA;
    }

    public String getAnswB() {
        return answB;
    }

    public void setAnswB(String answB) {
        this.answB = answB;
    }

    public String getAnswC() {
        return answC;
    }

    public void setAnswC(String answC) {
        this.answC = answC;
    }

    public String getAnswD() {
        return answD;
    }

    public void setAnswD(String answD) {
        this.answD = answD;
    }
}
