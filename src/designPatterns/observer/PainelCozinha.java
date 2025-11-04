package designPatterns.observer;

import model.Pedido;

public class PainelCozinha implements Observer{

    @Override
    public void atualizar(Pedido pedido) {
        System.out.println("[Cozinha] Pedido " + pedido.getId() + " agora está " + pedido.getStatus());
    }
}
