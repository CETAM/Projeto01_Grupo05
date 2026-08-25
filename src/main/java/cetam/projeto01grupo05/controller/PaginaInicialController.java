package cetam.projeto01grupo05.controller;

import cetam.projeto01grupo05.service.LivroService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginaInicialController {

    private final LivroService livroService;

    public PaginaInicialController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping("/")
    public String paginaInicial(
            Model model,
            HttpSession session) {

        Object usuario = session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/login";
        }

        model.addAttribute("usuario", usuario);

        model.addAttribute(
                "livrosDestaque",
                livroService.listarTodos()
        );

        return "pagina_inicial";
    }
}