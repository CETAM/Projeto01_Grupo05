package cetam.projeto01grupo05.controller;

import cetam.projeto01grupo05.model.Exemplar;
import cetam.projeto01grupo05.service.ExemplarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/exemplares")
public class ExemplarController {

    private final ExemplarService exemplarService;

    public ExemplarController(ExemplarService exemplarService) {
        this.exemplarService = exemplarService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("exemplares", exemplarService.listarTodos());
        return "exemplares";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("exemplar", new Exemplar());
        return "exemplares-form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Exemplar exemplar) {
        exemplarService.salvar(exemplar);
        return "redirect:/exemplares";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("exemplar", exemplarService.buscarPorId(id).orElseThrow());
        return "exemplares-form";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        exemplarService.deletar(id);
        return "redirect:/exemplares";
    }
}