package com.jobboard.propath.controller;

import com.jobboard.propath.entity.EmployerProfile;
import com.jobboard.propath.requests.EmployerProfileRequests;
import com.jobboard.propath.responses.ApiResponse;
import com.jobboard.propath.service.EmployerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employerprofiles")
public class EmployerProfileController {

    @Autowired
    private EmployerProfileService employerProfileService;

    @PostMapping
    public ResponseEntity<ApiResponse> createEmployerProfile(@RequestBody EmployerProfileRequests requests) {
        try {
            EmployerProfile profile = employerProfileService.saveProfile(requests);
            ApiResponse response = new ApiResponse(true, "Profile created successfully", profile);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(false, "Failed to create profile: " + e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllEmployerProfiles() {
        try {
            List<EmployerProfile> profiles = employerProfileService.getAllProfiles();
            ApiResponse response = new ApiResponse(true, "Profiles retrieved successfully", profiles);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(false, "Failed to retrieve profiles: " + e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateEmployerProfile(@PathVariable Long id, @RequestBody EmployerProfileRequests requests) {
        try {
            EmployerProfile updatedProfile = employerProfileService.updateProfile(id, requests);
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
    public ResponseEntity<ApiResponse> deleteEmployerProfile(@PathVariable Long id) {
        try {
            employerProfileService.deleteProfile(id);
            ApiResponse response = new ApiResponse(true, "Profile deleted successfully", null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(false, "Failed to delete profile: " + e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }
}
