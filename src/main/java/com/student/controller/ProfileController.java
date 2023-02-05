package com.student.controller;

import com.student.payload.ProfileDto;
import com.student.service.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProfileController {


    private ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping ("api/students/{studentId}/profiles")
    public ResponseEntity<ProfileDto> createProfile(@PathVariable("studentId") Long studentId,
                                                    @RequestBody ProfileDto profileDto){

        ProfileDto dto = profileService.createProfile(studentId, profileDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping("/profiles/{studentId}/profiles/{id}")
    public ResponseEntity<ProfileDto> updateProfile(@PathVariable(value="studentId") Long studentId,
                                                    @PathVariable(value="id") Long profileId,
                                                    @RequestBody ProfileDto profileDto){

        ProfileDto updatedProfile=profileService.updateProfile(studentId,profileId,profileDto);
        return new ResponseEntity<>(updatedProfile,HttpStatus.OK);
    }

    @GetMapping("api/students/{studentId}/profiles")
    public List<ProfileDto> findProfileByStudentId(@PathVariable("studentId") Long studentId){


        List<ProfileDto> dto = profileService.getProfileByStudentId(studentId);
        return dto;
    }

    @DeleteMapping("api/students/{studentId}/profiles")
    public ResponseEntity<String> deleteProfile(@PathVariable("studentId") Long studentId,
                                                    @RequestBody ProfileDto profileDto, long id){

        profileService.deleteProfile( studentId, id);

        return new ResponseEntity<>("the profile is deleted!", HttpStatus.OK);
    }
}
