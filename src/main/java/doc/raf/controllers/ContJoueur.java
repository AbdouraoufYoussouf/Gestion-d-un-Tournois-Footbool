package doc.raf.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import doc.raf.dao.EquipeRepository;
import doc.raf.dao.JoueurRepository;
import doc.raf.entities.Equipe;
import doc.raf.entities.Joueur;

import java.util.List;

@Controller
public class ContJoueur {
    @Autowired
    JoueurRepository joueRepo;
    @Autowired
    EquipeRepository equiRepo;

        /// RECUPERER LES JOUEUR 0

    @GetMapping("/joueur")
    public String AllJoueur(Model model,@RequestParam(name = "page",defaultValue = "0") int page,
                                        @RequestParam(name = "motCle",defaultValue = "") String mc){
        Page<Joueur> pagesJoueurs = joueRepo.findByNomJoueurContains(mc,PageRequest.of(page,6));
        model.addAttribute("lesJoueurs",pagesJoueurs.getContent());
        model.addAttribute("pages",new int[pagesJoueurs.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("motCle",mc);
        return "joueur";
    }

    ////////// SUPPRIMER UN JOUR PAR SON ID 0

    @GetMapping(value = "/delete")
    public String deleteJoueur(Long id, int page, String motCle) {
        joueRepo.deleteById(id);
        return "redirect:/joueur?page=" + page + "&motCle=" + motCle;
    }

    /// Ajouter un joueur

    @GetMapping(value = "/addJoueur")
    public String showFormJoueur(Model model){
        model.addAttribute("joueur",new Joueur());
        List<Equipe> equipeList = equiRepo.findAll();
        model.addAttribute("listEquipe",equipeList);
        return "addJoueur";
    }

    @PostMapping(value = "/regiterjoueur")
    public String saveJoueur( Joueur joueur){
        joueRepo.save(joueur);
        return "redirect:/joueur";
    }
////Modifier un joueur

    @GetMapping(value = "/editJoueur")
    public String showEditJoueur(Model model, Long id ){
        Joueur joueur = joueRepo.findById(id).get();
        model.addAttribute("joueur",joueur);

        List<Equipe> equipeList = equiRepo.findAll();
        model.addAttribute("lesEquipes",equipeList);
        return "joueurEdit";
    }


    @GetMapping("/joueurs/nomequipe/{equipe}")
    public List<Joueur> getAllJoueurEquipe(@PathVariable String equipe){
        return joueRepo.getJoueurByEquipes(equipe);
    }

    @GetMapping("/joueurs/poste/{poste}/equipe/{equipe}")
    public List<Joueur> getAllJoueurEquipe(@PathVariable String poste,@PathVariable String equipe){
        return joueRepo.getJoueurByEquipesAndPoste(poste,equipe);
    }
}

