package org.dtmhapcs.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.dtmhapcs.model.enums.PcsInfo;

@Entity
@Table(name = "FILMS")
public class Film implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "FILM_ID")
    @NotNull
    @Size(min = 2, max = 255, message = "Film ID must be between 2 and 255 symbols")
    private String filmId;

    @Column(name = "FILM_NAME")
    @NotNull
    @Size(min = 2, max = 255, message = "Film name must be between 2 and 255 symbols")
    private String filmName;

    @Column(name = "FILM_YEAR")
    @NotNull
    @Digits(integer = 4, fraction = 0, message = "Film year must have only 4 digits")
    @Min(value = 1895, message = "Film year must be more than 1895")

    private int filmYear;

    @Column(name = "FILM_DIRECTOR")
    @NotNull
    @Pattern(regexp = "^[a-zA-Z,\\s]{2,70}$", message = "Film director must be between 2 and 70 symbols. Only a-z, A-Z, \",\", \" \" available")
    private String filmDirector;

    @Column(name = "FILM_COUNTRY")
    @NotNull
    @Pattern(regexp = "^[a-zA-Z,\\s]{2,70}$", message = "Film country must be between 2 and 70 symbols. Only a-z, A-Z, \",\", \" \" available")
    private String filmCountry;

    @Enumerated(EnumType.STRING)
    @Column(name = "PCS_INFO")
    @NotNull
    private PcsInfo pcsInfo;

    public Film() {
    }

    public Film(String filmId, String filmName, int filmYear, String filmDirector, String filmCountry, PcsInfo pcsInfo) {
        this.filmId = filmId;
        this.filmName = filmName;
        this.filmYear = filmYear;
        this.filmDirector = filmDirector;
        this.filmCountry = filmCountry;
        this.pcsInfo = pcsInfo;
    }

    @Override
    public String toString() {
        return "Film [filmId=" + filmId + ", filmName=" + filmName + "," + "filmYear=" + filmYear + ", filmDirector=" + filmDirector + "," + "filmCountry="
                + filmCountry + ", pcsInfo=" + pcsInfo + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + filmCountry.hashCode();
        result = prime * result + filmDirector.hashCode();
        result = prime * result + filmId.hashCode();
        result = prime * result + filmName.hashCode();
        result = prime * result + filmYear;
        result = prime * result + pcsInfo.hashCode();
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
        Film other = (Film) obj;
        if (!filmCountry.equals(other.filmCountry))
            return false;
        if (!filmDirector.equals(other.filmDirector))
            return false;
        if (!filmId.equals(other.filmId))
            return false;
        if (!filmName.equals(other.filmName))
            return false;
        if (filmYear != other.filmYear)
            return false;
        if (pcsInfo != other.pcsInfo)
            return false;
        return true;
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