package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    public void inserir(Livro livro) {
        String sql = "INSERT INTO Livro (ID_Livro, Título, Ano_Publicação, ID_Autor) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.getConexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, livro.getIdLivro());
            stmt.setString(2, livro.getTitulo());
            stmt.setInt(3, livro.getAnoPublicacao());
            stmt.setInt(4, livro.getIdAutor());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Livro livro) {
        String sql = "UPDATE Livro SET Título = ?, Ano_Publicação = ?, ID_Autor = ? WHERE ID_Livro = ?";

        try (Connection conn = ConexaoBD.getConexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setInt(2, livro.getAnoPublicacao());
            stmt.setInt(3, livro.getIdAutor());
            stmt.setInt(4, livro.getIdLivro());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int idLivro) {
        String sql = "DELETE FROM Livro WHERE ID_Livro = ?";

        try (Connection conn = ConexaoBD.getConexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idLivro);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Livro> listarTodos() {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM Livro";

        try (Connection conn = ConexaoBD.getConexao(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Livro livro = new Livro(rs.getInt("ID_Livro"), rs.getString("Título"), rs.getInt("Ano_Publicação"), rs.getInt("ID_Autor"));
                livros.add(livro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return livros;
    }

    public List<Livro> listarPorAutor(int idAutor) {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM Livro WHERE ID_Autor = ?";

        try (Connection conn = ConexaoBD.getConexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAutor);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Livro livro = new Livro(rs.getInt("ID_Livro"), rs.getString("Título"), rs.getInt("Ano_Publicação"), rs.getInt("ID_Autor"));
                    livros.add(livro);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return livros;
    }
}
