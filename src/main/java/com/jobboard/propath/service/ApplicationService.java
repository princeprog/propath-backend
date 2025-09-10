package com.jobboard.propath.service;

import com.jobboard.propath.entity.Application;
import com.jobboard.propath.entity.User;
import com.jobboard.propath.repository.ApplicationRepository;
import com.jobboard.propath.repository.UserRepository;
import com.jobboard.propath.requests.ApplicationRequests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    public Application saveApplication(ApplicationRequests requests) {
        Application application = new Application();
        if (requests.getApplicantUserId() != null) {
            User user = userRepository.findById(requests.getApplicantUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
            application.setApplicantUser(user);
        }
        application.setResumeUrl(requests.getResumeUrl());
        application.setCoverLetter(requests.getCoverLetter());
        application.setStatus(requests.getStatus());
        application.setEmployerNotes(requests.getEmployerNotes());
        return applicationRepository.save(application);
    }

    public void deleteApplication(Long id) {
        applicationRepository.deleteById(id);
    }

    public Application getApplicationById(Long id) {
        return applicationRepository.findById(id).orElse(null);
    }

    public Application updateApplication(Long id, ApplicationRequests requests) {
        return applicationRepository.findById(id).map(application -> {
            if (requests.getApplicantUserId() != null) {
                User user = userRepository.findById(requests.getApplicantUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
                application.setApplicantUser(user);
            }
            application.setResumeUrl(requests.getResumeUrl());
            application.setCoverLetter(requests.getCoverLetter());
            application.setStatus(requests.getStatus());
            application.setEmployerNotes(requests.getEmployerNotes());
            return applicationRepository.save(application);
        }).orElse(null);
    }
}
