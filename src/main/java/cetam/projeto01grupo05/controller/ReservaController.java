package cetam.projeto01grupo05.controller;

import cetam.projeto01grupo05.model.Emprestimo;
import cetam.projeto01grupo05.model.Exemplar;
import cetam.projeto01grupo05.model.Reserva;
import cetam.projeto01grupo05.model.Usuario;
import cetam.projeto01grupo05.model.enums.StatusEmprestimo;
import cetam.projeto01grupo05.model.enums.StatusExemplar;
import cetam.projeto01grupo05.model.enums.StatusReserva;
import cetam.projeto01grupo05.repository.ExemplarRepository;
import cetam.projeto01grupo05.repository.ReservaRepository;
import cetam.projeto01grupo05.service.EmprestimoService;
import cetam.projeto01grupo05.service.ReservaService;
import cetam.projeto01grupo05.service.LivroService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;
    private final ReservaRepository reservaRepository;
    private final EmprestimoService emprestimoService;
    private final LivroService livroService;
    private final ExemplarRepository exemplarRepository;

    public ReservaController(ReservaService reservaService, ReservaRepository reservaRepository, EmprestimoService emprestimoService, LivroService livroService, ExemplarRepository exemplarRepository) {
        this.reservaService = reservaService;
        this.reservaRepository = reservaRepository;
        this.emprestimoService = emprestimoService;
        this.livroService = livroService;
        this.exemplarRepository = exemplarRepository;
    }

    @GetMapping
    public String listarReservas(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/login";
        }

        model.addAttribute("usuario", usuario);

        if (usuario.getTipoUsuario().toString().equals("FUNCIONARIO")) {
            model.addAttribute("reservas", reservaRepository.findAll());
        } else {
            model.addAttribute("reservas", reservaService.listarPorUsuario(usuario));
        }

        return "reserva-form";
    }

    @GetMapping("/novo/{idLivro}")
    public String solicitarReserva(@PathVariable Long idLivro, HttpSession session, RedirectAttributes redirectAttributes) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/login";
        }

        try {
            Reserva reserva = new Reserva();
            reserva.setUsuario(usuario);
            reserva.setLivro(livroService.buscarPorId(idLivro).orElseThrow(() -> new RuntimeException("Livro não encontrado")));
            reserva.setDataReserva(LocalDateTime.now());
            reserva.setStatus(StatusReserva.PENDENTE);

            reservaRepository.save(reserva);

            redirectAttributes.addFlashAttribute("mensagem", "Sua reserva foi solicitada com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Não foi possível realizar a reserva: " + e.getMessage());
        }

        return "redirect:/reservas";
    }

    @GetMapping("/{id}/cancelar")
    public String cancelarReserva(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            reservaRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("mensagem", "Reserva cancelada com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Não foi possível cancelar a reserva.");
        }
        return "redirect:/reservas";
    }

    @GetMapping("/{id}/atender")
    public String atenderReserva(@PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes) {
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

        if (usuarioLogado == null || !usuarioLogado.getTipoUsuario().toString().equals("FUNCIONARIO")) {
            return "redirect:/login";
        }

        try {
            Reserva reserva = reservaRepository.findById(id).orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

            // Busca um exemplar disponível para este livro usando o método real do ExemplarRepository
            List<Exemplar> exemplaresDisponiveis = exemplarRepository.findByLivroIdLivroAndStatus(reserva.getLivro().getIdLivro(), StatusExemplar.DISPONIVEL);

            if (exemplaresDisponiveis.isEmpty()) {
                throw new RuntimeException("Não há exemplares disponíveis deste livro para empréstimo no momento.");
            }

            Exemplar exemplarEscolhido = exemplaresDisponiveis.get(0);

            // Cria o empréstimo associando o usuário e o exemplar físico obrigatório
            Emprestimo emprestimo = new Emprestimo();
            emprestimo.setUsuario(reserva.getUsuario());
            emprestimo.setExemplar(exemplarEscolhido);
            emprestimo.setDataEmprestimo(LocalDateTime.now());
            emprestimo.setDataPrevisaoDevolucao(LocalDateTime.now().plusDays(7));
            emprestimo.setStatus(StatusEmprestimo.ATIVO);

            emprestimoService.salvar(emprestimo);

            // Atualiza o status do exemplar para emprestado/indisponível (se aplicável no seu enum)
            // exemplarEscolhido.setStatus(StatusExemplar.INDISPONIVEL);
            // exemplarRepository.save(exemplarEscolhido);

            // Atualiza a reserva para atendida
            reserva.setStatus(StatusReserva.ATENDIDA);
            reservaRepository.save(reserva);

            redirectAttributes.addFlashAttribute("mensagem", "Reserva atendida e convertida em empréstimo com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao atender reserva: " + e.getMessage());
        }

        return "redirect:/reservas";
    }
}