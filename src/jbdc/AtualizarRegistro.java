package jbdc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AtualizarRegistro {
    public static void main(String[] args) throws SQLException {
        Scanner entrada = new Scanner(System.in);

        System.out.println("Digite o número do registro: ");
        int codigo = entrada.nextInt();

        String select = "SELECT codigo, nome FROM pessoas WHERE codigo = ?";
        String update = "UPDATE pessoas SET nome = ? WHERE codigo = ?";
        Connection conexao = FabricaConexao.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(select);
        stmt.setInt(1, codigo);
        ResultSet rs = stmt.executeQuery();

        if(rs.next()) {
            Pessoa p = new Pessoa(rs.getInt(1), rs.getString(2));

            System.out.println("Nome cadastrado: " + p.getNome());
            entrada.nextLine();

            System.out.println("Informe o novo nome: ");
            String novoNome = entrada.nextLine();

            stmt.close();

            stmt = conexao.prepareStatement(update);
            stmt.setString(1, novoNome);
            stmt.setInt(2, codigo);
            stmt.executeUpdate();

            System.out.println("Nome atualizado com sucesso!");
        } else {
            System.out.println("Pessoa não encontrada!");
        }

        conexao.close();
        entrada.close();
    }
}
