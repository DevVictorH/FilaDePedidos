package designPatterns.factory;

public class PagamentoPix extends Pagamento{

    public void processar(double valor) {
        System.out.println("Pagamento PIX processado: R$" + valor);
    }

}
