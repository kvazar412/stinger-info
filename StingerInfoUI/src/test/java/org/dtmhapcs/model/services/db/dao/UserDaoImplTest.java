package org.dtmhapcs.model.services.db.dao;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.dtmhapcs.model.User;
import org.dtmhapcs.model.enums.UserRole;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

    @InjectMocks
    private UserDaoImpl userDaoMock;

    private String userId = "123456";
    private User user = new User(userId, UserRole.USER);

    @Before
    public void setUp() {
        when(sessionFactoryMock.getCurrentSession()).thenReturn(sessionMock);
    }

    @Test
    public void createOrUpdateUserShouldDelegateToTheHibernateMethod() {
        userDaoMock.createOrUpdateUser(user);
        verify(sessionMock).saveOrUpdate(user);
    }

    @Test
    public void readUserByIdShouldDelegateToTheHibernateMethod() {
        userDaoMock.readUserById(userId);
        verify(sessionMock).get(User.class, userId);
    }

    @Test
    public void deleteUserShouldDelegateToTheHibernateMethodForExistingUser() {
        when(sessionMock.get(User.class, userId)).thenReturn(user);
        userDaoMock.deleteUser(userId);
        verify(sessionMock).delete(user);
    }

    @Test
    public void deleteUserShouldDoNothingForNonExistingUser() {
        User otherUser = null;
        when(sessionMock.get(User.class, userId)).thenReturn(otherUser);
        userDaoMock.deleteUser(userId);
        verify(sessionMock, never()).delete(otherUser);
    }
}