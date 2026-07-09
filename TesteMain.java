import java.util.Date;
import java.util.List;

public class TesteMain {
    public static void main(String[] args) {
        System.out.println("=== INICIANDO TESTE DO BANCO DE DADOS NA AWS ===");

        // 1. Instanciar os DAOs conforme os seus arquivos do projeto
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        EstudanteDAO estudanteDAO = new EstudanteDAO();
        CursoDAO cursoDAO = new CursoDAO();
        VinculoDAO vinculoDAO = new VinculoDAO();

        // 2. CRIAR E SALVAR UM USUÁRIO (Usando o construtor completo de Usuario.java)
        long cpfTeste = 12345678901L; 
        Usuario novoUsuario = new Usuario(
            cpfTeste, 
            "Tácito Rodrigues", 
            new Date(), 
            "tacito@email.com", 
            "79999999999", 
            "tacito.dev", 
            "senha123"
        );
        
        System.out.println("\nTentando salvar usuário...");
        // O método no seu UsuarioDAO é estático e se chama inserirUsuario
        UsuarioDAO.inserirUsuario(novoUsuario);

        // 3. CRIAR E SALVAR UM ESTUDANTE (Usando o construtor completo de Estudante.java)
        // Parâmetros: idEstudante (0 pois é SERIAL), cpf, matricula, mc, anoIngresso
        Estudante novoEstudante = new Estudante(0, cpfTeste, "2026000123", 9.50, 2026);
        
        System.out.println("\nTentando cadastrar estudante...");
        // O método no seu EstudanteDAO é estático e se chama inserirEstudante
        EstudanteDAO.inserirEstudante(novoEstudante);

        // 4. CRIAR E SALVAR UM CURSO (Usando as novas colunas mapeadas)
        Curso novoCurso = new Curso();
        novoCurso.setNome("Engenharia de Computação");
        novoCurso.setGrau("Bacharelado");
        novoCurso.setTurno("Integral");
        novoCurso.setCampus("São Cristóvão");
        novoCurso.setNivel("Graduação");
        
        System.out.println("\nTentando criar curso...");
        // O método no seu CursoDAO é estático e se chama inserirCurso
        CursoDAO.inserirCurso(novoCurso);

        // 5. VINCULAR O ESTUDANTE AO CURSO
        System.out.println("\nBuscando dados para gerar o vínculo acadêmico...");
        
        // Chamando os métodos de listagem com os nomes exatos do seu projeto
        List<Estudante> listaEstudantes = estudanteDAO.listarTodosEstudantes();
        List<Curso> listaCursos = cursoDAO.listarTodosCursos();

        if (!listaEstudantes.isEmpty() && !listaCursos.isEmpty()) {
            // Pega o último estudante e o último curso cadastrados para o teste
            Estudante estudanteDoBanco = listaEstudantes.get(listaEstudantes.size() - 1);
            Curso cursoDoBanco = listaCursos.get(listaCursos.size() - 1);

            // Criando o vínculo usando o construtor completo de Vinculo.java
            // Parâmetros: idVinculo (0 pois é SERIAL), matricula, idCurso, dataEntrada, status, dataSaida
            Vinculo novoVinculo = new Vinculo(
                0, 
                estudanteDoBanco.getMatricula(), 
                cursoDoBanco.getIdCurso(), 
                new Date(), 
                "Ativo", 
                null // Data de saída nula pois acabou de entrar
            );

            System.out.println("Tentando registrar vínculo...");
            // O método no seu VinculoDAO não é estático, chamamos pela instância vinculoDAO
            vinculoDAO.inserirVinculo(novoVinculo);
        } else {
            System.err.println("Não foi possível gerar o vínculo: estudante ou curso não encontrados.");
        }

        System.out.println("\n=== FIM DO TESTE ===");
    }
}