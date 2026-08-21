package cetam.projeto01grupo05.repository;

import cetam.projeto01grupo05.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    Aluno findByMatricula(String matricula);
}