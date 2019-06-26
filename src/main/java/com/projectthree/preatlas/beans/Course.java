package com.projectthree.preatlas.beans;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;
    @NotBlank(message = "Course name is required.")
    @Size(min = 3, max = 100, message = "Course name must be between 3 and 100 characters.")
    @Column(name = "course_name")
    private String courseName;
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    @NotBlank(message = "Course code is required.")
    @Size(min = 5, max = 8, message = "Course code must be between 5 and 8 characters.")
    @Column(name = "course_code")
    private String courseCode;
    @Column(name = "course_date_from")
    private Date courseDateFrom;
    @Column(name = "course_date_to")
    private Date courseDateTo;
    @Column(name = "course_status")
    private byte courseStatus;
    @JsonBackReference
    @OneToMany(mappedBy = "course")
    List<StudentCourse> studentCourses;
}
