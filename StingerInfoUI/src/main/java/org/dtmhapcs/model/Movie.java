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
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.dtmhapcs.model.enums.PcsInfo;

@Entity
@Table(name = "MOVIES")
public class Movie implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "MOVIE_ID")
    @NotNull
    @Size(min = 2, max = 255, message = "Movie ID must be between 2 and 255 symbols")
    private String movieId;

    @Column(name = "MOVIE_TITLE")
    @NotNull
    @Size(min = 2, max = 255, message = "Movie name must be between 2 and 255 symbols")
    private String movieTitle;

    @Column(name = "MOVIE_YEAR")
    @NotNull
    @Digits(integer = 4, fraction = 0, message = "Movie year must have only 4 digits")
    @Min(value = 1895, message = "Movie year must be more than 1895")
    private int movieYear;

    @Column(name = "MOVIE_DIRECTOR")
    @NotNull
    @Pattern(regexp = "^[a-zA-Z,\\s]{2,70}$", message = "Movie director must be between 2 and 70 symbols. Only a-z, A-Z, \",\", \" \" available")
    private String movieDirector;

    @Column(name = "MOVIE_COUNTRY")
    @NotNull
    @Pattern(regexp = "^[a-zA-Z,\\s]{2,70}$", message = "Movie country must be between 2 and 70 symbols. Only a-z, A-Z, \",\", \" \" available")
    private String movieCountry;

    @Enumerated(EnumType.STRING)
    @Column(name = "PCS_INFO")
    @NotNull
    private PcsInfo pcsInfo;

    public Movie() {
    }

    public Movie(String movieId, String movieTitle, int movieYear, String movieDirector, String movieCountry, PcsInfo pcsInfo) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.movieYear = movieYear;
        this.movieDirector = movieDirector;
        this.movieCountry = movieCountry;
        this.pcsInfo = pcsInfo;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(movieId)
                .append(movieTitle)
                .append(movieYear)
                .append(movieDirector)
                .append(movieCountry)
                .append(pcsInfo)
                .toHashCode();
    }
    
    @Override
    public boolean equals (Object obj){
        if (obj == null) return false;
        if (obj == this) return true;       
        if (obj.getClass() != getClass()) return false;        
        Movie rhs = (Movie) obj;        
        return new EqualsBuilder()
                .append(movieId, rhs.movieId)
                .append(movieTitle, rhs.movieTitle)
                .append(movieYear, rhs.movieYear)
                .append(movieDirector, rhs.movieDirector)
                .append(movieCountry, rhs.movieCountry)
                .append(pcsInfo, rhs.pcsInfo)
                .isEquals();                
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("movieId", movieId)
                .append("movieTitle", movieTitle)
                .append("movieYear", movieYear)
                .append("movieDirector", movieDirector)
                .append("movieCountry", movieCountry)
                .append("pcsInfo", pcsInfo)
                .toString();
    }    

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieName) {
        this.movieTitle = movieName;
    }

    public int getMovieYear() {
        return movieYear;
    }

    public void setMovieYear(int movieYear) {
        this.movieYear = movieYear;
    }

    public String getMovieDirector() {
        return movieDirector;
    }

    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }

    public String getMovieCountry() {
        return movieCountry;
    }

    public void setMovieCountry(String movieCountry) {
        this.movieCountry = movieCountry;
    }

    public PcsInfo getPcsInfo() {
        return pcsInfo;
    }

    public void setPcsInfo(PcsInfo pcsInfo) {
        this.pcsInfo = pcsInfo;
    }
}
