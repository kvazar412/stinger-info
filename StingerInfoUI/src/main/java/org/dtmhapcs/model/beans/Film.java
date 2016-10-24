package org.dtmhapcs.model.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.dtmhapcs.model.beans.enums.PcsInfo;

@Entity
@Table(name = "FILMS")
public class Film implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "FILM_ID")
    private String filmId;

    @Column(name = "FILM_NAME")
    private String filmName;

    @Column(name = "FILM_YEAR")
    private int filmYear;

    @Column(name = "FILM_DIRECTOR")
    private String filmDirector;

    @Column(name = "FILM_COUNTRY")
    private String filmCountry;

    @Enumerated(EnumType.STRING)
    @Column(name = "PCS_IFNO")
    private PcsInfo pcsInfo;

    public Film() {
        super();
    }

    public Film(String filmId, String filmName, int filmYear, String filmDirector, String filmCountry, PcsInfo pcsInfo) {
        super();
        this.filmId = filmId;
        this.filmName = filmName;
        this.filmYear = filmYear;
        this.filmDirector = filmDirector;
        this.filmCountry = filmCountry;
        this.pcsInfo = pcsInfo;
    }

    public String getFilmId() {
        return filmId;
    }

    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public int getFilmYear() {
        return filmYear;
    }

    public void setFilmYear(int filmYear) {
        this.filmYear = filmYear;
    }

    public String getFilmDirector() {
        return filmDirector;
    }

    public void setFilmDirector(String filmDirector) {
        this.filmDirector = filmDirector;
    }

    public String getFilmCountry() {
        return filmCountry;
    }

    public void setFilmCountry(String filmCountry) {
        this.filmCountry = filmCountry;
    }

    public PcsInfo getPcsInfo() {
        return pcsInfo;
    }

    public void setPcsInfo(PcsInfo pcsInfo) {
        this.pcsInfo = pcsInfo;
    }
}