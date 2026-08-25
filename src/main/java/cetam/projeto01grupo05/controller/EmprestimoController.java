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
        model.addAttribute("emprestimo", new Emprestimo());
        return "emprestimo-form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Emprestimo emprestimo) {
        emprestimoService.salvar(emprestimo);
        return "redirect:/emprestimos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("emprestimo", emprestimoService.buscarPorId(id).orElseThrow());
        model.addAttribute("emprestimos", emprestimoService.listarTodos());
        return "emprestimo-form";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        emprestimoService.deletar(id);
        return "redirect:/emprestimos";
    }
}