import java.util.Date;

public class Vinculo {
    private int idVinculo;
    private String matricula; // FK para estudante (ligado à mat_estudante)
    private int idCurso; // FK para curso
    private Date dataEntrada;
    private String status; // Ex: 'Ativo', 'Trancado', etc.
    private Date dataSaida; // Pode ser nulo se o aluno ainda estiver cursando

    // Construtor vazio
    public Vinculo() {
    }

    // Construtor completo
    public Vinculo(int idVinculo, String matricula, int idCurso, Date dataEntrada, String status, Date dataSaida) {
        this.idVinculo = idVinculo;
        this.matricula = matricula;
        this.idCurso = idCurso;
        this.dataEntrada = dataEntrada;
        this.status = status;
        this.dataSaida = dataSaida;
    }

    // Getters e Setters
    public int getIdVinculo() {
        return idVinculo;
    }

    public void setIdVinculo(int idVinculo) {
        this.idVinculo = idVinculo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }
}