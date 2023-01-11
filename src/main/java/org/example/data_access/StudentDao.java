package org.example.data_access;

import org.example.exception.DataNotFoundException;
import org.example.models.Student;

import java.util.List;
import java.util.Optional;

public interface StudentDao {
    Student save(Student student);
    Student find(int id) throws DataNotFoundException;
    List<Student> findAll();
    void delete(int id) throws DataNotFoundException;
}
