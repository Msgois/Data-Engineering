import java.util.Scanner;

public class Organizador {
    /*
     * Aqui inicia a organização do C(Create)
     * 
     * 
     * 
     */
    public static void o_cusuario() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o cpf do novo Usuario:\n");
        long cpf = sc.nextLong();

        System.out.println("Digite o nome do novo Usuario:\n");
        String nome = sc.nextLine();

        System.out.println("Digite a data de nascimento do novo Usuario:\n");
        /*
         * Tem que criar um while com try catch, pra o scanner de data,
         * é provável que necessite de um formatador, para que o usuario
         * não digite a data de forma errônea
         */

        System.out.println("Digite o email do novo Usuario:\n");
        String email = sc.nextLine();

        System.out.println("Digite o telefone do novo Usuario:\n");
        String telefone = sc.nextLine();

        System.out.println("Digite o login do novo Usuario:\n");
        String login = sc.nextLine();

        System.out.println("Digite o senha do novo Usuario:\n");
        String senha = sc.nextLine();

        Usuario.inserirUsuario(cpf, nome, dataNascimento, email, telefone, login, senha);

    }

    public static void o_cestudante() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite a matricula do novo Estudante:\n");
        String matricula = sc.nextLine();

        System.out.println("Digite o cpf do novo Estudante:\n");
        long cpf = sc.nextLong();

        System.out.println("Digite a MC do novo Estudante:\n");
        double MC = sc.nextDouble();

        System.out.println("Digite o ano de ingresso do novo Estudante:\n");
        int anoDeIngresso = sc.nextInt();

        Estudante.inserirEstudante(matricula, cpf, MC, anoDeIngresso);

    }

    public static void o_ccurso() {
        Scanner sc = new Scanner(System.in);

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

        Curso.inserirCurso(nome, grau, turno, campus, nivel);

    }

    public static void o_cvinculo() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite a matricula do novo Vinculo:\n");
        String matricula = sc.nextLine();

        System.out.println("Digite o idDeCurso do novo Vinculo:\n");
        int idDeCurso = sc.nextInt();

        System.out.println("Digite a data de entrada do novo Vinculo:\n");
        /*
         * Tem que criar um while com try catch, pra o scanner de data,
         * é provável que necessite de um formatador, para que o usuario
         * não digite a data de forma errônea
         */

        System.out.println("Digite o status do novo Vinculo:\n");
        String status = sc.nextLine();

        System.out.println("Digite a data de saida do novo Vinculo:\n");
        /*
         * Tem que criar um while com try catch, pra o scanner de data,
         * é provável que necessite de um formatador, para que o usuario
         * não digite a data de forma errônea, ALÉM DISSO TEM Q CRIAR UMA OPÇÃO AI
         * NO CASO DE O VINCULO AINDA NAO TER DATA DE SAIDA, OU SEJA AINDA ESTA NA
         * UNIVERSIDADE
         */

        Vinculo.inserirVinculo(matricula, idDeCurso, dataEntrada, status, dataSaida);

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
