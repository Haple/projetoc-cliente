package br.edu.puccampinas.cliente.tela.janelas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class JanelaMusicas extends JFrame {

  private JPanel contentPane;
  private JTextField textField;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          JanelaMusicas frame = new JanelaMusicas();
          frame.setUndecorated(true);
          frame.setVisible(true);
          frame.setLocationRelativeTo(null);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  private void fecharJanela() {
    this.dispose();
  }

  /**
   * Create the frame.
   */
  public JanelaMusicas() {
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 961, 506);
    contentPane = new JPanel();
    contentPane.setBackground(Color.DARK_GRAY);
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    JButton btnFechar = new JButton("X");
    btnFechar.setIcon(null);
    btnFechar.setForeground(Color.WHITE);
    btnFechar.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent arg0) {
        fecharJanela();
      }
    });
    btnFechar.setBounds(828, 62, 113, 104);
    btnFechar.setOpaque(false);
    btnFechar.setContentAreaFilled(false);
    btnFechar.setBorderPainted(false);
    contentPane.add(btnFechar);

    JPanel panel = new JPanel();
    panel.setBounds(0, 0, 953, 43);
    contentPane.add(panel);
    panel.setLayout(null);
    
        textField = new JTextField();
        textField.setBounds(12, 0, 201, 32);
        panel.add(textField);
        textField.setColumns(10);
  }
}
