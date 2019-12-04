package edu.wctc.dao;

import edu.wctc.GroceryItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DAOImpl implements DAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<GroceryItem> getGroceryItem() {
        // Get current Hibernate session
        Session session = sessionFactory.getCurrentSession();

        // Get list of GroceryItems from query
        List<GroceryItem> GroceryItemList = session.createQuery("from GroceryItem ", GroceryItem.class).getResultList();

        // Return results
        return GroceryItemList;
    }

    @Override
    public void saveGroceryItem(GroceryItem theGroceryItem) {
        // Get current Hibernate session
        Session session = sessionFactory.getCurrentSession();

        // save/update the GroceryItem
        session.saveOrUpdate(theGroceryItem);
    }

    @Override
    public GroceryItem getGroceryItem(int theId) {
        // Get current Hibernate session
        Session session = sessionFactory.getCurrentSession();

        return session.get(GroceryItem.class, theId);
    }

    @Override
    public void deleteGroceryItem(int theId) {
        // Get current Hibernate session
        Session session = sessionFactory.getCurrentSession();

        // Delete object using primary key
        Query query = session.createQuery("delete from GroceryItem where id = :doomedGroceryItemId");
        // Set parameter value
        query.setParameter("doomedGroceryItemId", theId);

        // Perform the delete
        query.executeUpdate();
    }

    @Override
    public List<GroceryItem> getGroceryItemByName(String theSearchTerm) {
        // Get current Hibernate session
        Session session = sessionFactory.getCurrentSession();

        // Add wildcards and make search term lowercase (for case insensitivity)
        theSearchTerm = "%" + theSearchTerm.toLowerCase() + "%";

        Query<GroceryItem> query = session.createQuery("from GroceryItem where lower(itemName) like :nameToSearch");
        query.setParameter("nameToSearch", theSearchTerm);

        return query.getResultList();
    }
}
