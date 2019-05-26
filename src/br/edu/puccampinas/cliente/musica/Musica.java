package br.edu.puccampinas.cliente.musica;

import java.text.DecimalFormat;

/**
 * Representa um registro de música do banco de dados.
 * 
 * @author aleph
 *
 */
public class Musica implements Cloneable {

  private int codigo;
  private String titulo;
  private String cantor;
  private String estilo;
  private float preco;
  private int duracao;

  /**
   * 
   * @return devolve o código de uma música
   */
  public int getCodigo() {
    return codigo;
  }

  private void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  /**
   * 
   * @return devolve o código de uma música
   */
  public String getTitulo() {
    return titulo;
  }

  private void setTitulo(String titulo) throws Exception {
    if (titulo == null || titulo.equals(""))
      throw new Exception("Título é obrigatório");
    this.titulo = titulo;
  }

  /**
   * 
   * @return devolve o cantor de uma música
   */
  public String getCantor() {
    return cantor;
  }

  private void setCantor(String cantor) throws Exception {
    if (cantor == null || cantor.equals(""))
      throw new Exception("Cantor é obrigatório");
    this.cantor = cantor;
  }

  /**
   * 
   * @return devolve o estilo de uma música
   */
  public String getEstilo() {
    return estilo;
  }

  private void setEstilo(String estilo) throws Exception {
    if (estilo == null || estilo.equals(""))
      throw new Exception("Estilo é obrigatório");
    this.estilo = estilo;
  }

  /**
   * 
   * @return devolve o preço de uma música
   */
  public float getPreco() {
    return preco;
  }

  private void setPreco(float preco) throws Exception {
    if (preco <= 0)
      throw new Exception("Preço inválido");
    this.preco = preco;
  }

  /**
   * 
   * @return devolve a duração de uma música
   */
  public int getDuracao() {
    return duracao;
  }

  private String getDuracaoFormatada() {
    int hours = duracao / 3600;
    int minutes = (duracao % 3600) / 60;
    int seconds = duracao % 60;
    String resp = "";
    if (hours > 0) {
      resp += hours == 1 ? String.format("%02d hora ", hours) : String.format("%02d horas ", hours);
    }
    if (minutes > 0) {
      resp += minutes == 1 ? String.format("%02d minuto ", minutes)
          : String.format("%02d minutos ", minutes);
    }
    if (seconds > 0) {
      resp += seconds == 1 ? String.format("%02d segundo", seconds)
          : String.format("%02d segundos", seconds);
    }
    return resp.endsWith(" ") ? resp.substring(0, resp.length() - 1) : resp;
  }

  private void setDuracao(int duracao) throws Exception {
    if (duracao <= 0)
      throw new Exception("Duração inválida");
    this.duracao = duracao;
  }

  /**
   * Cria uma nova música.
   * 
   * @param codigo
   * @param titulo
   * @param cantor
   * @param estilo
   * @param preco
   * @param duracao
   * @throws Exception Caso algum parâmetro seja inválido
   */
  public Musica(int codigo, String titulo, String cantor, String estilo, float preco, int duracao)
      throws Exception {
    this.setCodigo(codigo);
    this.setTitulo(titulo);
    this.setCantor(cantor);
    this.setEstilo(estilo);
    this.setPreco(preco);
    this.setDuracao(duracao);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((cantor == null) ? 0 : cantor.hashCode());
    result = prime * result + codigo;
    result = prime * result + duracao;
    result = prime * result + ((estilo == null) ? 0 : estilo.hashCode());
    result = prime * result + Float.floatToIntBits(preco);
    result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
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
    Musica other = (Musica) obj;
    if (cantor == null) {
      if (other.cantor != null)
        return false;
    } else if (!cantor.equals(other.cantor))
      return false;
    if (codigo != other.codigo)
      return false;
    if (duracao != other.duracao)
      return false;
    if (estilo == null) {
      if (other.estilo != null)
        return false;
    } else if (!estilo.equals(other.estilo))
      return false;
    if (Float.floatToIntBits(preco) != Float.floatToIntBits(other.preco))
      return false;
    if (titulo == null) {
      if (other.titulo != null)
        return false;
    } else if (!titulo.equals(other.titulo))
      return false;
    return true;
  }

  /**
   * Cria uma nova música baseando-se em outra música.
   * 
   * @param modelo
   * @throws Exception caso a música seja nula.
   */
  public Musica(Musica modelo) throws Exception {
    if (modelo == null) {
      throw new Exception("Musica não pode ser nula");
    }
    this.codigo = modelo.codigo;
    this.titulo = modelo.titulo;
    this.cantor = modelo.cantor;
    this.estilo = modelo.estilo;
    this.preco = modelo.preco;
    this.duracao = modelo.duracao;
  }

  public Object clone() {
    Musica ret = null;
    try {
      ret = new Musica(this);
    } catch (Exception erro) {
    }
    return ret;
  }

  @Override
  public String toString() {
    return titulo + ", por " + cantor + " - " + estilo + ", R$"
        + new DecimalFormat("##.00").format(preco) + " (" + this.getDuracaoFormatada() + ")";
  }

}
