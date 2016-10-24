package org.dtmhapcs.model.beans.embeddedIds;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class VoteId implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String filmId;
    protected String userId;
}