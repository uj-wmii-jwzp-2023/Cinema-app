package uj.wmii.jwzp.Cinemaapp.web;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WebPageController {

    @GetMapping("/images/{imageName:.+}")
    public ResponseEntity<Resource> serveImage(@PathVariable String imageName) {
        Resource resource = new ClassPathResource("static/" + imageName);

        if (resource.exists())
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(resource);
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/pages/{pageName}")
    public String showPage(@PathVariable String pageName) {
        return switch (pageName) {
            case "homePage" -> "homePage";
            case "accountDetails" -> "account";
            case "password" -> "password";
            case "movies" -> "movies";
            case "reservation" -> "reservation";
            case "filmPosters" -> "filmPosters";
            default -> "Index";
        };
    }
}
