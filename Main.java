import java.util.Scanner;

public class Main {
    /* TA FALTANDO CRIAR FUNÇÃO DE SAIR/VOLTAR */
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println(
                "Digite o comando desejado:\n1-C(Create)\n2-R(Read)\n3-U(Update)\n4-D(Delete)");
        int escolhaDousuario = sc.nextInt();
        sc.close();
        switch (escolhaDousuario) {
            case 1:
                System.out.println(
                        "Digite o comando desejado:\n1-Criar Usuario\n2-Criar Estudante\n3-Criar Curso\n4-Criar Vinculo");
                int escolhaDousuario1 = sc.nextInt();
                switch (escolhaDousuario1) {
                    case 1:
                        Organizador.o_cusuario();
                        break;
                    case 2:
                        Organizador.o_cestudante();
                        break;
                    case 3:
                        Organizador.o_ccurso();
                        break;
                    case 4:
                        Organizador.o_cvinculo();
                        break;

                    default:
                        System.out.println("Opcao Invalida! Digite um numero valido para sua respectiva escolha");
                        break;
                }
                break;
            case 2:
                System.out.println(
                        "Digite o comando desejado:\n1-Ler todos os Usuarios\n2-Ler todos os Estudante\n3-Ler todos os Curso\n4-Ler todos os Vinculo");
                int escolhaDousuario2 = sc.nextInt();
                switch (escolhaDousuario2) {
                    case 1:
                        Organizador.o_rusuario();
                        break;
                    case 2:
                        Organizador.o_restudante();
                        break;
                    case 3:
                        Organizador.o_rcurso();
                        break;
                    case 4:
                        Organizador.o_rvinculo();
                        break;

                    default:
                        System.out.println("Opcao Invalida! Digite um numero valido para sua respectiva escolha");
                        break;
                }
                break;
            case 3:
                System.out.println(
                        "Digite o comando desejado:\n1-Atualizar Usuario\n2-Atualizar Estudante\n3-Atualizar Curso\n4-Atualizar Vinculo");
                int escolhaDousuario3 = sc.nextInt();
                switch (escolhaDousuario3) {
                    case 1:
                        Organizador.o_uusuario();
                        break;
                    case 2:
                        Organizador.o_uestudante();
                        break;
                    case 3:
                        Organizador.o_ucurso();
                        break;
                    case 4:
                        Organizador.o_uvinculo();
                        break;

                    default:
                        System.out.println("Opcao Invalida! Digite um numero valido para sua respectiva escolha");
                        break;
                }
                break;
            case 4:
                System.out.println(
                        "Digite o comando desejado:\n1-Deletar Usuario\n2-Deletar Estudante\n3-Deletar Curso\n4-Deletar Vinculo");
                int escolhaDousuario4 = sc.nextInt();
                switch (escolhaDousuario4) {
                    case 1:
                        Organizador.o_dusuario();
                        break;
                    case 2:
                        Organizador.o_destudante();
                        break;
                    case 3:
                        Organizador.o_dcurso();
                        break;
                    case 4:
                        Organizador.o_dvinculo();
                        break;

                    default:
                        System.out.println("Opcao Invalida! Digite um numero valido para sua respectiva escolha");
                        break;
                }
                break;
            default:
                System.out.println("Opcao Invalida! Digite um numero valido para sua respectiva escolha");
                break;
        }
    }
}