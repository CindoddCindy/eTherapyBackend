package com.etherapy.etherapyproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "penilaiankristen")
public class PenilaianKristen extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private int rateK;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "kristen_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Kristen kristen;

    public PenilaianKristen() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRateK() {
        return rateK;
    }

    public void setRateK(int rateK) {
        this.rateK = rateK;
    }

    public Kristen getKristen() {
        return kristen;
    }

    public void setKristen(Kristen kristen) {
        this.kristen = kristen;
    }
}
