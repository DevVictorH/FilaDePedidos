package designPatterns.factory;

public class PagamentoFactory {

    public static Pagamento criarPagamento(String tipo) {
        return switch (tipo.toLowerCase()) {
            case "pix" -> new PagamentoPix();
            case "cartao" -> new PagamentoCartao();
            default -> new PagamentoDinheiro();
        };
    }
}
