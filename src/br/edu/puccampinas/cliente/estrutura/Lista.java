package br.edu.puccampinas.cliente.estrutura;

public class Lista<TipoItem> {
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

  // insere no final da lista
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

  // insere em uma posição especifica
  public void insereItem(TipoItem item, int posicao) throws RuntimeException {
    if (item == null)
      throw new RuntimeException("Valor ausente");

    if (posicao <= 0 || posicao > (this.getTamanho() + 1))
      throw new RuntimeException("Posição inválida!");

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

  // remove do inicio da lista
  public void removeItem() throws RuntimeException {
    if (this.isVazia())
      throw new RuntimeException("Nada guardado");

    this.primeiro = this.primeiro.getProximo();
    this.tamanho--;
  }

  // remove de uma posição específica
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

  // recupera o item da primeira posicao
  public TipoItem getItem() throws RuntimeException {
    if (this.isVazia())
      throw new RuntimeException("Nada guardado");

    return this.primeiro.getItem();
  }

  // recupera o item de uma posicao especifica
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

  public boolean isVazia() {
    return this.primeiro == null;
  }

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
