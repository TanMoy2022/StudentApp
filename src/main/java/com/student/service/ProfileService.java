package com.student.service;

import com.student.payload.ProfileDto;

import java.util.List;

public interface ProfileService {

    ProfileDto createProfile(Long studentId, ProfileDto profileDto);


    List<ProfileDto> getProfileByStudentId(long studentId);

    ProfileDto updateProfile(Long studentId, Long id, ProfileDto profileRequest);

    void deleteProfile(long studentId, long id);
}
