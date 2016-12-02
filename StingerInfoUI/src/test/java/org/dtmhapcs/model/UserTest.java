package org.dtmhapcs.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.dtmhapcs.model.enums.UserRole;
import org.dtmhapcs.model.interfaces.BaseModelTest;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserTest implements BaseModelTest{
    private static User user;
    
    @BeforeClass
    public static void initialize() {
        user = new User("123456", UserRole.USER);
    }
    
    @Test
    @Override    
    public void hashCodeForEqualsIsEqual() {
        User otherUser = new User("123456", UserRole.USER);
        assertTrue(user.hashCode() == otherUser.hashCode());
    }
    
    @Test
    @Override
    public void equalsForBoundaryConditions() {        
        assertFalse(user.equals(null));
        assertTrue(user.equals(user));
        assertFalse(user.equals(new Object()));
    }
    
    @Test
    @Override
    public void equalsWithDefaultModel() {
        User otherUser = new User();
        assertFalse(user.equals(otherUser));        
    }

    @Test
    @Override
    public void equalsWithOtherModel() {
        User otherUser = new User("234567", UserRole.USER);
        assertFalse(user.equals(otherUser));
    }

    @Test
    @Override
    public void equalsWithSameModel() {
        User otherUser = new User("123456", UserRole.USER);
        assertTrue(user.equals(otherUser));
    }

    @Test
    @Override
    public void toStringContainsId() {
        assertTrue(user.toString().contains("123456"));
    }
}