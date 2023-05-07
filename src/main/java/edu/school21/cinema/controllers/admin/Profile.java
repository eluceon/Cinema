package edu.school21.cinema.controllers.admin;

import edu.school21.cinema.models.Admin;
import edu.school21.cinema.models.Avatar;
import edu.school21.cinema.models.Movie;
import edu.school21.cinema.models.Poster;
import edu.school21.cinema.services.AuthenticationService;
import edu.school21.cinema.services.AvatarService;
import edu.school21.cinema.util.Utils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.UUID;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/profile")
@Slf4j
public class Profile {
    private final AvatarService avatarService;
    private final AuthenticationService authenticationService;
    private final String storagePath;

    private final ServletContext context;
    @GetMapping
    public ModelAndView getPage(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("/admin/profile");

        Admin user = (Admin) session.getAttribute("admin");

        modelAndView.addObject( "user", user);
        modelAndView.addObject("avatarHistory", avatarService.getAllByUserId(user.getId()));
        modelAndView.addObject("authHistory", authenticationService.getUserAuthHistory(user.getId()));

        return modelAndView;
    }

    @PostMapping
    public String addAvatar(@RequestParam(value = "file") MultipartFile avatarFile,
                           HttpSession session) {
        makeAvatar(avatarFile, (Admin) session.getAttribute("admin"));

        return "redirect:/admin/profile";
    }

    private void makeAvatar(MultipartFile file, Admin admin ) {
        UUID uuid = UUID.randomUUID();
        String relativeDirPath = storagePath  + File.separator + "ava" + File.separator + admin.getId();
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

        String uploadPathFile = uploadDir + File.separator + uuid;

        if (Utils.createFile(file, uploadPathFile)) {
            Avatar avatar = new Avatar(
                    uuid,
                    file.getOriginalFilename(),
                    relativeDirPath + File.separator + uuid,
                    file.getSize(),
                    file.getContentType(),
                    LocalDateTime.now(),
                    admin
            );
            avatarService.add(avatar);
        }
    }

}
