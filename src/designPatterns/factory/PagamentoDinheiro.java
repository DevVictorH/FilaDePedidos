package designPatterns.factory;

public class PagamentoDinheiro extends Pagamento{

    public void processar(double valor) {
        System.out.println("Pagamento em dinheiro recebido: R$" + valor);
    }

}
