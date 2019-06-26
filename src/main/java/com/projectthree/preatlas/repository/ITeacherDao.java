package com.projectthree.preatlas.repository;

import com.projectthree.preatlas.beans.Teacher;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface ITeacherDao extends CrudRepository<Teacher, Integer> {
    @Query(value = "SELECT * FROM teachers t WHERE t.teacher_status = 1", nativeQuery = true)
    List<Teacher> findAllTeachersByTeacherStatus();
    @Query(value = "UPDATE Teacher t SET t.teacherStatus = 0 WHERE t.teacherId=?1")
    @Modifying
    @Transactional
    void teacherSoftDelete(Integer teacherId);
}
