package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {

    private ConexaoBD(){}
    private static ConexaoBD instancia;

    public synchronized static ConexaoBD getInstancia(){
        if(instancia == null){
            instancia = new ConexaoBD();
        }
        return instancia;
    }

    public Connection getConexao() throws SQLException {
        String url = "jdbc:sqlite:meu_banco.db";
        return DriverManager.getConnection(url);
    }
}