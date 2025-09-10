package com.jobboard.propath.controller;

import com.jobboard.propath.entity.JobPost;
import com.jobboard.propath.requests.JobPostRequests;
import com.jobboard.propath.responses.ApiResponse;
import com.jobboard.propath.service.JobPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobposts")
public class JobPostController {

    @Autowired
    JobPostService jserv;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllJobPosts(){
        try{
            List<JobPost> jobPosts = jserv.getAllJobPosts();
            ApiResponse response = new ApiResponse(true, "Job posts retrieved successfully", jobPosts);
            return ResponseEntity.ok(response);
        }catch(Exception e){
            ApiResponse response = new ApiResponse(false, "Failed to retrieve job posts: " + e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createJobPost(@RequestBody JobPostRequests requests){
        try{
            JobPost jobPost = jserv.saveJobPost(requests);
            ApiResponse response = new ApiResponse(true, "Job post created successfully", jobPost);
            return ResponseEntity.ok(response);
        }catch(Exception e){
            ApiResponse response = new ApiResponse(false, "Failed to create job post: " + e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteJobPost(@PathVariable Long id){
        try{
            jserv.deleteJobPost(id);
            ApiResponse response = new ApiResponse(true, "Job post deleted successfully", null);
            return ResponseEntity.ok(response);
        }catch(Exception e){
            ApiResponse response = new ApiResponse(false, "Failed to delete job post: " + e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getJobPostById(@PathVariable Long id){
        try{
            JobPost jobPost = jserv.getJobPostById(id);
            if(jobPost != null){
                ApiResponse response = new ApiResponse(true, "Job post retrieved successfully", jobPost);
                return ResponseEntity.ok(response);
            }else{
                ApiResponse response = new ApiResponse(false, "Job post not found", null);
                return ResponseEntity.status(404).body(response);
            }
        }catch(Exception e){
            ApiResponse response = new ApiResponse(false, "Failed to retrieve job post: " + e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateJobPost(@PathVariable Long id, @RequestBody JobPostRequests requests){
        try{
            JobPost updatedJobPost = jserv.updateJobPost(id, requests);
            if(updatedJobPost != null){
                ApiResponse response = new ApiResponse(true, "Job post updated successfully", updatedJobPost);
                return ResponseEntity.ok(response);
            }else{
                ApiResponse response = new ApiResponse(false, "Job post not found", null);
                return ResponseEntity.status(404).body(response);
            }
        }catch(Exception e){
            ApiResponse response = new ApiResponse(false, "Failed to update job post: " + e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }
}
