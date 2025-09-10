package com.jobboard.propath.service;

import com.jobboard.propath.entity.SavedJob;
import com.jobboard.propath.entity.User;
import com.jobboard.propath.entity.JobPost;
import com.jobboard.propath.repository.SavedJobRepository;
import com.jobboard.propath.repository.UserRepository;
import com.jobboard.propath.repository.JobPostRepository;
import com.jobboard.propath.requests.SavedJobRequests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavedJobService {

    @Autowired
    SavedJobRepository savedJobRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    JobPostRepository jobPostRepo;

    public List<SavedJob> getAllSavedJobs() {
        return savedJobRepo.findAll();
    }

    public SavedJob saveSavedJob(SavedJobRequests requests) {
        SavedJob savedJob = new SavedJob();
        if (requests.getUserId() != null) {
            User user = userRepo.findById(requests.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
            savedJob.setUser(user);
        }
        if (requests.getJobId() != null) {
            JobPost job = jobPostRepo.findById(requests.getJobId()).orElseThrow(() -> new RuntimeException("Job not found"));
            savedJob.setJob(job);
        }
        return savedJobRepo.save(savedJob);
    }

    public void deleteSavedJob(Long id) {
        savedJobRepo.deleteById(id);
    }

    public SavedJob getSavedJobById(Long id) {
        return savedJobRepo.findById(id).orElse(null);
    }

    public SavedJob updateSavedJob(Long id, SavedJobRequests requests) {
        return savedJobRepo.findById(id).map(savedJob -> {
            if (requests.getUserId() != null) {
                User user = userRepo.findById(requests.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
                savedJob.setUser(user);
            }
            if (requests.getJobId() != null) {
                JobPost job = jobPostRepo.findById(requests.getJobId()).orElseThrow(() -> new RuntimeException("Job not found"));
                savedJob.setJob(job);
            }
            return savedJobRepo.save(savedJob);
        }).orElse(null);
    }
}
