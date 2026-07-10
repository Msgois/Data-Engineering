package CRUD;

import java.util.List;
import java.util.Scanner;
import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Organizador {
    /*
     * Aqui inicia a organização do C(Create)
     * 
     * 
     * 
     */

    /*
     * O método o_cusuario tem como intuito conseguir os dados necessários para a
     * inserção de um novo usuário ao banco de dados,
     * por meio de perguntas ao usuário.
     */
    public static void o_cusuario(Scanner sc) {

        System.out.println("Digite o cpf do novo Usuario:\n");
        long cpf = sc.nextLong();
        sc.nextLine();

        System.out.println("Digite o nome do novo Usuario:\n");
        String nome = sc.nextLine();

        System.out.println("Digite a data de nascimento do novo Usuario(FORMATO: DD/MM/AAAA):\n");

        /* Lógica para a inserção de datas */
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataLocal = null;

        /* Vai prender o usuário até que ele digite a data corretamente */
        while (dataLocal == null) {
            String dataNascimento = sc.nextLine();
            try {

                /* Passando a data para o formato aceitado pelo SQL */
                dataLocal = LocalDate.parse(dataNascimento, formatador);
            } catch (DateTimeException e) {
                System.out.println("Erro ao inserir data! Tente Novamente!" + e.getMessage());
            }
        }

        /*
         * Transformação de LocalDate para Date, para que seja aceito como paramêtro
         * pelo método de inserção
         */
        Date dataNascimentoSQL = Date.valueOf(dataLocal);

        System.out.println("Digite o email do novo Usuario:\n");
        String email = sc.nextLine();

        System.out.println("Digite o telefone do novo Usuario:\n");
        String telefone = sc.nextLine();

        System.out.println("Digite o login do novo Usuario:\n");
        String login = sc.nextLine();

        System.out.println("Digite o senha do novo Usuario:\n");
        String senha = sc.nextLine();

        /* Instanciando o objeto Usuario para passar ao DAO */
        Usuario u = new Usuario(cpf, nome, dataNascimentoSQL, email, telefone, login, senha);

        /* Chama o método inserirUsuario passando os dados obtidos como paramêtro */
        UsuarioDAO.inserirUsuario(u);
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
        /* Instanciando o objeto Estudante para passar ao DAO */
        Estudante e = new Estudante(0, cpf, matricula, MC, anoDeIngresso);

        /* Chama o método inserirEstudante passando os dados obtidos como paramêtro */
        EstudanteDAO.inserirEstudante(e);
    }

    /*
     * O método o_ccurso tem como intuito conseguir os dados necessários para a
     * inserção de um novo curso ao banco de dados,
     * por meio de perguntas ao usuário.
     */
    public static void o_ccurso(Scanner sc) {

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

        /* Instanciando o objeto Curso para passar ao DAO */
        Curso c = new Curso(0, nome, grau, turno, campus, nivel);

        /* Chama o método inserirCurso passando os dados obtidos como paramêtro */
        CursoDAO.inserirCurso(c);

    }

    /*
     * O método o_cvinculo tem como intuito conseguir os dados necessários para a
     * inserção de um novo vinculo ao banco de dados,
     * por meio de perguntas ao usuário.
     */
    public static void o_cvinculo(Scanner sc) {

        System.out.println("Digite a matricula do novo Vinculo:\n");
        String matricula = sc.nextLine();

        System.out.println("Digite o idDeCurso do novo Vinculo:\n");
        int idDeCurso = sc.nextInt();
        sc.nextLine();

        System.out.println("Digite a data de entrada do novo Vinculo:\n");

        /* Lógica para a inserção de datas */
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataLocal = null;

        /* Vai prender o usuário até que ele digite a data corretamente */
        while (dataLocal == null) {
            String dataEntrada = sc.nextLine();
            try {

                /* Passando a data para o formato aceitado pelo SQL */
                dataLocal = LocalDate.parse(dataEntrada, formatador);
            } catch (DateTimeException e) {
                System.out.println("Erro ao inserir data! Tente Novamente!" + e.getMessage());
            }
        }

        /*
         * Transformação de LocalDate para Date, para que seja aceito como paramêtro
         * pelo método de inserção
         */
        Date dataEntradaSQL = Date.valueOf(dataLocal);

        System.out.println(
                "Digite o status do novo Vinculo:(Ativo, Trancado, Formado ou Cancelado (ESCREVA EXATAMENTE DESSA FORMA))\n");
        String status = sc.nextLine();

        System.out.println("Digite a data de saida do novo Vinculo:\n");

        /* Lógica para a inserção de datas */

        /*
         * Não é necessário criar novamente a variável dataLocal, somente passar para
         * ela o valor null novamente
         */
        dataLocal = null;

        /* Vai prender o usuário até que ele digite a data corretamente */
        while (dataLocal == null) {
            String dataSaida = sc.nextLine();
            try {

                /* Passando a data para o formato aceitado pelo SQL */
                dataLocal = LocalDate.parse(dataSaida, formatador);
            } catch (DateTimeException e) {
                System.out.println("Erro ao inserir data! Tente Novamente!" + e.getMessage());
            }
        }

        /*
         * Transformação de LocalDate para Date, para que seja aceito como paramêtro
         * pelo método de inserção
         */
        Date dataSaidaSQL = Date.valueOf(dataLocal);

        /* Instanciando o objeto Vinculo para passar ao DAO */
        Vinculo v = new Vinculo(0, matricula, idDeCurso, dataEntradaSQL, status, dataSaidaSQL);
        /* Chama o método inserirVinculo passando os dados obtidos como paramêtro */
        VinculoDAO.inserirVinculo(v);

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
        System.out.println("--- Lista de Usuários ---");
        UsuarioDAO dao = new UsuarioDAO();
        List<Usuario> usuarios = dao.listarTodosUsuarios();
        for (Usuario u : usuarios) {
            System.out.println("CPF: " + u.getCpf() + " | Nome: " + u.getNome() + " | Email: " + u.getEmail());
        }
        System.out.println("-------------------------");
    }

    public static void o_restudante(Scanner sc) {
        System.out.println("--- Lista de Estudantes ---");
        EstudanteDAO dao = new EstudanteDAO();
        List<Estudante> estudantes = dao.listarTodosEstudantes();
        for (Estudante e : estudantes) {
            System.out.println(
                    "Matrícula: " + e.getMatricula() + " | CPF: " + e.getCpf() + " | Ingresso: " + e.getAnoIngresso());
        }
        System.out.println("---------------------------");
    }

    public static void o_rcurso(Scanner sc) {
        System.out.println("--- Lista de Cursos ---");
        CursoDAO dao = new CursoDAO();
        List<Curso> cursos = dao.listarTodosCursos();
        for (Curso c : cursos) {
            System.out.println("ID: " + c.getIdCurso() + " | Nome: " + c.getNome() + " | Campus: " + c.getCampus());
        }
        System.out.println("-----------------------");
    }

    public static void o_rvinculo(Scanner sc) {
        System.out.println("--- Lista de Vínculos ---");
        VinculoDAO dao = new VinculoDAO();
        List<Vinculo> vinculos = dao.listarTodosVinculo();
        for (Vinculo v : vinculos) {
            System.out.println(
                    "ID: " + v.getIdVinculo() + " | Estudante: " + v.getMatricula() + " | Status: " + v.getStatus());
        }
        System.out.println("-------------------------");
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
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataLocal = null;
        while (dataLocal == null) {
            try {
                dataLocal = LocalDate.parse(sc.nextLine(), formatador);
            } catch (DateTimeException e) {
                System.out.println("Erro ao inserir data! Tente Novamente!");
            }
        }
        Date dataNascimentoSQL = Date.valueOf(dataLocal);

        System.out.println("Digite o novo email:\n");
        String email = sc.nextLine();

        System.out.println("Digite o novo telefone:\n");
        String telefone = sc.nextLine();

        System.out.println("Digite o novo login:\n");
        String login = sc.nextLine();

        System.out.println("Digite a nova senha:\n");
        String senha = sc.nextLine();

        Usuario u = new Usuario(cpf, nome, dataNascimentoSQL, email, telefone, login, senha);
        UsuarioDAO dao = new UsuarioDAO();
        dao.atualizar(u);
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
        EstudanteDAO dao = new EstudanteDAO();
        dao.atualizar(e);
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
        CursoDAO dao = new CursoDAO();
        dao.atualizar(c);
    }

    public static void o_uvinculo(Scanner sc) {
        System.out.println("Digite o ID do Vinculo que deseja atualizar o status:\n");
        int idVinculo = sc.nextInt();
        sc.nextLine();

        System.out.println("Digite o novo Status (ex: Ativo, Trancado, Concluído):\n");
        String status = sc.nextLine();

        VinculoDAO dao = new VinculoDAO();
        dao.atualizarStatus(idVinculo, status);
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

        UsuarioDAO dao = new UsuarioDAO();
        dao.deletar(cpf);
    }

    public static void o_destudante(Scanner sc) {
        System.out.println("Digite o ID do Estudante a ser deletado:\n");
        int id = sc.nextInt();
        sc.nextLine();

        EstudanteDAO dao = new EstudanteDAO();
        dao.deletar(id);
    }

    public static void o_dcurso(Scanner sc) {
        System.out.println("Digite o ID do Curso a ser deletado:\n");
        int id = sc.nextInt();
        sc.nextLine();

        CursoDAO dao = new CursoDAO();
        dao.deletar(id);
    }

    public static void o_dvinculo(Scanner sc) {
        System.out.println("Digite o ID do Vinculo a ser deletado:\n");
        int id = sc.nextInt();
        sc.nextLine();

        VinculoDAO dao = new VinculoDAO();
        dao.deletar(id);
    }
    /*
     * Aqui termina a organização do D(Delete)
     */

}
