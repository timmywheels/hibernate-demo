package com.timwheeler.hibernate.demo;

import com.timwheeler.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {

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
            // create 3 student objects
            System.out.println("Creating student objects...");
            Student tempStudent1 = new Student("Tim", "Wheeler", "tim@timwheeler.com");
            Student tempStudent2 = new Student("Andrea", "Beland", "andrearosebeland@gmail.com");
            Student tempStudent3 = new Student("Lisa", "Curro", "lmcstyle@aol.com");

            // start a transaction
            session.beginTransaction();
            // save the student obj
            System.out.println("Saving the student object...");
            session.save(tempStudent1);
            session.save(tempStudent2);
            session.save(tempStudent3);
            // commit the transaction
            session.getTransaction().commit();

            System.out.println("Success...");

        } finally {
            factory.close();
        }

    }

}
