package org.example;

import org.example.config.ComponentScanConfig;
import org.example.data_access.StudentDao;
import org.example.exception.DataNotFoundException;
import org.example.models.Student;
import org.example.service.StudentManagement;
import org.example.util.UserInputService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App
{
    public static void main( String[] args ) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ComponentScanConfig.class);
        StudentDao studentDao = context.getBean(StudentDao.class);
        StudentManagement studentManagement = context.getBean(StudentManagement.class);
        UserInputService userInputService = context.getBean(UserInputService.class);

        Student esther = studentDao.save(new Student("ESTHER"));
        Student lubna = studentDao.save(new Student("Lubna"));

        System.out.println(studentDao.findAll());

        System.out.println("---------------------");

        try {
            Student studentById = studentDao.find(2);
            System.out.println(studentById);
        } catch (DataNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("---------------------");

        try {
            studentDao.delete(2);
        } catch (DataNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println(studentDao.findAll());

        System.out.println("---------------------");

        Student student = studentManagement.create();
        System.out.println(student);
        System.out.println("---------------------");
        studentManagement.save(student);
        System.out.println("---------------------");
        System.out.println(studentManagement.findAll());
        System.out.println("---------------------");
        System.out.println(studentManagement.find(0));
        System.out.println("---------------------");
        studentManagement.remove(1);
        System.out.println(studentManagement.findAll());
        /*System.out.println("---------------------");
        System.out.println(studentManagement.edit(student));
        System.out.println("---------------------");
        System.out.println(studentManagement.findAll());*/

    }

}
