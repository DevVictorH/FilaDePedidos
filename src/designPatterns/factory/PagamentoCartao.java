package designPatterns.factory;

public class PagamentoCartao extends Pagamento{

    public void processar(double valor) {
        System.out.println("Pagamento com cartão aprovado: R$" + valor);
    }

}
