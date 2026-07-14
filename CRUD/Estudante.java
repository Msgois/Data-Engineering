package CRUD;

/* 
 * Classe de modelo (Entity/Model) responsável por representar o conceito de Estudante em escopo lógico da aplicação.
 */
public class Estudante {
    /*
     * Declaração de atributos que definem um estudante: id interno, número do CPF,
     * matrícula descritiva, MC (possivelmente média de curso ou similar) e ano de
     * entrada.
     */
    private int idEstudante;
    private long cpf;
    private String matricula;
    private double mc;
    private int anoIngresso;

    // Construtor vazio
    /* Inicialização padronizada vazia do estudante. */
    public Estudante() {
    }

    // Construtor completo
    /*
     * Inicialização do estudante onde o estado inicial é inteiramente provido de
     * forma parametrizada.
     */
    public Estudante(int idEstudante, long cpf, String matricula, double mc, int anoIngresso) {
        this.idEstudante = idEstudante;
        this.cpf = cpf;
        this.matricula = matricula;
        this.mc = mc;
        this.anoIngresso = anoIngresso;
    }

    // Getters e Setters
    /*
     * Declaração tradicional do encapsulamento dos campos internos permitindo obter
     * e registrar valores.
     */
    public int getIdEstudante() {
        return idEstudante;
    }

    public void setIdEstudante(int idEstudante) {
        this.idEstudante = idEstudante;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public double getMc() {
        return mc;
    }

    public void setMc(double mc) {
        this.mc = mc;
    }

    public int getAnoIngresso() {
        return anoIngresso;
    }

    public void setAnoIngresso(int anoIngresso) {
        this.anoIngresso = anoIngresso;
    }
}