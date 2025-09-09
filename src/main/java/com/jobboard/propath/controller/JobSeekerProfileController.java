package com.jobboard.propath.controller;

import com.jobboard.propath.entity.JobSeekerProfile;
import com.jobboard.propath.requests.JobSeekerProfileRequests;
import com.jobboard.propath.responses.ApiResponse;
import com.jobboard.propath.service.JobSeekerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobseekerprofiles")
public class JobSeekerProfileController {

    @Autowired
    private JobSeekerProfileService jobSeekerProfileService;

    @PostMapping
    public ResponseEntity<ApiResponse> createJobSeekerProfile(@RequestBody JobSeekerProfileRequests requests) {
        try {
            JobSeekerProfile profile = jobSeekerProfileService.saveProfile(requests);
            ApiResponse response = new ApiResponse(true, "Profile created successfully", profile);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(false, "Failed to create profile: " + e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllJobSeekerProfiles() {
        try {
            List<JobSeekerProfile> profiles = jobSeekerProfileService.getAllProfiles();
            ApiResponse response = new ApiResponse(true, "Profiles retrieved successfully", profiles);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(false, "Failed to retrieve profiles: " + e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateJobSeekerProfile(@PathVariable Long id, @RequestBody JobSeekerProfileRequests requests) {
        try {
            JobSeekerProfile updatedProfile = jobSeekerProfileService.updateProfile(id, requests);
            if (updatedProfile != null) {
                ApiResponse response = new ApiResponse(true, "Profile updated successfully", updatedProfile);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse response = new ApiResponse(false, "Profile not found", null);
                return ResponseEntity.status(404).body(response);
            }
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(false, "Failed to update profile: " + e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteJobSeekerProfile(@PathVariable Long id) {
        try {
            jobSeekerProfileService.deleteProfile(id);
            ApiResponse response = new ApiResponse(true, "Profile deleted successfully", null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(false, "Failed to delete profile: " + e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }
}
