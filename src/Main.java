import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Main {

    private static ArrayList<Aluno> listaAlunos = new ArrayList<>();
    private static ArrayList<Turma> listaTurmas = new ArrayList<>();
    public static void main(String[] args) {
     menuPrincipal();
    }


    public static void menuPrincipal(){
        System.out.println("\n==== Secretaria ====");
        System.out.println("1 - Alunos.");
        System.out.println("2 - Turmas.");
        System.out.println("3 - Sair.");
        String opcao = Leitura.dados("Digite a opção desejada!");

        switch (opcao){
            case "1":
                menuAlunos();
                break;
            case "2":
                menuTurma();
                break;
            case "3":
                System.out.println("Ate a proxima...");
                System.exit(0);
            default:
                System.out.println("Opção invalida! Tente novamente!");
                menuPrincipal();
        }
    }

    // --------------------------------------alunos
    private static void menuAlunos() {
        System.out.println("\n==== Alunos ====");
        System.out.println("1 - Listar Alunos.");
        System.out.println("2 - Cadastrar Alunos.");
        System.out.println("3 - Atualizar Alunos.");
        System.out.println("4 - Excluir Aluno.");
        System.out.println("5 - Voltar ao MENU.");
        String opcao = Leitura.dados("Digite a opção desejada!");

        switch (opcao){
            case "1":
                listarAlunos();
                menuAlunos();
                break;
            case "2":
                CadastrarAluno();
                break;
            case "3":
                AtualizarAluno();
                break;
            case "4":
                ExcluirAluno();
                break;
            case "5":
                menuPrincipal();
                break;
            default:
                System.out.println("Opção invalida! Tente novamente!");
                menuAlunos();
        }
    }

//----------------------------------Listar--------------------------------------
    private static void listarAlunos() {

        if (isVazioAl(listaAlunos)) {
            System.out.println("Não há alunos cadastrados.");
            return;
        }
        for (Aluno a : listaAlunos){
            if(a.isAtivo())
                System.out.println(a);
        }
    }

    private static boolean isVazioAl(ArrayList<Aluno> listaAlunos) {
        if (listaAlunos.isEmpty()) return true;

        for (Aluno aluno: listaAlunos){
            if (aluno.isAtivo()) return false;
        }
        return true;
    }
// -------------------------------Cadastrar Aluno------------------------------
  private static void CadastrarAluno() {
     Turma turma = validaTurma();
      String nome = ValidaNome();
      LocalDate DataNascimento = ValidaData();
      Aluno aluno = new Aluno(nome, DataNascimento, turma);
      listaAlunos.add(aluno);
      System.out.println("Aluno cadastrado com sucesso!");
      menuAlunos();
   }

    private static LocalDate ValidaData() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            String data = Leitura.dados("Digite a data de nascimento (no formato dia/mês/ano ): ");
               try{
                   LocalDate nascimento = LocalDate.parse(data, formatter);
                    int idade = Period.between(nascimento, LocalDate.now()).getYears();

                    if (idade < 14){
                        System.out.println("Aluno precisa ter pelo menos 14 anos para iniciar um curso.");
                        continue;

                    }else if (idade > 130){
                        System.out.println("Idade maxima atingida. Idade maxima permitida: 130 anos");
                        continue;
                    }
                    return nascimento;


               }catch (DateTimeParseException e){
            System.out.println("Data invalida, use o formato dd/mm/aaaa");
               }


        }
    }

    private static String ValidaNome(){

           String nomeAluno = Leitura.dados("Digite o nome do aluno: ");
        while (!isCharacther(nomeAluno)) {
            System.out.println("Nome invalido! Digite novamente sem numeros!");
            nomeAluno = Leitura.dados("Digite o nome do aluno: ");
       }
        return nomeAluno;
    }

    private static Turma validaTurma(){
        listarTurmaSigla();
        while (listaTurmas.isEmpty()){
            System.out.println("Precisa de uma turma cadastrada para prosseguir com essa ação!");
            menuAlunos();
            break;
        }
        int idAtualizar = validaIDTurma();
        return listaTurmas.get(idAtualizar);
    }
