package cetam.projeto01grupo05.controller;

import cetam.projeto01grupo05.service.CategoriaService;
import cetam.projeto01grupo05.service.LivroService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/catalogo")
public class CatalogoController {

    private final LivroService livroService;
    private final CategoriaService categoriaService;

    public CatalogoController(LivroService livroService, CategoriaService categoriaService) {
        this.livroService = livroService;
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public String abrirCatalogo(Model model, HttpSession session) {

        Object usuario = session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/login";
        }

        model.addAttribute("usuario", usuario);
        model.addAttribute("livros", livroService.listarTodos());
        model.addAttribute("categorias", categoriaService.listarTodos());

        return "catalogo";
    }
}