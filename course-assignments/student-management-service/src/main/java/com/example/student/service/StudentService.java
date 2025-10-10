package com.example.student.service;

import com.example.student.entity.Student;
import com.example.student.exception.NotFoundException;
import com.example.student.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public Student create(Student s) {
        return repo.save(s);
    }

    public Student getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Student not found: " + id));
    }

    public List<Student> listAll() {
        return repo.findAll();
    }

    public Student update(Long id, Student updated) {
        Student s = getById(id);
        s.setFirstName(updated.getFirstName());
        s.setLastName(updated.getLastName());
        s.setEmail(updated.getEmail());
        s.setDob(updated.getDob());
        return repo.save(s);
    }

    public void delete(Long id) {
        Student s = getById(id);
        repo.delete(s);
    }
}
