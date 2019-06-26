package com.projectthree.preatlas.controllers;

import com.projectthree.preatlas.beans.Student;
import com.projectthree.preatlas.services.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {
    @Autowired
    IStudentService studentService;

    @GetMapping("/api/1.0/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        ResponseEntity<List<Student>> responseEntityListStudents = new ResponseEntity<List<Student>>(studentService.getAllStudents(), HttpStatus.OK);
//        if(responseEntityListStudents.getStatusCodeValue()==200){
//            return responseEntityListStudents;
//        } else if(responseEntityListStudents.getStatusCodeValue()==404){
//
//        }
        // if list is [] control the flow with 400
        return responseEntityListStudents;
    }

    @GetMapping("/api/1.0/students/{studentId}")
    public ResponseEntity<Student> getOneStudent(@PathVariable(value = "studentId") int studentId) {

//        Optional<Student> studentResponseEntity = studentService.getOneStudent(studentId);
//        System.out.println("studentResponseEntity: " + studentResponseEntity);
//        return studentResponseEntity.isPresent() ? new ResponseEntity<>(studentResponseEntity.get(), HttpStatus.OK) : ResponseEntity.notFound().build();

        return studentService.getOneStudent(studentId).isPresent()
                ? new ResponseEntity<>(studentService.getOneStudent(studentId).get(), HttpStatus.OK)
                : ResponseEntity.notFound().build();

//        Optional<Student> optionalStudent = studentService.getOneStudent(studentId);
//        ResponseEntity<Optional<Student>> optionalResponseEntity = optionalStudent;
//        return Option

//        ResponseEntity<Student> studentResponseEntity = null;
//        return Optional.of(studentResponseEntity).flatMap(studentResponseEntity1 -> {
//            studentResponseEntity1 = new ResponseEntity<>(studentService.getOneStudent(studentId).get(), HttpStatus.OK);
//        }).orElseGet();
    }

    @PostMapping("/api/1.0/students")
//    @RequestMapping(value = "/api/1.0/students", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> postOneStudent(@Validated @RequestBody Student student) {
        Student savedStudent = studentService.postOneStudent(student);
        if (savedStudent != null) {
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("{studentId}").buildAndExpand(savedStudent.getStudentId())
                    .toUri();
            return ResponseEntity.created(location).build();
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

//    @DeleteMapping("/api/1.0/students/{studentId}")
//    public ResponseEntity<Void> deleteOneStudent(@Valid @PathVariable(value = "studentId") Integer studentId) {
//        try {
//            int s = studentService.softStudentDelete(studentId);
//            if (s == 1) {
//                return ResponseEntity.ok().build();
//            } else {
//                return ResponseEntity.notFound().build();
//            }
//        } catch (Exception e) {
////            e.printStackTrace();
//            return ResponseEntity.notFound().build();
//        }
//    }

    @DeleteMapping("/api/1.0/students/{studentId}")
    public ResponseEntity<Void> deleteOneStundent(@Valid @PathVariable(value = "studentId") Integer studentId){
        try {
            studentService.softStudentDelete(studentId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

}
