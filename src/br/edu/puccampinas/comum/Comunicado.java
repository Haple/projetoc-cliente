package br.edu.puccampinas.comum;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Classe responsável por armazenar uma informação do protocolo.
 * 
 * Todo o tráfego de informações entre cliente e servidor é feito através dessa classe, que é comum
 * aos dois programas.
 * 
 * Essa classe é responsável por transmitir o seguinte:
 * 
 * <ol>
 * <li>Comando: identifica a ação que deve ser tomada por quem recebe o comunicado</li>
 * <li>Complementos: informações extras usadas na interpretação do comando</li>
 * </ol>
 * 
 * @author aleph
 *
 */
public class Comunicado implements Serializable {

  private static final long serialVersionUID = 1L;
  private String comando;
  private String[] complementos;

  /**
   * Cria um objeto do tipo Comunicado.
   * 
   * Garante que os parâmetros usados sejam válidos e instancia um novo Comunicado.
   * 
   * @param comando identifica a ação que deve ser tomada por quem recebe o comunicado
   * @param complementos informações extras usadas na interpretação do comando
   * @throws Exception caso os parâmetros sejam inválidos
   */
  public Comunicado(String comando, String... complementos) throws Exception {
    if (comando == null || comando.equals(""))
      throw new Exception("Comando ausente");
    if (complementos == null)
      throw new Exception("Complementos ausentes");
    this.comando = comando;
    this.complementos = complementos;
  }

  /**
   * Cria um objeto do tipo Comunicado, sem complementos.
   * 
   * Garante que o comando não esteja vazio.
   * 
   * @param comando identifica a ação que deve ser tomada por quem recebe o comunicado
   * @throws Exception caso o comando esteja vazio
   */
  public Comunicado(String comando) throws Exception {
    if (comando == null || comando.equals(""))
      throw new Exception("Comando ausente");
    this.comando = comando;
  }

  /**
   * 
   * @return o comando usado no comunicado
   */
  public String getComando() {
    return comando;
  }

  /**
   * Busca um complemento.
   * 
   * @param posicao posição do complemento alvo
   * @return devolve o complemento da posição almejada
   * @throws Exception caso a posição seja inválida (menor que zero) ou o complemento não exista
   */
  public String getComplemento(int posicao) throws Exception {
    if (posicao < 0)
      throw new Exception("Complemento inválido");
    if (posicao >= complementos.length) {
      throw new Exception("Complemento inexistente");
    }
    return complementos[posicao];
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((comando == null) ? 0 : comando.hashCode());
    result = prime * result + Arrays.hashCode(complementos);
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Comunicado other = (Comunicado) obj;
    if (comando == null) {
      if (other.comando != null)
        return false;
    } else if (!comando.equals(other.comando))
      return false;
    if (!Arrays.equals(complementos, other.complementos))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Comunicado [comando=" + comando + ", complementos=" + Arrays.toString(complementos)
        + "]";
  }
}
