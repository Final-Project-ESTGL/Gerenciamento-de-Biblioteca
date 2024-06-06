package com.librarymanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EmprestimoPanel extends JFrame {
    private JComboBox<String> livroComboBox;
    private JComboBox<String> clienteComboBox;
    private DefaultListModel<String> emprestimoListModel;
    private JList<String> emprestimoList;

    public EmprestimoPanel() {
        setTitle("Gerenciamento de Empréstimos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        livroComboBox = new JComboBox<>();
        clienteComboBox = new JComboBox<>();

        JLabel livroLabel = new JLabel("Livro:");
        JLabel clienteLabel = new JLabel("Cliente:");

        inputPanel.add(livroLabel);
        inputPanel.add(livroComboBox);
        inputPanel.add(clienteLabel);
        inputPanel.add(clienteComboBox);

        JButton adicionarButton = new JButton("Adicionar Empréstimo");
        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String livroSelecionado = (String) livroComboBox.getSelectedItem();
                String clienteSelecionado = (String) clienteComboBox.getSelectedItem();
                if (livroSelecionado != null && clienteSelecionado != null) {
                    // Aqui você registra o empréstimo no banco de dados
                    // Use os métodos do banco de dados para isso
                    String emprestimo = "Livro: " + livroSelecionado + " - Cliente: " + clienteSelecionado;
                    emprestimoListModel.addElement(emprestimo);
                    System.out.println("Empréstimo registrado: " + emprestimo);
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um livro e um cliente para empréstimo.");
                }
            }
        });

        JButton removerButton = new JButton("Remover Empréstimo");
        removerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = emprestimoList.getSelectedIndex();
                if (selectedIndex != -1) {
                    emprestimoListModel.remove(selectedIndex);
                }
            }
        });

        add(inputPanel, BorderLayout.NORTH);

        emprestimoListModel = new DefaultListModel<>();
        emprestimoList = new JList<>(emprestimoListModel);
        JScrollPane emprestimoScrollPane = new JScrollPane(emprestimoList);
        emprestimoScrollPane.setBorder(BorderFactory.createTitledBorder("Empréstimos"));
        add(emprestimoScrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(adicionarButton);
        buttonPanel.add(removerButton);
        add(buttonPanel, BorderLayout.SOUTH);

        carregarLivros();
        carregarClientes();

        setLocationRelativeTo(null); // Centralizar a janela na tela
        setVisible(true); // Exibir a janela
    }

    private void carregarLivros() {
        livroComboBox.removeAllItems();
        List<Livro> livros = Database.getAllLivros();
        for (Livro livro : livros) {
            livroComboBox.addItem(livro.getTitulo());
        }
    }

    private void carregarClientes() {
        clienteComboBox.removeAllItems();
        List<Cliente> clientes = Database.getAllClientes();
        for (Cliente cliente : clientes) {
            clienteComboBox.addItem(cliente.getNome());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EmprestimoPanel();
            }
        });
    }
}
