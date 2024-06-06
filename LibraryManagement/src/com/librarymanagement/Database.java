package com.librarymanagement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String DB_URL = "jdbc:sqlite:library.db";

    public static Connection connect() {
        try {
            return DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void createTables() {
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            String livroTable = "CREATE TABLE IF NOT EXISTS livro (id INTEGER PRIMARY KEY, titulo TEXT, autor TEXT, editora TEXT, edicao TEXT, capa BLOB)";
            String clienteTable = "CREATE TABLE IF NOT EXISTS cliente (id INTEGER PRIMARY KEY, nome TEXT, email TEXT, data_nascimento TEXT, numero_telemovel TEXT, numero_cliente TEXT)";
            String emprestimoTable = "CREATE TABLE IF NOT EXISTS emprestimo (id INTEGER PRIMARY KEY, livro_id INTEGER, cliente_id INTEGER, data_emprestimo TEXT, data_devolucao TEXT)";
            stmt.execute(livroTable);
            stmt.execute(clienteTable);
            stmt.execute(emprestimoTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Métodos para Livro
    public static void insertLivro(Livro livro) {
        String sql = "INSERT INTO livro(titulo, autor, editora, edicao, capa) VALUES(?, ?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, livro.getTitulo());
            pstmt.setString(2, livro.getAutor());
            pstmt.setString(3, livro.getEditora());
            pstmt.setString(4, livro.getEdicao());
            pstmt.setBytes(5, livro.getCapa());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateLivro(Livro livro) {
        String sql = "UPDATE livro SET titulo = ?, autor = ?, editora = ?, edicao = ?, capa = ? WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, livro.getTitulo());
            pstmt.setString(2, livro.getAutor());
            pstmt.setString(3, livro.getEditora());
            pstmt.setString(4, livro.getEdicao());
            pstmt.setBytes(5, livro.getCapa());
            pstmt.setInt(6, livro.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteLivro(int id) {
        String sql = "DELETE FROM livro WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Livro> getAllLivros() {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livro";
        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Livro livro = new Livro();
                livro.setId(rs.getInt("id"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setEditora(rs.getString("editora"));
                livro.setEdicao(rs.getString("edicao"));
                livro.setCapa(rs.getBytes("capa"));
                livros.add(livro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livros;
    }

    public static Livro getLivroById(int id) {
        Livro livro = new Livro();
        String sql = "SELECT * FROM livro WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                livro.setId(rs.getInt("id"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setEditora(rs.getString("editora"));
                livro.setEdicao(rs.getString("edicao"));
                livro.setCapa(rs.getBytes("capa"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livro;
    }

    public static Livro getLivroByTitulo(String titulo) {
        Livro livro = new Livro();
        String sql = "SELECT * FROM livro WHERE titulo = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, titulo);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                livro.setId(rs.getInt("id"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setEditora(rs.getString("editora"));
                livro.setEdicao(rs.getString("edicao"));
                livro.setCapa(rs.getBytes("capa"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livro;
    }

    // Métodos para Cliente
    public static void insertCliente(Cliente cliente) {
        String sql = "INSERT INTO cliente(nome, email, data_nascimento, numero_telemovel, numero_cliente) VALUES(?, ?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getEmail());
            pstmt.setString(3, cliente.getDataNascimento());
            pstmt.setString(4, cliente.getNumeroTelemovel());
            pstmt.setString(5, cliente.getNumeroCliente());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateCliente(Cliente cliente) {
        String sql = "UPDATE cliente SET nome = ?, email = ?, data_nascimento = ?, numero_telemovel = ?, numero_cliente = ? WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getNome());
            pstmt.setString(2, cliente.getEmail());
            pstmt.setString(3, cliente.getDataNascimento());
            pstmt.setString(4, cliente.getNumeroTelemovel());
            pstmt.setString(5, cliente.getNumeroCliente());
            pstmt.setInt(6, cliente.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteCliente(int id) {
        String sql = "DELETE FROM cliente WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Cliente> getAllClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setDataNascimento(rs.getString("data_nascimento"));
                cliente.setNumeroTelemovel(rs.getString("numero_telemovel"));
                cliente.setNumeroCliente(rs.getString("numero_cliente"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public static Cliente getClienteById(int id) {
        Cliente cliente = new Cliente();
        String sql = "SELECT * FROM cliente WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setDataNascimento(rs.getString("data_nascimento"));
                cliente.setNumeroTelemovel(rs.getString("numero_telemovel"));
                cliente.setNumeroCliente(rs.getString("numero_cliente"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    public static Cliente getClienteByNome(String nome) {
        Cliente cliente = new Cliente();
        String sql = "SELECT * FROM cliente WHERE nome = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nome);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setDataNascimento(rs.getString("data_nascimento"));
                cliente.setNumeroTelemovel(rs.getString("numero_telemovel"));
                cliente.setNumeroCliente(rs.getString("numero_cliente"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    // Métodos para Emprestimo
    public static void insertEmprestimo(Emprestimo emprestimo) {
        String sql = "INSERT INTO emprestimo(livro_id, cliente_id, data_emprestimo, data_devolucao) VALUES(?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, emprestimo.getLivroId());
            pstmt.setInt(2, emprestimo.getClienteId());
            pstmt.setString(3, emprestimo.getDataEmprestimo());
            pstmt.setString(4, emprestimo.getDataDevolucao());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateEmprestimo(Emprestimo emprestimo) {
        String sql = "UPDATE emprestimo SET livro_id = ?, cliente_id = ?, data_emprestimo = ?, data_devolucao = ? WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, emprestimo.getLivroId());
            pstmt.setInt(2, emprestimo.getClienteId());
            pstmt.setString(3, emprestimo.getDataEmprestimo());
            pstmt.setString(4, emprestimo.getDataDevolucao());
            pstmt.setInt(5, emprestimo.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteEmprestimo(int id) {
        String sql = "DELETE FROM emprestimo WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Emprestimo> getAllEmprestimos() {
        List<Emprestimo> emprestimos = new ArrayList<>();
        String sql = "SELECT * FROM emprestimo";
        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Emprestimo emprestimo = new Emprestimo();
                emprestimo.setId(rs.getInt("id"));
                emprestimo.setLivroId(rs.getInt("livro_id"));
                emprestimo.setClienteId(rs.getInt("cliente_id"));
                emprestimo.setDataEmprestimo(rs.getString("data_emprestimo"));
                emprestimo.setDataDevolucao(rs.getString("data_devolucao"));
                emprestimos.add(emprestimo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emprestimos;
    }

    public static Emprestimo getEmprestimoById(int id) {
        Emprestimo emprestimo = new Emprestimo();
        String sql = "SELECT * FROM emprestimo WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                emprestimo.setId(rs.getInt("id"));
                emprestimo.setLivroId(rs.getInt("livro_id"));
                emprestimo.setClienteId(rs.getInt("cliente_id"));
                emprestimo.setDataEmprestimo(rs.getString("data_emprestimo"));
                emprestimo.setDataDevolucao(rs.getString("data_devolucao"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emprestimo;
    }
}
