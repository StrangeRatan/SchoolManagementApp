package SchoolManagementApp.Repository;

import SchoolManagementApp.Entity.EnrollmentEntity;
import SchoolManagementApp.Entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<EnrollmentEntity,Long> {

}
