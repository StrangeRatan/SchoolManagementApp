package SchoolManagementApp.Controller;

import SchoolManagementApp.DTO.UserEntityDtoSignup;
import SchoolManagementApp.ExternalApiAdd.BookApi.BookDto;
import SchoolManagementApp.ExternalApiAdd.BookApi.BookService;
import SchoolManagementApp.ExternalApiAdd.WeatherApi.WeatherService;
import SchoolManagementApp.ExternalApiAdd.WeatherApi.weatherDto;
import SchoolManagementApp.Service.UserDetailServiceImp;
import SchoolManagementApp.Service.UserService;
import SchoolManagementApp.Util.JwtUtil;
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
@RequestMapping("/publicJWT")
public class publicContollerJWT {

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
    public ResponseEntity<?> checkHealthCheck() {
        weatherDto weatherResponse = weatherService.getWeather("Varanasi");
        String greeting = "";
        if (weatherResponse != null) {
            greeting = ",and Today  Weather feels like " + weatherResponse.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>("Hello Welcome to Student-Mangement-App " + greeting, HttpStatus.OK);
    }

    @GetMapping("/book/{title}")
    public List<BookDto> searchBooks(@PathVariable String title) {
        return bookService.searchBooks(title);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody UserEntityDtoSignup newUser) {
        userService.createUser(newUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserEntityDtoSignup user) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            UserDetails userDetails = userDetailServiceImp.loadUserByUsername(user.getUsername());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt,HttpStatus.CREATED);
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }


    }


}





