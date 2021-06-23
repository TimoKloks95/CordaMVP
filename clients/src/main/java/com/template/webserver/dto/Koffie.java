package com.template.webserver.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Koffie {
    private String id;
    private String country;
    private String region;
    private String unit;
    private String species;
    private String process;
    private String sector;
    private String harvestAt;
    private String qualitySegment;
    private String parentId;
    private boolean isBulk;
    private int quantity;
    private int minScreenSize;
    private int maxScreenSize;
    private int cuppingScore;
    private String[] certificates;

    public Koffie() {

    }

    @JsonCreator
    public static Koffie createKoffie(String id, String region, String unit, String species, String process,
                                      String sector, String harvestAt, String qualitySegment, String parentId,
                                      boolean isBulk, int quantity, int minScreenSize, int maxScreenSize, int cuppingScore,
                                      String[] certificates) {
        return new Koffie().setId(id).setRegion(region).setUnit(unit).setSpecies(species).setProcess(process).setSector(sector)
                .setHarvestAt(harvestAt).setQualitySegment(qualitySegment).setParentId(parentId).setBulk(isBulk)
                .setQuantity(quantity).setMinScreenSize(minScreenSize).setMaxScreenSize(maxScreenSize).setCuppingScore(cuppingScore)
                .setCertificates(certificates);
    }

    public String getId() {
        return id;
    }

    private Koffie setId(String id) {
        this.id = id;
        return this;
    }

    public String getCountry() {
        return country;
    }

    private Koffie setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getRegion() {
        return region;
    }

    private Koffie setRegion(String region) {
        this.region = region;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    private Koffie setUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public String getSpecies() {
        return species;
    }

    private Koffie setSpecies(String species) {
        this.species = species;
        return this;
    }

    public String getProcess() {
        return process;
    }

    private Koffie setProcess(String process) {
        this.process = process;
        return this;
    }

    public String getSector() {
        return sector;
    }

    private Koffie setSector(String sector) {
        this.sector = sector;
        return this;
    }

    public String getHarvestAt() {
        return harvestAt;
    }

    private Koffie setHarvestAt(String harvestAt) {
        this.harvestAt = harvestAt;
        return this;
    }

    public String getQualitySegment() {
        return qualitySegment;
    }

    private Koffie setQualitySegment(String qualitySegment) {
        this.qualitySegment = qualitySegment;
        return this;
    }

    public String getParentId() {
        return parentId;
    }

    private Koffie setParentId(String parentId) {
        this.parentId = parentId;
        return this;
    }

    public boolean isBulk() {
        return isBulk;
    }

    private Koffie setBulk(boolean bulk) {
        isBulk = bulk;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    private Koffie setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public int getMinScreenSize() {
        return minScreenSize;
    }

    private Koffie setMinScreenSize(int minScreenSize) {
        this.minScreenSize = minScreenSize;
        return this;
    }

    public int getMaxScreenSize() {
        return maxScreenSize;
    }

    private Koffie setMaxScreenSize(int maxScreenSize) {
        this.maxScreenSize = maxScreenSize;
        return this;
    }

    public int getCuppingScore() {
        return cuppingScore;
    }

    private Koffie setCuppingScore(int cuppingScore) {
        this.cuppingScore = cuppingScore;
        return this;
    }

    public String[] getCertificates() {
        return certificates;
    }

    private Koffie setCertificates(String[] certificates) {
        this.certificates = certificates;
        return this;
    }
}
