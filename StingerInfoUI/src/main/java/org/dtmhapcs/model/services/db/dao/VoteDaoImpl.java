package org.dtmhapcs.model.services.db.dao;

import java.util.ArrayList;
import java.util.List;
import org.dtmhapcs.model.Vote;
import org.dtmhapcs.model.services.db.dao.interfaces.VoteDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository("voteDao")
public class VoteDaoImpl implements VoteDao {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createOrUpdate(Vote vote) {
        Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(vote);
    }

    @Override
    public List<Vote> readAllVotes() {
        List<Vote> voteList = new ArrayList<Vote>();
        Session session = this.sessionFactory.getCurrentSession();
        for (Object obj : session.createQuery("FROM Vote v").getResultList()) {
            voteList.add((Vote) obj);
        }
        return voteList;
    }

    @Override
    public void deleteVote(String movieId, String userId) {
        if (movieId != null && userId != null) {
            Session session = this.sessionFactory.getCurrentSession();
            Query<?> query = session.createQuery("DELETE FROM Vote v" + "WHERE v.voteId.movieId = :movieId AND v.voteId.userId = :userId");
            query.setParameter("movieId", movieId);
            query.setParameter("userId", userId);
            int result = query.executeUpdate();
            System.out.println("Rows deleted: " + result);
        }
    }
}