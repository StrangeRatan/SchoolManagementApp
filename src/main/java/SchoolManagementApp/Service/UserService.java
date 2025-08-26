package SchoolManagementApp.Service;

import SchoolManagementApp.DTO.UserEntityDto;
import SchoolManagementApp.DTO.UserEntityDtoSignup;
import SchoolManagementApp.Entity.EnrollmentEntity;
import SchoolManagementApp.Entity.UserEntity;
import SchoolManagementApp.Repository.EnrollmentRepository;
import SchoolManagementApp.Repository.StudentRepository;
import SchoolManagementApp.Repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void createUser(UserEntityDtoSignup newUser) {
        try {
            UserEntityDtoSignup user = newUser;
            UserEntity adduser = new UserEntity();
            adduser.setUsername(user.getUsername());
            adduser.setPassword(passwordEncoder.encode(user.getPassword()));
            adduser.setRole("USER");
            userEntityRepository.save(adduser);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<UserEntityDto> getAllUsers() {
        List<UserEntity> all = userEntityRepository.findAll();
        List<UserEntityDto> alluser = new ArrayList<>();

        for (UserEntity user : all) {
            UserEntityDto userEntityDto = new UserEntityDto();
            userEntityDto.setId(user.getId());
            userEntityDto.setUsername(user.getUsername());
            userEntityDto.setRole(user.getRole());
            userEntityDto.setStudentinformation(user.getStudentinformation());
            userEntityDto.setEnrollment(user.getEnrollment());
            alluser.add(userEntityDto);
        }
        return alluser;
    }

    @Transactional
    public void deleteUser(String username) {
        UserEntity user = userEntityRepository.findByUsername(username);
        if (user != null) {
//            Long id1 = user.getStudentinformation().getId();
//            List<EnrollmentEntity> all=enrollmentRepository.findByStudentId(id1);
//            if(!all.isEmpty()){
//                enrollmentRepository.deleteAll(all);
            userEntityRepository.delete(user);
        }

//            studentRepository.deleteById(id1);
//
//            userEntityRepository.delete(user);


    }
}


