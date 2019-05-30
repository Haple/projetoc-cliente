package br.edu.puccampinas.cliente;

import br.edu.puccampinas.cliente.tela.janelas.LojaMusicas;

/**
 * A classe Cliente representa a classe principal da Loja de Músicas. Ela está preparada para
 * receber parâmetros opcionais de comunicação com o servidor, os quais quando não especificados são
 * iniciados com valores padrão. Sua responsabilidade é instanciar uma nova janela da Loja de
 * Músicas.
 * 
 * @author aleph
 *
 */
public class Cliente {
  public static void main(String[] args) {
    if (args.length > 2) {
      System.err.println("Uso esperado: java Cliente [HOST [PORTA]]\n");
      return;
    }
    String host = "localhost";
    int porta = 12345;
    if (args.length > 0)
      host = args[0];
    if (args.length == 2)
      porta = Integer.parseInt(args[1]);
    try {
      new LojaMusicas(host, porta);
    } catch (Exception e) {
      System.err.println("Erro ao criar interface");
    }
  }
}
