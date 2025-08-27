package SchoolManagementApp.Controller;

import SchoolManagementApp.DTO.EnrollmentEntityDto;
import SchoolManagementApp.DTO.EnrollmentEntityDtoEnroll;
import SchoolManagementApp.DTO.StudentEntityDto;
import SchoolManagementApp.Service.EnrollmentService;
import SchoolManagementApp.Service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@Tag(name="Student-APIs", description = "Check Student our Profile, CREATE, UPDATE and DELETE profile")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    EnrollmentService enrollmentService;


    @GetMapping("/student-information")
    @Operation(summary = "This endpoint show all date related student")
    public ResponseEntity<?> getStudentDetails() {
        try {
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            StudentEntityDto allDetails = studentService.getAllDetails(username);
            return new ResponseEntity<>(allDetails, HttpStatus.FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/create-profile")
    @Operation(summary = "this endpoint user create our profile")
    public ResponseEntity<?> createProfile(@Valid @RequestBody StudentEntityDto profile) {
        try {
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            studentService.createProfile(profile, username);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update-profile")
    @Operation(summary = "This endpoint user update there profile")
    public ResponseEntity<?> updateProfile( @RequestBody StudentEntityDto profile) {
        try {
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            studentService.updateProfile(profile, username);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete-profile")
    @Operation(summary = "This endpoint user delete our profile")
    public ResponseEntity<?> deleteProfile() {
        try {
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            studentService.deleteprofile(username);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/enrollment-information")
    @Operation(summary = "This endpoint show  user enroll all subject or courses")
    public ResponseEntity<?> subjectEnrollment() {
        try {
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            List<EnrollmentEntityDto> allEnrollment = enrollmentService.getAllEnrollment(username);
            return new ResponseEntity<>(allEnrollment, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/enroll-subject")
    @Operation(summary = "This endpoint user enroll subject or course")
    public ResponseEntity<?> createProfile(@RequestBody EnrollmentEntityDtoEnroll enrollSubject) {
        try {
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            enrollmentService.enrollemtEntry(enrollSubject, username);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
