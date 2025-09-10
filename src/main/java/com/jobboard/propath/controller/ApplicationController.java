package com.jobboard.propath.controller;

import com.jobboard.propath.entity.Application;
import com.jobboard.propath.requests.ApplicationRequests;
import com.jobboard.propath.responses.ApiResponse;
import com.jobboard.propath.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/applications")
@RestController
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<ApiResponse> createApplication(@RequestBody ApplicationRequests requests) {
        try {
            Application application = applicationService.saveApplication(requests);
            ApiResponse response = new ApiResponse(true, "Application created successfully", application);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(false, "Failed to create application: " + e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllApplications() {
        try {
            List<Application> applications = applicationService.getAllApplications();
            ApiResponse response = new ApiResponse(true, "Applications retrieved successfully", applications);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(false, "Failed to retrieve applications: " + e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getApplicationById(@PathVariable Long id) {
        try {
            Application application = applicationService.getApplicationById(id);
            if (application != null) {
                ApiResponse response = new ApiResponse(true, "Application retrieved successfully", application);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse response = new ApiResponse(false, "Application not found", null);
                return ResponseEntity.status(404).body(response);
            }
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(false, "Failed to retrieve application: " + e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateApplication(@PathVariable Long id, @RequestBody ApplicationRequests requests) {
        try {
            Application updatedApplication = applicationService.updateApplication(id, requests);
            if (updatedApplication != null) {
                ApiResponse response = new ApiResponse(true, "Application updated successfully", updatedApplication);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse response = new ApiResponse(false, "Application not found", null);
                return ResponseEntity.status(404).body(response);
            }
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(false, "Failed to update application: " + e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteApplication(@PathVariable Long id) {
        try {
            applicationService.deleteApplication(id);
            ApiResponse response = new ApiResponse(true, "Application deleted successfully", null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(false, "Failed to delete application: " + e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }
}
