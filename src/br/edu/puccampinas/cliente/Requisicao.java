package br.edu.puccampinas.cliente;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import br.edu.puccampinas.cliente.estrutura.Lista;
import br.edu.puccampinas.comum.Comunicado;

public class Requisicao {

  private String host;
  private int porta;

  public Requisicao(String host, int porta) throws Exception {
    if (host == null || host.trim().equals(""))
      throw new Exception("Host inválido");
    if (porta <= 0)
      throw new Exception("Porta inválida");
    this.host = host;
    this.porta = porta;
  }

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
