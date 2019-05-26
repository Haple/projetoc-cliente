package br.edu.puccampinas.cliente.estrutura;

/**
 * Classe responsável por representar uma lista de itens.
 * 
 * @author aleph
 *
 * @param <TipoItem> representa o tipo de itens que a lista deve armazenar
 */
public class Lista<TipoItem> {
  /**
   * Classe responsável por representar um nó de uma lista.
   * 
   * Um nó é representado por um item e o próximo nó em que ele está ligado, dessa forma, criando
   * uma lista encadeada.
   * 
   * @author aleph
   *
   */
  private class No {
    private TipoItem item;
    private No proximo;

    public No(TipoItem item, No proximo) {
      this.item = item;
      this.proximo = proximo;
    }

    public TipoItem getItem() {
      return this.item;
    }

    public No getProximo() {
      return this.proximo;
    }

    @SuppressWarnings("unused")
    public void setItem(TipoItem item) {
      this.item = item;
    }

    public void setProximo(No proximo) {
      this.proximo = proximo;
    }
  } // fim da classe No

  private No primeiro, ultimo;
  private int tamanho;

  public Lista() {
    limpar();
  }

  public void limpar() {
    this.primeiro = null;
    this.ultimo = null;
    this.tamanho = 0;
  }

  /**
   * Insere no final da lista
   * 
   * @param item Item a ser inserido
   * @throws Exception Caso o valor a ser inserido seja nulo
   */
  public void insereItem(TipoItem item) throws RuntimeException {
    if (item == null)
      throw new RuntimeException("Valor ausente");

    No penultimo = this.ultimo;
    this.ultimo = new No(item, null);

    if (this.isVazia())
      this.primeiro = this.ultimo;
    else
      penultimo.setProximo(this.ultimo);

    this.tamanho++;
  }

  /**
   * Insere em uma posição especifica.
   * 
   * @param item Item a ser inserido
   * @param posicao Posicao na lista em que o item deve ser inserido
   * @throws Exception Caso o valor da posição seja inválido (menor ou igual a zero ou maior que o
   *         tamanho da lista) ou o valor a ser inserido seja nulo
   */
  public void insereItem(TipoItem item, int posicao) throws Exception {
    if (item == null)
      throw new Exception("Valor ausente");

    if (posicao <= 0 || posicao > (this.getTamanho() + 1))
      throw new Exception("Posição inválida!");

    // verifica vazia ou insercao no final da lista
    if (this.isVazia() || posicao == this.getTamanho() + 1)
      this.insereItem(item);
    else {// insercao no inicio ou no meio da lista

      // cria o novo no
      No novoNo = new No(item, null);

      // inserção no inicio
      if (posicao == 1) {
        novoNo.setProximo(this.primeiro);
        this.primeiro = novoNo;
      } else {// encontra o item anterior a posicao de insercao
        No anterior = this.primeiro;
        for (int i = 2; i < posicao; i++)
          anterior = anterior.getProximo();

        // atualiza o proximo do novo e do anterior
        novoNo.setProximo(anterior.getProximo());
        anterior.setProximo(novoNo);
      }
      this.tamanho++;
    }
  }

  /**
   * Remove do inicio da lista.
   * 
   * @throws Exception Caso não tenha nenhum item na lista.
   */
  public void removeItem() throws RuntimeException {
    if (this.isVazia())
      throw new RuntimeException("Nada guardado");

    this.primeiro = this.primeiro.getProximo();
    this.tamanho--;
  }

  /**
   * Remove de uma posição específica.
   * 
   * @param posicao Posição do item que deve ser removido
   * @throws Exception Caso o valor da posição seja inválido (menor ou igual a zero ou maior que o
   *         tamanho da lista) ou a lista não tenha nenhum registro
   */
  public void removeItem(int posicao) throws RuntimeException {
    if (this.isVazia())
      throw new RuntimeException("Nada guardado");

    if (posicao <= 0 || posicao > this.getTamanho())
      throw new RuntimeException("Posição inválida!");

    // remove do inicio
    if (posicao == 1)
      removeItem();
    else {
      // encontra anterior ao item que sera removido
      No anterior = this.primeiro;
      for (int i = 2; i < posicao; i++)
        anterior = anterior.getProximo();

      // atualiza proximo do anterior
      anterior.setProximo(anterior.getProximo().getProximo());

      // remocao na ultima posicao, atualiza o ultimo
      if (posicao == this.getTamanho())
        this.ultimo = anterior;

      this.tamanho--;
    }
  }

  /**
   * Recupera o item da primeira posição.
   * 
   * @return Devolve o item da primeira posição da lista
   * @throws Exception Caso não tenha nada guardado na lista.
   */
  public TipoItem getItem() throws RuntimeException {
    if (this.isVazia())
      throw new RuntimeException("Nada guardado");

    return this.primeiro.getItem();
  }

  /**
   * Recupera o item de uma posição específica.
   * 
   * @param posicao Posição do item que deve ser encontrado
   * @return O item da posição especificada
   * @throws Exception Caso o valor da posição seja inválido (menor ou igual a zero ou maior que o
   *         tamanho da lista) ou a lista não tenha nenhum registro
   */
  public TipoItem getItem(int posicao) throws RuntimeException {
    if (this.isVazia())
      throw new RuntimeException("Nada guardado");

    if (posicao <= 0 || posicao > this.getTamanho())
      throw new RuntimeException("Posição inválida!");

    if (posicao == this.getTamanho())
      return this.ultimo.getItem();
    else {
      // encontra item a ser retornado
      No item = this.primeiro;
      for (int i = 1; i < posicao; i++)
        item = item.getProximo();

      return item.getItem();

    }
  }

  /**
   * Verifica se um item está guardado na lista.
   * 
   * @param item
   * @return devolve a posição do item encontrado
   * @throws RuntimeException caso não seja possível encontrar o item
   */
  public int getItem(TipoItem item) throws RuntimeException {
    if (item == null)
      throw new RuntimeException("Item inválido!");
    if (this.isVazia())
      throw new RuntimeException("Nada guardado");

    for (int i = 1; i <= this.tamanho; i++) {
      if (item.equals(this.getItem(i))) {
        return i;
      }
    }
    return 0;
  }

  /**
   * Verifica se a lista está vazia.
   * 
   * @return Verdadeiro se a lista estiver vazia e falso caso contrário.
   */
  public boolean isVazia() {
    return this.primeiro == null;
  }

  /**
   * Busca o tamanho da lista.
   * 
   * @return Devolve um inteiro com o tamanho da lista
   */
  public int getTamanho() {
    return this.tamanho;
  }

  @Override
  public String toString() {
    String retorno = "";
    for (int j = 1; j <= this.getTamanho(); j++) {
      try {
        retorno += " " + this.getItem(j);
      } catch (Exception e) {
      }
    }
    return retorno.substring(1);
  }
}