//-------------------------------------Atualizar-------------------------------
    private static void AtualizarAluno() {
        if (isVazioAl(listaAlunos)) {
            System.out.println("Não há turmas cadastradas.");
            return;
        }
        listarAlunosId();
        int idAtualizar = ValidaIDaluno();

        System.out.printf("O Nome atual é: %s ", listaAlunos.get(idAtualizar).getNome());
        atualizaAluno("nome", idAtualizar);

        System.out.printf("A data de nascimento atual é: %s", listaAlunos.get(idAtualizar).getDataNascimento());
        atualizaAluno("dataNascimento", idAtualizar);


        System.out.printf("A turma atual é: %s", listaAlunos.get(idAtualizar).getTurma());
        atualizaAluno("turma", idAtualizar);

        menuAlunos();

    }

    private static void atualizaAluno(String atributo, int idAtualizar){
        boolean rodarNovamente = true;
        while(rodarNovamente) {
            String opcao = Leitura.dados("\nDeseja modificar o "+atributo+" ? (s/n):").toUpperCase();
            switch (opcao) {
                case "S":
                    switch (atributo){
                        case "nome":
                            String nome = ValidaNome();
                            listaAlunos.get(idAtualizar).setNome(nome);

                            break;
                        case "dataNascimento":
                            LocalDate DataNascimento = ValidaData();
                            listaAlunos.get(idAtualizar).setDataNascimento(DataNascimento);

                            break;
                        case "turma":
                            Turma turma = validaTurma();
                            listaAlunos.get(idAtualizar).setTurma(turma);
                            break;
                    }
                    System.out.println(atributo+" Atualizado com sucesso");
                    rodarNovamente = false;
                    break;
                case "N":
                    rodarNovamente = false;
                    break;
                default:
                    System.out.println("Opção invalida, escolha S para Sim ou N para Não");
                    continue;
            }
            break;
        }
    }

//------------------------------------Excluir-------------------------------------
    private static void ExcluirAluno() {
        if (isVazioAl(listaAlunos)) {
            System.out.println("Não há alunos cadastradas.");
            return;
        }
        listarAlunosId();
        int idExcluir = ValidaIDaluno();

        if (confirmaExclusao()) {
            listaAlunos.get(idExcluir).setAtivo(false);
            System.out.println("Aluno excluida com sucesso!");
        }else {
            System.out.println("Operação cancelada!");
        }
        menuAlunos();
    }

    private static int ValidaIDaluno() {
        String opcao = Leitura.dados("\nDigite o id do aluno que deseja:");
        int opcaoValida = -1;
        int opcaoUsuario = -1;
        while (opcaoValida ==-1){
            opcaoUsuario = validarOpcao(opcao, listaAlunos);
            if(opcaoUsuario == -1){
                System.out.println("Opção inválida! Digite novamente");
                opcao = Leitura.dados("Digite o id do aluno que deseja:");
            } else{
                opcaoValida = opcaoUsuario;
            }
        }
        return opcaoValida;
    }

    private static void listarAlunosId() {
        System.out.println("\n==== Lista de Alunos ====");
        for (int i=0;i<listaAlunos.size(); i++){
            if (listaAlunos.get(i).isAtivo())
                System.out.printf("\n%d - %s",i+1, listaAlunos.get(i));
        }
    }


    //-------------------------------------- turma
    private static void menuTurma() {
        System.out.println("\n==== Turmas ====");
        System.out.println("1 - Listar Turmas.");
        System.out.println("2 - Cadastrar Turma.");
        System.out.println("3 - Atualizar Turma.");
        System.out.println("4 - Excluir Turma.");
        System.out.println("5 - Voltar ao MENU");
        String opcao = Leitura.dados("Digite a opção desejada!");

        switch (opcao){
            case "1":
                listarTurmas();
                menuTurma();
                break;
            case "2":
                CadastrarTurmas();
                menuTurma();
                break;
            case "3":
                AtualizarTurmas();
                menuTurma();
                break;
            case "4":
                ExcluirTurmas();
                menuTurma();
                break;
            case "5":
                menuPrincipal();
                menuTurma();
                break;
            default:
                System.out.println("Opção invalida! Tente novamente!");
                menuTurma();
        }
    }
