import java.util.Date;
import java.util.List;

public class TesteMain {
    public static void main(String[] args) {
        System.out.println("=== INICIANDO TESTE DO BANCO DE DADOS NA AWS ===");

        // 1. Instanciar os DAOs
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        EstudanteDAO estudanteDAO = new EstudanteDAO();
        CursoDAO cursoDAO = new CursoDAO();
        VinculoDAO vinculoDAO = new VinculoDAO();

        // 2. CRIAR E SALVAR UM USUÁRIO
        // Usando um CPF fictício de 11 dígitos que se encaixa no tipo_cpf (NUMERIC 13)
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
        usuarioDAO.salvar(novoUsuario);

        // 3. CRIAR E SALVAR UM ESTUDANTE (Vinculado ao CPF do usuário acima)
        Estudante novoEstudante = new Estudante();
        novoEstudante.setCpfUsuario(cpfTeste);
        novoEstudante.setMatricula("2026000123");
        
        System.out.println("\nTentando cadastrar estudante...");
        estudanteDAO.salvar(novoEstudante);

        // 4. CRIAR E SALVAR UM CURSO
        Curso novoCurso = new Curso();
        novoCurso.setNome("Engenharia de Computação");
        
        System.out.println("\nTentando criar curso...");
        cursoDAO.salvar(novoCurso);

        // 5. VINCULAR O ESTUDANTE AO CURSO
        // Como o id_estudante e id_curso são gerados automaticamente (SERIAL),
        // vamos buscar do banco para pegar os IDs reais gerados para o vínculo
        System.out.println("\nBuscando dados para gerar o vínculo acadêmico...");
        
        List<Estudante> listaEstudantes = estudanteDAO.listarTodos();
        List<Curso> listaCursos = cursoDAO.listarTodos();

        if (!listaEstudantes.isEmpty() && !listaCursos.isEmpty()) {
            // Pega o último estudante e o último curso cadastrados para o teste
            Estudante estudanteDoBanco = listaEstudantes.get(listaEstudantes.size() - 1);
            Curso cursoDoBanco = listaCursos.get(listaCursos.size() - 1);

            Vinculo novoVinculo = new Vinculo();
            novoVinculo.setIdEstudante(estudanteDoBanco.getIdEstudante());
            novoVinculo.setIdCurso(cursoDoBanco.getIdCurso());
            novoVinculo.setStatus("Ativo");

            System.out.println("Tentando registrar vínculo...");
            vinculoDAO.salvar(novoVinculo);
        } else {
            System.err.println("Não foi possível gerar o vínculo: estudante ou curso não encontrados.");
        }

        System.out.println("\n=== FIM DO TESTE ===");
    }
}
