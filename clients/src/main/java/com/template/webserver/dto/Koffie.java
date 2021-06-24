package com.template.webserver.dto;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
public class Koffie {
    @NotBlank(message = "id is mandatory")
    private String id;

    @NotBlank(message = "country is mandatory")
    private String country;

    @NotBlank(message = "region is mandatory")
    private String region;

    @NotBlank(message = "unit is mandatory")
    private String unit;

    @NotBlank(message = "species is mandatory")
    private String species;

    @NotBlank(message = "process is mandatory")
    private String process;

    @NotBlank(message = "sector is mandatory")
    private String sector;

    @NotBlank(message = "harvestAt is mandatory")
    private String harvestAt;

    @NotBlank(message = "qualitySegment is mandatory")
    private String qualitySegment;

    private String parentId;

    private boolean isBulk;

    @Min(0)
    private int quantity;

    @Min(0)
    private int minScreenSize;

    @Min(0)
    private int maxScreenSize;

    @Min(0)
    private int cuppingScore;

    private String[] certificates;

    public Koffie(String id, String country, String region, String unit, String species, String process, String sector,
                  String harvestAt, String qualitySegment, String parentId, boolean isBulk, int quantity, int minScreenSize,
                  int maxScreenSize, int cuppingScore, String[] certificates) {
        this.id = id;
        this.country = country;
        this.region = region;
        this.unit = unit;
        this.species = species;
        this.process = process;
        this.sector = sector;
        this.harvestAt = harvestAt;
        this.qualitySegment = qualitySegment;
        this.parentId = parentId;
        this.isBulk = isBulk;
        this.quantity = quantity;
        this.minScreenSize = minScreenSize;
        this.maxScreenSize = maxScreenSize;
        this.cuppingScore = cuppingScore;
        this.certificates = certificates;
    }

//    @JsonCreator
//    public static Koffie createKoffie(String id, String country, String region, String unit, String species, String process,
//                                      String sector, String harvestAt, String qualitySegment, String parentId,
//                                      boolean isBulk, int quantity, int minScreenSize, int maxScreenSize, int cuppingScore,
//                                      String[] certificates) {
//        return new Koffie().setId(id).setCountry(country).setRegion(region).setUnit(unit).setSpecies(species).setProcess(process).setSector(sector)
//                .setHarvestAt(harvestAt).setQualitySegment(qualitySegment).setParentId(parentId).setBulk(isBulk)
//                .setQuantity(quantity).setMinScreenSize(minScreenSize).setMaxScreenSize(maxScreenSize).setCuppingScore(cuppingScore)
//                .setCertificates(certificates);
//    }

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
