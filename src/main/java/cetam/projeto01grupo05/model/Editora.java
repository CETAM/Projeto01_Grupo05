package cetam.projeto01grupo05.model;

import jakarta.persistence.*;

@Entity
@Table(name = "editora")
public class Editora {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_editora;
    private String nome;
    private String endereco;
    private String telefone;
    private String email;

    public Editora() {}

    public Long getId_editora() {
        return id_editora;
    }

    public void setId_editora(Long id_editora) {
        this.id_editora = id_editora;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
