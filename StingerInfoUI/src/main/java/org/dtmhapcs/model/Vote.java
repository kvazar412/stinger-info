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

import org.dtmhapcs.model.embeddedIds.VoteId;
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
        return "Vote [film=" + film + ", user=" + user
                + ", voteChanged=" + voteChanged + ", voteCalue=" + voteValue + "]";
    }    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + film.hashCode();
        result = prime * result + user.hashCode();
        result = prime * result + voteValue.hashCode();
        result = prime * result + voteChanged.hashCode();
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
        Vote other = (Vote) obj;
        if (!film.equals(other.film))
            return false;
        if (!user.equals(other.user))
            return false;
        if (voteValue != other.voteValue)
            return false;
        if (!voteChanged.equals(other.voteChanged))
            return false;
        return true;
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