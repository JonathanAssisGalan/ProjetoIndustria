import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Principal {
    public static void main(String[] args) {

        //

        List<Funcionario> funcionarios = new ArrayList<>();

        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 8, 17), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1985, 3, 5), new BigDecimal("9605.14"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1 , 5 ), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11 ,19 ), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8 ), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003,5 ,24 ), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996,9 ,2 ), new BigDecimal("2799.93"), "Gerente"));

        // 3.1

        System.out.println("3.1 - Inserindo todos os funcionários na mesma ordem e informações da tabela acima:");
        imprimirFuncionarios(funcionarios);

        // 3.2 - Remover o funcionário "João" da lista.
        System.out.println("\n3.2 - Removendo o funcionário \"João\" da lista:");
        funcionarios.removeIf(f -> f.getNome().equals("João"));
        imprimirFuncionarios(funcionarios);

        // 3.3 - Imprimir todos os funcionários com todas suas informações, sendo que:
        // - A informação de data deve ser exibido no formato dd/mm/aaaa.
        // - Os funcionários receberam 10% de aumento no salário, com separador de milhar como ponto e decimal como vírgula.
        System.out.println("\n3.3 - Imprimindo todos os funcionários com todas suas informações:");
        for (Funcionario f : funcionarios) {
            f.aumentarSalario(new BigDecimal("0.10")); // Aumento de 10% no salário
            System.out.println("Nome: " + f.getNome());
            System.out.println("Data de Nascimento: " + f.getDataNascimento().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            System.out.println("Salário: R$" + f.getSalario().setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString().replace('.', ','));
            System.out.println("Função: " + f.getFuncao());
            System.out.println("-------------------------");
        }

        // 3.4 - Os funcionários receberam 10% de aumento no salário, atualizar a lista de funcionários
        System.out.println("\n3.4 - Os funcionários receberam 10% de aumento no salário, atualizando a lista de funcionários:");
        imprimirFuncionarios(funcionarios); // Os funcionários já foram atualizados no loop anterior.

        // 3.5 - Agrupar os funcionários por função em um MAP, sendo a chave "função" e o valor a "lista de funcionários".
        System.out.println("\n3.5 - Agrupar os funcionários por função em um MAP:");
        Map<String, List<Funcionario>> funcionariosPorFuncao = agruparFuncionariosPorFuncao(funcionarios);
        imprimirFuncionariosPorFuncao(funcionariosPorFuncao);

        // 3.6 - Imprimir os funcionários agrupados por função.
        System.out.println("\n3.6 - Imprimir os funcionários agrupados por função:");
        imprimirFuncionariosPorFuncao(funcionariosPorFuncao);

        // 3.7 - Imprimir os funcionários que fazem aniversário no mês 10 e 12.
        System.out.println("\n3.7 - Imprimir os funcionários que fazem aniversário no mês 10 e 12:");
        imprimirFuncionariosAniversario(funcionarios, 10, 12);

        // 3.8 - Imprimir os funcionários com a maior idade, exibir os atributos: nome e idade.
        System.out.println("\n3.8 - Imprimir os funcionários com a maior idade:");
        imprimirFuncionariosComMaiorIdade(funcionarios);

        // 3.9 - Imprimir a lista de funcionários por ordem alfabética.
        System.out.println("\n3.9 - Imprimir a lista de funcionários por ordem alfabética:");
        Collections.sort(funcionarios);
        imprimirFuncionarios(funcionarios);

        // 3.10 - Imprimir o total dos salários dos funcionários.
        System.out.println("\n3.10 - Imprimir o total dos salários dos funcionários:");
        BigDecimal totalSalarios = calcularTotalSalarios(funcionarios);
        System.out.println("Total dos salários: R$" + totalSalarios.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString().replace('.', ','));

        // 3.11 - Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212,00.
        System.out.println("\n3.11 - Imprimir quantos salários mínimos ganha cada funcionário:");
        imprimirSalariosMinimos(funcionarios, new BigDecimal("1212.00"));

        // 3.12 - Imprimir o total dos salários dos funcionários
        System.out.println("\n3.12 - Imprimir o total dos salários dos funcionários:");
        totalSalarios = calcularTotalSalarios(funcionarios);
        System.out.println("Total dos salários: R$" + totalSalarios.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString().replace('.', ','));
    }

    // Função para imprimir a lista de funcionários
    private static void imprimirFuncionarios(List<Funcionario> funcionarios) {
        for (Funcionario f : funcionarios) {
            System.out.println(f);
        }
    }

    // Função para agrupar os funcionários por função
    private static Map<String, List<Funcionario>> agruparFuncionariosPorFuncao(List<Funcionario> funcionarios) {
        Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();
        for (Funcionario f : funcionarios) {
            if (!funcionariosPorFuncao.containsKey(f.getFuncao())) {
                funcionariosPorFuncao.put(f.getFuncao(), new ArrayList<>());
            }
            funcionariosPorFuncao.get(f.getFuncao()).add(f);
        }
        return funcionariosPorFuncao;
    }

    // Função para imprimir os funcionários agrupados por função
    private static void imprimirFuncionariosPorFuncao(Map<String, List<Funcionario>> funcionariosPorFuncao) {
        for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
            System.out.println("Função: " + entry.getKey());
            for (Funcionario f : entry.getValue()) {
                System.out.println("  " + f);
            }
        }
    }

    // Função para imprimir os funcionários que fazem aniversário no mês 10 e 12
    private static void imprimirFuncionariosAniversario(List<Funcionario> funcionarios, int mes1, int mes2) {
        for (Funcionario f : funcionarios) {
            if (f.getDataNascimento().getMonthValue() == mes1 || f.getDataNascimento().getMonthValue() == mes2) {
                System.out.println(f);
            }
        }
    }

    // Função para imprimir os funcionários com a maior idade
    private static void imprimirFuncionariosComMaiorIdade(List<Funcionario> funcionarios) {
        int maiorIdade = 0;
        for (Funcionario f : funcionarios) {
            int idade = LocalDate.now().getYear() - f.getDataNascimento().getYear();
            if (idade > maiorIdade) {
                maiorIdade = idade;
            }
        }
        for (Funcionario f : funcionarios) {
            int idade = LocalDate.now().getYear() - f.getDataNascimento().getYear();
            if (idade == maiorIdade) {
                System.out.println(f);
            }
        }
    }

    // Função para calcular o total dos salários dos funcionários
    private static BigDecimal calcularTotalSalarios(List<Funcionario> funcionarios) {
        BigDecimal totalSalarios = BigDecimal.ZERO;
        for (Funcionario f : funcionarios) {
            totalSalarios = totalSalarios.add(f.getSalario());
        }
        return totalSalarios;
    }

    // Função para imprimir quantos salários mínimos ganha cada funcionário
    private static void imprimirSalariosMinimos(List<Funcionario> funcionarios, BigDecimal salarioMinimo) {
        for (Funcionario f : funcionarios) {
            BigDecimal salariosMinimos = f.getSalario().divide(salarioMinimo, BigDecimal.ROUND_DOWN);
            System.out.println("Nome: " + f.getNome() + ", Salário Mínimo: " + salariosMinimos.intValue());
        }
    }
}

// Classe Funcionario
class Funcionario extends Pessoa implements Comparable<Funcionario> {

    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    // Método para aumentar o salário
    public void aumentarSalario(BigDecimal percentual) {
        salario = salario.add(salario.multiply(percentual));
    }

    // Implementação do método compareTo para ordenação alfabética
    @Override
    public int compareTo(Funcionario outroFuncionario) {
        return this.getNome().compareTo(outroFuncionario.getNome());
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "nome='" + getNome() + '\'' +
                ", dataNascimento=" + getDataNascimento() +
                ", salario=" + salario +
                ", funcao='" + funcao + '\'' +
                '}';
    }
}

// Classe Pessoa
class Pessoa {

    private String nome;
    private LocalDate dataNascimento;

    public Pessoa(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                '}';
    }
}