package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    private static Connection conexao = null;

    private ConexaoBD() {
        // Construtor privado para impedir instâncias
    }

    public static Connection getConexao() {
        if (conexao == null) {
            try {
                // Configurações de conexão
                String url = "jdbc:mysql://localhost:3306/sua_base_de_dados";
                String usuario = "seu_usuario";
                String senha = "sua_senha";

                conexao = DriverManager.getConnection(url, usuario, senha);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conexao;
    }

    public static void fecharConexao() {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
