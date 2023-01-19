package org.example.data_access;

import org.example.config.ComponentScanConfig;
import org.example.exception.DataNotFoundException;
import org.example.models.Student;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;



@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ComponentScanConfig.class)
public class StudentDaoTest {

    @Autowired
    StudentDao testObject;

    Student createdStudent;

    @BeforeEach
    public void setup(){
        Student student = new Student("Stephen Frank");
        createdStudent = testObject.save(student);
    }

    @Test
    public void find_id(){
        Student expected = new Student(createdStudent.getId(), "Stephen Frank");
        try {
            Student actual = testObject.find(createdStudent.getId());
            assertEquals(expected,actual);
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findId(){
        assertThrows(DataNotFoundException.class, () -> testObject.find(4));
    }

    @Test
    public void find_all(){
        int expected = 1;
        int actual = testObject.findAll().size();
        assertSame(expected,actual);
    }

    @Test
    public void delete_id(){
        assertDoesNotThrow(() -> testObject.delete(createdStudent.getId()));
    }

}
