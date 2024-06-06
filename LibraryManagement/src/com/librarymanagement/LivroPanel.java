package com.librarymanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LivroPanel extends JFrame {
    private DefaultListModel<String> livroListModel;
    private DefaultListModel<String> detalhesListModel;
    private JList<String> livroList;
    private JTextArea detalhesTextArea;

    public LivroPanel() {
        setTitle("Gerenciamento de Livros");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        livroListModel = new DefaultListModel<>();
        livroList = new JList<>(livroListModel);
        detalhesListModel = new DefaultListModel<>();
        detalhesTextArea = new JTextArea();
        detalhesTextArea.setEditable(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));

        JButton adicionarButton = new JButton("Adicionar Livro");
        JButton editarButton = new JButton("Editar Detalhes");
        JButton removerButton = new JButton("Remover Livro");
        JButton voltarButton = new JButton("Voltar ao Menu");

        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField tituloField = new JTextField();
                JTextField autorField = new JTextField();
                JTextField editoraField = new JTextField();
                JTextField edicaoField = new JTextField();

                JPanel inputPanel = new JPanel();
                inputPanel.setLayout(new GridLayout(5, 2));
                inputPanel.add(new JLabel("Título:"));
                inputPanel.add(tituloField);
                inputPanel.add(new JLabel("Autor:"));
                inputPanel.add(autorField);
                inputPanel.add(new JLabel("Editora:"));
                inputPanel.add(editoraField);
                inputPanel.add(new JLabel("Edição:"));
                inputPanel.add(edicaoField);

                int result = JOptionPane.showConfirmDialog(null, inputPanel, "Adicionar Livro",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    String titulo = tituloField.getText();
                    String autor = autorField.getText();
                    String editora = editoraField.getText();
                    String edicao = edicaoField.getText();

                    if (!titulo.isEmpty()) {
                        Livro livro = new Livro();
                        livro.setTitulo(titulo);
                        livro.setAutor(autor);
                        livro.setEditora(editora);
                        livro.setEdicao(edicao);

                        Database.insertLivro(livro);
                        carregarLivros();
                    } else {
                        JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
                    }
                }
            }
        });

        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = livroList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String detalhes = detalhesTextArea.getText();
                    String novoDetalhe = JOptionPane.showInputDialog("Editar detalhes:", detalhes);
                    if (novoDetalhe != null) {
                        detalhesListModel.set(selectedIndex, novoDetalhe);
                        detalhesTextArea.setText(novoDetalhe);
                    }
                }
            }
        });

        removerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = livroList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Livro livro = Database.getAllLivros().get(selectedIndex);
                    Database.deleteLivro(livro.getId());
                    carregarLivros();
                    detalhesListModel.clear();
                    detalhesTextArea.setText("");
                }
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        buttonPanel.add(adicionarButton);
        buttonPanel.add(editarButton);
        buttonPanel.add(removerButton);
        buttonPanel.add(voltarButton);

        add(new JScrollPane(livroList), BorderLayout.WEST);
        add(new JScrollPane(detalhesTextArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        livroList.addListSelectionListener(e -> {
            int selectedIndex = livroList.getSelectedIndex();
            if (selectedIndex != -1) {
                String detalhes = detalhesListModel.getElementAt(selectedIndex);
                detalhesTextArea.setText(detalhes);
            }
        });

        carregarLivros();

        setVisible(true);
    }

    private void carregarLivros() {
        livroListModel.clear();
        detalhesListModel.clear();
        List<Livro> livros = Database.getAllLivros();
        for (Livro livro : livros) {
            livroListModel.addElement(livro.getTitulo());
            detalhesListModel.addElement("Autor: " + livro.getAutor() +
                    ", Editora: " + livro.getEditora() +
                    ", Edição: " + livro.getEdicao());
        }
    }
}
