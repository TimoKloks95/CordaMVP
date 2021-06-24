package com.template.webserver.dto;
import javax.persistence.Entity;
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

    public void setId(String id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getHarvestAt() {
        return harvestAt;
    }

    public void setHarvestAt(String harvestAt) {
        this.harvestAt = harvestAt;
    }

    public String getQualitySegment() {
        return qualitySegment;
    }

    public void setQualitySegment(String qualitySegment) {
        this.qualitySegment = qualitySegment;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public boolean isBulk() {
        return isBulk;
    }

    public void setBulk(boolean bulk) {
        isBulk = bulk;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getMinScreenSize() {
        return minScreenSize;
    }

    public void setMinScreenSize(int minScreenSize) {
        this.minScreenSize = minScreenSize;
    }

    public int getMaxScreenSize() {
        return maxScreenSize;
    }

    public void setMaxScreenSize(int maxScreenSize) {
        this.maxScreenSize = maxScreenSize;
    }

    public int getCuppingScore() {
        return cuppingScore;
    }

    public void setCuppingScore(int cuppingScore) {
        this.cuppingScore = cuppingScore;
    }

    public String[] getCertificates() {
        return certificates;
    }

    public void setCertificates(String[] certificates) {
        this.certificates = certificates;
    }
}
