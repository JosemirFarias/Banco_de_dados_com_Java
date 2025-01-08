package jbdc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DeletarRegistro {
    public static void main(String[] args) throws SQLException {
        Scanner entrada = new Scanner(System.in);

        System.out.println("Digite o número do registro: ");
        int codigo = entrada.nextInt();

        String select = "SELECT codigo, nome FROM pessoas WHERE codigo = ?";
        String delete = "DELETE FROM pessoas WHERE codigo = ?";
        Connection conexao = FabricaConexao.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(select);
        stmt.setInt(1, codigo);
        ResultSet rs = stmt.executeQuery();

        // Busca e exibe o dado cadastrado.
        if (rs.next()) {
            Pessoa p = new Pessoa(rs.getInt(1), rs.getString(2));

            System.out.println("Nome cadastrado: " + p.getNome());
            entrada.nextLine();

            stmt.close();

            // Pede a confirmação do DELETE ao usuário, em seguida realiza as operações dentro das condições descritas.
            System.out.println("Tem certeza que deseja excluir " + p.getNome() + "? S/N. ");
            String confirmar = entrada.nextLine();

            if (confirmar.equalsIgnoreCase("S")) {

                stmt = conexao.prepareStatement(delete);
                stmt.setInt(1, codigo);
                stmt.executeUpdate();

                System.out.println("Cadastro excluido com sucesso!");

            } else if (confirmar.equalsIgnoreCase("N")) {
                System.out.println("Ação cancelada pelo usuário!");

            } else {
                System.out.println("Erro digite (S) para excluir registro ou (N) para cancelar ação!");
            }

        } else {
            System.out.println("Registro não encontrado!");
        }

        conexao.close();
        entrada.close();
    }
}
