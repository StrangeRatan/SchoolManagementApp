package SchoolManagementApp.Repository;

import SchoolManagementApp.Entity.StudenMangementConfi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationRepository extends JpaRepository<StudenMangementConfi,Integer>{
}

