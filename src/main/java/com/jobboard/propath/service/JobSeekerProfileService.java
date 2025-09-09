package com.jobboard.propath.service;

import com.jobboard.propath.entity.JobSeekerProfile;
import com.jobboard.propath.entity.User;
import com.jobboard.propath.repostitory.JobSeekerProfileRepository;
import com.jobboard.propath.repostitory.UserRepository;
import com.jobboard.propath.requests.JobSeekerProfileRequests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobSeekerProfileService {

    @Autowired
    JobSeekerProfileRepository jrepo;

    @Autowired
    UserRepository urepo;

    public List<JobSeekerProfile> getAllProfiles() {
        return jrepo.findAll();
    }

    public JobSeekerProfile saveProfile(JobSeekerProfileRequests requests) {
        JobSeekerProfile profile = new JobSeekerProfile();
        if(requests.getUserId() != null) {
            User user = urepo.findById(requests.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
            profile.setUser(user);
        }
        profile.setResumeUrl(requests.getResumeUrl());
        profile.setSkills(requests.getSkills());
        profile.setDesiredSalary(requests.getDesiredSalary());
        profile.setExperience(requests.getExperience());
        profile.setEducation(requests.getEducation());
        profile.setLocation(requests.getLocation());
        return jrepo.save(profile);
    }

    public void deleteProfile(Long id) {
        jrepo.deleteById(id);
    }

    public JobSeekerProfile getProfileById(Long id) {
        return jrepo.findById(id).orElse(null);
    }

    public JobSeekerProfile updateProfile(Long id, JobSeekerProfileRequests requests) {
        return jrepo.findById(id).map(profile -> {
            profile.setResumeUrl(requests.getResumeUrl());
            profile.setSkills(requests.getSkills());
            profile.setSkills(requests.getSkills());
            profile.setDesiredSalary(requests.getDesiredSalary());
            profile.setExperience(requests.getExperience());
            return jrepo.save(profile);
        }).orElse(null);
    }
}
