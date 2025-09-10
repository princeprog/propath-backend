package com.jobboard.propath.service;

import com.jobboard.propath.entity.SavedJob;
import com.jobboard.propath.repository.SavedJobRepository;
import com.jobboard.propath.requests.SavedJobRequests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavedJobService {

    @Autowired
    SavedJobRepository savedJobRepo;

    public List<SavedJob> getAllSavedJobs() {
        return savedJobRepo.findAll();
    }

    public SavedJob saveSavedJob(SavedJobRequests requests) {
        SavedJob savedJob = new SavedJob();
        savedJob.setUserId(requests.getUserId());
        savedJob.setJobId(requests.getJobId());
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
            savedJob.setUserId(requests.getUserId());
            savedJob.setJobId(requests.getJobId());
            return savedJobRepo.save(savedJob);
        }).orElse(null);
    }
}
