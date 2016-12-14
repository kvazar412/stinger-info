package org.dtmhapcs.model.services.db.dao;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.dtmhapcs.model.User;
import org.dtmhapcs.model.enums.UserRole;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserDaoImplTest {

    @Mock
    private SessionFactory sessionFactoryMock;

    @Mock
    private Session sessionMock;

    @Mock
    private Query<User> queryMock;

    @InjectMocks
    private UserDaoImpl userDao;

    private String userId = "123456";
    private User user = new User(userId, UserRole.USER);

    private List<User> userList = new ArrayList<User>();

    @Before
    public void setUp() {
        when(sessionFactoryMock.getCurrentSession()).thenReturn(sessionMock);
    }

    @Test
    public void testCreateOrUpdateUserDelegatesToHibernate() {
        userDao.createOrUpdateUser(user);

        verify(sessionMock).saveOrUpdate(user);
    }

    @Test
    public void testReadUserByIdDelegatesToHibernate() {
        userDao.readUserById(userId);

        verify(sessionMock).get(User.class, userId);
    }

    @Test
    public void testReadAllUsersDelegatesToHibernate() {
        when(sessionMock.createQuery(anyString())).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(userList);
        userDao.readAllUsers();

        verify(sessionMock).createQuery(anyString());
    }

    @Test
    public void testReadAllUserReturnsTheSameUserListAsHibernate() {
        userList.add(user);
        userList.add(new User("234567", UserRole.USER));

        when(sessionMock.createQuery(anyString())).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(userList);
        List<User> userListFromDao = userDao.readAllUsers();

        assertTrue(userListFromDao.size() == userList.size());
        assertTrue(userListFromDao.containsAll(userList));
    }

    @Test
    public void testReadAllUsersReturnesEmptyListForEmptyDbTable() {
        when(sessionMock.createQuery(anyString())).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(userList);
        List<User> userListFromDao = userDao.readAllUsers();

        assertTrue(userListFromDao.size() == userList.size());
        assertTrue(userListFromDao.containsAll(userList));
    }

    @Test
    public void testDeleteUserDelegatesToHibernateForExistingUser() {
        when(sessionMock.get(User.class, userId)).thenReturn(user);
        userDao.deleteUser(userId);

        verify(sessionMock).delete(user);
    }

    @Test
    public void testDeleteUserDoesNothingForNonExistingUser() {
        User otherUser = null;

        when(sessionMock.get(User.class, userId)).thenReturn(otherUser);
        userDao.deleteUser(userId);

        verify(sessionMock, never()).delete(otherUser);
    }
}