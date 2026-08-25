package cetam.projeto01grupo05.controller;

import cetam.projeto01grupo05.model.Professor;
import cetam.projeto01grupo05.service.ProfessorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/professores")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("professores", professorService.listarTodos());
        return "professores";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("professor", new Professor());
        return "professor-form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Professor professor) {
        professorService.salvar(professor);
        return "redirect:/professores";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        professorService.buscarPorId(id).ifPresent(professor ->
                model.addAttribute("professor", professor)
        );

        return "professor-form";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        professorService.deletar(id);
        return "redirect:/professores";
    }
}