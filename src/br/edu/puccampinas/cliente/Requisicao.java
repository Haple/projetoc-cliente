package br.edu.puccampinas.cliente;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import br.edu.puccampinas.cliente.estrutura.Lista;
import br.edu.puccampinas.comum.Comunicado;

/**
 * Representa uma requisição a um servidor
 * 
 * @author aleph
 *
 */
public class Requisicao {

  private String host;
  private int porta;

  /**
   * Cria um objeto de uma requisição
   * 
   * @param host
   * @param porta
   * @throws Exception caso o host esteja vazio ou a porta seja menor ou iguaul a zero
   */
  public Requisicao(String host, int porta) throws Exception {
    if (host == null || host.trim().equals(""))
      throw new Exception("Host inválido");
    if (porta <= 0)
      throw new Exception("Porta inválida");
    this.host = host;
    this.porta = porta;
  }

  /**
   * Envia um novo comunicado para o servidor
   * 
   * @param x
   * @return devolve uma lista de comunicados respondidos pelo servidor
   * @throws Exception caso ocorra um erro ao se comunicar com o servidor
   */
  public Lista<Comunicado> envie(Comunicado x) throws Exception {
    Lista<Comunicado> comunicados = new Lista<Comunicado>();
    try (Socket conexao = new Socket(host, porta);
        ObjectOutputStream transmissor = new ObjectOutputStream(conexao.getOutputStream());
        ObjectInputStream receptor = new ObjectInputStream(conexao.getInputStream())) {
      transmissor.writeObject(x);
      transmissor.flush();
      while (true) {
        Comunicado comunicado;
        try {
          comunicado = (Comunicado) receptor.readObject();
        } catch (Exception e) {
          break;
        }
        comunicados.insereItem(comunicado);
      }
    } catch (Exception erro) {
      erro.printStackTrace();
      throw new Exception("Falha ao se comunicar com o servidor");
    }
    return comunicados;
  }
}
