package org.dtmhapcs.model.services.db.dao.interfaces;

import java.util.List;

import org.dtmhapcs.model.Vote;

public interface VoteDao {
    void createOrUpdate(Vote vote);
    List<Vote> readAllVotes();
    void deleteVote(String movieId, String userId);
}