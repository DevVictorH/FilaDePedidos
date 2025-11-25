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

public class Main {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   SISTEMA DE FILA DE PEDIDOS");
        System.out.println("   Arquitetura MVC + Design Patterns");
        System.out.println("========================================\n");

        PedidoController controller = new PedidoController();
        PedidoView view = new PedidoView();

        Produto burger = new Produto("X-Burger", 20.0, 10);
        Produto refri = new Produto("Refrigerante", 6.0, 2);
        Produto batata = new Produto("Batata Frita", 8.0, 5);
        Produto suco = new Produto("Suco Natural", 5.0, 3);

        List<Produto> produtos = Arrays.asList(burger, refri, batata, suco);
        view.exibirListaProdutos(produtos);

        System.out.println(">>> Usando BUILDER PATTERN para criar pedido <<<");
        PedidoBuilder builder = new PedidoBuilder();
        Pedido pedido1 = builder
                .adicionarItem(burger, 2)
                .adicionarItem(refri, 1)
                .comCanal(Canal.BALCAO)
                .comPrioridade(Prioridade.EXPRESSO)
                .comObservacoes("Sem cebola, sem tomate")
                .construir();

        controller.adicionarPedidoNaFila(pedido1);
        view.exibirDetalhesPedido(pedido1);

        System.out.println(">>> OBSERVER PATTERN: Notificando mudanças de status <<<");
        controller.atualizarStatusPedido(pedido1, Status.EM_PREPARO);
        controller.atualizarStatusPedido(pedido1, Status.PRONTO);
        controller.atualizarStatusPedido(pedido1, Status.ENTREGUE);

        System.out.println("\n>>> FACTORY PATTERN: Processando pagamento <<<");
        controller.processarPagamento(pedido1, "pix");

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

        System.out.println(">>> STRATEGY PATTERN: Ordenação de fila <<<");

        controller.atualizarStatusPedido(pedido2, Status.EM_PREPARO);
        controller.processarPagamento(pedido2, "cartao");

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
