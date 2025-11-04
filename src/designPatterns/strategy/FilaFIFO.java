package designPatterns.strategy;

import model.Pedido;

import java.util.Comparator;
import java.util.List;

public class FilaFIFO implements StrategyFila{

    @Override
    public List<Pedido> ordenar(List<Pedido> pedidos) {
        pedidos.sort(Comparator.comparing(p -> p.getPrioridade()));
        return pedidos;
    }
}
