package SchoolManagementApp.Service;

import SchoolManagementApp.DTO.UserEntityDto;
import SchoolManagementApp.DTO.UserEntityDtoSignup;
import SchoolManagementApp.Entity.UserEntity;
import SchoolManagementApp.Repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserEntityRepository userEntityRepository;

    public void createUser(UserEntityDtoSignup newUser){
        try{
            UserEntityDtoSignup user=newUser;
            UserEntity adduser=new UserEntity();
            adduser.setUsername(user.getUsername());
            adduser.setPassword(user.getPassword());
            adduser.setRole("USER");
            userEntityRepository.save(adduser);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<UserEntityDto> getAllUsers(){
        List<UserEntity> all=userEntityRepository.findAll();
        List<UserEntityDto> alluser=new ArrayList<>();

        for(UserEntity user: all){
            UserEntityDto userEntityDto=new UserEntityDto();
            userEntityDto.setId(user.getId());
            userEntityDto.setUsername(user.getUsername());
            userEntityDto.setRole(user.getRole());
            userEntityDto.setStudentinformation(user.getStudentinformation());
            userEntityDto.setEnrollment(user.getEnrollment());
            alluser.add(userEntityDto);
        }
        return alluser;
    }

}
