package org.example.service;

import org.example.config.ComponentScanConfig;
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
public class StudentManagementTest {

    @Autowired
    StudentManagement testObject;

    Student newStudent;


    @BeforeEach
    public void setup(){
        Student student = new Student("John Wick");
        newStudent = testObject.save(student);
    }

    @Test
    public void find(){
        Student expected = new Student(newStudent.getId(), "Henry Wick");
        Student actual = testObject.find(newStudent.getId());
        assertNotEquals(expected,actual);
    }

    @Test
    public void findAll(){
        assertNotNull(testObject.findAll());
    }

    @Test
    public void remove(){
        assertNull(testObject.remove(2));
    }

    @Test
    public void findAll_element(){
        int expected = 1;
        int actual = testObject.findAll().size();
        assertSame(expected,actual);
    }
}
