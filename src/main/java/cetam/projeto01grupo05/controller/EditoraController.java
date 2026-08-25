package cetam.projeto01grupo05.controller;

import cetam.projeto01grupo05.model.Editora;
import cetam.projeto01grupo05.service.EditoraService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/editoras")
public class EditoraController {

    private final EditoraService editoraService;

    public EditoraController(EditoraService editoraService) {
        this.editoraService = editoraService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("editoras", editoraService.listarTodos());
        return "editoras";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("editora", new Editora());
        return "editoras-form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Editora editora) {
        editoraService.salvar(editora);
        return "redirect:/editoras";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("editora", editoraService.buscarPorId(id).orElseThrow());
        return "editoras-form";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        editoraService.deletar(id);
        return "redirect:/editoras";
    }
}