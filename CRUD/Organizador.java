package CRUD;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import org.bson.Document;

public class Organizador {
    private static final DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    private static Date lerData(Scanner sc) {
        LocalDate dataLocal = null;
        while (dataLocal == null) {
            String entrada = sc.nextLine();
            try {
                dataLocal = LocalDate.parse(entrada, formatador);
            } catch (DateTimeException e) {
                System.out.println("Erro ao inserir data! Tente Novamente! " + e.getMessage());
            }
        }
        return Date.valueOf(dataLocal);
    }
    
    /* Aqui inicia a organização do C(Create) */

    /* O método o_cusuario tem como intuito conseguir os dados necessários para 
     * a inserção de um novo usuário ao banco de dados, 
     * por meio de perguntas ao usuário. 
     */
    public static void o_cusuario(Scanner sc) {
        System.out.println("Digite o cpf do novo Usuario:\n");
        long cpf = sc.nextLong();
        sc.nextLine();

        System.out.println("Digite o nome do novo Usuario:\n");
        String nome = sc.nextLine();

        System.out.println("Digite a data de nascimento do novo Usuario(FORMATO: DD/MM/AAAA):\n");
        Date dataNascimentoSQL = lerData(sc);

        System.out.println("Digite o email do novo Usuario:\n");
        String email = sc.nextLine();

        System.out.println("Digite o telefone do novo Usuario:\n");
        String telefone = sc.nextLine();

        System.out.println("Digite o login do novo Usuario:\n");
        String login = sc.nextLine();

        System.out.println("Digite o senha do novo Usuario:\n");
        String senha = sc.nextLine();

        Usuario u = new Usuario(cpf, nome, dataNascimentoSQL, email, telefone, login, senha);

        // 1. Grava no Relacional
        UsuarioDAO.inserirUsuario(u);

        // 2. Grava no NoSQL (Inicia o documento apenas com dados de usuário, sem estudante/vínculos ainda)
        UsuarioNoSQLDAO.inserirUsuarioNoSQL(u, null, null);
    }

    /*
     * O método o_cestudante tem como intuito conseguir os dados necessários para a
     * inserção de um novo estudante ao banco de dados,
     * por meio de perguntas ao usuário.
     */
    public static void o_cestudante(Scanner sc) {
        System.out.println("Digite a matricula do novo Estudante:\n");
        String matricula = sc.nextLine();

        System.out.println("Digite o cpf do novo Estudante:\n");
        long cpf = sc.nextLong();
        sc.nextLine();

        System.out.println("Digite a MC do novo Estudante:\n");
        double MC = sc.nextDouble();
        sc.nextLine();

        System.out.println("Digite o ano de ingresso do novo Estudante:\n");
        int anoDeIngresso = sc.nextInt();
        sc.nextLine();

        Estudante e = new Estudante(0, cpf, matricula, MC, anoDeIngresso);

        // 1. Grava no Relacional
        EstudanteDAO.inserirEstudante(e);

        // 2. NoSQL: Como o Usuário já existe, vamos "injetar" o perfil do estudante dentro dele
        try {
            com.mongodb.client.MongoCollection<Document> colecao = com.mongodb.client.MongoDatabase.class
                    .cast(Conexao.getMongoDatabase()).getCollection("usuarios");
            
            Document docEstudante = new Document("mat_estudante", e.getMatricula())
                    .append("MC", e.getMc())
                    .append("ano_ingresso", e.getAnoIngresso())
                    .append("vinculos", new ArrayList<>()); // Inicializa a lista de vínculos vazia

            colecao.updateOne(
                com.mongodb.client.model.Filters.eq("cpf", cpf),
                com.mongodb.client.model.Updates.set("perfil_estudante", docEstudante)
            );
            System.out.println("Perfil de estudante embutido com sucesso no MongoDB!");
        } catch (Exception ex) {
            System.err.println("Erro ao espelhar estudante no MongoDB: " + ex.getMessage());
        }
    }

