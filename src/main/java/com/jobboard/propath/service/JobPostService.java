package com.jobboard.propath.service;

import com.jobboard.propath.entity.Company;
import com.jobboard.propath.entity.EmployerProfile;
import com.jobboard.propath.entity.JobPost;
import com.jobboard.propath.repository.CompanyRepository;
import com.jobboard.propath.repository.EmployerProfileRepository;
import com.jobboard.propath.repository.JobPostRepository;
import com.jobboard.propath.requests.JobPostRequests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobPostService {

    @Autowired
    JobPostRepository jrepo;

    @Autowired
    EmployerProfileRepository eprepo;

    @Autowired
    CompanyRepository crepo;

    public List<JobPost> getAllJobPosts(){
        return jrepo.findAll();
    }

    public JobPost saveJobPost(JobPostRequests requests){
        try{
            JobPost jobPost = new JobPost();
            if(requests.getCompanyId() != null && requests.getEmployerProfileId() != null){
                EmployerProfile employerProfile = eprepo.findById(requests.getEmployerProfileId()).orElseThrow(() -> new RuntimeException("Employer Profile not found"));
                jobPost.setEmployerProfile(employerProfile);
                Company company = crepo.findById(requests.getCompanyId()).orElseThrow(() -> new RuntimeException("Company not found"));
                jobPost.setCompany(company);
            }
            jobPost.setTitle(requests.getTitle());
            jobPost.setDescription(requests.getDescription());
            jobPost.setRequirements(requests.getRequirements());
            jobPost.setLocation(requests.getLocation());
            jobPost.setJobType(requests.getJobType());
            jobPost.setSalaryMinimum(requests.getSalaryMinimum());
            jobPost.setSalaryMaximum(requests.getSalaryMaximum());
            jobPost.setCategory(requests.getCategory());
            jobPost.setStatus(requests.getStatus());
            return jrepo.save(jobPost);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void deleteJobPost(Long id){
        JobPost jobPost = jrepo.findById(id).orElseThrow(() -> new RuntimeException("Job Post not found"));
        jrepo.delete(jobPost);
    }

    public JobPost getJobPostById(Long id){
        return jrepo.findById(id).orElseThrow(() -> new RuntimeException("Job Post not found"));
    }

    public JobPost updateJobPost(Long id, JobPostRequests requests){
        try{
            return jrepo.findById(id).map(jobPost -> {
                if(requests.getCompanyId() != null && requests.getEmployerProfileId() != null){
                    EmployerProfile employerProfile = eprepo.findById(requests.getEmployerProfileId()).orElseThrow(() -> new RuntimeException("Employer Profile not found"));
                    jobPost.setEmployerProfile(employerProfile);
                    Company company = crepo.findById(requests.getCompanyId()).orElseThrow(() -> new RuntimeException("Company not found"));
                    jobPost.setCompany(company);
                }
                jobPost.setTitle(requests.getTitle());
                jobPost.setDescription(requests.getDescription());
                jobPost.setRequirements(requests.getRequirements());
                jobPost.setLocation(requests.getLocation());
                jobPost.setJobType(requests.getJobType());
                jobPost.setSalaryMinimum(requests.getSalaryMinimum());
                jobPost.setSalaryMaximum(requests.getSalaryMaximum());
                jobPost.setCategory(requests.getCategory());
                jobPost.setStatus(requests.getStatus());
                return jrepo.save(jobPost);
            }).orElse(null);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
