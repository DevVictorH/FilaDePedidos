package controller;

import designPatterns.factory.Pagamento;
import designPatterns.factory.PagamentoFactory;
import designPatterns.observer.PainelCaixa;
import designPatterns.observer.PainelCliente;
import designPatterns.observer.PainelCozinha;
import designPatterns.singleton.GerenciadorFilaSingleton;
import model.Pedido;
import model.Produto;
import model.enums.Status;

import java.util.List;

public class PedidoController {
    private GerenciadorFilaSingleton gerenciadorFila;

    public PedidoController() {
        this.gerenciadorFila = GerenciadorFilaSingleton.getInstancia();
    }

    public Pedido criarPedido(List<model.ItemPedido> itens, model.enums.Canal canal, 
                              model.enums.Prioridade prioridade, String observacoes) {
        Pedido pedido = new Pedido(itens, canal, prioridade, observacoes);
        
        pedido.adicionarObservador(new PainelCozinha());
        pedido.adicionarObservador(new PainelCaixa());
        pedido.adicionarObservador(new PainelCliente());
        
        return pedido;
    }

    public void adicionarPedidoNaFila(Pedido pedido) {
        if (pedido.getObservadores().isEmpty()) {
            pedido.adicionarObservador(new PainelCozinha());
            pedido.adicionarObservador(new PainelCaixa());
            pedido.adicionarObservador(new PainelCliente());
        }
        gerenciadorFila.adicionarPedido(pedido);
    }

    public void atualizarStatusPedido(Pedido pedido, Status novoStatus) {
        pedido.mudarStatus(novoStatus);
    }

    public Pedido removerPedidoDaFila() {
        return gerenciadorFila.removerPedido();
    }

    public boolean verificarFilaVazia() {
        return gerenciadorFila.filaVazia();
    }

    public void processarPagamento(Pedido pedido, String tipoPagamento) {
        Pagamento pagamento = PagamentoFactory.criarPagamento(tipoPagamento);
        pagamento.processar(pedido.calcularTotal());
    }

    public double calcularTotalPedido(Pedido pedido) {
        return pedido.calcularTotal();
    }

    public int estimarTempoPreparo(Pedido pedido) {
        return pedido.estimarTempoPreparo();
    }
}

