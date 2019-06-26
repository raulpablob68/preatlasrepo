package com.projectthree.preatlas.repository;

import com.projectthree.preatlas.beans.Course;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface ICourseDao extends CrudRepository<Course, Integer> {
    @Query(value = "SELECT * FROM courses c WHERE c.course_status = 1", nativeQuery = true)
    List<Course> findAllCoursesByCourseStatus();

    @Query(value = "UPDATE Course c SET c.courseStatus = 0 WHERE c.courseId=?1")
    @Modifying
    @Transactional
    void courseSoftDelete(Integer courseId);
}
