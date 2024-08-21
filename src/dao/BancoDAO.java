package dao;

import java.sql.Connection;
import java.sql.SQLException;

public class BancoDAO {
    protected Connection conexao() throws SQLException {

        return ConexaoBD.getInstancia().getConexao();
    }
}