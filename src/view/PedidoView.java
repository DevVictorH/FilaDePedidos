package view;

import model.ItemPedido;
import model.Pedido;
import model.Produto;
import model.enums.Status;

import java.util.List;

public class PedidoView {
    
    public void exibirDetalhesPedido(Pedido pedido) {
        System.out.println("\n========== DETALHES DO PEDIDO ==========");
        System.out.println("ID: " + pedido.getId());
        System.out.println("Canal: " + pedido.getCanal());
        System.out.println("Prioridade: " + pedido.getPrioridade());
        System.out.println("Status: " + pedido.getStatus());
        System.out.println("Observações: " + (pedido.getObservacoes().isEmpty() ? "Nenhuma" : pedido.getObservacoes()));
        System.out.println("\nItens:");
        for (ItemPedido item : pedido.getItens()) {
            Produto produto = item.getProduto();
            System.out.println("  - " + produto.getNome() + 
                             " x" + item.getQuantidade() + 
                             " | R$" + String.format("%.2f", item.getSubtotal()));
        }
        System.out.println("\nTotal: R$" + String.format("%.2f", pedido.calcularTotal()));
        System.out.println("Tempo estimado: " + pedido.estimarTempoPreparo() + " minutos");
        System.out.println("========================================\n");
    }

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public void exibirStatusAtualizado(Pedido pedido) {
        System.out.println("\n>>> Status do Pedido #" + pedido.getId() + 
                         " atualizado para: " + pedido.getStatus() + " <<<");
    }

    public void exibirListaProdutos(List<Produto> produtos) {
        System.out.println("\n========== PRODUTOS DISPONÍVEIS ==========");
        for (int i = 0; i < produtos.size(); i++) {
            Produto p = produtos.get(i);
            System.out.println((i + 1) + ". " + p.getNome() + 
                             " - R$" + String.format("%.2f", p.getPreco()) + 
                             " (Tempo: " + p.getTempoPreparo() + " min)");
        }
        System.out.println("==========================================\n");
    }

    public void exibirResumoPedido(Pedido pedido) {
        System.out.println("\n[RESUMO] Pedido #" + pedido.getId() + 
                         " | Total: R$" + String.format("%.2f", pedido.calcularTotal()) + 
                         " | Status: " + pedido.getStatus());
    }
}