//----------------------------------------LISTAR------------------------------------
    private static void listarTurmas() {

        if (isVazio(listaTurmas)) {
            System.out.println("Não há turmas cadastradas.");
            return;
        }
        for (Turma t : listaTurmas){
            if(t.isAtivo())
            System.out.println(t);
        }
    }

//--------------------------------CADASTRAR-----------------------------------------
    private static void CadastrarTurmas() {
     Periodo periodo = ValidarPeriodo();
     String curso = ValidarCurso();
     String sigla = ValidarSigla();
     Turma turma = new Turma(curso, sigla, periodo);
        listaTurmas.add(turma);
        menuTurma();
    }

    private static boolean validarSigla(String sigla) {
        if (sigla.isBlank()) return false;

        for (Turma turma : listaTurmas) {
            if (turma.getSigla().equals(sigla)){
                return false;
            }
        }

        return true;
    }

    private static boolean isCharacther(String curso) {
        String textoSemNumero = curso.replaceAll("\\d", " ");
        return  !curso.isBlank() && curso.equals((textoSemNumero));
    }

    private static Periodo ValidarPeriodo(){
        Periodo periodo;
        String opcaoPeriodo = Leitura.dados("""
                Digite o numero do periodo escolhido:
                1 - Matutino
                2 - Vespertino
                3 - Noturno
                4 - Integral""");

        switch (opcaoPeriodo){
            case "1":
                return Periodo.MATUTINO;

            case "2":
                return Periodo.VESPERTINO;

            case "3":
                return Periodo.NOTURNO;

            case "4":
                return Periodo.INTEGRAL;

            default:
                System.out.println("Opção invalida, tente novamente");
                return ValidarPeriodo();
        }

    }

    //---------------------------------ATUALIZAR--------------------------------------------
    private static void AtualizarTurmas() {
        if (isVazio(listaTurmas)) {
            System.out.println("Não há turmas cadastradas.");
            return;
        }
        listarTurmaSigla();
        int idAtualizar = validaIDTurma();

        System.out.printf("O periodo atual é: %s ", listaTurmas.get(idAtualizar).getPeriodo());
        atualizarParcial("periodo", idAtualizar);
//        atualizarPeriodo(idAtualizar);
//
        System.out.printf("O curso atual é: %s", listaTurmas.get(idAtualizar).getCurso());
        atualizarParcial("curso", idAtualizar);

//        atualizarCurso(idAtualizar);
//
        System.out.printf("A sigla atual é: %s", listaTurmas.get(idAtualizar).getSigla());
        atualizarParcial("sigla", idAtualizar);

//        atualizarSigla(idAtualizar);
    }

    private static void atualizarParcial(String atributo, int idAtualizar){
        boolean rodarNovamente = true;
        while(rodarNovamente) {
            String opcao = Leitura.dados("\nDeseja modificar o "+atributo+" ? (s/n):").toUpperCase();
            switch (opcao) {
                case "S":
                    switch (atributo){
                        case "periodo":
                            Periodo periodo = ValidarPeriodo();
                            listaTurmas.get(idAtualizar).setPeriodo(periodo);

                            break;
                        case "curso":
                            String curso = ValidarCurso();
                            listaTurmas.get(idAtualizar).setCurso(curso);

                            break;
                        case "sigla":
                            String sigla = ValidarSigla();
                            listaTurmas.get(idAtualizar).setSigla(sigla);
                            break;
                    }
                    System.out.println(atributo+" Atualizado com sucesso");
                    rodarNovamente = false;
                    break;
                case "N":
                    rodarNovamente = false;
                    break;
                default:
                    System.out.println("Opção invalida, escolha S para Sim ou N para Não");
                    continue;
            }
            break;
        }
    }


    private static String ValidarSigla() {
        String sigla = Leitura.dados("Digite a sigla: ");
        while(!validarSigla(sigla)) {
            System.out.println("Sigla de curso invalida, utilize apenas letras!");
            sigla = Leitura.dados("Digite a sigla:");
        }
        return sigla;

    }

    private static void atualizarPeriodo(int idAtualizar) {
        boolean rodarNovamente = true;
        while(rodarNovamente) {
            String opcaoPeriodo = Leitura.dados("\nDeseja modificar o periodo? (s/n):").toUpperCase();
            switch (opcaoPeriodo) {
                case "S":
                    Periodo periodo = ValidarPeriodo();
                    listaTurmas.get(idAtualizar).setPeriodo(periodo);
                    System.out.printf("O periodo atual é: %s ", listaTurmas.get(idAtualizar).getPeriodo());
                    rodarNovamente = false;
                    break;
                case "N":
                    rodarNovamente = false;
                    break;
                default:
                    System.out.println("Opção invalida, Escolha S para Sim ou N para Não");
                    continue;
            }
            break;
        }
    }

    private static String ValidarCurso(){
        String curso = Leitura.dados("Digite o curso:");
        while(!isCharacther(curso)) {
            System.out.println("nome de curso invalido, utilize apenas letras!");
            curso = Leitura.dados("Digite o curso:");
        }
        return curso;

    }



    private static int validaIDTurma() {
        String opcao = Leitura.dados("\nDigite o numero da turma que desejada:");
        int opcaoValida = -1;
        int opcaoUsuario = -1;
        while (opcaoValida ==-1){
            opcaoUsuario = validarOpcao(opcao, listaTurmas);
            if(opcaoUsuario == -1){
                System.out.println("Opção inválida! Digite novamente");
                opcao = Leitura.dados("Digite o numero da turma que desejada:");
            } else{
                opcaoValida = opcaoUsuario;
            }
        }
        return opcaoValida;
    }
