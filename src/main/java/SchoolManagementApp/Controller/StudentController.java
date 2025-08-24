package SchoolManagementApp.Controller;

import SchoolManagementApp.DTO.StudentEntityDto;
import SchoolManagementApp.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;




    @GetMapping("/student-details/{username}")
    public ResponseEntity<?> getStudentDetails(@PathVariable String username){
    try{
        StudentEntityDto allDetails = studentService.getAllDetails(username);
        return new ResponseEntity<>(allDetails,HttpStatus.FOUND);

    } catch (Exception e) {
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    }

    @PostMapping("/create-profile/{username}")
    public ResponseEntity<?> createProfile(@RequestBody StudentEntityDto profile ,@PathVariable String username){
       try {
           studentService.createProfile(profile, username);
           return new ResponseEntity<>(HttpStatus.CREATED);
       } catch (Exception e) {

           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }


}
