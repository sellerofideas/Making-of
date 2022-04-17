package org.sellerofideas.makingof.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.sellerofideas.makingof.model.Perfil;
import org.sellerofideas.makingof.model.cookies.CookieService;
import org.sellerofideas.makingof.repositorio.PerfilRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PerfisController {

    @Autowired
    private PerfilRepo repo;

    @GetMapping("/cadastrar")
    public String cadastro() {
        return "perfil/cadastro";
    }

    @PostMapping("/criar")
    public String criar(Perfil perfil) {
        repo.save(perfil);
        return "redirect:/meuPerfil";
    }

    @GetMapping("/meuPerfil")
    public String perfil(Model model, HttpServletRequest request) throws UnsupportedEncodingException{
        model.addAttribute("nome", CookieService.getCookie(request, "nome"));       
        model.addAttribute("email", CookieService.getCookie(request, "email"));    
        model.addAttribute("bio", CookieService.getCookie(request, "bio"));    
        model.addAttribute("nomeArtistico", CookieService.getCookie(request, "nomeArtistico"));    
        return "perfil/meuPerfil";
    }

    @PostMapping("perfil/{id}/atualizar")
    public String atualizar(@PathVariable int id, Perfil perfil) {
        if (!repo.existsById(id)) {
            return "redirect:/meuPerfil";
        }
        repo.save(perfil);
        return "redirect:/meuPerfil";
    }

    @GetMapping("/perfil/{id}/excluir")
    public String excluir(@PathVariable int id){
      repo.deleteById(id);
      return "redirect:/listaPerfis";
    }

    @GetMapping("/listaPerfis")
    public String sarch(Model model){
        List<Perfil> administrador = (List<Perfil>)repo.findAll();
        model.addAttribute("administrador", administrador);        
        return "perfil/listaPerfis";
    }

}
