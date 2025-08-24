package SchoolManagementApp.DTO;

import SchoolManagementApp.Entity.CourseEntity;
import SchoolManagementApp.Entity.StudentEntity;


import java.time.LocalDate;


public class EnrollmentEntityDto {


    private Long id;


    private StudentEntity student;        // Many-to-One


    private CourseEntity course;          // Many-to-One

    private LocalDate enrollmentDate;

    private String grade;

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }

    public StudentEntity getStudent() {
        return student;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
