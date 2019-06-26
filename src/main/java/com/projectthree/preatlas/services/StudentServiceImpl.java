package com.projectthree.preatlas.services;

import com.projectthree.preatlas.beans.Student;
import com.projectthree.preatlas.repository.IStudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements IStudentService {
    @Autowired
    private IStudentDao studentDao;

    @Override
    public List<Student> getAllStudents() {
        List<Student> allValidStudentList = studentDao.findAllStudentsByStudentStatus();
        Optional<List<Student>> optionalAllValidStudentList = Optional.of(allValidStudentList);
        return optionalAllValidStudentList.get();
    }

    @Override
    public Optional<Student> getOneStudent(Integer studentId) {
        int id = studentId==null ? 0 : studentId;
        return studentDao.findByStudentId(id);
    }

    @Override
    public Student postOneStudent(Student student) {
        student.setStudentStatus((byte)1);
        studentDao.save(student);
        if(studentDao.findById(student.getStudentId()) != null) {
            return student;
        } else {
            return null;
        }
//        return null;
    }

    @Override
    public void softStudentDelete(Integer studentId) {
        int id = studentId==null ? 0 : studentId;
        studentDao.findOneStudentValidStudentByStudentId(id).ifPresent(student ->studentDao.studentSoftDelete(student.getStudentId()));
    }

//    @Override
//    public int softStudentDelete(Integer studentId) {
//        int id = studentId==null ? 0 : studentId;
//        AtomicInteger r = new AtomicInteger();
//        studentDao.findOneStudentValidStudentByStudentId(id).ifPresent(student ->{studentDao.studentSoftDelete(student.getStudentId());
//        System.out.println("Se realizó el softStudentDelete correctamente. id: " + id);
//            r.set(1);
//        });
//        System.out.println("r = " + r.get());
//        return r.get();
//    }


}
