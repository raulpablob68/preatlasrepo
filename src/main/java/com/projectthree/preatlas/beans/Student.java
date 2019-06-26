package com.projectthree.preatlas.beans;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;
    @Column(name = "student_gender")
    private char studentGender;
    @NotBlank(message = "First name is required.")
    @Size(min = 2,max = 100, message = "First name must be between 2 and 100 characters.")
    @Pattern(regexp = "^[A-Za-z]+$", message = "First name must have only letters.")
    @Column(name = "student_first_name")
    private String studentFirstName;
    @Nullable
    @Size(min = 2,max = 100, message = "Middle name must be between 2 and 100 characters.")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Middle name must have only letters.")
    @Column(name = "student_middleName")
    private String studentMiddleName;
    @NotBlank(message = "Last name is required.")
    @Size(min = 2,max = 100, message = "Last name must be between 2 and 100 characters.")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Last name must have only letters.")
    @Column(name = "student_last_name")
    private String studentLastName;
    @Column(name = "student_date_of_birth")
    private Date studentDateOfBirth;
    @Column(name = "student_status")
    private byte studentStatus;
//    @JsonManagedReference
    @JsonBackReference
    @OneToMany(mappedBy = "student")
    List<StudentCourse> studentCourses;

    public Student(int studentId, char studentGender, String studentFirstName, String studentMiddleName, String studentLastName, byte studentStatus) {
        this.studentId = studentId;
        this.studentGender = studentGender;
        this.studentFirstName = studentFirstName;
        this.studentMiddleName = studentMiddleName;
        this.studentLastName = studentLastName;
        this.studentStatus = studentStatus;
    }

    public Student(int studentId, char studentGender, String studentFirstName, String studentMiddleName, String studentLastName, Date studentDateOfBirth, byte studentStatus) {
        this.studentId = studentId;
        this.studentGender = studentGender;
        this.studentFirstName = studentFirstName;
        this.studentMiddleName = studentMiddleName;
        this.studentLastName = studentLastName;
        this.studentDateOfBirth = studentDateOfBirth;
        this.studentStatus = studentStatus;
    }
}
