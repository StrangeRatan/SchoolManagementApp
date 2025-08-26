package SchoolManagementApp.Component;

import SchoolManagementApp.Entity.StudenMangementConfi;
import SchoolManagementApp.Repository.ConfigurationRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {
    @Autowired
    private ConfigurationRepository configurationRepository;

    public Map<String,String> APP_CACHE=new HashMap<>();

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @PostConstruct
    public  void init(){
        List<StudenMangementConfi> all = configurationRepository.findAll();
        for (StudenMangementConfi bookEntryConfig : all) {
            APP_CACHE.put(bookEntryConfig.getKey(), bookEntryConfig.getApi());

        }


    }
}
