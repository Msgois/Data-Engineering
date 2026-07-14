package CRUD;

import java.util.Date;

/* 
 * Classe que atua como Model/Entidade responsável pela abstração e transporte de dados (DTO/POJO) referentes a um Usuário do sistema.
 */
public class Usuario {
    /*
     * Atributos definidores que correspondem aos campos das tabelas ou chaves do
     * MongoDB: Cadastro de Pessoa Física (identificador principal), nome, data de
     * nascimento, e-mail, telefone e credenciais sistêmicas (login/senha).
     */
    private long cpf;
    private String nome;
    private Date dataNascimento;
    private String email;
    private String telefone;
    private String login;
    private String senha;

    // Construtores
    /*
     * Inicializador padrão sem parâmetros para flexibilizar a instanciação.[cite:
     * 9]
     */
    public Usuario() {
    }

    /*
     * Construtor que possibilita a injeção imediata de todos os estados do usuário
     * durante a criação do objeto.
     */
    public Usuario(long cpf, String nome, Date dataNascimento, String email, String telefone, String login,
            String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.telefone = telefone;
        this.login = login;
        this.senha = senha;
    }

    // Getters e Setters
    /*
     * Estruturas usuais de encapsulamento permitindo a manipulação e resgate dos
     * atributos resguardados de forma modularizada.
     */
    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}