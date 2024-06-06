package com.librarymanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoPanel extends JPanel {
    private DefaultListModel<String> livroListModel;
    private DefaultListModel<String> clienteListModel;
    private DefaultListModel<String> emprestimoListModel;
    private JList<String> livroList;
    private JList<String> clienteList;
    private JList<String> emprestimoList;

    public EmprestimoPanel() {
        setLayout(new BorderLayout());

        livroListModel = new DefaultListModel<>();
        livroList = new JList<>(livroListModel);

        clienteListModel = new DefaultListModel<>();
        clienteList = new JList<>(clienteListModel);

        emprestimoListModel = new DefaultListModel<>();
        emprestimoList = new JList<>(emprestimoListModel);

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane livroScrollPane = new JScrollPane(livroList);
        livroScrollPane.setBorder(BorderFactory.createTitledBorder("Livros"));
        leftPanel.add(livroScrollPane, BorderLayout.NORTH);

        JScrollPane clienteScrollPane = new JScrollPane(clienteList);
        clienteScrollPane.setBorder(BorderFactory.createTitledBorder("Clientes"));
        leftPanel.add(clienteScrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton adicionarButton = new JButton("Adicionar Empréstimo");
        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int livroIndex = livroList.getSelectedIndex();
                int clienteIndex = clienteList.getSelectedIndex();
                if (livroIndex != -1 && clienteIndex != -1) {
                    String livroSelecionado = livroListModel.getElementAt(livroIndex);
                    String clienteSelecionado = clienteListModel.getElementAt(clienteIndex);
                    String emprestimo = "Livro: " + livroSelecionado + " - Cliente: " + clienteSelecionado;
                    emprestimoListModel.addElement(emprestimo);
                    // Aqui você pode adicionar a lógica para registrar o empréstimo no banco de dados
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um livro e um cliente para empréstimo.");
                }
            }
        });
        buttonPanel.add(adicionarButton);
        leftPanel.add(buttonPanel, BorderLayout.SOUTH);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane emprestimoScrollPane = new JScrollPane(emprestimoList);
        emprestimoScrollPane.setBorder(BorderFactory.createTitledBorder("Empréstimos"));
        rightPanel.add(emprestimoScrollPane, BorderLayout.CENTER);

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        carregarLivros();
        carregarClientes();
    }

    private void carregarLivros() {
        livroListModel.clear();
        // Aqui você pode carregar os livros do banco de dados ou de outra fonte de dados
        List<String> livros = new ArrayList<>();
        // Exemplo de livros fictícios
        livros.add("Livro 1");
        livros.add("Livro 2");
        livros.add("Livro 3");
        for (String livro : livros) {
            livroListModel.addElement(livro);
        }
    }

    private void carregarClientes() {
        clienteListModel.clear();
        // Aqui você pode carregar os clientes do banco de dados ou de outra fonte de dados
        List<String> clientes = new ArrayList<>();
        // Exemplo de clientes fictícios
        clientes.add("Cliente 1");
        clientes.add("Cliente 2");
        clientes.add("Cliente 3");
        for (String cliente : clientes) {
            clienteListModel.addElement(cliente);
        }
    }
}
