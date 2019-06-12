package com.timwheeler.hibernate.demo;

import com.timwheeler.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {

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
            // start a transaction
            session.beginTransaction();

            // query students
            List<Student> theStudents = session.createQuery("from Student").getResultList();
            // display students
            displayStudents(theStudents);

            theStudents = session.createQuery("from Student s where s.lastName = 'Wheeler' or s.lastName = 'Beland'").getResultList();
            displayStudents(theStudents);

            theStudents = session.createQuery("from Student s where s.email like '%wheeler%'").getResultList();
            displayStudents(theStudents);
            // commit the transaction
            session.getTransaction().commit();

            System.out.println("Success...");

        } finally {
            factory.close();
        }


    }

    private static void displayStudents(List<Student> theStudents) {
        for (Student tempStudent : theStudents) {
            System.out.println(tempStudent);
        }
    }

}
