package com.timwheeler.hibernate.demo;

import com.timwheeler.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateStudentDemo {

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
            // create the student object
            System.out.println("Creating student object...");
            Student tempStudent = new Student("Tim", "Wheeler", "tim@timwheeler.com");
            // start a transaction
            session.beginTransaction();
            // save the student obj
            System.out.println("Saving the student object...");
            session.save(tempStudent);
            // commit the transaction
            session.getTransaction().commit();

            System.out.println("Success...");

        } finally {
            factory.close();
        }


    }

}
