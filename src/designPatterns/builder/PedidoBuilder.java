package designPatterns.builder;

import model.ItemPedido;
import model.Pedido;
import model.Produto;
import model.enums.Canal;
import model.enums.Prioridade;

import java.util.ArrayList;
import java.util.List;

public class PedidoBuilder {
    private List<ItemPedido> itens;
    private Canal canal;
    private Prioridade prioridade;
    private String observacoes;

    public PedidoBuilder() {
        this.itens = new ArrayList<>();
    }

    public PedidoBuilder adicionarItem(Produto produto, int quantidade) {
        this.itens.add(new ItemPedido(produto, quantidade));
        return this;
    }

    public PedidoBuilder comCanal(Canal canal) {
        this.canal = canal;
        return this;
    }

    public PedidoBuilder comPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
        return this;
    }

    public PedidoBuilder comObservacoes(String observacoes) {
        this.observacoes = observacoes;
        return this;
    }

    public Pedido construir() {
        if (itens.isEmpty()) {
            throw new IllegalStateException("Pedido deve ter pelo menos um item");
        }
        if (canal == null) {
            throw new IllegalStateException("Canal é obrigatório");
        }
        if (prioridade == null) {
            prioridade = Prioridade.NORMAL;
        }
        if (observacoes == null) {
            observacoes = "";
        }

        return new Pedido(itens, canal, prioridade, observacoes);
    }
}

