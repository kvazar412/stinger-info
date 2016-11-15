package org.dtmhapcs.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.dtmhapcs.model.enums.UserRole;

@Entity
@Table(name = "USERS")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "USER_ID")
    private String userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "USER_ROLE")
    private UserRole userRole;

    public User() {        
    }

    public User(String userId, UserRole userRole) {
        this.userId = userId;
        this.userRole = userRole;
    }    
    
    @Override
    public String toString() {
        return "User [userId=" + userId + ", userRole=" + userRole + "]";
    }   
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + userId.hashCode();
        result = prime * result + userRole.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (!userId.equals(other.userId))
            return false;
        if (userRole != other.userRole)
            return false;
        return true;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}