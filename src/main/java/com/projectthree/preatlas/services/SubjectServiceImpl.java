package com.projectthree.preatlas.services;

import com.projectthree.preatlas.beans.Subject;
import com.projectthree.preatlas.repository.ISubjectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImpl implements ISubjectService {
    @Autowired
    private ISubjectDao subjectDao;
    @Override
    public List<Subject> getAllSubjects() {
        List<Subject> allValidSubjectList = subjectDao.findAllSubjectsBySubjectStatus();
        Optional<List<Subject>> optionalAllValidSubjectList = Optional.of(allValidSubjectList);
        return optionalAllValidSubjectList.get();
    }

    @Override
    public Subject getOneSubject(Integer subjectId) {
        int id = subjectId==null ? 0 : subjectId;
        return subjectDao.findById(id).get();
    }

    @Override
    public Subject postOneSubject(Subject subject) {
        subject.setSubjectStatus((byte)1);
        subjectDao.save(subject);
        if(subjectDao.findById(subject.getSubjectId())!=null){
            return subject;
        }
        return null;
    }
}
