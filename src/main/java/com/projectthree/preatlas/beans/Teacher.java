package com.projectthree.preatlas.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int teacherId;
    @NotBlank(message = "First name is required.")
    @Size(min = 2,max = 100, message = "First name must be between 2 and 100 characters.")
    @Column(name = "teacher_first_name")
    private String teacherFirstName;
    @Nullable
    @Column(name = "teacher_middle_name")
    private String teacherMiddleName;
    @NotBlank(message = "Last name is required.")
    @Size(min = 2,max = 100, message = "Last name must be between 2 and 100 characters.")
    @Column(name = "teacher_last_name")
    private String teacherLastName;
    @Column(name = "teacher_status")
    private byte teacherStatus;
}
