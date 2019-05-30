package br.edu.puccampinas.cliente.tela.componentes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.edu.puccampinas.cliente.musica.Musica;

public class ListaDesejos extends JPanel {

  private static final long serialVersionUID = 6004168521278139690L;
  JLabel lblValorTotal = new JLabel("R$ 00,00");
  private DefaultListModel<Musica> modDesejos = new DefaultListModel<Musica>();
  private JList<Musica> lstDesejos = new JList<Musica>(this.modDesejos);

  public ListaDesejos(int largura, int altura) {
    this.setLayout(new BorderLayout());
    this.setPreferredSize(new Dimension(largura, altura));

    Font fntPtFixa = new Font("Arial", Font.BOLD, 16);
    Font fntPtVar = new Font("Arial", Font.PLAIN, 13);

    // Painel da Lista de Desejos
    JLabel lblMusicas = new JLabel("Lista de desejos:");
    lblMusicas.setFont(fntPtFixa);
    lblMusicas.setBorder(new EmptyBorder(30, 0, 0, 0));
    this.lstDesejos.setFont(fntPtVar);
    this.lstDesejos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    this.lstDesejos.setSelectedIndex(0);
    JScrollPane scrMusicas = new JScrollPane(this.lstDesejos);

    JPanel pnlMusicasDesejadas = new JPanel();
    pnlMusicasDesejadas.setLayout(new BorderLayout());
    pnlMusicasDesejadas.add(lblMusicas, BorderLayout.NORTH);
    pnlMusicasDesejadas.add(scrMusicas, BorderLayout.CENTER);

    // Painel do total
    this.lblValorTotal.setFont(fntPtVar);
    this.lblValorTotal.setHorizontalAlignment(SwingConstants.RIGHT);
    JPanel pnlTotal = new JPanel();
    pnlTotal.setLayout(new GridLayout(1, 2));
    JLabel lblTotal = new JLabel("Total: ");
    lblTotal.setFont(fntPtFixa);
    pnlTotal.add(lblTotal, BorderLayout.WEST);
    pnlTotal.add(this.lblValorTotal, BorderLayout.EAST);

    JPanel pnlListaDesejos = new JPanel();
    BorderLayout borderLayout = new BorderLayout();
    borderLayout.preferredLayoutSize(this);
    pnlListaDesejos.setLayout(borderLayout);
    pnlListaDesejos.add(pnlMusicasDesejadas, BorderLayout.CENTER);
    pnlListaDesejos.add(pnlTotal, BorderLayout.SOUTH);

    this.add(pnlListaDesejos);
  }

  public JLabel getLblValorTotal() {
    return lblValorTotal;
  }

  public void setLblValorTotal(JLabel lblValorTotal) {
    this.lblValorTotal = lblValorTotal;
  }

  public DefaultListModel<Musica> getModDesejos() {
    return modDesejos;
  }

  public void setModDesejos(DefaultListModel<Musica> modDesejos) {
    this.modDesejos = modDesejos;
  }

  public JList<Musica> getLstDesejos() {
    return lstDesejos;
  }

  public void setLstDesejos(JList<Musica> lstDesejos) {
    this.lstDesejos = lstDesejos;
  }
}
