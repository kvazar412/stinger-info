package org.dtmhapcs.model.services.db.dao;

import java.util.ArrayList;
import java.util.List;

import org.dtmhapcs.model.User;
import org.dtmhapcs.model.services.db.dao.interfaces.UserDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    private SessionFactory sessionFactory;
    static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createOrUpdate(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
        if (LOGGER.isInfoEnabled()){
            LOGGER.info("User {} is saved or updated to DB", user); 
        }
    }

    @Override
    public User readUserById(String userId) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = session.get(User.class, userId);
        if (LOGGER.isInfoEnabled()){
            LOGGER.info("User {} is returned from DB", user); 
        }
        return user;
    }

    @Override
    public List<User> readAllUsers() {
        List<User> userList = new ArrayList<User>();
        Session session = this.sessionFactory.getCurrentSession();
        for (Object obj : session.createQuery("FROM User u").getResultList()) {
            userList.add((User) obj);
        }
        if (LOGGER.isInfoEnabled()){
            LOGGER.info("List of users are returned from DB");
        }
        return userList;
    }

    @Override
    public void deleteUser(String userId) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = session.get(User.class, userId);        
        if (user != null) {
            session.delete(user);
            if (LOGGER.isInfoEnabled()){
                LOGGER.info("User {} is deleted from DB", user); 
            }
        }
    }
}