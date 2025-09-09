package com.jobboard.propath.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Table(name = "job_seeker_profiles")
@Entity
public class JobSeekerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long profileId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, name = "resume_url")
    private String resumeUrl;

    private String skills;
    private String experience;
    private String education;
    private String location;
    private String desiredSalary;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    public JobSeekerProfile(long profileId, String resumeUrl, String skills, String experience, String education, String location, String desiredSalary, Date createdAt, Date updatedAt, User user) {
        this.profileId = profileId;
        this.resumeUrl = resumeUrl;
        this.skills = skills;
        this.experience = experience;
        this.education = education;
        this.location = location;
        this.desiredSalary = desiredSalary;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
    }

    public JobSeekerProfile() {
    }

    public long getProfileId() {
        return profileId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setProfileId(long profileId) {
        this.profileId = profileId;
    }

    public String getResumeUrl() {
        return resumeUrl;
    }

    public void setResumeUrl(String resumeUrl) {
        this.resumeUrl = resumeUrl;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDesiredSalary() {
        return desiredSalary;
    }

    public void setDesiredSalary(String desiredSalary) {
        this.desiredSalary = desiredSalary;
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