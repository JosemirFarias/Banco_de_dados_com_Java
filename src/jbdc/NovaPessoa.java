package jbdc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class NovaPessoa {
    public static void main(String[] args) throws SQLException {
        Scanner entrada = new Scanner(System.in);

        String sqlVerificar = "SELECT * FROM pessoas WHERE nome = ?";
        String sqlAdd = "INSERT INTO pessoas (nome) VALUES (?)";
        Connection conexao = FabricaConexao.getConnection();

        try {  // Loop controlado pela entrada do usuário.
            for (; ; ) {
                System.out.println("Realizar novo cadastro? S/N ");
                String novoCadastro = entrada.nextLine();

                // Condições para continuar com o cadras ou sair da aplicação.
                if (novoCadastro.equalsIgnoreCase("S")) {
                    System.out.println("Informe o nome: ");
                    String nome = entrada.nextLine();

                    //Verifica se o dado já esta cadastrado.
                    try (PreparedStatement stmtVerificar = conexao.prepareStatement(sqlVerificar)) {
                        stmtVerificar.setString(1, nome);
                        ResultSet rs = stmtVerificar.executeQuery();

                        if (rs.next()) {
                            System.out.println("Dados já existentes!");
                            continue; // Volta ao início do loop sem tentar inserir
                        }
                    }
                    // Adiciona o novo dado
                    try (PreparedStatement stmtAdd = conexao.prepareStatement(sqlAdd)) {
                        stmtAdd.setString(1, nome);
                        stmtAdd.execute();
                        System.out.println("Pessoa inserida com sucesso!");
                    }
                } else if (novoCadastro.equalsIgnoreCase("N")) {
                    System.out.println("Encerrando!");
                    break;
                } else {
                    System.out.println("Erro digite (S) para novo cadastro ou (N) para sair!");
                }
            }
        } finally {
            entrada.close();
            conexao.close();
        }
    }
}
