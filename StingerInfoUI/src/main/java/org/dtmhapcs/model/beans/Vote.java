package org.dtmhapcs.model.beans;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.dtmhapcs.model.beans.embeddedIds.VoteId;
import org.dtmhapcs.model.beans.enums.VoteValue;

@Entity
@Table(name = "VOTES")
public class Vote implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private VoteId voteId;

    @ManyToMany
    @MapsId("filmId")
    private Film film;

    @ManyToMany
    @MapsId("userId")
    private User user;

    @Column(name = "VOTE_CHANGED")
    private Date voteChanged;

    @Enumerated(EnumType.STRING)
    @Column(name = "VOTE_VALUE")
    private VoteValue voteCalue;

    public Vote() {
        super();
    }

    public Vote(VoteId voteId, Film film, User user, Date voteChanged, VoteValue voteCalue) {
        super();
        this.voteId = voteId;
        this.film = film;
        this.user = user;
        this.voteChanged = voteChanged;
        this.voteCalue = voteCalue;
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
        return voteCalue;
    }

    public void setVoteCalue(VoteValue voteCalue) {
        this.voteCalue = voteCalue;
    }
}