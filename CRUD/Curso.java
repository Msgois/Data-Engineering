package CRUD;

/* 
 * Classe de modelo (Entity/Model) que representa um Curso no sistema. 
 * Contém os atributos privados da entidade e a estrutura básica de encapsulamento utilizando getters e setters.
 */
public class Curso {
    /*
     * Variáveis que representam as colunas/campos da tabela ou documento Curso,
     * como identificador, nome, grau acadêmico, turno de aulas, campus e
     * nível.
     */
    private int idCurso;
    private String nome;
    private String grau;
    private String turno;
    private String campus;
    private String nivel;

    // Construtor vazio
    /*
     * Construtor padrão sem argumentos para facilitar a instanciação e o mapeamento
     * de frameworks/DAOs.
     */
    public Curso() {
    }

    // Construtor completo
    /*
     * Construtor parametrizado para inicializar todos os atributos de uma só vez no
     * momento da criação do objeto.
     */
    public Curso(int idCurso, String nome, String grau, String turno, String campus, String nivel) {
        this.idCurso = idCurso;
        this.nome = nome;
        this.grau = grau;
        this.turno = turno;
        this.campus = campus;
        this.nivel = nivel;
    }

    // Getters e Setters
    /*
     * Conjunto de métodos de acesso (Getters) e modificação (Setters) para os
     * atributos privados de forma controlada.
     */
    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGrau() {
        return grau;
    }

    public void setGrau(String grau) {
        this.grau = grau;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}