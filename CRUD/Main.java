package CRUD;

import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        boolean controlador = true;
        /*
         * Loop infinito até que o usuário realize todas as suas ações e selecione sair
         * do terminal
         */
        while (controlador) {

            System.out.println(
                    "Digite o comando desejado:\n1-C(Create)\n2-R(Read)\n3-U(Update)\n4-D(Delete)\n5-SAIR\n");
            int escolhaDousuario = sc.nextInt();
            sc.nextLine();

            switch (escolhaDousuario) {
                case 1:
                    boolean controlador1 = true;
                    while (controlador1) {
                        System.out.println(
                                "Digite o comando desejado:\n1-Criar Usuario\n2-Criar Estudante\n3-Criar Curso\n4-Criar Vinculo\n5-VOLTAR\n");
                        int escolhaDousuario1 = sc.nextInt();
                        sc.nextLine();

                        switch (escolhaDousuario1) {
                            case 1:
                                Organizador.o_cusuario(sc);
                                break;
                            case 2:
                                Organizador.o_cestudante(sc);
                                break;
                            case 3:
                                Organizador.o_ccurso(sc);
                                break;
                            case 4:
                                Organizador.o_cvinculo(sc);
                                break;
                            case 5:
                                System.out.println("Voltando\n");
                                controlador1 = false;
                                break;

                            default:
                                System.out
                                        .println("Opcao Invalida! Digite um numero valido para sua respectiva escolha");
                                break;
                        }
                    }
                    break;
                case 2:
                    boolean controlador2 = true;
                    while (controlador2) {
                        System.out.println(
                                "Digite o comando desejado:\n1-Ler todos os Usuarios\n2-Ler todos os Estudante\n3-Ler todos os Curso\n4-Ler todos os Vinculo\n5-VOLTAR\n");
                        int escolhaDousuario2 = sc.nextInt();
                        sc.nextLine();

                        switch (escolhaDousuario2) {
                            case 1:
                                Organizador.o_rusuario(sc);
                                break;
                            case 2:
                                Organizador.o_restudante(sc);
                                break;
                            case 3:
                                Organizador.o_rcurso(sc);
                                break;
                            case 4:
                                Organizador.o_rvinculo(sc);
                                break;
                            case 5:
                                System.out.println("Voltando\n");
                                controlador2 = false;
                                break;

                            default:
                                System.out
                                        .println("Opcao Invalida! Digite um numero valido para sua respectiva escolha");
                                break;
                        }
                    }
                    break;
                case 3:
                    boolean controlador3 = true;
                    while (controlador3) {
                        System.out.println(
                                "Digite o comando desejado:\n1-Atualizar Usuario\n2-Atualizar Estudante\n3-Atualizar Curso\n4-Atualizar Vinculo\n5-VOLTAR\n");
                        int escolhaDousuario3 = sc.nextInt();
                        sc.nextLine();

                        switch (escolhaDousuario3) {
                            case 1:
                                Organizador.o_uusuario(sc);
                                break;
                            case 2:
                                Organizador.o_uestudante(sc);
                                break;
                            case 3:
                                Organizador.o_ucurso(sc);
                                break;
                            case 4:
                                Organizador.o_uvinculo(sc);
                                break;
                            case 5:
                                System.out.println("Voltando\n");
                                controlador3 = false;
                                break;

                            default:
                                System.out
                                        .println("Opcao Invalida! Digite um numero valido para sua respectiva escolha");
                                break;
                        }
                    }
                    break;
                case 4:
                    boolean controlador4 = true;
                    while (controlador4) {

                        System.out.println(
                                "Digite o comando desejado:\n1-Deletar Usuario\n2-Deletar Estudante\n3-Deletar Curso\n4-Deletar Vinculo\n5-VOLTAR\n");
                        int escolhaDousuario4 = sc.nextInt();
                        sc.nextLine();

                        switch (escolhaDousuario4) {
                            case 1:
                                Organizador.o_dusuario(sc);
                                break;
                            case 2:
                                Organizador.o_destudante(sc);
                                break;
                            case 3:
                                Organizador.o_dcurso(sc);
                                break;
                            case 4:
                                Organizador.o_dvinculo(sc);
                                break;
                            case 5:
                                System.out.println("Voltando\n");
                                controlador4 = false;
                                break;

                            default:
                                System.out
                                        .println("Opcao Invalida! Digite um numero valido para sua respectiva escolha");
                                break;
                        }
                    }
                    break;
                case 5:
                    System.out.println("Saindo do CRUD!!");
                    /*
                     * Alterando o controlador para falso para que saia do while, finalizando o
                     * sistema
                     */
                    controlador = false;
                    /* Como o sistema irá finalizar, fechamento do scanner */
                    Conexao.fecharConexao();
                    sc.close();
                    break;
                default:
                    System.out.println("Opcao Invalida! Digite um numero valido para sua respectiva escolha");
                    break;
            }
        }
    }
}