package com.example.trainingnotification.model;

import java.io.Serializable;

public class NotificationModel implements Serializable {

    private int id;
    private String nome;
    private String horario;
    private String intervaloDose;
    private int quantidade;

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}
    public String getHorario() {return horario;}
    public void setHorario(String horario) {this.horario = horario;}
    public String getIntervaloDose() {return intervaloDose;}

    public void setIntervaloDose(String intervaloDose) {
        this.intervaloDose = intervaloDose;
    }

    public int getQuantidade() {return quantidade;}
    public void setQuantidade(int quantidade) {this.quantidade = quantidade;}

}