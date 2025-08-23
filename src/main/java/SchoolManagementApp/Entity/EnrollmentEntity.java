package SchoolManagementApp.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="enrollments")
public class EnrollmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    StudentEntity student;        // Many-to-One

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    CourseEntity course;          // Many-to-One

    LocalDate enrollmentDate;

    String grade;

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
