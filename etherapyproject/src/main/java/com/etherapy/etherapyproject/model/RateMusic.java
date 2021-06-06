package com.etherapy.etherapyproject.model;

import com.etherapy.etherapyproject.music.model.Music;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "rate_music")
public class RateMusic extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String musicName;

    @NotNull
    private int rateMusic;

    public RateMusic() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public int getRateMusic() {
        return rateMusic;
    }

    public void setRateMusic(int rateMusic) {
        this.rateMusic = rateMusic;
    }
}