    /*
     * O método o_ccurso tem como intuito conseguir os dados necessários para a
     * inserção de um novo curso ao banco de dados,
     * por meio de perguntas ao usuário.
     */
    public static void o_ccurso(Scanner sc) {
        System.out.println("Digite o ID (Número) para este novo Curso (Verifique o próximo número na sequência do Postgres):\n");
        int idCurso = sc.nextInt();
        sc.nextLine();

        System.out.println("Digite o nome do novo Curso:\n");
        String nome = sc.nextLine();

        System.out.println("Digite o grau do novo Curso:\n");
        String grau = sc.nextLine();

        System.out.println("Digite o turno do novo Curso:\n");
        String turno = sc.nextLine();

        System.out.println("Digite o campus do novo Curso:\n");
        String campus = sc.nextLine();

        System.out.println("Digite o nivel do novo Curso:\n");
        String nivel = sc.nextLine();

        // AGORA PASSAMOS O ID DIGITADO EM VEZ DE 0:
        Curso c = new Curso(idCurso, nome, grau, turno, campus, nivel);

        // 1. Grava no Relacional
        CursoDAO.inserirCurso(c);

        // 2. Grava no NoSQL (Coleção própria de cursos)
        CursoNoSQLDAO.inserirCursoNoSQL(c);
    }

    /*
     * O método o_cvinculo tem como intuito conseguir os dados necessários para a
     * inserção de um novo vinculo ao banco de dados,
     * por meio de perguntas ao usuário.
     */
    public static void o_cvinculo(Scanner sc) {
        System.out.println("Digite a matricula do novo Vinculo:\n");
        String matricula = sc.nextLine();

        System.out.println("Digite o ID do Curso do novo Vinculo:\n");
        int idDeCurso = sc.nextInt();
        sc.nextLine();

        System.out.println("Digite a data de entrada do novo Vinculo:\n");
        Date dataEntradaSQL = lerData(sc);

        System.out.println("Digite o status do novo Vinculo:(Ativo, Trancado, Formado ou Cancelado)\n");
        String status = sc.nextLine();

        System.out.println("Digite a data de saida do novo Vinculo:\n");
        Date dataSaidaSQL = lerData(sc);

        Vinculo v = new Vinculo(0, matricula, idDeCurso, dataEntradaSQL, status, dataSaidaSQL);

        // 1. Grava no Relacional
        VinculoDAO.inserirVinculo(v);

        // 2. NoSQL: Localiza o usuário que possui essa matrícula e adiciona o vínculo ao array dele
        try {
            com.mongodb.client.MongoCollection<Document> colecao = com.mongodb.client.MongoDatabase.class
                    .cast(Conexao.getMongoDatabase()).getCollection("usuarios");

            Document docVinculo = new Document("id_curso", v.getIdCurso())
                    .append("data_entrada", v.getDataEntrada().toString())
                    .append("status", v.getStatus())
                    .append("data_saida", v.getDataSaida().toString());

            // Adiciona ($push) o vínculo dentro do array 'vinculos' que está em 'perfil_estudante'
            colecao.updateOne(
                com.mongodb.client.model.Filters.eq("perfil_estudante.mat_estudante", matricula),
                com.mongodb.client.model.Updates.push("perfil_estudante.vinculos", docVinculo)
            );
            System.out.println("Vínculo aninhado com sucesso no MongoDB!");
        } catch (Exception ex) {
            System.err.println("Erro ao espelhar vínculo no MongoDB: " + ex.getMessage());
        }
    }

    /*
     * Aqui Termina a organização do C(Create)
     * 
     * 
     * 
     * 
     * 
     * Aqui inicia a organização do R(Read)
     */
    public static void o_rusuario(Scanner sc) {
        System.out.println("--- Lista de Usuários (Postgres) ---");
        UsuarioDAO dao = new UsuarioDAO();
        List<Usuario> usuarios = dao.listarTodosUsuarios();
        for (Usuario u : usuarios) {
            System.out.println("CPF: " + u.getCpf() + " | Nome: " + u.getNome() + " | Email: " + u.getEmail());
        }
        System.out.println("------------------------------------");
        
        // Exibe o espelho do MongoDB logo abaixo para fins de comparação acadêmica
        System.out.println("\n--- Espelho correspondente no MongoDB (JSON) ---");
        List<Document> mongoDocs = UsuarioNoSQLDAO.listarTodosUsuariosNoSQL();
        for (Document doc : mongoDocs) {
            System.out.println(doc.toJson());
        }
        System.out.println("------------------------------------------------");
    }

