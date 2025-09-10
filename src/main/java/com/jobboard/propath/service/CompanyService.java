package com.jobboard.propath.service;

import com.jobboard.propath.entity.Company;
import com.jobboard.propath.repository.CompanyRepository;
import com.jobboard.propath.requests.CompanyRequests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository crepo;

    public List<Company> getAllCompanies() {
        return crepo.findAll();
    }

    public Company saveCompany(CompanyRequests requests) {
        Company company = new Company();
        company.setCompanyName(requests.getCompanyName());
        company.setDescription(requests.getDescription());
        company.setLogoUrl(requests.getLogoUrl() != null ? requests.getLogoUrl().getBytes() : null);
        company.setLocation(requests.getLocation());
        company.setWebsite(requests.getWebsite());
        company.setIndustry(requests.getIndustry());
        return crepo.save(company);
    }

    public void deleteCompany(Long id) {
        crepo.deleteById(id);
    }

    public Company getCompanyById(Long id) {
        return crepo.findById(id).orElse(null);
    }

    public Company updateCompany(Long id, CompanyRequests requests) {
        return crepo.findById(id).map(company -> {
            company.setCompanyName(requests.getCompanyName());
            company.setDescription(requests.getDescription());
            company.setLogoUrl(requests.getLogoUrl() != null ? requests.getLogoUrl().getBytes() : null);
            company.setLocation(requests.getLocation());
            company.setWebsite(requests.getWebsite());
            company.setIndustry(requests.getIndustry());
            return crepo.save(company);
        }).orElse(null);
    }
}
