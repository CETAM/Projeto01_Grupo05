package cetam.projeto01grupo05.controller;

import cetam.projeto01grupo05.model.Emprestimo;
import cetam.projeto01grupo05.service.EmprestimoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/emprestimos")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("emprestimos", emprestimoService.listarTodos());
        return "emprestimos";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("emprestimo", new Emprestimo());
        return "emprestimo-form";
    }

    @PostMapping("/salvar")
    public String salvar(
            @RequestParam Long idUsuario,
            @RequestParam Long idExemplar,
            @RequestParam int dias) {

        emprestimoService.realizarEmprestimo(
                idUsuario,
                idExemplar,
                dias
        );

        return "redirect:/emprestimos";
    }

    @GetMapping("/{id}")
    public String buscarPorId(
            @PathVariable Long id,
            Model model) {

        model.addAttribute(
                "emprestimo",
                emprestimoService.buscarPorId(id).orElseThrow()
        );

        return "emprestimo-detalhes";
    }
}