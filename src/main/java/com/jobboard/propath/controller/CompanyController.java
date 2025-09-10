package com.jobboard.propath.controller;

import com.jobboard.propath.entity.Company;
import com.jobboard.propath.requests.CompanyRequests;
import com.jobboard.propath.responses.ApiResponse;
import com.jobboard.propath.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/companies")
@RestController
public class CompanyController {
    
    @Autowired
    private CompanyService companyService;

    @PostMapping
    public ResponseEntity<ApiResponse> createCompany(@RequestBody CompanyRequests requests) {
        try {
            Company company = companyService.saveCompany(requests);
            ApiResponse response = new ApiResponse(true, "Company created successfully", company);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(false, "Failed to create company: " + e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllCompanies() {
        try {
            List<Company> companies = companyService.getAllCompanies();
            ApiResponse response = new ApiResponse(true, "Companies retrieved successfully", companies);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(false, "Failed to retrieve companies: " + e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateCompany(@PathVariable Long id, @RequestBody CompanyRequests requests) {
        try {
            Company updatedCompany = companyService.updateCompany(id, requests);
            if (updatedCompany != null) {
                ApiResponse response = new ApiResponse(true, "Company updated successfully", updatedCompany);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse response = new ApiResponse(false, "Company not found", null);
                return ResponseEntity.status(404).body(response);
            }
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(false, "Failed to update company: " + e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCompany(@PathVariable Long id) {
        try {
            companyService.deleteCompany(id);
            ApiResponse response = new ApiResponse(true, "Company deleted successfully", null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(false, "Failed to delete company: " + e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getCompanyById(@PathVariable Long id) {
        try {
            Company company = companyService.getCompanyById(id);
            if (company != null) {
                ApiResponse response = new ApiResponse(true, "Company retrieved successfully", company);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse response = new ApiResponse(false, "Company not found", null);
                return ResponseEntity.status(404).body(response);
            }
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(false, "Failed to retrieve company: " + e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }
}