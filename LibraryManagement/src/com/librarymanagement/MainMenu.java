package com.librarymanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    private LivroPanel livroPanel;
    private ClientePanel clientePanel;
    private EmprestimoPanel emprestimoPanel;

    public MainMenu() {
        setTitle("Menu Principal");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel background = new JLabel(new ImageIcon("images/background.jpg"));
        setContentPane(background);
        setLayout(new FlowLayout());

        JButton gerenciarLivrosButton = new JButton("Gerenciar Livros");
        JButton gerenciarClientesButton = new JButton("Gerenciar Clientes");
        JButton gerenciarEmprestimosButton = new JButton("Gerenciar Empréstimos");

        livroPanel = new LivroPanel();
        clientePanel = new ClientePanel();

        gerenciarLivrosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                livroPanel.setVisible(true);
                clientePanel.setVisible(false);
                if (emprestimoPanel != null) {
                    emprestimoPanel.setVisible(false);
                }
            }
        });

        gerenciarClientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientePanel.setVisible(true);
                livroPanel.setVisible(false);
                if (emprestimoPanel != null) {
                    emprestimoPanel.setVisible(false);
                }
            }
        });

        gerenciarEmprestimosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (emprestimoPanel == null) {
                    emprestimoPanel = new EmprestimoPanel();
                    add(emprestimoPanel, BorderLayout.CENTER);
                }
                emprestimoPanel.setVisible(true);
                livroPanel.setVisible(false);
                clientePanel.setVisible(false);
            }
        });

        add(gerenciarLivrosButton);
        add(gerenciarClientesButton);
        add(gerenciarEmprestimosButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}
