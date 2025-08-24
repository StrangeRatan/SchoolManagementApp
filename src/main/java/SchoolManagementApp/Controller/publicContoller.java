package SchoolManagementApp.Controller;

import SchoolManagementApp.DTO.UserEntityDto;
import SchoolManagementApp.DTO.UserEntityDtoSignup;
import SchoolManagementApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class publicContoller {

    @Autowired
    UserService userService;

    @GetMapping("/health-check")
    public String checkHealthCheck(){
        return "OK";
    }

    @PostMapping("/create-users")
    public ResponseEntity<?> createUser(@RequestBody UserEntityDtoSignup newUser){
        userService.createUser(newUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
