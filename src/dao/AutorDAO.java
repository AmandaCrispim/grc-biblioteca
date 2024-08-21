package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO {

    public void inserir(Autor autor) {
        String sql = "INSERT INTO Autor (ID_Autor, Nome, Nacionalidade) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoBD.getConexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, autor.getIdAutor());
            stmt.setString(2, autor.getNome());
            stmt.setString(3, autor.getNacionalidade());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Autor autor) {
        String sql = "UPDATE Autor SET Nome = ?, Nacionalidade = ? WHERE ID_Autor = ?";

        try (Connection conn = ConexaoBD.getConexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, autor.getNome());
            stmt.setString(2, autor.getNacionalidade());
            stmt.setInt(3, autor.getIdAutor());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int idAutor) {
        String sql = "DELETE FROM Autor WHERE ID_Autor = ?";

        try (Connection conn = ConexaoBD.getConexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAutor);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Autor> listarTodos() {
        List<Autor> autores = new ArrayList<>();
        String sql = "SELECT * FROM Autor";

        try (Connection conn = ConexaoBD.getConexao(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Autor autor = new Autor(rs.getInt("ID_Autor"), rs.getString("Nome"), rs.getString("Nacionalidade"));
                autores.add(autor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return autores;
    }
}

