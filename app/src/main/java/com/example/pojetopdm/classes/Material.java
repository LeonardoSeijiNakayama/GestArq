package com.example.pojetopdm.classes;

import java.io.Serializable;

public class Material implements Serializable {
    private String name;
    private double unitPrice;

    public Material(String name, double unitPrice) {
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
