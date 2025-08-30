package SchoolManagementApp.Controller;

import SchoolManagementApp.DTO.UserEntityDtoSignup;
import SchoolManagementApp.ExternalApiAdd.BookApi.BookDto;
import SchoolManagementApp.ExternalApiAdd.BookApi.BookService;
import SchoolManagementApp.ExternalApiAdd.WeatherApi.WeatherService;
import SchoolManagementApp.ExternalApiAdd.WeatherApi.weatherDto;
import SchoolManagementApp.Service.UserDetailServiceImp;
import SchoolManagementApp.Service.UserService;
import SchoolManagementApp.Util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
@Tag(name = "Public APIs",
        description = "Check API working, Check BOOKs, SIGNUP and LOGIN then get TOKEN for uses endpoint")
public class publicContoller {

    @Autowired
    private UserService userService;


    @Autowired
    private WeatherService weatherService;

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailServiceImp userDetailServiceImp;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/health-check")
    @Operation(
            summary = "Check API health ",
            description = "Returns a simple greeting message along with today's weather in Varanasi. "
                    + "This is mainly used to verify that the API and external weather service are working correctly.")
    public ResponseEntity<?> checkHealthCheck() {
        weatherDto weatherResponse = weatherService.getWeather("Varanasi");
        String greeting = "";
        if (weatherResponse != null) {
            greeting = ",and Today  Weather feels like " + weatherResponse.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>("Hello Welcome to Student-Mangement-App " + greeting, HttpStatus.OK);
    }

    @GetMapping("/book/{title}")
    @Operation(summary = "Search books by title",
            description = "Searches for books based on the provided title using the external Book API. "
                    + "Returns a list of matching books with their details.")
    public ResponseEntity<?> searchBooks(@PathVariable String title) {
        List<BookDto> books = bookService.searchBooks(title);
        if (books.isEmpty()) {
            return new ResponseEntity<>("Sorry This book is not available", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping("/signup")
    @Operation(
            summary = "Register a new user",
            description = "Creates a new user account by saving the provided username and password. "
                    + "This endpoint should be called only once for new users before login."
    )
    public ResponseEntity<?> signup(@Valid @RequestBody UserEntityDtoSignup newUser) {
        userService.createUser(newUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @Operation(summary = "Authenticate and get JWT token",
            description = "Authenticates the user with username and password. "
                    + "If successful, returns a JWT token that must be included in the Authorization header "
                    + "for accessing protected student-related APIs.")
    public ResponseEntity<?> login(@Valid @RequestBody UserEntityDtoSignup user) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            UserDetails userDetails = userDetailServiceImp.loadUserByUsername(user.getUsername());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }


    }


}





