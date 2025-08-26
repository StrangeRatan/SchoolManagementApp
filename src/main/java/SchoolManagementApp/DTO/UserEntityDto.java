package SchoolManagementApp.DTO;


import SchoolManagementApp.Entity.EnrollmentEntity;
import SchoolManagementApp.Entity.StudentEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;


public class UserEntityDto {

    private Long id;


    private String username;

    private String role;

    private StudentEntity studentinformation;

    public List<EnrollmentEntity> getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(List<EnrollmentEntity> enrollment) {
        this.enrollment = enrollment;
    }

    private List<EnrollmentEntity> enrollment = new ArrayList<>();


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public StudentEntity getStudentinformation() {
        return studentinformation;
    }

    public void setStudentinformation(StudentEntity studentinformation) {
        this.studentinformation = studentinformation;
    }

    public Long getId() {
        return id;


    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
