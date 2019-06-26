package com.projectthree.preatlas.controllers;

import com.projectthree.preatlas.beans.Subject;
import com.projectthree.preatlas.services.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class SubjectController {
    @Autowired
    private ISubjectService subjectService;

    @GetMapping("/api/1.0/subjects")
    public ResponseEntity<List<Subject>> getAllSubject(){
        return new ResponseEntity<>(subjectService.getAllSubjects(), HttpStatus.OK);
    }

    @GetMapping("/api/1.0/subjects/{subjectId}")
    public ResponseEntity<Subject> getOneSubject(@PathVariable(value = "subjectId")Integer subjectId){
        return new ResponseEntity<Subject>(subjectService.getOneSubject(subjectId), HttpStatus.OK);
    }

    @PostMapping("/api/1.0/subjects")
    public ResponseEntity<Object> postOneSubject(@Validated @RequestBody Subject subject){
        Subject savedSubject = subjectService.postOneSubject(subject);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{subjectId}").buildAndExpand(savedSubject.getSubjectId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
