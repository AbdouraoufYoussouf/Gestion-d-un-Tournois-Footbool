package doc.raf.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import doc.raf.dao.ArbitreRepository;
import doc.raf.entities.Arbitre;

import java.util.List;

@Controller
public class ContArbitre {
    @Autowired
    ArbitreRepository arbiRepory;

    @PostMapping("/arbitres")
    public Arbitre saveArbitre(@RequestBody Arbitre arbitre){
        return arbiRepory.save(arbitre);
    }
 //////////////// Afficher tous les arbitres  ////////////

    @GetMapping("/arbitre")
    public String getAllArbitre(Model model){
        List<Arbitre> arbitres = arbiRepory.findAll();
        model.addAttribute("lesArbitres",arbitres);
        return "arbitre";
    }

    //////////////// Fin Afficher tous les arbitres ////////////

    //////////////// Ajouter un arbitres ////////////

    @GetMapping(value = "addArbitre")
    public String showAddArbitre(Model model){
        model.addAttribute("arbitre",new Arbitre());
        return "arbitreAdd";
    }

    @PostMapping(value = "registerArbitre")
    public String saveArbitre(Arbitre arbitre ,Long id){
        arbiRepory.save(arbitre);
        return "redirect:/arbitre";
    }

    //////////////// Fin Ajout arbitres ////////////

    //////////////// Delete arbitres ////////////

    @GetMapping(value = "deleteArbitre")
    public String deleteArbitre(Long id){
        arbiRepory.deleteById(id);
        return "redirect:/arbitre";
    }

    //////////////// Fin Delete arbitres ////////////

    //////////////// Edit arbitres ////////////

    @GetMapping(value = "editArbitre")
    public String showEditArbitre(Model model,Long id){
        Arbitre arbitre = arbiRepory.findById(id).get();
        model.addAttribute("arbitre",arbitre);
        return "arbitreEdit";
    }

    //////////////// Fin Edit arbitres ////////////

    @PutMapping("/arbitres/{idArbitre}")
    public ResponseEntity<Arbitre> updateArbitre(@RequestBody Arbitre arbitre, @PathVariable Long idArbitre){
        Arbitre arbitre1 = arbiRepory.findById(idArbitre).orElseThrow(()->new RuntimeException("Arbitre inexistant"));
        arbitre1.setNomArbitre(arbitre.getNomArbitre());
        arbitre1.setNationaliteArbitre(arbitre.getNationaliteArbitre());
        final Arbitre updateArbitre = arbiRepory.save(arbitre1);
        return ResponseEntity.ok(updateArbitre);
    }

    @DeleteMapping("/arbitre/delete/{idArbitre}")
    public void deletEquipe(@PathVariable Long idArbitre){
        arbiRepory.deleteById(idArbitre);
    }
}
