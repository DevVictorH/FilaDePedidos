package designPatterns.strategy;

import model.Pedido;

import java.util.List;

public interface StrategyFila {

    List<Pedido> ordenar(List<Pedido> pedidos);

}
