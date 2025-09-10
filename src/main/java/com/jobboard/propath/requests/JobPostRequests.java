package com.jobboard.propath.requests;

import com.jobboard.propath.entity.JobPost;

public class JobPostRequests {
    private Long companyId;
    private Long employerProfileId;
    private String title;
    private String description;
    private String requirements;
    private String location;
    private JobPost.JobType jobType;
    private double salaryMinimum;
    private double salaryMaximum;
    private String category;
    private JobPost.JobStatus status;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getEmployerProfileId() {
        return employerProfileId;
    }

    public void setEmployerProfileId(Long employerProfileId) {
        this.employerProfileId = employerProfileId;
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

    public JobPost.JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobPost.JobType jobType) {
        this.jobType = jobType;
    }
}
