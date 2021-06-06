package com.etherapy.etherapyproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "rate_muslim")
public class RateMuslim extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private int rateM;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "muslim_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Muslim muslim;

    public RateMuslim() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRateM() {
        return rateM;
    }

    public void setRateM(int rateM) {
        this.rateM = rateM;
    }

    public Muslim getMuslim() {
        return muslim;
    }

    public void setMuslim(Muslim muslim) {
        this.muslim = muslim;
    }
}