//----------------------------EXCLUIR---------------------------------------------
    private static void ExcluirTurmas() {
        if (isVazio(listaTurmas)) {
            System.out.println("Não há turmas cadastradas.");
            return;
        }

        listarTurmaSigla();
       int idExcluir = validaIDTurma();
        confirmaExclusao();

        if (confirmaExclusao()){
            listaTurmas.get(idExcluir).setAtivo(false);
            System.out.println("Turma excluida com sucesso!");
        }else {
            System.out.println("Operação cancelada!");
        }
    }

    private static boolean isVazio(ArrayList<Turma> listaTurmas) {
        if (listaTurmas.isEmpty()) return true;

        for (Turma turma: listaTurmas){
            if (turma.isAtivo()) return false;
        }
        return true;
    }

    private static Boolean confirmaExclusao() {
        String confirma = Leitura.dados("Você tem certeza? (S/N): ").toUpperCase();
        while (true) {
            switch (confirma) {
                case "S":
                    return true;
                case "N":
                    return false;
                default:
                    System.out.println("Opcao invalida. digite S para sim ou N para não.");
                    break;
            }
        }
    }

    private static void listarTurmaSigla() {
        System.out.println("\n==== Lista de turmas ====");
        for (int i=0;i<listaTurmas.size(); i++){
            if (listaTurmas.get(i).isAtivo())
            System.out.printf("\n%d - %s",i+1, listaTurmas.get(i).getSigla());
        }
    }

    private static int validarOpcao(String opcao, ArrayList<?> lista) {
        if (opcao.isBlank()) return -1;
        int opcaoNumero = -1;
        try {
            opcaoNumero = Integer.parseInt(opcao);

        }catch (NumberFormatException e){
            return -1;
        }

        int indiceLista = opcaoNumero-1;
        return indiceLista>=0 && lista.size() > indiceLista? indiceLista: -1;
    }


 }