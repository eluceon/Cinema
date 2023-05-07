package edu.school21.cinema.util;

import edu.school21.cinema.models.MovieHall;
import edu.school21.cinema.services.MovieHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MovieHallValidator implements Validator {
    private final MovieHallService movieHallService;

    @Autowired
    public MovieHallValidator(MovieHallService movieHallService) {
        this.movieHallService = movieHallService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return MovieHall.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MovieHall movieHall = (MovieHall) target;
        if (movieHallService.findBySerialNumber(movieHall.getSerialNumber()) != null) {
            errors.rejectValue("serialNumber", "", "Such serial number already exists");
        }
    }
}
