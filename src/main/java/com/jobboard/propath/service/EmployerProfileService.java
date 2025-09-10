package com.jobboard.propath.service;

import com.jobboard.propath.entity.EmployerProfile;
import com.jobboard.propath.entity.User;
import com.jobboard.propath.entity.Company;
import com.jobboard.propath.repository.EmployerProfileRepository;
import com.jobboard.propath.repository.UserRepository;
import com.jobboard.propath.repository.CompanyRepository;
import com.jobboard.propath.requests.EmployerProfileRequests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerProfileService {

    @Autowired
    EmployerProfileRepository erepo;

    @Autowired
    UserRepository urepo;

    @Autowired
    CompanyRepository crepo;

    public List<EmployerProfile> getAllProfiles() {
        return erepo.findAll();
    }

    public EmployerProfile saveProfile(EmployerProfileRequests requests) {
        EmployerProfile profile = new EmployerProfile();
        if (requests.getUserId() != null) {
            User user = urepo.findById(requests.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
            profile.setUser(user);
        }
        if (requests.getCompanyId() != null) {
            Company company = crepo.findById(requests.getCompanyId()).orElseThrow(() -> new RuntimeException("Company not found"));
            profile.setCompany(company);
        }
        profile.setPosition(requests.getPosition());
        return erepo.save(profile);
    }

    public void deleteProfile(Long id) {
        erepo.deleteById(id);
    }

    public EmployerProfile getProfileById(Long id) {
        return erepo.findById(id).orElse(null);
    }

    public EmployerProfile updateProfile(Long id, EmployerProfileRequests requests) {
        return erepo.findById(id).map(profile -> {
            if (requests.getUserId() != null) {
                User user = urepo.findById(requests.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
                profile.setUser(user);
            }
            if (requests.getCompanyId() != null) {
                Company company = crepo.findById(requests.getCompanyId()).orElseThrow(() -> new RuntimeException("Company not found"));
                profile.setCompany(company);
            }
            profile.setPosition(requests.getPosition());
            return erepo.save(profile);
        }).orElse(null);
    }
}
