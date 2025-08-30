package SchoolManagementApp;

import SchoolManagementApp.Repository.UserEntityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SchoolManagementAppApplicationTests {

    @Autowired
    UserEntityRepository userEntityRepository;

	@Test
	public  void testFindByUserName(){
        assertEquals(4,2+2);
        assertNotNull(userEntityRepository.findByUsername("Ratan"));

    }
}
