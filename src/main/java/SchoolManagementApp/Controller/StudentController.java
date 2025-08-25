package SchoolManagementApp.Controller;

import SchoolManagementApp.DTO.EnrollmentEntityDto;
import SchoolManagementApp.DTO.StudentEntityDto;
import SchoolManagementApp.Service.EnrollmentService;
import SchoolManagementApp.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    EnrollmentService enrollmentService;


    @GetMapping("/student-details/{username}")
    public ResponseEntity<?> getStudentDetails(@PathVariable String username) {
        try {
            StudentEntityDto allDetails = studentService.getAllDetails(username);
            return new ResponseEntity<>(allDetails, HttpStatus.FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/create-profile/{username}")
    public ResponseEntity<?> createProfile(@RequestBody StudentEntityDto profile, @PathVariable String username) {
        try {
            studentService.createProfile(profile, username);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update-profile/{username}")
    public ResponseEntity<?> updateProfile(@RequestBody StudentEntityDto profile, @PathVariable String username) {
        try {
            studentService.updateProfile(profile, username);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete-profile/{username}")
    public ResponseEntity<?> deleteProfile(@PathVariable String username) {
        try {
            studentService.deleteprofile(username);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/enrollment-details/{username}")
    public ResponseEntity<?> subjectEnrollment(@PathVariable String username) {
        try {
            List<EnrollmentEntityDto> allEnrollment = enrollmentService.getAllEnrollment(username);
            return new ResponseEntity<>(allEnrollment, HttpStatus.FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/enrollment-info/{username}")
    public ResponseEntity<?> createProfile(@RequestBody EnrollmentEntityDto enrollSubject, @PathVariable String username) {
        try {
            enrollmentService.enrollemtEntry(enrollSubject, username);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
