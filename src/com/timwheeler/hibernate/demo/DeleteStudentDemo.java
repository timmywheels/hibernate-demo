package com.timwheeler.hibernate.demo;

import com.timwheeler.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DeleteStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory =
                new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // get a new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            session.createQuery("delete from Student where id='4'").executeUpdate();

            // commit the transaction
            session.getTransaction().commit();
            System.out.println("Successfully deleted...");

            session = factory.getCurrentSession();
            session.beginTransaction();

            //update email for all students
            int studentId = 5;
            Student myStudent = session.get(Student.class, studentId);
            session.delete(myStudent);
            List<Student> students = session.createQuery("from Student").getResultList();

            displayStudents(students);

            // commit transaction
            session.getTransaction().commit();


        } finally {
            factory.close();
        }


    }

    private static void displayStudents(List<Student> students) {
        for (Student student : students) {
            System.out.println(student);
        }
    }

}
