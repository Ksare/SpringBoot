package ru.muhlaev.MySpringBoot2Dbase.dao;


import org.springframework.stereotype.Repository;
import java.util.List;
import ru.muhlaev.MySpringBoot2Dbase.entity.Student;
@Repository
public interface StudentDAO {
    List<Student> getAllStudents();

    Student saveStudent(Student student);

    Student getStudent(int id);

    void deleteStudent(int id);

}
