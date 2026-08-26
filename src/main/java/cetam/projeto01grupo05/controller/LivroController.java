package cetam.projeto01grupo05.controller;

import cetam.projeto01grupo05.model.Livro;
import cetam.projeto01grupo05.model.Usuario;
import cetam.projeto01grupo05.service.CategoriaService;
import cetam.projeto01grupo05.service.LivroService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;
    private final CategoriaService categoriaService;

    public LivroController(LivroService livroService, CategoriaService categoriaService) {
        this.livroService = livroService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/{id}")
    public String verDetalhes(@PathVariable Long id, Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/login";
        }

        model.addAttribute("usuario", usuario);
        model.addAttribute("livro", livroService.buscarPorId(id).orElseThrow());

        return "livro-detalhes";
    }

    @GetMapping("/novo")
    public String novo(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/login";
        }

        if (!usuario.getTipoUsuario().toString().equals("FUNCIONARIO")) {
            return "redirect:/catalogo";
        }

        model.addAttribute("usuario", usuario);
        model.addAttribute("livro", new Livro());
        model.addAttribute("categorias", categoriaService.listarTodos());

        return "cadastro_livro";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Livro livro, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null || !usuario.getTipoUsuario().toString().equals("FUNCIONARIO")) {
            return "redirect:/login";
        }

        livroService.salvar(livro);

        return "redirect:/catalogo";
    }
}