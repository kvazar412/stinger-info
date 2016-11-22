package org.dtmhapcs.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.dtmhapcs.model.enums.VoteValue;

@Entity
@Table(name = "VOTES")
public class Vote implements Serializable {
    private static final long serialVersionUID = 1L;  

    @EmbeddedId
    private VoteId voteId;    

    @ManyToOne
    @JoinColumn (name = "movie_id", insertable = false, updatable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn (name = "user_id", insertable = false, updatable = false)
    private User user;

    @Temporal(TemporalType.DATE)
    @Column(name = "VOTE_CHANGED")    
    private Date voteChanged;

    @Enumerated(EnumType.STRING)
    @Column(name = "VOTE_VALUE")
    private VoteValue voteValue;
    
    @Embeddable
    public static class VoteId implements Serializable {
        private static final long serialVersionUID = 1L;        
        
        @Column(name = "movie_id")
        private String movieId;
        
        @Column(name = "user_id")
        private String userId; 
        
        public VoteId() {
        }

        public VoteId(String movieId, String userId) {
            this.movieId = movieId;
            this.userId = userId;
        }

        @Override
        public String toString() {            
            return new ToStringBuilder(this)
                    .append("movieId", movieId)
                    .append("userId", userId)
                    .toString();
        }

        @Override
        public int hashCode() {           
            return new HashCodeBuilder()
                    .append(movieId)
                    .append(userId)
                    .toHashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (obj == this) return true;       
            if (obj.getClass() != getClass()) return false;        
            VoteId rhs = (VoteId) obj;        
            return new EqualsBuilder()
                    .append(movieId, rhs.movieId)
                    .append(userId, rhs.userId)
                    .isEquals();
        }   
        
        public String getMovieId() {
            return movieId;
        }

        public void setMovieId(String movieId) {
            this.movieId = movieId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }

    public Vote() {
    }

    public Vote(VoteId voteId, Movie movie, User user, VoteValue voteValue) {
        this.voteId = voteId;
        this.movie = movie;
        this.user = user;
        this.voteChanged = new Date();
        this.voteValue = voteValue;        
    }        

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("voteId", voteId)
                .append("movie", movie)
                .append("user", user)
                .append("voteChanged", voteChanged)
                .append("voteValue", voteValue)
                .toString();
    }    

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(voteId)
                .append(movie)
                .append(user)
                .append(voteChanged)
                .append(voteValue)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;       
        if (obj.getClass() != getClass()) return false;        
        Vote rhs = (Vote) obj;        
        return new EqualsBuilder()
                .append(voteId, rhs.voteId)
                .append(movie, rhs.movie)
                .append(user, rhs.user)
                .append(voteChanged, rhs.voteChanged)
                .append(voteValue, rhs.voteValue)
                .isEquals();
    }

    public VoteId getVoteId() {
        return voteId;
    }

    public void setVoteId(VoteId voteId) {
        this.voteId = voteId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getVoteChanged() {
        return voteChanged;
    }

    public void setVoteChanged(Date voteChanged) {
        this.voteChanged = voteChanged;
    } 

    public VoteValue getVoteValue() {
        return voteValue;
    }

    public void setVoteCalue(VoteValue voteValue) {
        this.voteValue = voteValue;
    }
}