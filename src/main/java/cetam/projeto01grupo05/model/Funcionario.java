package cetam.projeto01grupo05.model;

import jakarta.persistence.*;

@Entity
@Table(name = "funcionario")
public class Funcionario {
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
