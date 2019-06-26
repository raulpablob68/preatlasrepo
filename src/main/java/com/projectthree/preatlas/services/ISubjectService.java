package com.projectthree.preatlas.services;

import com.projectthree.preatlas.beans.Subject;

import java.util.List;

public interface ISubjectService {
    public List<Subject> getAllSubjects();
    public Subject getOneSubject(Integer subjectId);
    public Subject postOneSubject(Subject subject);
}
