package com.student.repository;

import com.student.model.Profile;
import com.student.payload.ProfileDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    List<Profile> findByStudentId(Long studentId);
}
