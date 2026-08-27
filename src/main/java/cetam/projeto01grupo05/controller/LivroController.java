package cetam.projeto01grupo05.controller;

import cetam.projeto01grupo05.model.Livro;
import cetam.projeto01grupo05.model.Usuario;
import cetam.projeto01grupo05.service.CategoriaService;
import cetam.projeto01grupo05.service.EditoraService;
import cetam.projeto01grupo05.service.LivroService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;
    private final CategoriaService categoriaService;
    private final EditoraService editoraService;

    public LivroController(LivroService livroService, CategoriaService categoriaService, EditoraService editoraService) {
        this.livroService = livroService;
        this.categoriaService = categoriaService;
        this.editoraService = editoraService;
    }

    @GetMapping("/catalogo")
    public String listarCatalogo(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/login";
        }

        List<Livro> livros = livroService.listarTodos();
        Map<Long, Integer> estoques = new HashMap<>();

        for (Livro livro : livros) {
            estoques.put(livro.getIdLivro(), livroService.contarExemplaresDisponiveis(livro.getIdLivro()));
        }

        model.addAttribute("usuario", usuario);
        model.addAttribute("livros", livros);
        model.addAttribute("estoques", estoques);

        return "catalogo";
    }

    @GetMapping("/{id}")
    public String verDetalhes(@PathVariable Long id, Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/login";
        }

        Livro livro = livroService.buscarPorId(id).orElseThrow();
        int qtdDisponivel = livroService.contarExemplaresDisponiveis(id);

        model.addAttribute("usuario", usuario);
        model.addAttribute("livro", livro);
        model.addAttribute("quantidadeDisponivel", qtdDisponivel);

        return "livro-detalhes";
    }

    @GetMapping("/novo")
    public String novo(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/login";
        }

        if (!usuario.getTipoUsuario().toString().equals("FUNCIONARIO")) {
            return "redirect:/livros/catalogo";
        }

        model.addAttribute("usuario", usuario);
        model.addAttribute("livro", new Livro());
        model.addAttribute("categorias", categoriaService.listarTodos());
        model.addAttribute("editoras", editoraService.listarTodos());

        return "livro-form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Livro livro, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null || !usuario.getTipoUsuario().toString().equals("FUNCIONARIO")) {
            return "redirect:/login";
        }

        livroService.salvar(livro);

        return "redirect:/livros/catalogo";
    }
}