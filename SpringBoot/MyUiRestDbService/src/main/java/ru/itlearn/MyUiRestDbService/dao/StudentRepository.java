package ru.itlearn.MyUiRestDbService.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itlearn.MyUiRestDbService.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>
//Временно изменил с Integer на long чтобы заработало  Optional<Student> optionalStudent = studentRepository.findById(studentId);
{


}
