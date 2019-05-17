package br.edu.puccampinas.comum;

import java.io.Serializable;
import java.util.Arrays;

public class Comunicado implements Serializable {

  private static final long serialVersionUID = 1L;
  private String comando;
  private String[] complementos;

  public Comunicado(String comando, String... complementos) throws Exception {
    if (comando == null || comando.equals(""))
      throw new Exception("Comando ausente");
    if (complementos == null)
      throw new Exception("Complementos ausentes");
    this.comando = comando;
    this.complementos = complementos;
  }

  public Comunicado(String comando) throws Exception {
    if (comando == null || comando.equals(""))
      throw new Exception("Comando ausente");
    this.comando = comando;
  }

  public String getComando() {
    return comando;
  }

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
