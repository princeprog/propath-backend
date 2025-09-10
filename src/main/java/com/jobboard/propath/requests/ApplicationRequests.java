package com.jobboard.propath.requests;
import com.jobboard.propath.entity.ApplicationStatus;

public class ApplicationRequests {
    private Long applicantUserId;
    private String resumeUrl;
    private String coverLetter;
    private ApplicationStatus status;
    private String employerNotes;

    public Long getApplicantUserId() {
        return applicantUserId;
    }

    public void setApplicantUserId(Long applicantUserId) {
        this.applicantUserId = applicantUserId;
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

    public String getEmployerNotes() {
        return employerNotes;
    }

    public void setEmployerNotes(String employerNotes) {
        this.employerNotes = employerNotes;
    }
}
