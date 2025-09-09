package com.jobboard.propath.repostitory;

import com.jobboard.propath.entity.JobSeekerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobSeekerProfileRepository extends JpaRepository<JobSeekerProfile, Long> {
}
