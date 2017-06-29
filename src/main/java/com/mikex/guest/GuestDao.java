package com.mikex.guest;
 
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
 
//@Component
@Repository
public class GuestDao {
    // Injected database connection. The persistence unit name must be same as the name in persistence.xml 
    @PersistenceContext(name="GuestbookPU") private EntityManager em;
 
    // Stores a new guest:
    @Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT)
    public void persist(Guest guest) {
        //em.getTransaction().begin();
        em.persist(guest);
        //em.getTransaction().commit();
    }
 
    // Retrieves all the guests:
    public List<Guest> getAllGuests() {
    	TypedQuery<Guest> query = em.createQuery(
            "SELECT g1 FROM Guest g1 ORDER BY g1.id", Guest.class);
    	return query.getResultList();
    }
}