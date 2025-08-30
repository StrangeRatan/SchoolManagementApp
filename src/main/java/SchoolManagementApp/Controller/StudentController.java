package SchoolManagementApp.Controller;

import SchoolManagementApp.DTO.EnrollmentEntityDto;
import SchoolManagementApp.DTO.EnrollmentEntityDtoEnroll;
import SchoolManagementApp.DTO.StudentEntityDto;
import SchoolManagementApp.DTO.StudentEntityDtoshowInfo;
import SchoolManagementApp.Service.EnrollmentService;
import SchoolManagementApp.Service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@Tag(name = "Student-APIs", description = "Manage student profile and enrollment")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    EnrollmentService enrollmentService;


    @GetMapping("/student-information")
    @Operation(summary = "Get all details of the logged-in student")
    public ResponseEntity<?> getStudentDetails() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            StudentEntityDtoshowInfo allDetails = studentService.getAllDetails(username);
            return new ResponseEntity<>(allDetails, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Student profile not found", HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/create-profile")
    @Operation(summary = "Create a new student profile")
    public ResponseEntity<?> createProfile(@Valid @RequestBody StudentEntityDto profile) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            studentService.createProfile(profile, username);
            return new ResponseEntity<>("Profile created successfully", HttpStatus.CREATED);
        } catch (Exception e) {

            return new ResponseEntity<>("Failed to create profile: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update-profile")
    @Operation(summary = "Update student profile")
    public ResponseEntity<?> updateProfile(@RequestBody StudentEntityDto profile) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            studentService.updateProfile(profile, username);
            return new ResponseEntity<>("Profile updated successfully", HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>("Failed to update profile: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete-profile")
    @Operation(summary = "Delete student profile")
    public ResponseEntity<?> deleteProfile() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            studentService.deleteprofile(username);
            return new ResponseEntity<>("Profile deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Profile not found to delete", HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/enrollment-information")
    @Operation(summary = "Get all enrolled subjects/courses")
    public ResponseEntity<?> subjectEnrollment() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            List<EnrollmentEntityDto> allEnrollment = enrollmentService.getAllEnrollment(username);
            return new ResponseEntity<>(allEnrollment, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("No enrollment data found", HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/enroll-subject")
    @Operation(summary = "Enroll in a subject or course")
    public ResponseEntity<?> enrollSubject(@RequestBody EnrollmentEntityDtoEnroll enrollSubject) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            enrollmentService.enrollemtEntry(enrollSubject, username);
            return new ResponseEntity<>("Enrolled successfully", HttpStatus.CREATED);
        } catch (Exception e) {

            return new ResponseEntity<>("Failed to enroll: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
