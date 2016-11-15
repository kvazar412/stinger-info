package org.dtmhapcs.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.dtmhapcs.model.embedded.VoteId;
import org.dtmhapcs.model.enums.VoteValue;

@Entity
@Table(name = "VOTES")
public class Vote implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private VoteId voteId;

    @ManyToOne
    @MapsId("filmId")
    private Film film;

    @ManyToOne
    @MapsId("userId")
    private User user;

    @Temporal(TemporalType.DATE)
    @Column(name = "VOTE_CHANGED")
    private Date voteChanged;

    @Enumerated(EnumType.STRING)
    @Column(name = "VOTE_VALUE")
    private VoteValue voteValue;

    public Vote() {
        super();
    }

    public Vote(VoteId voteId, Film film, User user,
            Date voteChanged, VoteValue voteCalue) {
        this.voteId = voteId;
        this.film = film;
        this.user = user;
        this.voteChanged = voteChanged;
        this.voteValue = voteCalue;
    }        

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("voteId", voteId)
                .append("film", film)
                .append("user", user)
                .append("voteChanged", voteChanged)
                .append("voteValue", voteValue)
                .toString();
    }    

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(voteId)
                .append(film)
                .append(user)
                .append(voteChanged)
                .append(voteValue)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;       
        if (obj.getClass() != getClass()) {
            return false;
        }
        Vote rhs = (Vote) obj;        
        return new EqualsBuilder()
                .append(voteId, rhs.voteId)
                .append(film, rhs.film)
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

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
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

    public VoteValue getVoteCalue() {
        return voteValue;
    }

    public void setVoteCalue(VoteValue voteCalue) {
        this.voteValue = voteCalue;
    }
}