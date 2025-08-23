package SchoolManagementApp.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class publicContoller {

    @GetMapping("/health-check")
    public String checkHealthCheck(){
        return "OK";
    }
}
