package com.example.pojetopdm.classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Project implements Serializable {
    private String name, status, startDate, term, briefing;
    private Client client;
    private ArrayList<Ambient> ambients = new ArrayList<>();

    public Project(String name, String status, String startDate, String briefing, Client client, String term) {
        this.name = name;
        this.status = status;
        this.startDate = startDate;
        this.briefing = briefing;
        this.client = client;
        this.term = term;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDateTerm) {
        this.startDate = startDateTerm;
    }

    public String getBriefing() {
        return briefing;
    }

    public void setBriefing(String briefing) {
        this.briefing = briefing;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ArrayList<Ambient> getAmbients() {
        return ambients;
    }

    public void setAmbients(ArrayList<Ambient> ambients) {
        this.ambients = ambients;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public void addAmbient(Ambient ambient){
        ambients.add(ambient);
    }

    @Override
    public String toString(){
        return name;
    }
}
