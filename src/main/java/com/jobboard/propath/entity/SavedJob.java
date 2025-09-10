package com.jobboard.propath.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

    @Entity
    @Table(name = "saved_jobs")
public class SavedJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long savedJobId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "job_id", nullable = false)
    private Long jobId;

    @CreationTimestamp
    @Column(name = "saved_at", nullable = false, updatable = false)
    private Date savedAt;

    public SavedJob() {
    }

    public SavedJob(Long savedJobId, Long userId, Long jobId, Date savedAt) {
        this.savedJobId = savedJobId;
        this.userId = userId;
        this.jobId = jobId;
        this.savedAt = savedAt;
    }

    public Long getSavedJobId() {
        return savedJobId;
    }

    public void setSavedJobId(Long savedJobId) {
        this.savedJobId = savedJobId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Date getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(Date savedAt) {
        this.savedAt = savedAt;
    }

}
