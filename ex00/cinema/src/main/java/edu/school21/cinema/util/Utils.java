package edu.school21.cinema.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
public class Utils {
    public static boolean createFile(MultipartFile file, String path) {
        try {
            byte[] barr = file.getBytes();
            BufferedOutputStream stream = new BufferedOutputStream((new FileOutputStream(path)));
            stream.write(barr);
            stream.flush();
            stream.close();
        } catch (IOException e) {
            log.error(e.getMessage());
            return false;
        }
        return true;
    }
}
