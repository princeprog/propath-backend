package com.jobboard.propath.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
public class JobPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobPostId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "companyId", nullable = false)
    private Company company;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employerProfileId", nullable = false)
    private EmployerProfile employerProfile;

    private String title;
    private String description;
    private String requirements;
    private String location;
    @Enumerated(EnumType.STRING)
    public JobType jobType;
    public enum JobType { FULL_TIME, PART_TIME, CONTRACT, INTERNSHIP};

    private double salaryMinimum;
    private double salaryMaximum;
    private String category;

    @Enumerated(EnumType.STRING)
    public JobStatus status;

    public enum JobStatus { OPEN, CLOSED, PAUSED };

    @CreationTimestamp
    @Column(updatable = false, name = "createdAt")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updatedAt")
    private Date updatedAt;

    public JobPost() {}

    public JobPost(Long jobPostId, Company company, EmployerProfile employerProfile, String title, String description, String requirements, String location, JobType jobType, double salaryMinimum, double salaryMaximum, String category, JobStatus status, Date createdAt, Date updatedAt) {
        this.jobPostId = jobPostId;
        this.company = company;
        this.title = title;
        this.description = description;
        this.requirements = requirements;
        this.location = location;
        this.jobType = jobType;
        this.salaryMinimum = salaryMinimum;
        this.salaryMaximum = salaryMaximum;
        this.category = category;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.employerProfile = employerProfile;
    }

    public Long getJobPostId() {
        return jobPostId;
    }

    public void setJobPostId(Long jobPostId) {
        this.jobPostId = jobPostId;
    }

    public EmployerProfile getEmployerProfile() {
        return employerProfile;
    }

    public void setEmployerProfile(EmployerProfile employerProfile) {
        this.employerProfile = employerProfile;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public double getSalaryMinimum() {
        return salaryMinimum;
    }

    public void setSalaryMinimum(double salaryMinimum) {
        this.salaryMinimum = salaryMinimum;
    }

    public double getSalaryMaximum() {
        return salaryMaximum;
    }

    public void setSalaryMaximum(double salaryMaximum) {
        this.salaryMaximum = salaryMaximum;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
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
