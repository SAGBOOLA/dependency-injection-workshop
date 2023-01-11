package org.example.data_access;

import org.example.data_access.sequencer.StudentIdSequencer;
import org.example.exception.DataNotFoundException;
import org.example.models.Student;
import org.example.util.UserInputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class StudentDaoListImpl implements StudentDao {

    private List<Student> students = new ArrayList<>();
    @Autowired
    UserInputService scannerService;


    @Override
    public Student save(Student student) {
        if (student == null) throw new IllegalArgumentException("the student data was null");
        if (student.getId() == 0) {
            student.setId(StudentIdSequencer.nextId());
            students.add(student);
        }else{
            System.out.println("ENTER NAME TO BE UPDATED:");
            String userInput = scannerService.getString();
            student.setName(userInput);
            //students.add(student);
        }
        return student;
    }

    @Override
    public Student find(int id) throws DataNotFoundException {
        return students.stream()
                .filter(student -> student.getId() == id)
                .findFirst()
                .orElseThrow(() -> new DataNotFoundException("student not found"));
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(students);
    }

    @Override
    public void delete(int id) throws DataNotFoundException {
       Student deleteStudent = find(id);
      students.remove(deleteStudent);
    }
}
