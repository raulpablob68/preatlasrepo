package com.projectthree.preatlas.repository;

import com.projectthree.preatlas.beans.Subject;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ISubjectDao extends CrudRepository<Subject, Integer> {
    @Query(value = "SELECT * FROM subjects s WHERE s.subject_status = 1", nativeQuery = true)
    List<Subject> findAllSubjectsBySubjectStatus();
    @Query(value = "UPDATE Subject s SET s.subjectStatus = 0 WHERE s.subjectId=?1")
    @Modifying
    @Transactional
    void subjectSoftDelete(Integer subjectId);
}
