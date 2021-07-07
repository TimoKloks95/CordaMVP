package nl.beyco.webserver.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.corda.core.serialization.ConstructorForDeserialization;
import nl.beyco.helpers.LocalDateTimeDeserializer;
import nl.beyco.helpers.LocalDateTimeSerializer;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Coffee {
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

    @NotNull(message = "harvestAt is mandatory")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime harvestAt;

    @NotBlank(message = "qualitySegment is mandatory")
    private String qualitySegment;

    @NotBlank(message = "Minimal screen size is mandatory")
    private String minScreenSize;

    @NotBlank(message = "Maximal screen size is mandatory")
    private String maxScreenSize;

    private String parentId;

    @JsonProperty("bulk")
    private boolean isBulk;

    @Min(0)
    private int quantity;

    @Min(0)
    private double cuppingScore;

    private String[] certificates;

    public Coffee() {

    }

    @ConstructorForDeserialization
    public Coffee(String id, String country, String region, String unit, String species, String process, String sector,
                  LocalDateTime harvestAt, String qualitySegment, String parentId, boolean isBulk, int quantity, String minScreenSize,
                  String maxScreenSize, double cuppingScore, String[] certificates) {
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

    public LocalDateTime getHarvestAt() {
        return harvestAt;
    }

    public void setHarvestAt(LocalDateTime harvestAt) {
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

    public String getMinScreenSize() {
        return minScreenSize;
    }

    public void setMinScreenSize(String minScreenSize) {
        this.minScreenSize = minScreenSize;
    }

    public String getMaxScreenSize() {
        return maxScreenSize;
    }

    public void setMaxScreenSize(String maxScreenSize) {
        this.maxScreenSize = maxScreenSize;
    }

    public double getCuppingScore() {
        return cuppingScore;
    }

    public void setCuppingScore(double cuppingScore) {
        this.cuppingScore = cuppingScore;
    }

    public String[] getCertificates() {
        return certificates;
    }

    public void setCertificates(String[] certificates) {
        this.certificates = certificates;
    }
}
