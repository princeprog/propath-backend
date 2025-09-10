package com.jobboard.propath.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Table(name = "companies")
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;

    @Column(nullable = false, unique = true)
    private String companyName;

    @Column(nullable = false, length = 2000)
    private String description;
    
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] logoUrl;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String website;

    @Column(nullable = false)
    private String industry;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

        public Company() {
    }

    public Company(long companyId, String companyName, String description, byte[] logoUrl, String location, String website, String industry, Date createdAt, Date updatedAt) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.description = description;
        this.logoUrl = logoUrl;
        this.location = location;
        this.website = website;
        this.industry = industry;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(byte[] logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}


