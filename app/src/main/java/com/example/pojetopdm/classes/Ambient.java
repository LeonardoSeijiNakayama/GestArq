package com.example.pojetopdm.classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Ambient implements Serializable {
    private String name, area, existentMaterials, proposedMaterials;
    private ArrayList<String> openings = new ArrayList<>();

    public Ambient(String name, String area, String existentMaterials, String proposedMaterials) {
        this.name = name;
        this.area = area;
        this.existentMaterials = existentMaterials;
        this.proposedMaterials = proposedMaterials;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public ArrayList<String> getOpenings() {
        return openings;
    }

    public void setOpenings(ArrayList<String> openings) {
        this.openings = openings;
    }

    public String getExistentMaterials() {
        return existentMaterials;
    }

    public void setExistentMaterials(String existentMaterials) {
        this.existentMaterials = existentMaterials;
    }

    public String getProposedMaterials() {
        return proposedMaterials;
    }

    public void setProposedMaterials(String proposedMaterials) {
        this.proposedMaterials = proposedMaterials;
    }
}
