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

        /* Chama o método inserirUsuario passando os dados obtidos como paramêtro */
        Usuario.inserirUsuario(cpf, nome, dataNascimentoSQL, email, telefone, login, senha);

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

        /* Chama o método inserirEstudante passando os dados obtidos como paramêtro */
        Estudante.inserirEstudante(matricula, cpf, MC, anoDeIngresso);

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

        /* Chama o método inserirCurso passando os dados obtidos como paramêtro */
        Curso.inserirCurso(nome, grau, turno, campus, nivel);

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

        System.out.println("Digite o status do novo Vinculo:\n");
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

        /* Chama o método inserirVinculo passando os dados obtidos como paramêtro */
        Vinculo.inserirVinculo(matricula, idDeCurso, dataEntradaSQL, status, dataSaidaSQL);

    }

    /*
     * Aqui Termina a organização do C(Create)
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * Aqui inicia a organização do R(Read)
     */
    public static void o_rusuario() {

    }

    public static void o_restudante() {

    }

    public static void o_rcurso() {

    }

    public static void o_rvinculo() {

    }

    /*
     * Aqui termina a organização do R(Read)
     * 
     *
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * Aqui inicia a organização do U(Update)
     */
    public static void o_uusuario() {

    }

    public static void o_uestudante() {

    }

    public static void o_ucurso() {

    }

    public static void o_uvinculo() {

    }

    /*
     * Aqui termina a organização do U(Update)
     *
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     *
     * Aqui inicia a organização do D(Delete)
     */
    public static void o_dusuario() {

    }

    public static void o_destudante() {

    }

    public static void o_dcurso() {

    }

    public static void o_dvinculo() {

    }
    /*
     * Aqui termina a organização do D(Delete)
     */

}
