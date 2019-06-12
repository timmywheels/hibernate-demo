package com.timwheeler.hibernate.demo;

import com.timwheeler.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {

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
            System.out.println("Creating object...");
            Student tempStudent = new Student("Elon", "Musk", "elon@tesla.com");
            // start a transaction
            session.beginTransaction();
            // save the student obj
            System.out.println("Saving the student object...");
            System.out.println(tempStudent);
            session.save(tempStudent);
            // commit the transaction
            session.getTransaction().commit();

            // retrieving the object from the db
            System.out.println("ID: " + tempStudent.getId());

            // get a new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            // retrieve student based on id (primary key)
            System.out.println("\nGetting student with ID: " + tempStudent.getId() + "...");
            Student myStudent = session.get(Student.class, tempStudent.getId());
            // commit the transaction
            session.getTransaction().commit();

            System.out.println("Success...");

            System.out.println(myStudent.toString());

        } finally {
            factory.close();
        }


    }

}
