package designPatterns.observer;

import model.Pedido;

public class PainelCliente implements Observer{

    @Override
    public void atualizar(Pedido pedido) {
        System.out.println("[Cliente] Seu pedido " + pedido.getId() + " está " + pedido.getStatus());
    }
}
