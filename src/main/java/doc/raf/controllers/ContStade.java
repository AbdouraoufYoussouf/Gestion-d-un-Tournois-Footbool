package doc.raf.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import doc.raf.dao.StadeRepository;
import doc.raf.entities.Stade;

import java.util.List;

@Controller
public class ContStade {
    @Autowired
    StadeRepository stadRepo;

    // @PostMapping("stades")
    // public Stade saveStade(@RequestBody State stade){
    //     return stadRepo.save(stade);
    // }

    @GetMapping("/stade")
    public String getAllEquipe(Model model){
        List<Stade> stades = stadRepo.findAll();
        model.addAttribute("lesStades",stades);
        return "stade";
    }

    ///////// Add stade /////////

    @GetMapping(value = "addStade")
    public String showAddStade(Model model){
        model.addAttribute("stade", new Stade());

        return "stadeAdd";
    }

    @PostMapping(value = "registerStade")
    public String saveStade(Stade stade){
        stadRepo.save(stade);
        return "redirect:/stade";
    }

    ///////// fin add stade /////////

    ///////// delet  stade /////////

    @GetMapping(value = "deleteStade")
    public String deleteStade(Long id){
        stadRepo.deleteById(id);
        return "redirect:/stade";
    }

    ///////// Fin delete  stade /////////

    ///////// Modifier un  stade /////////

    @GetMapping(value = "editStade")
    public String showEditStade(Model model,Long id){
        Stade stade = stadRepo.findById(id).get();
        model.addAttribute("stade", stade);
        return "stadeEdit";
    }

    ///////// Fin Modification d'un  stade /////////




    @GetMapping("/stades")
    public List<Stade> getEquipe(){
        return stadRepo.findAll();
    }

    @DeleteMapping("/stades/{idStade}")
    public void deletMatch(@PathVariable Long idStade){
        stadRepo.deleteById(idStade);
    }

    @PutMapping("/stades/{idStade}")
    public ResponseEntity<Stade> updateStade(@RequestBody Stade stade,@PathVariable Long idStade){
        Stade stade1 = stadRepo.findById(idStade).orElseThrow(()->new RuntimeException("Le stade n'existe pas"));
        stade1.setNomStade(stade.getNomStade());
        stade1.setVilleStade(stade.getVilleStade());
        final Stade updateStade = stadRepo.save(stade1);
        return ResponseEntity.ok(updateStade);
    }

    @GetMapping("/stades/match/{idMatch}")
    public Stade getStadeByIdMatch(@PathVariable Long idMatch){
        return stadRepo.getByMatchId(idMatch);
    }
}
