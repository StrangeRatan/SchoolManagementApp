package SchoolManagementApp.Controller;

import SchoolManagementApp.DTO.UserEntityDtoSignup;

import SchoolManagementApp.ExternalApiAdd.BookApi.BookDto;
import SchoolManagementApp.ExternalApiAdd.BookApi.BookService;
import SchoolManagementApp.ExternalApiAdd.WeatherApi.WeatherService;
import SchoolManagementApp.ExternalApiAdd.WeatherApi.weatherDto;
import SchoolManagementApp.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
public class publicContoller {

    @Autowired
    UserService userService;


    @Autowired
    WeatherService weatherService;

    @Autowired
    BookService bookService;

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

    @PostMapping("/create-users")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserEntityDtoSignup newUser) {
        userService.createUser(newUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }






}





