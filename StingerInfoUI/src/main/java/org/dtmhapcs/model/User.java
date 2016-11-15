package org.dtmhapcs.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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
        return new ToStringBuilder(this)
                .append("userId", userId)
                .append("userRole", userRole)
                .toString();
    }  
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(userId)
                .append(userRole)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;       
        if (obj.getClass() != getClass()) {
            return false;            
        }
        User rhs = (User) obj;
        return new EqualsBuilder()
                .append(userId, rhs.userId)
                .append(userRole, rhs.userRole)
                .isEquals();
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