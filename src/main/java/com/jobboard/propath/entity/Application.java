package com.jobboard.propath.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "applicantUserId", nullable = false)
    private User applicantUser;

    @Column(nullable = false)
    private String resumeUrl;

    @Column(length = 2000)
    private String coverLetter;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status;

    @CreationTimestamp
    @Column(updatable = false, name = "appliedAt")
    private Date appliedAt;

    @UpdateTimestamp
    @Column(name = "updatedAt")
    private Date updatedAt;

    @Column(length = 2000)
    private String employerNotes;

    public Application() {
    }

    public Application(long applicationId, User applicantUser, String resumeUrl, String coverLetter, ApplicationStatus status, Date appliedAt, Date updatedAt, String employerNotes) {
        this.applicationId = applicationId;
        this.applicantUser = applicantUser;
        this.resumeUrl = resumeUrl;
        this.coverLetter = coverLetter;
        this.status = status;
        this.appliedAt = appliedAt;
        this.updatedAt = updatedAt;
        this.employerNotes = employerNotes;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public User getApplicantUser() {
        return applicantUser;
    }

    public void setApplicantUser(User applicantUser) {
        this.applicantUser = applicantUser;
    }

    public String getResumeUrl() {
        return resumeUrl;
    }

    public void setResumeUrl(String resumeUrl) {
        this.resumeUrl = resumeUrl;
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public Date getAppliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(Date appliedAt) {
        this.appliedAt = appliedAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getEmployerNotes() {
        return employerNotes;
    }

    public void setEmployerNotes(String employerNotes) {
        this.employerNotes = employerNotes;
    }
}


