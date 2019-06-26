package com.projectthree.preatlas.repository;

import com.projectthree.preatlas.beans.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface IStudentDao extends CrudRepository<Student, Integer> {
    @Query(value = "SELECT * FROM students s WHERE s.student_status = 1", nativeQuery = true)
    List<Student> findAllStudentsByStudentStatus();

//    @Query(value = "SELECT s FROM students s WHERE s.student_status = 1 AND s.student_id=?1", nativeQuery = true)
    @Query(value = "SELECT s FROM Student s WHERE s.studentStatus = 1 AND s.studentId=?1")
    Optional<Student> findOneStudentValidStudentByStudentId(Integer student_id);

    @Query(value = "SELECT s FROM Student s WHERE s.studentId=?1")
    Optional<Student> findByStudentId(Integer student_id);

//    @Query(value = "UPDATE Student s SET s.studentStatus = 0 WHERE s.studentId=?1")
//    @Modifying
//    @Transactional
//    int studentSoftDelete(Integer studentId);

    @Query(value = "UPDATE Student s SET s.studentStatus = 0 WHERE s.studentId=?1")
    @Modifying
    @Transactional
    void studentSoftDelete(Integer studentId);
}
