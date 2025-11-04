import designPatterns.factory.Pagamento;
import designPatterns.factory.PagamentoFactory;
import designPatterns.observer.PainelCaixa;
import designPatterns.observer.PainelCliente;
import designPatterns.observer.PainelCozinha;
import model.ItemPedido;
import model.Pedido;
import model.Produto;
import model.enums.Canal;
import model.enums.Prioridade;
import model.enums.Status;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Produto burger = new Produto("X-Burger", 20.0, 10);
        Produto refri = new Produto("Refrigerante", 6.0, 2);


        ItemPedido i1 = new ItemPedido(burger, 1);
        ItemPedido i2 = new ItemPedido(refri, 2);


        Pedido pedido = new Pedido(Arrays.asList(i1, i2), Canal.BALCAO, Prioridade.EXPRESSO, "Sem cebola");
        pedido.adicionarObservador(new PainelCozinha());
        pedido.adicionarObservador(new PainelCaixa());
        pedido.adicionarObservador(new PainelCliente());


        System.out.println("Total: R$" + pedido.calcularTotal());
        System.out.println("Tempo estimado: " + pedido.estimarTempoPreparo() + " min");


        pedido.mudarStatus(Status.EM_PREPARO);
        pedido.mudarStatus(Status.PRONTO);
        pedido.mudarStatus(Status.ENTREGUE);


        Pagamento pagamento = PagamentoFactory.criarPagamento("pix");
        pagamento.processar(pedido.calcularTotal());
    }
}