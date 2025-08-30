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
@Tag(name = "Admin APIs", description = "Admin can check all users and delete users")
public class AdminContoller {

    @Autowired
    UserService userService;

    @GetMapping("/get-All-users")
    @Operation(summary = "Get a list of all users (Admin only)")
    public ResponseEntity<?> getAllUsers() {
        try{
            return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>("Failed to fetch users: " + e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/delete-userbyName/{username}")
    @Operation(summary = "Delete a user by username (Admin only)")
    public ResponseEntity<?> deleteUserByName(@PathVariable String username) {
        try {
            userService.deleteUser(username);
            return new ResponseEntity<>("User deleted successfully",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("User not found: " + username,HttpStatus.NOT_FOUND);
        }
    }


}
