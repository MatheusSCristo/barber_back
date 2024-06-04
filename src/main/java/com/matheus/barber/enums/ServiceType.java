package com.matheus.barber.enums;

public enum ServiceType {
    hair("Cabelo"),
    beard("Barba"),
    hair_beard("Cabelo e Barba"),
    eyebrows("Sobrancelhas");


    private final String service;
    ServiceType(String service) {
        this.service=service;
    }

    public String getService(){
        return this.service;
    }
}
