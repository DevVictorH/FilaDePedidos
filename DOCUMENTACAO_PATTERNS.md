# Documentação Detalhada dos Design Patterns

## 📚 Índice
1. [Singleton Pattern](#1-singleton-pattern)
2. [Factory Pattern](#2-factory-pattern)
3. [Observer Pattern](#3-observer-pattern)
4. [Strategy Pattern](#4-strategy-pattern)
5. [Builder Pattern](#5-builder-pattern)

---

## 1. Singleton Pattern

### Objetivo
Garantir que existe apenas uma instância do gerenciador de fila em todo o sistema.

### Implementação
**Arquivo:** `src/designPatterns/singleton/GerenciadorFilaSingleton.java`

```java
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
}
```

### Uso no Sistema
- **Controller:** `PedidoController` obtém a instância única do gerenciador
- **Benefício:** Evita múltiplas filas que poderiam causar inconsistências

### Exemplo de Uso
```java
GerenciadorFilaSingleton gerenciador = GerenciadorFilaSingleton.getInstancia();
gerenciador.adicionarPedido(pedido);
```

---

## 2. Factory Pattern

### Objetivo
Criar objetos de pagamento sem expor a lógica de criação ao cliente.

### Implementação
**Arquivos:**
- `src/designPatterns/factory/Pagamento.java` (classe abstrata)
- `src/designPatterns/factory/PagamentoFactory.java` (factory)
- `src/designPatterns/factory/PagamentoPix.java`
- `src/designPatterns/factory/PagamentoCartao.java`
- `src/designPatterns/factory/PagamentoDinheiro.java`

```java
public class PagamentoFactory {
    public static Pagamento criarPagamento(String tipo) {
        return switch (tipo.toLowerCase()) {
            case "pix" -> new PagamentoPix();
            case "cartao" -> new PagamentoCartao();
            default -> new PagamentoDinheiro();
        };
    }
}
```

### Uso no Sistema
- **Controller:** `PedidoController.processarPagamento()` usa a factory
- **Benefício:** Facilita adição de novos tipos de pagamento sem modificar código existente

### Exemplo de Uso
```java
Pagamento pagamento = PagamentoFactory.criarPagamento("pix");
pagamento.processar(46.0);
```

---

## 3. Observer Pattern

### Objetivo
Notificar múltiplos observadores sobre mudanças no status do pedido.

### Implementação
**Arquivos:**
- `src/designPatterns/observer/Observer.java` (interface)
- `src/designPatterns/observer/PainelCozinha.java`
- `src/designPatterns/observer/PainelCaixa.java`
- `src/designPatterns/observer/PainelCliente.java`

**No Model:**
- `src/model/Pedido.java` implementa o Subject (observável)

```java
public interface Observer {
    void atualizar(Pedido pedido);
}

public class Pedido {
    private List<Observer> observadores = new ArrayList<>();
    
    public void mudarStatus(Status novoStatus) {
        this.status = novoStatus;
        notificar();
    }
    
    private void notificar() {
        observadores.forEach(o -> o.atualizar(this));
    }
}
```

### Uso no Sistema
- **Controller:** Registra observadores automaticamente ao adicionar pedido à fila
- **Benefício:** Desacoplamento entre pedido e seus observadores

### Exemplo de Uso
```java
pedido.adicionarObservador(new PainelCozinha());
pedido.adicionarObservador(new PainelCaixa());
pedido.mudarStatus(Status.EM_PREPARO); // Notifica todos
```

**Saída:**
```
[Cozinha] Pedido 1 agora está EM_PREPARO
[Caixa] Pedido 1 mudou para EM_PREPARO
[Cliente] Seu pedido 1 está EM_PREPARO
```

---

## 4. Strategy Pattern

### Objetivo
Definir algoritmos intercambiáveis para ordenação da fila de pedidos.

### Implementação
**Arquivos:**
- `src/designPatterns/strategy/StrategyFila.java` (interface)
- `src/designPatterns/strategy/FilaFIFO.java` (First In First Out)
- `src/designPatterns/strategy/FilaPrioridade.java` (por prioridade)

```java
public interface StrategyFila {
    List<Pedido> ordenar(List<Pedido> pedidos);
}

public class FilaFIFO implements StrategyFila {
    @Override
    public List<Pedido> ordenar(List<Pedido> pedidos) {
        // Ordena por ordem de chegada
        return pedidos;
    }
}

public class FilaPrioridade implements StrategyFila {
    @Override
    public List<Pedido> ordenar(List<Pedido> pedidos) {
        pedidos.sort(Comparator.comparing(Pedido::getPrioridade));
        return pedidos;
    }
}
```

### Uso no Sistema
- Pode ser integrado ao `GerenciadorFilaSingleton` para ordenar pedidos
- **Benefício:** Flexibilidade para mudar estratégia de ordenação em tempo de execução

### Exemplo de Uso
```java
StrategyFila estrategia = new FilaPrioridade();
List<Pedido> pedidosOrdenados = estrategia.ordenar(pedidos);
```

---

## 5. Builder Pattern

### Objetivo
Facilitar a construção de objetos Pedido complexos de forma fluente e legível.

### Implementação
**Arquivo:** `src/designPatterns/builder/PedidoBuilder.java`

```java
public class PedidoBuilder {
    private List<ItemPedido> itens;
    private Canal canal;
    private Prioridade prioridade;
    private String observacoes;

    public PedidoBuilder adicionarItem(Produto produto, int quantidade) {
        this.itens.add(new ItemPedido(produto, quantidade));
        return this;
    }

    public PedidoBuilder comCanal(Canal canal) {
        this.canal = canal;
        return this;
    }

    public PedidoBuilder comPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
        return this;
    }

    public Pedido construir() {
        // Validações e construção do objeto
        return new Pedido(itens, canal, prioridade, observacoes);
    }
}
```

### Uso no Sistema
- **Main:** Usado para criar pedidos de forma fluente
- **Benefício:** Código mais legível, validação durante construção

### Exemplo de Uso
```java
Pedido pedido = new PedidoBuilder()
    .adicionarItem(burger, 2)
    .adicionarItem(refri, 1)
    .comCanal(Canal.BALCAO)
    .comPrioridade(Prioridade.EXPRESSO)
    .comObservacoes("Sem cebola")
    .construir();
```

---

## 🎯 Resumo da Aplicação

| Pattern | Localização | Responsabilidade |
|---------|-------------|------------------|
| **Singleton** | `GerenciadorFilaSingleton` | Gerenciar fila única de pedidos |
| **Factory** | `PagamentoFactory` | Criar tipos de pagamento |
| **Observer** | `Observer` + `Pedido` | Notificar mudanças de status |
| **Strategy** | `StrategyFila` | Ordenar fila de pedidos |
| **Builder** | `PedidoBuilder` | Construir pedidos complexos |

---

## 🔄 Fluxo de Integração

1. **Builder** cria o pedido
2. **Controller** (MVC) gerencia o pedido
3. **Singleton** armazena na fila única
4. **Observer** notifica mudanças
5. **Factory** processa pagamento
6. **Strategy** pode ordenar a fila
7. **View** (MVC) exibe informações

---

## 📝 Notas de Implementação

- Todos os patterns estão integrados e funcionando
- A arquitetura MVC separa responsabilidades claramente
- O código segue princípios SOLID
- Fácil manutenção e extensão

