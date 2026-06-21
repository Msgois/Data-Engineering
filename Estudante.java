public class Estudante {
    private int idEstudante;
    private long cpfUsuario; // Chave estrangeira que liga com o Usuario
    private String matricula;

    // Construtor vazio
    public Estudante() {}

    // Construtor completo
    public Estudante(int idEstudante, long cpfUsuario, String matricula) {
        this.idEstudante = idEstudante;
        this.cpfUsuario = cpfUsuario;
        this.matricula = matricula;
    }

    // Getters e Setters
    public int getIdEstudante() { return idEstudante; }
    public void setIdEstudante(int idEstudante) { this.idEstudante = idEstudante; }

    public long getCpfUsuario() { return cpfUsuario; }
    public void setCpfUsuario(long cpfUsuario) { this.cpfUsuario = cpfUsuario; }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
}
