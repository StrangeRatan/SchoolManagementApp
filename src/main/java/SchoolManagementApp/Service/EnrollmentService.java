package SchoolManagementApp.Service;

import SchoolManagementApp.DTO.EnrollmentEntityDto;
import SchoolManagementApp.Entity.EnrollmentEntity;
import SchoolManagementApp.Entity.UserEntity;
import SchoolManagementApp.Repository.EnrollmentRepository;
import SchoolManagementApp.Repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EnrollmentService {

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Autowired
    UserEntityRepository userEntityRepository;

    public List<EnrollmentEntityDto> getAllEnrollment(String username){
        try{
            UserEntity user=userEntityRepository.findByUsername(username);
            List<EnrollmentEntityDto> allEnrollment=new ArrayList<>();

            if(user !=null){
                List<EnrollmentEntity> all=user.getEnrollment();
                for(EnrollmentEntity enrollment : all){
                    EnrollmentEntityDto entityDto=new EnrollmentEntityDto();
                    entityDto.setId(enrollment.getId());
                    entityDto.setCourse(enrollment.getCourse());
                    entityDto.setGrade(enrollment.getGrade());
                    entityDto.setEnrollmentDate(enrollment.getEnrollmentDate());
                    allEnrollment.add(entityDto);
                }
                return allEnrollment;
            }else {
                throw  new RuntimeException("User not foud :"+ username);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void enrollemtEntry(EnrollmentEntityDto enrollmentEntityDto, String username) {
        try {
            UserEntity user = userEntityRepository.findByUsername(username);
            if (user != null) {
                EnrollmentEntity newEnrollment=new EnrollmentEntity();
                newEnrollment.setCourse(enrollmentEntityDto.getCourse());
                newEnrollment.setGrade("A");
                newEnrollment.setEnrollmentDate(LocalDate.now());
                newEnrollment.setUser(user);
                EnrollmentEntity save = enrollmentRepository.save(newEnrollment);
                user.getEnrollment().add(save);
                userEntityRepository.save(user);
            }else {
                throw new RuntimeException("User Not found"+ username);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
