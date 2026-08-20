package cetam.projeto01grupo05.model;

import jakarta.persistence.*;

@Entity
@Table(name = "funcionario")
@PrimaryKeyJoinColumn(name = "id_usuario")
public class Funcionario extends Usuario {

    @Column(name = "cargo", nullable = false, length = 50)
    private String cargo;

    public Funcionario() {
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}