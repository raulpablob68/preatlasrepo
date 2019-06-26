package com.projectthree.preatlas.services;

import com.projectthree.preatlas.beans.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentService {
    public List<Student> getAllStudents();
    public Optional<Student> getOneStudent(Integer studentId);
    public Student postOneStudent(Student student);
//    public int softStudentDelete(Integer studentId);
    public void softStudentDelete(Integer studentId);
}
