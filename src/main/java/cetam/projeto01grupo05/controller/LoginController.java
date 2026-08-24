package cetam.projeto01grupo05.controller;

import cetam.projeto01grupo05.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String entrar(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session) {

        return usuarioService.autenticar(username, password)
                .map(usuario -> {

                    session.setAttribute("usuarioLogado", usuario);

                    return "redirect:/";

                })
                .orElse("redirect:/login?error=true");
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/login?logout=true";
    }
}