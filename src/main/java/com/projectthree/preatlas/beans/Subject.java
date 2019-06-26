package com.projectthree.preatlas.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subjectId;
    @NotBlank(message = "Subject name is required.")
    @Size(min = 2,max = 100, message = "Subject name must be between 2 and 100 characters.")
    @Column(name = "subject_name")
    private String subjectName;
    @Column(name = "subject_status")
    private byte subjectStatus;
    @JsonIgnore
    @OneToMany(mappedBy = "subject")
    private List<Course> courseList;

    public Subject(int subjectId, String subjectName, byte subjectStatus) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.subjectStatus = subjectStatus;
    }
}
