package SchoolManagementApp.Controller;


import SchoolManagementApp.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Admin")
@Tag(name = "Admin APIs", description = "Admin can check all users, DELETE user")
public class AdminContoller {

    @Autowired
    UserService userService;

    @GetMapping("/get-All-users")
    @Operation(summary = "This endpoint used of Admin user only he can check all users")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);

    }

    @DeleteMapping("/delete-userbyName/{username}")
    @Operation(summary = "This endpoint used of Admin user only, and delete user by there username")
    public ResponseEntity<?> deleteUserByName(@PathVariable String username) {
        try {
            userService.deleteUser(username);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
