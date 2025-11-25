import controller.PedidoController;
import designPatterns.builder.PedidoBuilder;
import model.Pedido;
import model.Produto;
import model.enums.Canal;
import model.enums.Prioridade;
import model.enums.Status;
import view.PedidoView;

import java.util.Arrays;
import java.util.List;

/**
 * Sistema de Fila de Pedidos
 * 
 * Este projeto demonstra a implementação de:
 * - Arquitetura MVC (Model-View-Controller)
 * - 5 Design Patterns:
 *   1. Singleton - GerenciadorFilaSingleton
 *   2. Factory - PagamentoFactory
 *   3. Observer - Sistema de notificação de status
 *   4. Strategy - StrategyFila para ordenação
 *   5. Builder - PedidoBuilder para construção de pedidos
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   SISTEMA DE FILA DE PEDIDOS");
        System.out.println("   Arquitetura MVC + Design Patterns");
        System.out.println("========================================\n");

        // Inicialização do MVC
        PedidoController controller = new PedidoController();
        PedidoView view = new PedidoView();

        // Criar produtos disponíveis
        Produto burger = new Produto("X-Burger", 20.0, 10);
        Produto refri = new Produto("Refrigerante", 6.0, 2);
        Produto batata = new Produto("Batata Frita", 8.0, 5);
        Produto suco = new Produto("Suco Natural", 5.0, 3);

        List<Produto> produtos = Arrays.asList(burger, refri, batata, suco);
        view.exibirListaProdutos(produtos);

        // ========== DEMONSTRAÇÃO DO BUILDER PATTERN ==========
        System.out.println(">>> Usando BUILDER PATTERN para criar pedido <<<");
        PedidoBuilder builder = new PedidoBuilder();
        Pedido pedido1 = builder
                .adicionarItem(burger, 2)
                .adicionarItem(refri, 1)
                .comCanal(Canal.BALCAO)
                .comPrioridade(Prioridade.EXPRESSO)
                .comObservacoes("Sem cebola, sem tomate")
                .construir();

        // Usando Controller (MVC)
        controller.adicionarPedidoNaFila(pedido1);
        view.exibirDetalhesPedido(pedido1);

        // ========== DEMONSTRAÇÃO DO OBSERVER PATTERN ==========
        System.out.println(">>> OBSERVER PATTERN: Notificando mudanças de status <<<");
        controller.atualizarStatusPedido(pedido1, Status.EM_PREPARO);
        controller.atualizarStatusPedido(pedido1, Status.PRONTO);
        controller.atualizarStatusPedido(pedido1, Status.ENTREGUE);

        // ========== DEMONSTRAÇÃO DO FACTORY PATTERN ==========
        System.out.println("\n>>> FACTORY PATTERN: Processando pagamento <<<");
        controller.processarPagamento(pedido1, "pix");

        // Criar segundo pedido usando Builder
        System.out.println("\n>>> Criando segundo pedido com BUILDER <<<");
        Pedido pedido2 = new PedidoBuilder()
                .adicionarItem(batata, 1)
                .adicionarItem(suco, 2)
                .comCanal(Canal.APP)
                .comPrioridade(Prioridade.VIP)
                .comObservacoes("Suco sem açúcar")
                .construir();

        controller.adicionarPedidoNaFila(pedido2);
        view.exibirDetalhesPedido(pedido2);

        // ========== DEMONSTRAÇÃO DO STRATEGY PATTERN ==========
        System.out.println(">>> STRATEGY PATTERN: Ordenação de fila <<<");
        // O Strategy Pattern está implementado no GerenciadorFilaSingleton
        // e pode ser usado para ordenar pedidos por diferentes estratégias

        // Processar segundo pedido
        controller.atualizarStatusPedido(pedido2, Status.EM_PREPARO);
        controller.processarPagamento(pedido2, "cartao");

        // ========== DEMONSTRAÇÃO DO SINGLETON PATTERN ==========
        System.out.println("\n>>> SINGLETON PATTERN: Gerenciador de Fila Único <<<");
        System.out.println("Verificando se a fila está vazia: " + controller.verificarFilaVazia());
        controller.removerPedidoDaFila();
        controller.removerPedidoDaFila();
        System.out.println("Fila vazia após remoção: " + controller.verificarFilaVazia());

        System.out.println("\n========================================");
        System.out.println("   DEMONSTRAÇÃO CONCLUÍDA");
        System.out.println("========================================");
    }
}
