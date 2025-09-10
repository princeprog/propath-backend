package com.jobboard.propath.controller;

import com.jobboard.propath.entity.SavedJob;
import com.jobboard.propath.requests.SavedJobRequests;
import com.jobboard.propath.responses.ApiResponse;
import com.jobboard.propath.service.SavedJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/saved-jobs")
@RestController
public class SavedJobController {

    @Autowired
    private SavedJobService savedJobService;

    @PostMapping
    public ResponseEntity<ApiResponse> createSavedJob(@RequestBody SavedJobRequests requests) {
        try {
            SavedJob savedJob = savedJobService.saveSavedJob(requests);
            ApiResponse response = new ApiResponse(true, "Saved job created successfully", savedJob);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(false, "Failed to create saved job: " + e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllSavedJobs() {
        try {
            List<SavedJob> savedJobs = savedJobService.getAllSavedJobs();
            ApiResponse response = new ApiResponse(true, "Saved jobs retrieved successfully", savedJobs);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(false, "Failed to retrieve saved jobs: " + e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getSavedJobById(@PathVariable Long id) {
        try {
            SavedJob savedJob = savedJobService.getSavedJobById(id);
            if (savedJob != null) {
                ApiResponse response = new ApiResponse(true, "Saved job retrieved successfully", savedJob);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse response = new ApiResponse(false, "Saved job not found", null);
                return ResponseEntity.status(404).body(response);
            }
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(false, "Failed to retrieve saved job: " + e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateSavedJob(@PathVariable Long id, @RequestBody SavedJobRequests requests) {
        try {
            SavedJob updatedSavedJob = savedJobService.updateSavedJob(id, requests);
            if (updatedSavedJob != null) {
                ApiResponse response = new ApiResponse(true, "Saved job updated successfully", updatedSavedJob);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse response = new ApiResponse(false, "Saved job not found", null);
                return ResponseEntity.status(404).body(response);
            }
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(false, "Failed to update saved job: " + e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteSavedJob(@PathVariable Long id) {
        try {
            savedJobService.deleteSavedJob(id);
            ApiResponse response = new ApiResponse(true, "Saved job deleted successfully", null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(false, "Failed to delete saved job: " + e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }
}
