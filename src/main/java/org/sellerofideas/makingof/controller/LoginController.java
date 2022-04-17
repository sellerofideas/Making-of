package org.sellerofideas.makingof.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.sellerofideas.makingof.model.Perfil;
import org.sellerofideas.makingof.model.cookies.CookieService;
import org.sellerofideas.makingof.repositorio.PerfilRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private PerfilRepo repo;
    
    @GetMapping("/login")
    public String login() {
        return "login/index";
    }

    @PostMapping("/logar")
    public String Logar(Model model, Perfil admParam, String lembrar, HttpServletResponse response) throws IOException{
        Perfil adm = this.repo.Login(admParam.getEmail(), admParam.getSenha());
        if(adm != null){
          int tempoLogado = (60*60); // 1 hora de cookie
          if(lembrar != null) tempoLogado = (60*60*24*365); // 1 ano de cookie
          CookieService.setCookie(response, "Id", String.valueOf(adm.getId()), tempoLogado);
          CookieService.setCookie(response, "nome", String.valueOf(adm.getNome()), tempoLogado);
          CookieService.setCookie(response, "nomeArtistico", String.valueOf(adm.getNomeArtistico()), tempoLogado);
          CookieService.setCookie(response, "bio", String.valueOf(adm.getBio()), tempoLogado);
          CookieService.setCookie(response, "email", String.valueOf(adm.getEmail()), tempoLogado);
          return "redirect:/meuPerfil";
        }
        model.addAttribute("erro", "Usuário ou senha inválidos");
        return "login/index";
    }

    @GetMapping("/sair")
    public String logout(HttpServletResponse response) throws IOException{
    CookieService.setCookie(response, "usuarioId", "", 0);
    return "redirect:/login";
    }
}