    public static void o_restudante(Scanner sc) {
        System.out.println("--- Lista de Estudantes (Postgres) ---");
        EstudanteDAO dao = new EstudanteDAO();
        List<Estudante> estudantes = dao.listarTodosEstudantes();
        for (Estudante e : estudantes) {
            System.out.println("Matrícula: " + e.getMatricula() + " | CPF: " + e.getCpf() + " | Ingresso: " + e.getAnoIngresso());
        }
        System.out.println("--------------------------------------");
    }

    public static void o_rcurso(Scanner sc) {
        System.out.println("--- Lista de Cursos (Postgres) ---");
        CursoDAO dao = new CursoDAO();
        List<Curso> cursos = dao.listarTodosCursos();
        for (Curso c : cursos) {
            System.out.println("ID: " + c.getIdCurso() + " | Nome: " + c.getNome() + " | Campus: " + c.getCampus());
        }
        System.out.println("----------------------------------");
    }

    public static void o_rvinculo(Scanner sc) {
        System.out.println("--- Lista de Vínculos (Postgres) ---");
        VinculoDAO dao = new VinculoDAO();
        List<Vinculo> vinculos = dao.listarTodosVinculo();
        for (Vinculo v : vinculos) {
            System.out.println("ID: " + v.getIdVinculo() + " | Estudante: " + v.getMatricula() + " | Status: " + v.getStatus());
        }
        System.out.println("------------------------------------");
    }

    /*
     * Aqui termina a organização do R(Read)
     * 
     *
     * 
     * 
     * 
     * Aqui inicia a organização do U(Update)
     */
    public static void o_uusuario(Scanner sc) {
        System.out.println("Digite o CPF do Usuario que deseja atualizar:\n");
        long cpf = sc.nextLong();
        sc.nextLine();

        System.out.println("Digite o novo nome:\n");
        String nome = sc.nextLine();

        System.out.println("Digite a nova data de nascimento (FORMATO: DD/MM/AAAA):\n");
        Date dataNascimentoSQL = lerData(sc);

        System.out.println("Digite o novo email:\n");
        String email = sc.nextLine();

        System.out.println("Digite o novo telefone:\n");
        String telefone = sc.nextLine();

        System.out.println("Digite o novo login:\n");
        String login = sc.nextLine();

        System.out.println("Digite a nova senha:\n");
        String senha = sc.nextLine();

        Usuario u = new Usuario(cpf, nome, dataNascimentoSQL, email, telefone, login, senha);
        
        // Atualiza Postgres
        new UsuarioDAO().atualizar(u);
        // Atualiza MongoDB
        UsuarioNoSQLDAO.atualizarNoSQL(u);
    }

    public static void o_uestudante(Scanner sc) {
        System.out.println("Digite o ID do Estudante que deseja atualizar:\n");
        int idEstudante = sc.nextInt();
        sc.nextLine();

        System.out.println("Digite a nova matricula:\n");
        String matricula = sc.nextLine();

        System.out.println("Digite o novo CPF:\n");
        long cpf = sc.nextLong();

        System.out.println("Digite a nova MC:\n");
        double MC = sc.nextDouble();

        System.out.println("Digite o novo ano de ingresso:\n");
        int anoDeIngresso = sc.nextInt();
        sc.nextLine();

        Estudante e = new Estudante(idEstudante, cpf, matricula, MC, anoDeIngresso);
        
        // Atualiza Postgres
        new EstudanteDAO().atualizar(e);

        // Atualiza MongoDB (perfil_estudante embutido)
        try {
            com.mongodb.client.MongoCollection<Document> colecao = com.mongodb.client.MongoDatabase.class
                    .cast(Conexao.getMongoDatabase()).getCollection("usuarios");
            colecao.updateOne(
                com.mongodb.client.model.Filters.eq("cpf", cpf),
                com.mongodb.client.model.Updates.combine(
                    com.mongodb.client.model.Updates.set("perfil_estudante.mat_estudante", matricula),
                    com.mongodb.client.model.Updates.set("perfil_estudante.MC", MC),
                    com.mongodb.client.model.Updates.set("perfil_estudante.ano_ingresso", anoDeIngresso)
                )
            );
        } catch (Exception ex) {
            System.err.println("Erro ao atualizar estudante no Mongo: " + ex.getMessage());
        }
    }

