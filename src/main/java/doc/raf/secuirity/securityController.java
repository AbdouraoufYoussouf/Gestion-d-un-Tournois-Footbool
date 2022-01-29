package doc.raf.secuirity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class securityController {
    @GetMapping(value = "/noAutoried")
    public String erreurPage(){
        return "erreurPage";
    }

    @GetMapping(value = {"/success"})
    public String accueil(@AuthenticationPrincipal OAuth2User user,Model model) {
        String nom = user.getAttribute("name");
        String email = user.getAttribute("email");
        String photo = user.getAttribute("picture");
        model.addAttribute("nom", nom);
        model.addAttribute("email", email);
        model.addAttribute("photo", photo);
        return "home";
    }
    @GetMapping(value = {"/home","/"})
    public String home() {
      
        return "home";
    }

}
