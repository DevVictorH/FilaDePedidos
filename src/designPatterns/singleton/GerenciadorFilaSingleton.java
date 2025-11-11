package designPatterns.singleton;

import java.util.LinkedList;
import java.util.Queue;
import model.Pedido;

public class GerenciadorFilaSingleton {

    private static GerenciadorFilaSingleton instancia;
    private Queue<Pedido> filaPedidos;

    private GerenciadorFilaSingleton() {
        filaPedidos = new LinkedList<>();
    }

    public static GerenciadorFilaSingleton getInstancia() {
        if (instancia == null) {
            instancia = new GerenciadorFilaSingleton();
        }
        return instancia;
    }

    public void adicionarPedido(Pedido pedido) {
        filaPedidos.add(pedido);
        System.out.println("Pedido adicionado à fila. Total de pedidos: " + filaPedidos.size());
    }

    public Pedido removerPedido() {
        Pedido p = filaPedidos.poll();
        if (p != null) {
            System.out.println("Pedido removido da fila: " + p);
        }
        return p;
    }

    public boolean filaVazia() {
        return filaPedidos.isEmpty();
    }
}
