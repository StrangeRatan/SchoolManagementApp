package SchoolManagementApp.Controller;


import SchoolManagementApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Admin")
public class AdminContoller {

    @Autowired
    UserService userService;

    @GetMapping("/get-All-users")
    public ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.FOUND);

    }




}
