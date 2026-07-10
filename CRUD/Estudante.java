package CRUD;

public class Estudante {
    private int idEstudante;
    private long cpf;
    private String matricula;
    private double mc;
    private int anoIngresso;

    // Construtor vazio
    public Estudante() {
    }

    // Construtor completo
    public Estudante(int idEstudante, long cpf, String matricula, double mc, int anoIngresso) {
        this.idEstudante = idEstudante;
        this.cpf = cpf;
        this.matricula = matricula;
        this.mc = mc;
        this.anoIngresso = anoIngresso;
    }

    // Getters e Setters
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