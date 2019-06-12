package com.timwheeler.hibernate.demo;

import com.timwheeler.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class UpdateStudentDemo {

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

            int studentId = 1;

            // get a new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            // retrieve the object from the db
            System.out.println("\nGetting student with ID: " + studentId + "...");
            Student myStudent = session.get(Student.class, studentId);

            System.out.println("Updating the student...");

            // update the students first name
            myStudent.setFirstName("Timothy");

            // commit the transaction
            session.getTransaction().commit();
            System.out.println("Success...");
            System.out.println(myStudent.toString());

            session = factory.getCurrentSession();
            session.beginTransaction();

            //update email for all students
            session.createQuery("update Student s set s.email = 'tim@timwheeler.com'").executeUpdate();
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
