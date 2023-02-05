package com.student.service;

import com.student.exception.BlogApiException;
import com.student.exception.RequestNotFoundException;
import com.student.model.Profile;
import com.student.model.Student;
import com.student.payload.ProfileDto;
import com.student.repository.ProfileRepository;
import com.student.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService{

    private ProfileRepository profileRepo;

    private StudentRepository studentRepo;

    public ProfileServiceImpl(ProfileRepository profileRepo, StudentRepository studentRepo) {
        this.profileRepo = profileRepo;
        this.studentRepo = studentRepo;
    }

    @Override
    public ProfileDto createProfile(Long studentId, ProfileDto profileDto) {

        Student student = studentRepo.findById(studentId).orElseThrow(
                                                        () -> new RuntimeException("not found:" + studentId));

        Profile profile=mapToEntity(profileDto);
        profile.setStudent(student);

        Profile save = profileRepo.save(profile);


        return mapToDto(save);
    }

    @Override
    public List<ProfileDto> getProfileByStudentId(long studentId) {
        List<Profile> profiles = profileRepo.findByStudentId(studentId);
        return profiles.stream().map(c->mapToDto(c)).collect(Collectors.toList());
    }


    @Override
    public ProfileDto updateProfile(Long studentId, Long id, ProfileDto profileRequest) {

        Student student=studentRepo.findById(studentId).orElseThrow(()->new RequestNotFoundException("Student","id","studentId"));

        Profile profile=profileRepo.findById(id).orElseThrow(()->new RequestNotFoundException("Profile","id","id"));

        if(!profile.getStudent().equals(student.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Profile does not belong to student");
        }
        profile.setAddress(profileRequest.getAddress());
        profile.setFeedback(profileRequest.getFeedback());
        profile.setStatus(profileRequest.getStatus());

        Profile updatedProfile=profileRepo.save(profile);
        return mapToDto(updatedProfile);


    }

    @Override
    public void deleteProfile(long studentId, long id) {
        Student student=studentRepo.findById(studentId).orElseThrow(()->new RequestNotFoundException("Student","id","studentId"));

        Profile profile=profileRepo.findById(id).orElseThrow(()->new RequestNotFoundException("Profile","id","id"));

        if(!profile.getStudent().equals(student.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Profile does not belong to student");
        }

        profileRepo.delete(profile);
    }

    private ProfileDto mapToDto(Profile profile) {


            ProfileDto profileDto=new ProfileDto();
            profileDto.setId(profile.getId());
            profileDto.setAddress(profile.getAddress());
            profileDto.setFeedback(profileDto.getFeedback());
            profileDto.setStatus(profile.getStatus());


        return profileDto;
    }

    private Profile mapToEntity(ProfileDto profileDto) {

           Profile profile=new Profile();
           profile.setAddress(profileDto.getAddress());
           profile.setFeedback(profileDto.getFeedback());
           profile.setStatus(profileDto.getStatus());

        return profile;
    }
}
