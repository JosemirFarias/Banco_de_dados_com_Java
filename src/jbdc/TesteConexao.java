package jbdc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TesteConexao {
    public static void main(String[] args) {

        // URL do banco de dados
        String url = "jdbc:mysql://localhost:3306/curso_java"; // Ajuste para o seu banco
        String usuario = "root"; // Substitua pelo seu usuário do banco
        String senha = "8558jfbm"; // Substitua pela sua senha do banco

        // Conexão com o banco
        try {
            Connection conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão bem-sucedida!");
            conexao.close(); // Feche a conexão após o uso
        } catch (SQLException e) {
            System.err.println("Erro ao conectar: " + e.getMessage());
        }
    }
}
