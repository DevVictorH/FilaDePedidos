package designPatterns.observer;

import model.Pedido;

public class PainelCaixa implements Observer{

    @Override
    public void atualizar(Pedido pedido) {
        System.out.println("[Caixa] Pedido " + pedido.getId()+ " mudou para " + pedido.getStatus());
    }
}
