package SchoolManagementApp.Repository;

import SchoolManagementApp.Entity.EnrollmentEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<EnrollmentEntity,Long> {

    List<EnrollmentEntity> findByUser_Id(Long studentId);
}
