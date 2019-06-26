package com.projectthree.preatlas.beans;

import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

@Embeddable
@EqualsAndHashCode
public class StudentCourseKey implements Serializable {
    @Column(name = "student_id")
    int studentId;
    @Column(name = "course_id")
    int courseId;
    @Column(name = "date_from")
    Date dateFrom;
}
