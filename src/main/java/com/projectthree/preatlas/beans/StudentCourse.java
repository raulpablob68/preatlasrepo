package com.projectthree.preatlas.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Student_Courses")
public class StudentCourse {
    @EmbeddedId
    private StudentCourseKey id;
    @ManyToOne
    @MapsId("student_id")
    @JoinColumn(name = "student_id")
    private Student student;
    @ManyToOne
    @MapsId("course_id")
    @JoinColumn(name = "course_id")
    private Course course;
    private Date dateTo;
}
