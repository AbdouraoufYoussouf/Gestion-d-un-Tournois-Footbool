package doc.raf.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import doc.raf.dao.UserRepositoey;
import doc.raf.entities.User;
import doc.raf.service.UserService;

@Controller
public class ContUser {
    @Autowired 
    UserRepositoey userRepo;

    @Autowired
    UserService userService;

    @GetMapping(value = "/user")
    public String gettAllEquipe(Model model) {
        List<User> users = userRepo.findAll();
        model.addAttribute("lesUsers", users);
        return "user";
    }
    //////////////// Add User ////////

    @GetMapping(value = "/addUser")
    public String showAddUser(Model model) {
        model.addAttribute("user", new User());
        return "userAdd";
    }

    @PostMapping("/registerUser")
    public String saveUser(final @Valid User user, final BindingResult bindingResult, final Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("user",user);
            return "userAdd";
        }
        
        if (userService.checkIfUserExist(user.getUsername())) {
            model.addAttribute("user", user);
            model.addAttribute("exist", "Cet Email exist déjà! Choisissez un autre");
            return "userAdd";
        }
        String encoded = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encoded);
        userRepo.save(user);
        model.addAttribute("succes", "Votre compte a bien été enregistrer avec succès, vous voupez vous connecter");
        return "userAdd";
    }

    ///////////////// Fin Add User ////////

    /// ######## SUPPRIMER UN user PAR SON ID 0

    @GetMapping(value = "/deleteUser")
    public String deleteById(Long id) {
        userRepo.deleteById(id);
        return "redirect:/user";
    }

    /// ######## Edit UN user PAR SON ID 0

    @GetMapping(value = "/editUser")
    public String showEditEquipe(Model model, Long id) {
        User user = userRepo.findById(id).get();
        model.addAttribute("user", user);
        return "userEdit";
    }

    @GetMapping(value = "/userLogin")
    public String loginUser(Model model) {
        model.addAttribute("google", "oauth2/authorization/google");
        model.addAttribute("facebook", "oauth2/authorization/facebook");
        model.addAttribute("linkedin", "oauth2/authorization/linkedin");
        return "userLogin";
    }

    @GetMapping(value = "/userRegister")
    public String registerNewUser(Model model) {
        model.addAttribute("user",new User());
        return "userRegister";
    }

    

   @PostMapping(value = "/userSave")
   public String saveNewUser(final @Valid User user, final BindingResult bindingResult,final Model model)  {
       if (bindingResult.hasErrors()) {
           model.addAttribute("user", user);
           return "userRegister";
       }

       if(userService.checkIfUserExist(user.getUsername())){
            model.addAttribute("user", user);
            model.addAttribute("exist", "Votre Email exist déjà! Choisissez un autre");
           return "userRegister";
       }
       userService.register(user);
       model.addAttribute("succes", "Votre compte a bien été enregistrer avec succès, vous voupez vous connecter");     
       return "redirect:/userLogin                                                                                                                                                                                                                          ";
   }
}
