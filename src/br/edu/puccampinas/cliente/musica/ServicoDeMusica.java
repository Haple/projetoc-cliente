package br.edu.puccampinas.cliente.musica;

import br.edu.puccampinas.cliente.Requisicao;
import br.edu.puccampinas.cliente.estrutura.Lista;
import br.edu.puccampinas.comum.Comunicado;

/**
 * Classe responsável por servir de interface entre o cliente e o servidor
 * 
 * @author aleph
 *
 */
public class ServicoDeMusica {

  private Requisicao servidor;

  /**
   * Cria um objeto do serviço de música
   * 
   * @param host
   * @param porta
   * @throws Exception
   */
  public ServicoDeMusica(String host, int porta) throws Exception {
    if (host == null) {
      throw new Exception("Host não pode estar vazio");
    }
    if (porta <= 0) {
      throw new Exception("Porta inválida");
    }
    try {
      this.servidor = new Requisicao(host, porta);
    } catch (Exception erro) {
      throw new Exception("Indique o servidor e a porta corretos!");
    }
  }

  /**
   * Busca no servidor músicas no servidor de acordo com umva chave de busca
   * 
   * @param chaveDeBusca
   * @return devolve uma lista de músicas baseado na chave de busca
   */
  public Lista<Musica> consultaMusicas(String chaveDeBusca) {
    Lista<Musica> musicas = new Lista<Musica>();
    // Recebe mensagens do servidor
    try {
      Lista<Comunicado> comunicados = servidor.envie(new Comunicado("COM", chaveDeBusca));
      while (!comunicados.isVazia()) {
        Comunicado c = comunicados.getItem();
        if (c == null || c.getComando().equals("ERR")) {
          System.err.println("Músicas não encontradas (erro no servidor)");
        } else if (c.getComando().equals("FIC")) {
          System.out.println("Fim da consulta");
        } else if (c.getComando().equals("MUS")) {
          musicas.insereItem(
              new Musica(Integer.valueOf(c.getComplemento(0)).intValue(), c.getComplemento(1),
                  c.getComplemento(2), c.getComplemento(3), Float.parseFloat(c.getComplemento(4)),
                  Integer.valueOf(c.getComplemento(5)).intValue()));
        }
        comunicados.removeItem();
      }
    } catch (Exception erro) {
      erro.printStackTrace();
    }
    return musicas;
  }
}
