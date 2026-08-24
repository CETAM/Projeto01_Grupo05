package cetam.projeto01grupo05.controller;

import cetam.projeto01grupo05.model.Emprestimo;
import cetam.projeto01grupo05.model.Usuario;
import cetam.projeto01grupo05.model.enums.TipoUsuario;
import cetam.projeto01grupo05.service.EmprestimoService;
import jakarta.servlet.http.HttpSession;
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
    public String listar(
            Model model,
            HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/login";
        }

        model.addAttribute("usuario", usuario);

        if (usuario.getTipoUsuario() == TipoUsuario.FUNCIONARIO) {
            model.addAttribute(
                    "emprestimos",
                    emprestimoService.listarTodos()
            );
        } else {
            model.addAttribute(
                    "emprestimos",
                    emprestimoService.listarPorUsuario(
                            usuario.getIdUsuario()
                    )
            );
        }

        return "emprestimos";
    }

    @GetMapping("/novo")
    public String novo(
            Model model,
            HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/login";
        }

        if (usuario.getTipoUsuario() != TipoUsuario.FUNCIONARIO) {
            return "redirect:/emprestimos";
        }

        model.addAttribute("usuario", usuario);
        model.addAttribute("emprestimo", new Emprestimo());

        return "emprestimo-form";
    }

    @PostMapping("/salvar")
    public String salvar(
            @RequestParam Long idUsuario,
            @RequestParam Long idExemplar,
            @RequestParam int dias,
            HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/login";
        }

        if (usuario.getTipoUsuario() != TipoUsuario.FUNCIONARIO) {
            return "redirect:/emprestimos";
        }

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
            Model model,
            HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/login";
        }

        model.addAttribute("usuario", usuario);

        model.addAttribute(
                "emprestimo",
                emprestimoService
                        .buscarPorId(id)
                        .orElseThrow()
        );

        return "emprestimo-detalhes";
    }
}