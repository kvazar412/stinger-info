package org.dtmhapcs.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.dtmhapcs.model.enums.UserRole;

import org.junit.Test;

public class UserTest {
    private User user = new User("123456", UserRole.USER);

    @Test
    public void hashCodeShouldBeEqualForEquals() {
        User otherUser = new User("123456", UserRole.USER);
        assertTrue(user.hashCode() == otherUser.hashCode());
    }

    @Test
    public void equalsForBoundaryConditions() {
        assertFalse(user.equals(null));
        assertTrue(user.equals(user));
        assertFalse(user.equals(new Object()));
    }

    @Test
    public void shouldNotBeEqualWithDefaultUser() {
        User otherUser = new User();
        assertFalse(user.equals(otherUser));
    }

    @Test
    public void shouldNotBeEqualWithNonEqualUser() {
        User otherUser = new User("234567", UserRole.USER);
        assertFalse(user.equals(otherUser));
    }

    @Test
    public void shouldBeEqualWithEqualMovie() {
        User otherUser = new User("123456", UserRole.USER);
        assertTrue(user.equals(otherUser));
    }

    @Test
    public void toStringShouldContainUserId() {
        assertTrue(user.toString().contains("123456"));
    }
}