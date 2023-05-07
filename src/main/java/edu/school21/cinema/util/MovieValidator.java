package edu.school21.cinema.util;

import edu.school21.cinema.models.Movie;
import edu.school21.cinema.models.MovieHall;
import edu.school21.cinema.services.MovieHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.Year;

@Component
public class MovieValidator implements Validator {

    @Autowired
    public MovieValidator(MovieHallService movieHallService) {
        this.movieHallService = movieHallService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return MovieHall.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Movie movie = (Movie) target;
        if (movie != null && movie.getYearOfRelease() != null && movie.getYearOfRelease() > Year.now().getValue()) {
            errors.rejectValue("yearOfRelease", "", "Year of release can't be greater current year");
        }
    }
}