    public static void o_ucurso(Scanner sc) {
        System.out.println("Digite o ID do Curso que deseja atualizar:\n");
        int idCurso = sc.nextInt();
        sc.nextLine();

        System.out.println("Digite o novo nome:\n");
        String nome = sc.nextLine();

        System.out.println("Digite o novo grau:\n");
        String grau = sc.nextLine();

        System.out.println("Digite o novo turno:\n");
        String turno = sc.nextLine();

        System.out.println("Digite o novo campus:\n");
        String campus = sc.nextLine();

        System.out.println("Digite o novo nivel:\n");
        String nivel = sc.nextLine();

        Curso c = new Curso(idCurso, nome, grau, turno, campus, nivel);
        
        // Atualiza Postgres
        new CursoDAO().atualizar(c);
        // Atualiza MongoDB
        CursoNoSQLDAO.atualizarCursoNoSQL(c);
    }

    public static void o_uvinculo(Scanner sc) {
        System.out.println("Digite o ID do Vinculo que deseja atualizar o status:\n");
        int idVinculo = sc.nextInt();
        sc.nextLine();

        System.out.println("Digite o novo Status (ex: Ativo, Trancado, Concluído):\n");
        String status = sc.nextLine();

        // No relacional você usa o ID sequencial do vínculo
        new VinculoDAO().atualizarStatus(idVinculo, status);

        // No MongoDB, como o menu original não pede CPF/Curso aqui, buscamos o registro para atualizar via matriz posicional
        System.out.println("Digite o CPF do usuário dono deste vínculo (para sincronizar com o MongoDB):\n");
        long cpf = sc.nextLong();
        System.out.println("Digite o ID do curso deste vínculo:\n");
        int idCurso = sc.nextInt();
        sc.nextLine();

        UsuarioNoSQLDAO.atualizarStatusVinculoNoSQL(cpf, idCurso, status);
    }

    /*
     * Aqui termina a organização do U(Update)
     *
     * 
     * 
     * 
     * 
     * 
     * Aqui inicia a organização do D(Delete)
     */
    public static void o_dusuario(Scanner sc) {
        System.out.println("Digite o CPF do Usuario a ser deletado:\n");
        long cpf = sc.nextLong();
        sc.nextLine();

        // Remove Postgres
        new UsuarioDAO().deletar(cpf);
        // Remove MongoDB
        UsuarioNoSQLDAO.deletarNoSQL(cpf);
    }

    public static void o_destudante(Scanner sc) {
        System.out.println("Digite o ID do Estudante a ser deletado:\n");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.println("Digite o CPF correspondente para o MongoDB:\n");
        long cpf = sc.nextLong();
        sc.nextLine();

        // Remove Postgres
        new EstudanteDAO().deletar(id);

        // No MongoDB, removemos apenas o campo 'perfil_estudante' mantendo o usuário salvo
        try {
            com.mongodb.client.MongoCollection<Document> colecao = com.mongodb.client.MongoDatabase.class
                    .cast(Conexao.getMongoDatabase()).getCollection("usuarios");
            colecao.updateOne(
                com.mongodb.client.model.Filters.eq("cpf", cpf),
                com.mongodb.client.model.Updates.unset("perfil_estudante")
            );
        } catch (Exception ex) {
            System.err.println("Erro ao remover perfil estudante do Mongo: " + ex.getMessage());
        }
    }

    public static void o_dcurso(Scanner sc) {
        System.out.println("Digite o ID do Curso a ser deletado:\n");
        int id = sc.nextInt();
        sc.nextLine();

        // Remove Postgres
        new CursoDAO().deletar(id);
        // Remove MongoDB
        CursoNoSQLDAO.deletarCursoNoSQL(id);
    }

    public static void o_dvinculo(Scanner sc) {
        System.out.println("Digite o ID do Vinculo a ser deletado:\n");
        int id = sc.nextInt();
        sc.nextLine();

        // Remove Postgres
        new VinculoDAO().deletar(id);
        
        System.out.println("Remoção concluída no Postgres. Devido à estrutura embutida do NoSQL, remoções de vínculos específicos devem ser feitas via atualização de perfil.");
    }
    /*
     * Aqui termina a organização do D(Delete)
     */

}
