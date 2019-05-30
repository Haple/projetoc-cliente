package br.edu.puccampinas.cliente.tela.componentes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import br.edu.puccampinas.cliente.estrutura.Lista;
import br.edu.puccampinas.cliente.musica.Musica;

public class PesquisaMusicas extends JPanel {

  private static final long serialVersionUID = 6004168521278139690L;
  private JTextField txfPalavraChave = new JTextField();
  private JButton btnPesquisar = new JButton("Pesquisar");
  private Lista<Musica> musicasRemovidas = new Lista<Musica>();
  private DefaultListModel<Musica> modMusicas = new DefaultListModel<Musica>();
  private JList<Musica> lstMusicas = new JList<Musica>(this.modMusicas);

  public PesquisaMusicas(int largura, int altura) {
    this.setLayout(new BorderLayout());
    this.setPreferredSize(new Dimension(largura, altura));

    Font fntPtFixa = new Font("Arial", Font.BOLD, 16);
    Font fntPtVar = new Font("Arial", Font.PLAIN, 13);

    this.btnPesquisar.setFont(fntPtFixa);
    this.lstMusicas.setFont(fntPtVar);
    this.txfPalavraChave.setFont(fntPtVar);
    this.txfPalavraChave.setText("Digite aqui sua palavra chave");

    JLabel lblMusicas = new JLabel("Lista de músicas:");
    lblMusicas.setFont(fntPtFixa);

    lstMusicas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    JScrollPane scrMusicas = new JScrollPane(lstMusicas);
    lstMusicas.setSelectedIndex(0);

    JPanel pnlPesquisar = new JPanel();
    pnlPesquisar.setLayout(new GridLayout(1, 2));
    pnlPesquisar.add(this.txfPalavraChave, BorderLayout.WEST);
    pnlPesquisar.add(this.btnPesquisar, BorderLayout.EAST);

    JPanel pnlMusicas = new JPanel();
    pnlMusicas.setLayout(new BorderLayout());
    pnlMusicas.add(lblMusicas, BorderLayout.NORTH);
    pnlMusicas.add(scrMusicas, BorderLayout.CENTER);

    JPanel pnlPesquisaDeMusicas = new JPanel();
    BorderLayout borderLayout = new BorderLayout();
    borderLayout.preferredLayoutSize(this);
    pnlPesquisaDeMusicas.setLayout(borderLayout);
    pnlPesquisaDeMusicas.add(pnlPesquisar, BorderLayout.NORTH);
    pnlPesquisaDeMusicas.add(pnlMusicas, BorderLayout.CENTER);

    this.add(pnlPesquisaDeMusicas);
  }

  public JTextField getTxfPalavraChave() {
    return txfPalavraChave;
  }

  public void setTxfPalavraChave(JTextField txfPalavraChave) {
    this.txfPalavraChave = txfPalavraChave;
  }

  public JButton getBtnPesquisar() {
    return btnPesquisar;
  }

  public void setBtnPesquisar(JButton btnPesquisar) {
    this.btnPesquisar = btnPesquisar;
  }

  public DefaultListModel<Musica> getModMusicas() {
    return modMusicas;
  }

  public void setModMusicas(DefaultListModel<Musica> modMusicas) {
    this.modMusicas = modMusicas;
  }

  public JList<Musica> getLstMusicas() {
    return lstMusicas;
  }

  public void setLstMusicas(JList<Musica> lstMusicas) {
    this.lstMusicas = lstMusicas;
  }

  public Lista<Musica> getMusicasRemovidas() {
    return musicasRemovidas;
  }

  public void setMusicasRemovidas(Lista<Musica> musicasRemovidas) {
    this.musicasRemovidas = musicasRemovidas;
  }

}
