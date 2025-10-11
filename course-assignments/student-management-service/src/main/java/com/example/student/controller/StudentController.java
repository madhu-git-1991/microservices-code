package com.example.student.controller;

import com.example.student.entity.Student;
import com.example.student.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService svc;

    public StudentController(StudentService svc) { this.svc = svc; }

    @GetMapping
    public List<Student> list() { return svc.listAll(); }

    @GetMapping("/{id}")
    public Student get(@PathVariable (name = "id") Long id) { return svc.getById(id); }

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student s) {
        Student created = svc.create(s);
        return ResponseEntity.created(URI.create("/api/students/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable (name = "id") Long id, @RequestBody Student s) { return svc.update(id, s); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable (name = "id") Long id) {
        svc.delete(id);
        return ResponseEntity.noContent().build();
    }
}
