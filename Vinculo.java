public class Vinculo {
    private int idVinculo;
    private int idEstudante; // FK para estudante
    private int idCurso;     // FK para curso
    private String status;   // Padrão no banco é 'Ativo'

    public Vinculo() {}

    public Vinculo(int idVinculo, int idEstudante, int idCurso, String status) {
        this.idVinculo = idVinculo;
        this.idEstudante = idEstudante;
        this.idCurso = idCurso;
        this.status = status;
    }

    public int getIdVinculo() { return idVinculo; }
    public void setIdVinculo(int idVinculo) { this.idVinculo = idVinculo; }

    public int getIdEstudante() { return idEstudante; }
    public void setIdEstudante(int idEstudante) { this.idEstudante = idEstudante; }

    public int getIdCurso() { return idCurso; }
    public void setIdCurso(int idCurso) { this.idCurso = idCurso; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
