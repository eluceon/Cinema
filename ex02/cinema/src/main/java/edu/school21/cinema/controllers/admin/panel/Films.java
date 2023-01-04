package edu.school21.cinema.controllers.admin.panel;

import edu.school21.cinema.models.Admin;
import edu.school21.cinema.models.Movie;
import edu.school21.cinema.models.Poster;
import edu.school21.cinema.services.MovieService;
import edu.school21.cinema.services.PosterService;
import edu.school21.cinema.util.MovieValidator;
import edu.school21.cinema.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/admin/panel/films")
@Slf4j
class Films {
    private final MovieService movieService;
    private final PosterService posterService;
    private final MovieValidator movieValidator;
    private final String storagePath;

    private final ServletContext context;

    @Autowired
    public Films(MovieService movieService, PosterService posterService,
                 MovieValidator movieValidator, String storagePath, ServletContext context) {
        this.movieService = movieService;
        this.posterService = posterService;
        this.movieValidator = movieValidator;
        this.storagePath = storagePath;
        this.context = context;
    }

    @GetMapping
    public String getMovies(Model model) {
        model.addAttribute("movies", movieService.getAll());
        model.addAttribute("movie", new Movie());
        return "/admin/panel/films";
    }

    @PostMapping
    public String addMovie(@ModelAttribute @Valid Movie movie, BindingResult result,
                           @RequestParam(value = "file") MultipartFile file,
                           HttpSession session) {
        movieValidator.validate(movie, result);
        if (result.hasErrors()) {
            return "/admin/panel/films";
        }

        Admin admin = (Admin) session.getAttribute("admin");
        movie.setAdmin(admin);

        if (!file.isEmpty()) {
            makePoster(file, movie, admin);
        }
        movieService.add(movie);
        return "redirect:/admin/panel/films";
    }

    private void makePoster(MultipartFile file, Movie movie, Admin admin ) {
        String relativeDirPath = storagePath  + File.separator + movie.getAdmin().getId();
        String uploadAbsoluteDirPath = context.getRealPath(relativeDirPath);
        // creates the save directory if it does not exist
        File uploadDir = new File(uploadAbsoluteDirPath);
        if (!uploadDir.exists()) {
            boolean success = uploadDir.mkdirs();
            if (!success) {
                log.error("Can't create directory");
            }
        }
        log.info("Upload File Directory = " + uploadDir.getAbsolutePath());

        String fileName = getFileName(file.getOriginalFilename());
        String uploadPathFile = uploadDir + File.separator + fileName;

        if (Utils.createFile(file, uploadPathFile)) {
            Poster poster = new Poster();
            poster.setName(file.getOriginalFilename());
            poster.setPath(relativeDirPath + File.separator + fileName);
            poster.setSize(file.getSize());
            poster.setMime(file.getContentType());
            poster.setAdmin(admin);
            posterService.add(poster);
            movie.setPoster(poster);
        }
    }

    private String getFileName(String name) {
        return new SimpleDateFormat("HHmmssddMMyyyyy").format(new Date()) + name;
    }


}
