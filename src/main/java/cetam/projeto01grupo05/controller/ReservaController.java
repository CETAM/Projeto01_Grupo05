package cetam.projeto01grupo05.controller;

import cetam.projeto01grupo05.model.Livro;
import cetam.projeto01grupo05.model.Reserva;
import cetam.projeto01grupo05.model.Usuario;
import cetam.projeto01grupo05.service.LivroService;
import cetam.projeto01grupo05.service.ReservaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;
    private final LivroService livroService;

    public ReservaController(ReservaService reservaService, LivroService livroService) {
        this.reservaService = reservaService;
        this.livroService = livroService;
    }

    @GetMapping
    public String listar(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/login";
        }

        model.addAttribute("usuario", usuario);

        if (usuario.getTipoUsuario().toString().equals("FUNCIONARIO")) {
            model.addAttribute("reservas", reservaService.listarTodos());
        } else {
            model.addAttribute("reservas", reservaService.listarPorUsuario(usuario));
        }

        return "reserva-form";
    }

    @GetMapping("/nova")
    public String novaReserva(@RequestParam Long idLivro, HttpSession session, RedirectAttributes redirectAttributes) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/login";
        }

        Optional<Livro> livroOpt = livroService.buscarPorId(idLivro);

        if (livroOpt.isPresent()) {
            reservaService.criarReserva(usuario, livroOpt.get());
            redirectAttributes.addFlashAttribute("mensagem", "Sua reserva foi solicitada com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("erro", "Livro não encontrado para reserva.");
        }

        return "redirect:/reservas";
    }

    @GetMapping("/{id}/cancelar")
    public String cancelar(@PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/login";
        }

        reservaService.deletar(id);
        redirectAttributes.addFlashAttribute("mensagem", "Reserva cancelada com sucesso!");

        return "redirect:/reservas";
    }
}