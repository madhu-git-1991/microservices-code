package com.example.student.service;

import com.example.student.entity.Fee;
import com.example.student.entity.Student;
import com.example.student.exception.NotFoundException;
import com.example.student.repository.FeeRepository;
import com.example.student.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeeService {
    private final FeeRepository feeRepo;
    private final StudentRepository studentRepo;

    public FeeService(FeeRepository feeRepo, StudentRepository studentRepo) {
        this.feeRepo = feeRepo;
        this.studentRepo = studentRepo;
    }

    public Fee create(Long studentId, Fee fee) {
        Student s = studentRepo.findById(studentId).orElseThrow(() -> new NotFoundException("Student not found: " + studentId));
        fee.setStudent(s);
        return feeRepo.save(fee);
    }

    public List<Fee> getByStudent(Long studentId) {
        return feeRepo.findByStudentId(studentId);
    }
}
