package ru.muhlaev.MySpringBoot2Dbase.service;

import org.springframework.stereotype.Service;
import ru.muhlaev.MySpringBoot2Dbase.entity.Student;

import java.util.List;

@Service
public interface StudentService {
    List<Student> getAllStudents();
    Student saveStudent(Student student);
    Student getStudent(int id);
    void deleteStudent(int id);
}
