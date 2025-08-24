package SchoolManagementApp.Service;

import SchoolManagementApp.DTO.StudentEntityDto;

import SchoolManagementApp.Entity.StudentEntity;
import SchoolManagementApp.Entity.UserEntity;
import SchoolManagementApp.Repository.StudentRepository;
import SchoolManagementApp.Repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    UserEntityRepository userEntityRepository;

    public StudentEntityDto getAllDetails(String username) {
        try {
            UserEntity user = userEntityRepository.findByUsername(username);

            if (user != null) {
                StudentEntity studentEntity = user.getStudentinformation();
                StudentEntityDto userdto = new StudentEntityDto();
                userdto.setId(studentEntity.getId());
                userdto.setUsername(user.getUsername());
                userdto.setFirstName(studentEntity.getFirstName());
                userdto.setLastName(studentEntity.getLastName());
                userdto.setEmail(studentEntity.getEmail());
                userdto.setPhone(studentEntity.getPhone());
                userdto.setDob(studentEntity.getDob());
                userdto.setGender(studentEntity.getGender());
                userdto.setAddress(studentEntity.getAddress());
                userdto.setEnrollmentDate(studentEntity.getEnrollmentDate());
                return userdto;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public void createProfile(StudentEntityDto profile, String username) {
        try {
            UserEntity user = userEntityRepository.findByUsername(username);

            if (user != null) {
                StudentEntity studentEntity = new StudentEntity();
                studentEntity.setFirstName(profile.getFirstName());
                studentEntity.setLastName(profile.getLastName());
                studentEntity.setEmail(profile.getEmail());
                studentEntity.setPhone(profile.getPhone());
                studentEntity.setDob(profile.getDob());
                studentEntity.setGender(profile.getGender());
                studentEntity.setAddress(profile.getAddress());
                studentEntity.setEnrollmentDate(LocalDate.now());

                studentEntity.setUser(user);

                StudentEntity save = studentRepository.save(studentEntity);
                user.setStudentinformation(save);
                userEntityRepository.save(user);


            } else {
                throw new RuntimeException("User not found: " + username);
            }

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
}
