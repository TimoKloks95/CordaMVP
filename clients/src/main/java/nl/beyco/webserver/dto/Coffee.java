package nl.beyco.webserver.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.corda.core.serialization.ConstructorForDeserialization;
import nl.beyco.helpers.LocalDateTimeDeserializer;
import nl.beyco.helpers.LocalDateTimeSerializer;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
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

    @Min(8)
    @Max(20)
    private int minScreenSize;

    @Min(8)
    @Max(20)
    private int maxScreenSize;

    private String parentId;

    private boolean bulk;

    @Min(0)
    private int quantity;

    @Min(0)
    private double cuppingScore;

    private String[] certificates;

    public Coffee() {

    }

    @ConstructorForDeserialization
    public Coffee(String id, String country, String region, String unit, String species, String process, String sector,
                  LocalDateTime harvestAt, String qualitySegment, String parentId, boolean bulk, int quantity, int minScreenSize,
                  int maxScreenSize, double cuppingScore, String[] certificates) {
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
        this.bulk = bulk;
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
        return bulk;
    }

    public void setBulk(boolean bulk) {
        bulk = bulk;
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
