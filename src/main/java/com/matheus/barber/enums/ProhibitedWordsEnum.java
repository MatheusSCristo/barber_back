package com.matheus.barber.enums;

public enum ProhibitedWordsEnum {
    BABACA("babaca"),
    BICHA("bicha"),
    BIXA("bixa"),

    BUCETA("buceta"),

    BOCETA("boceta"),
    CACETE("cacete"),
    BUNDAO("bundão"),
    SAFADO("safado"),
    CUZAO("cuzão"),
    FDP("fdp"),
    MERDA("merda"),
    OTARIO("otário"),
    PAU("pau"),
    CU("cu"),
    PUTA("puta"),
    PUTO("puto"),
    VACA("vaca"),
    VIADO("viado"),
    ARROMBADO("arrombado"),
    BOSTA("bosta"),
    CACHORRA("cachorra"),
    CACHORRO("cachorro"),
    CARALHO("caralho"),
    CORNUDO("cornudo"),
    CRETINO("cretino"),
    ESCROTO("escroto"),
    ESTUPIDO("estúpido"),
    IMBECIL("imbecil"),
    LAZARENTO("lazarento"),
    MERDINHA("merdinha"),
    MERDOSO("merdoso"),
    NOJENTO("nojento"),
    PILANTRA("pilantra"),
    PORRA("porra"),
    PROSTITUTA("prostituta"),
    TARADO("tarado"),
    TROUXA("trouxa"),
    VAGABUNDA("vagabunda"),
    VAGABUNDO("vagabundo"),
    CORNO("corno");

    private final String word;

    ProhibitedWordsEnum(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }
}

