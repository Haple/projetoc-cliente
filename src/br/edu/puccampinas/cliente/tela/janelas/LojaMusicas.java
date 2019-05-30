package br.edu.puccampinas.cliente.tela.janelas;

import java.awt.BorderLayout;
import java.awt.DefaultKeyboardFocusManager;
import java.awt.KeyEventDispatcher;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.DecimalFormat;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import br.edu.puccampinas.cliente.estrutura.Lista;
import br.edu.puccampinas.cliente.musica.Musica;
import br.edu.puccampinas.cliente.musica.ServicoDeMusica;
import br.edu.puccampinas.cliente.tela.componentes.ListaDesejos;
import br.edu.puccampinas.cliente.tela.componentes.PesquisaMusicas;

public class LojaMusicas extends JFrame
    implements WindowListener, MouseListener, FocusListener, KeyEventDispatcher {

  private static final long serialVersionUID = -3456766838629861471L;

  private String host;
  private int porta;

  private PesquisaMusicas pesquisaMusicas;
  private ListaDesejos listaDesejos;

  public LojaMusicas(String host, int porta) throws Exception {
    if (host == null) {
      throw new Exception("Host não pode estar vazio");
    }
    if (porta <= 0) {
      throw new Exception("Porta inválida");
    }
    this.host = host;
    this.porta = porta;

    this.setTitle("Loja de Músicas");
    this.setSize(1400, 700);
    this.pesquisaMusicas = new PesquisaMusicas(690, 690);
    this.listaDesejos = new ListaDesejos(690, 690);

    this.add(pesquisaMusicas, BorderLayout.WEST);
    this.add(listaDesejos, BorderLayout.EAST);

    this.addWindowListener(this);
    this.pesquisaMusicas.getTxfPalavraChave().addFocusListener(this);
    this.pesquisaMusicas.getBtnPesquisar().addMouseListener(this);
    this.pesquisaMusicas.getLstMusicas().addMouseListener(this);
    this.listaDesejos.getLstDesejos().addMouseListener(this);

    DefaultKeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this);
    this.setLocationRelativeTo(null); // centraliza

    this.setVisible(true);

    this.pesquisaMusicas.getTxfPalavraChave().requestFocus();

  }

  @Override
  public boolean dispatchKeyEvent(KeyEvent arg0) {
    return false;
  }

  @Override
  public void focusGained(FocusEvent arg0) {}

  @Override
  public void focusLost(FocusEvent arg0) {}

  @Override
  public void mouseClicked(MouseEvent evt) {
    if (evt.getButton() == MouseEvent.BUTTON1 && evt.getClickCount() == 1 && !evt.isAltDown()
        && !evt.isAltGraphDown() && !evt.isControlDown() && !evt.isMetaDown()
        && !evt.isShiftDown()) {
      if (evt.getComponent() == this.pesquisaMusicas.getBtnPesquisar())
        this.trateClickEmPesquisar();
    }
    if (evt.getButton() == MouseEvent.BUTTON1 && evt.getClickCount() == 2 && !evt.isAltDown()
        && !evt.isAltGraphDown() && !evt.isControlDown() && !evt.isMetaDown()
        && !evt.isShiftDown()) {
      if (evt.getComponent() == this.pesquisaMusicas.getLstMusicas())
        this.trateClickDuploNaListaDeMusicas();
      if (evt.getComponent() == this.listaDesejos.getLstDesejos())
        this.trateClickDuploNaListaDeDesejos();
    }
  }

  /**
   * Trata os cliques no botão pesquisar
   */
  private void trateClickEmPesquisar() {
    ServicoDeMusica servico = null;
    try {
      servico = new ServicoDeMusica(host, porta);
    } catch (Exception e) {
      System.err.println("Falha ao se conectar ao servidor: " + e.getMessage());
      System.exit(0);
    }
    Lista<Musica> musicas =
        servico.consultaMusicas(this.pesquisaMusicas.getTxfPalavraChave().getText());
    this.pesquisaMusicas.getModMusicas().clear();
    this.pesquisaMusicas.getMusicasRemovidas().limpar();
    while (!musicas.isVazia()) {
      this.pesquisaMusicas.getModMusicas().addElement(musicas.getItem());
      musicas.removeItem();
    }
  }

  /**
   * Trata os cliques duplos nos itens da lista de músicas. Envia a música para a lista de desejos.
   */
  private void trateClickDuploNaListaDeMusicas() {
    int selectedIndex = this.pesquisaMusicas.getLstMusicas().getSelectedIndex();
    if (selectedIndex >= 0) {
      Musica musicaDesejada = this.pesquisaMusicas.getLstMusicas().getSelectedValue();
      if (!this.listaDesejos.getModDesejos().contains(musicaDesejada)) {
        this.pesquisaMusicas.getModMusicas().removeElementAt(selectedIndex);
        this.pesquisaMusicas.getMusicasRemovidas().insereItem(musicaDesejada);
        this.listaDesejos.getModDesejos().addElement(musicaDesejada);
        this.atualizaTotal();
      } else {
        JOptionPane.showMessageDialog(null, "Música já adicionada na lista de desejos.", "Ops!",
            JOptionPane.INFORMATION_MESSAGE);
      }
    }
  }

  /**
   * Trata os cliques duplos nos itens da lista de desejos. Remove a música da lista de desejos e
   * retonar para a lista de músicas caso não exista a música e ela tenha a ver com a pesquisa.
   */
  private void trateClickDuploNaListaDeDesejos() {
    int selectedIndex = this.listaDesejos.getLstDesejos().getSelectedIndex();
    if (selectedIndex >= 0) {
      Musica musicaIndesejada = this.listaDesejos.getLstDesejos().getSelectedValue();
      this.listaDesejos.getModDesejos().removeElementAt(selectedIndex);
      int posicaoDoItemJaExcluido = 0;
      if (!this.pesquisaMusicas.getMusicasRemovidas().isVazia())
        posicaoDoItemJaExcluido =
            this.pesquisaMusicas.getMusicasRemovidas().getItem(musicaIndesejada);
      if (posicaoDoItemJaExcluido > 0) {
        this.pesquisaMusicas.getModMusicas().addElement(musicaIndesejada);
        this.pesquisaMusicas.getMusicasRemovidas().removeItem(posicaoDoItemJaExcluido);
      }
    }
    this.atualizaTotal();
  }

  private void atualizaTotal() {
    float total = 0.0f;
    int duracao = 0;
    DefaultListModel<Musica> modDesejos = this.listaDesejos.getModDesejos();
    for (int i = 0; i < modDesejos.size(); i++) {
      Musica musica = modDesejos.getElementAt(i);
      total += musica.getPreco();
      duracao += musica.getDuracao();
    }
    duracao = duracao / 60;
    if (duracao > 30 && duracao < 60) {
      total = total * 0.9f;
    } else if (duracao > 60 && duracao < 90) {
      total = total * 0.8f;
    } else if (duracao > 90) {
      total = total * 0.7f;
    }
    this.listaDesejos.getLblValorTotal().setText(new DecimalFormat("R$00.00").format(total));
  }

  @Override
  public void mouseEntered(MouseEvent arg0) {}

  @Override
  public void mouseExited(MouseEvent arg0) {}

  @Override
  public void mousePressed(MouseEvent arg0) {}

  @Override
  public void mouseReleased(MouseEvent arg0) {}

  @Override
  public void windowActivated(WindowEvent arg0) {}

  @Override
  public void windowClosed(WindowEvent arg0) {}

  @Override
  public void windowClosing(WindowEvent arg0) {
    System.exit(0);
  }

  @Override
  public void windowDeactivated(WindowEvent arg0) {}

  @Override
  public void windowDeiconified(WindowEvent arg0) {}

  @Override
  public void windowIconified(WindowEvent arg0) {}

  @Override
  public void windowOpened(WindowEvent arg0) {}

}
