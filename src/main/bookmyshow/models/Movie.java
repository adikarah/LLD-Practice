package main.bookmyshow.models;

import java.util.Set;

public class Movie {

    private String movieName;
    private Set<Language> languages;
    private Set<String> starCast;
    private MovieCertificate movieCertificate;

    public Movie(String movieName, Set<Language> languages, Set<String> starCast, MovieCertificate movieCertificate) {
        this.movieName = movieName;
        this.languages = languages;
        this.starCast = starCast;
        this.movieCertificate = movieCertificate;
    }

    public String getMovieName() {
        return movieName;
    }

    public Set<Language> getLanguages() {
        return languages;
    }

    public Set<String> getStarCast() {
        return starCast;
    }

    public MovieCertificate getMovieCertificate() {
        return movieCertificate;
    }
}
