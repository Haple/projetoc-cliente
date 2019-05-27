package br.edu.puccampinas.cliente.tela.janelas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;

public class Loja extends JFrame {

  private JPanel contentPane;
  int xx, xy;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          Loja frame = new Loja();
          frame.setUndecorated(true);
          frame.setVisible(true);
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
  public Loja() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 1122, 559);
    contentPane = new JPanel();
    contentPane.setBackground(Color.WHITE);
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);

    JPanel pnlTopo = new JPanel();
    pnlTopo.setBorder(null);
    pnlTopo.setBackground(Color.LIGHT_GRAY);
    pnlTopo.setBounds(0, 0, 1126, 44);
    pnlTopo.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        xx = e.getX();
        xy = e.getY();
      }
    });
    pnlTopo.addMouseMotionListener(new MouseMotionAdapter() {
      @Override
      public void mouseDragged(MouseEvent arg0) {
        int x = arg0.getXOnScreen();
        int y = arg0.getYOnScreen();
        Loja.this.setLocation(x - xx, y - xy);
      }
    });
    contentPane.setLayout(null);
    
    JPanel panel = new JPanel();
    panel.setBorder(null);
    panel.setForeground(Color.WHITE);
    panel.setBackground(Color.DARK_GRAY);
    panel.setBounds(0, 0, 268, 571);
    contentPane.add(panel);
    contentPane.add(pnlTopo);
    pnlTopo.setLayout(null);

    JLabel lblX = new JLabel("");
    lblX.setIcon(new ImageIcon(Loja.class.getResource("/br/edu/puccampinas/cliente/tela/imagens/fechar.png")));
    lblX.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent arg0) {
        fecharJanela();
      }
    });
    lblX.setBounds(1072, 6, 34, 32);
    lblX.setForeground(Color.RED);
    lblX.setFont(new Font("Dialog", Font.BOLD, 14));
    pnlTopo.add(lblX);
  }
}
