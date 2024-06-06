package com.librarymanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ClientePanel extends JFrame {
    private DefaultListModel<String> clienteListModel;
    private JList<String> clienteList;
    private JTextArea detalhesTextArea;
    private List<Cliente> clientes;
    private Cliente clienteSelecionado;

    public ClientePanel() {
        setTitle("Gerenciamento de Clientes");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        clienteListModel = new DefaultListModel<>();
        clienteList = new JList<>(clienteListModel);
        detalhesTextArea = new JTextArea();
        detalhesTextArea.setEditable(true);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));

        JButton adicionarButton = new JButton("Adicionar Cliente");
        JButton editarButton = new JButton("Editar Detalhes");
        JButton removerButton = new JButton("Remover Cliente");
        JButton voltarButton = new JButton("Voltar ao Menu");

        carregarClientes();

        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField nomeField = new JTextField(10);
                JTextField emailField = new JTextField(10);
                JTextField dataNascimentoField = new JTextField(10);
                JTextField numeroTelemovelField = new JTextField(10);
                JTextField numeroClienteField = new JTextField(10);

                JPanel panel = new JPanel();
                panel.add(new JLabel("Nome:"));
                panel.add(nomeField);
                panel.add(new JLabel("Email:"));
                panel.add(emailField);
                panel.add(new JLabel("Data de Nascimento:"));
                panel.add(dataNascimentoField);
                panel.add(new JLabel("Número de Telemóvel:"));
                panel.add(numeroTelemovelField);
                panel.add(new JLabel("Número de Cliente:"));
                panel.add(numeroClienteField);

                int result = JOptionPane.showConfirmDialog(null, panel, "Adicionar Cliente", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    Cliente cliente = new Cliente();
                    cliente.setNome(nomeField.getText());
                    cliente.setEmail(emailField.getText());
                    cliente.setDataNascimento(dataNascimentoField.getText());
                    cliente.setNumeroTelemovel(numeroTelemovelField.getText());
                    cliente.setNumeroCliente(numeroClienteField.getText());
                    Database.insertCliente(cliente);
                    carregarClientes();
                }
            }
        });

        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = clienteList.getSelectedIndex();
                if (selectedIndex != -1) {
                    clienteSelecionado = clientes.get(selectedIndex);
                    detalhesTextArea.setText("Nome: " + clienteSelecionado.getNome() +
                            "\nEmail: " + clienteSelecionado.getEmail() +
                            "\nData de Nascimento: " + clienteSelecionado.getDataNascimento() +
                            "\nNúmero de Telemóvel: " + clienteSelecionado.getNumeroTelemovel() +
                            "\nNúmero de Cliente: " + clienteSelecionado.getNumeroCliente());
                }
            }
        });

        detalhesTextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                if (clienteSelecionado != null) {
                    String[] detalhes = detalhesTextArea.getText().split("\n");
                    clienteSelecionado.setNome(detalhes[0].replace("Nome: ", "").trim());
                    clienteSelecionado.setEmail(detalhes[1].replace("Email: ", "").trim());
                    clienteSelecionado.setDataNascimento(detalhes[2].replace("Data de Nascimento: ", "").trim());
                    clienteSelecionado.setNumeroTelemovel(detalhes[3].replace("Número de Telemóvel: ", "").trim());
                    clienteSelecionado.setNumeroCliente(detalhes[4].replace("Número de Cliente: ", "").trim());
                    Database.updateCliente(clienteSelecionado);
                    carregarClientes();
                }
            }
        });

        removerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = clienteList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Cliente cliente = clientes.get(selectedIndex);
                    Database.deleteCliente(cliente.getId());
                    carregarClientes();
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

        add(new JScrollPane(clienteList), BorderLayout.WEST);
        add(new JScrollPane(detalhesTextArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void carregarClientes() {
        clienteListModel.clear();
        clientes = Database.getAllClientes();
        for (Cliente cliente : clientes) {
            clienteListModel.addElement(cliente.getNome());
        }
    }
}
