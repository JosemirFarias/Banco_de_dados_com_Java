package jbdc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CriarTabelaPessoas {
    public static void main(String[] args) throws SQLException {
        Connection conexao = FabricaConexao.getConnection();

        String sql = "CREATE TABLE IF NOT EXISTS pessoas (codigo INT PRIMARY KEY AUTO_INCREMENT, nome VARCHAR(100))";

        Statement stmt = conexao.createStatement();
        stmt.execute(sql);

        System.out.println("Tabela criada com sucesso!");
        conexao.close();
    }
}
