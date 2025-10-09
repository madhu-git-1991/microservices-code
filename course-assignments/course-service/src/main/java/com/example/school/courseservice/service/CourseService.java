package com.example.school.courseservice.service;

import com.example.school.courseservice.model.Course;
import com.example.school.courseservice.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository repo;

    public CourseService(CourseRepository repo) {
        this.repo = repo;
    }

    public List<Course> getAll() { return repo.findAll(); }

    public Optional<Course> getById(Long id) { return repo.findById(id); }

    public Course create(Course course) {
        // simple uniqueness check by code
        if (repo.existsByCode(course.getCode())) {
            throw new IllegalArgumentException("Course code already exists: " + course.getCode());
        }
        return repo.save(course);
    }

    public Course update(Long id, Course updated) {
        return repo.findById(id).map(c -> {
            c.setCode(updated.getCode());
            c.setTitle(updated.getTitle());
            c.setDescription(updated.getDescription());
            c.setCredits(updated.getCredits());
            return repo.save(c);
        }).orElseThrow(() -> new IllegalArgumentException("Course not found: " + id));
    }

    public void delete(Long id) { repo.deleteById(id); }
}
