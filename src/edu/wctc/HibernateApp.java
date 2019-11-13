package edu.wctc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateApp {
    private SessionFactory factory;

    public HibernateApp() {
        factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(GroceryItem.class)
                .buildSessionFactory();
    }

    public static void main(String[] args) {
        HibernateApp demo = new HibernateApp();

        try {

            // creating a grocery item.
            //GroceryItem item = new GroceryItem("Soda", "Ice cold cola", 4.99, "pepsi.jpg");
            //demo.createGroceryItem(item);

            // reading a grocery item.
            //demo.readGroceryItem(804);

            // updating a grocery item.
            //demo.updateGroceryItem(804);

            // deleting a grocery item.
            //demo.deleteGroceryItem(804);

        } finally {
            demo.close();
        }
    }

    private void close() {
        factory.close();
    }

    private int createGroceryItem(GroceryItem item) {
        Session session = factory.getCurrentSession();

        // Start a transaction
        session.beginTransaction();

        // Save the GroceryItem object
        session.save(item);

        // Commit the transaction
        session.getTransaction().commit();

        return item.getId();
    }

    private void readGroceryItem(int itemId) {
        Session session = factory.getCurrentSession();

        // Start a transaction, even for reads
        session.beginTransaction();

        // Retrieve GroceryItem based on primary key (ID)
        GroceryItem item = session.get(GroceryItem.class, itemId);

        // Object will be null if no match was found
        if (item == null) {
            System.out.println("Could not find the item with ID " + itemId);
        } else {
            System.out.println(item);
        }

        // Commit the transaction
        session.getTransaction().commit();
    }

    private void updateGroceryItem(int itemId) {
        Session session = factory.getCurrentSession();

        // Start a transaction
        session.beginTransaction();

        // Retrieve persistent object from database
        GroceryItem item = session.get(GroceryItem.class, itemId);

        // May have been null if primary key was not found
        if (item != null) {
            item.setItemName("Pepsi");
        }

        // Commit the transaction
        session.getTransaction().commit();
    }

    private void deleteGroceryItem(int itemId) {
        Session session = factory.getCurrentSession();

        // Start a transaction
        session.beginTransaction();

        // Retrieve the persistent object
        GroceryItem doomedItem = session.get(GroceryItem.class, itemId);

        if (doomedItem != null) {
            // Delete it
            session.delete(doomedItem);
        }

        // Commit the transaction
        session.getTransaction().commit();
    }
}