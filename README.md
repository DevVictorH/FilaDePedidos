# Sistema de Fila de Pedidos

## 📋 Descrição

Sistema de gerenciamento de fila de pedidos implementado em Java, demonstrando a aplicação de **Arquitetura MVC** e **5 Design Patterns**.

## 🏗️ Arquitetura MVC

O projeto está organizado seguindo o padrão **Model-View-Controller**:

### Model (Modelo)
- `model/Pedido.java` - Entidade principal do sistema
- `model/Produto.java` - Representa produtos do cardápio
- `model/ItemPedido.java` - Itens que compõem um pedido
- `model/enums/` - Enumeradores (Canal, Prioridade, Status)

### View (Visão)
- `view/PedidoView.java` - Responsável por exibir informações ao usuário
  - Exibe detalhes de pedidos
  - Lista produtos disponíveis
  - Mostra atualizações de status

### Controller (Controlador)
- `controller/PedidoController.java` - Gerencia a lógica de negócio
  - Criação de pedidos
  - Gerenciamento de fila
  - Atualização de status
  - Processamento de pagamentos

## 🎨 Design Patterns Implementados

### 1. **Singleton Pattern**
**Localização:** `designPatterns/singleton/GerenciadorFilaSingleton.java`

Garante que existe apenas uma instância do gerenciador de fila em todo o sistema.

```java
GerenciadorFilaSingleton gerenciador = GerenciadorFilaSingleton.getInstancia();
```

**Benefícios:**
- Controle centralizado da fila de pedidos
- Evita múltiplas instâncias que poderiam causar inconsistências

---

### 2. **Factory Pattern**
**Localização:** `designPatterns/factory/PagamentoFactory.java`

Cria objetos de pagamento sem expor a lógica de criação ao cliente.

```java
Pagamento pagamento = PagamentoFactory.criarPagamento("pix");
```

**Tipos de pagamento:**
- `PagamentoPix` - Pagamento via PIX
- `PagamentoCartao` - Pagamento com cartão
- `PagamentoDinheiro` - Pagamento em dinheiro

**Benefícios:**
- Facilita adição de novos tipos de pagamento
- Encapsula a lógica de criação

---

### 3. **Observer Pattern**
**Localização:** `designPatterns/observer/`

Notifica múltiplos observadores sobre mudanças no status do pedido.

**Componentes:**
- `Observer.java` - Interface do observador
- `PainelCozinha.java` - Observa mudanças para a cozinha
- `PainelCaixa.java` - Observa mudanças para o caixa
- `PainelCliente.java` - Observa mudanças para o cliente

```java
pedido.adicionarObservador(new PainelCozinha());
pedido.mudarStatus(Status.EM_PREPARO); // Notifica todos os observadores
```

**Benefícios:**
- Desacoplamento entre o pedido e seus observadores
- Fácil adição de novos painéis de observação

---

### 4. **Strategy Pattern**
**Localização:** `designPatterns/strategy/`

Define algoritmos intercambiáveis para ordenação da fila de pedidos.

**Componentes:**
- `StrategyFila.java` - Interface da estratégia
- `FilaFIFO.java` - Ordenação First In First Out
- `FilaPrioridade.java` - Ordenação por prioridade

**Benefícios:**
- Flexibilidade para mudar a estratégia de ordenação em tempo de execução
- Facilita adição de novas estratégias

---

### 5. **Builder Pattern**
**Localização:** `designPatterns/builder/PedidoBuilder.java`

Facilita a construção de objetos Pedido complexos de forma fluente e legível.

```java
Pedido pedido = new PedidoBuilder()
    .adicionarItem(burger, 2)
    .adicionarItem(refri, 1)
    .comCanal(Canal.BALCAO)
    .comPrioridade(Prioridade.EXPRESSO)
    .comObservacoes("Sem cebola")
    .construir();
```

**Benefícios:**
- Código mais legível e expressivo
- Validação durante a construção
- Permite construção passo a passo

---

## 📁 Estrutura do Projeto

```
FilaDePedidos/
├── src/
│   ├── controller/          # Camada Controller (MVC)
│   │   └── PedidoController.java
│   ├── view/                # Camada View (MVC)
│   │   └── PedidoView.java
│   ├── model/               # Camada Model (MVC)
│   │   ├── Pedido.java
│   │   ├── Produto.java
│   │   ├── ItemPedido.java
│   │   └── enums/
│   │       ├── Canal.java
│   │       ├── Prioridade.java
│   │       └── Status.java
│   ├── designPatterns/
│   │   ├── singleton/
│   │   │   └── GerenciadorFilaSingleton.java
│   │   ├── factory/
│   │   │   ├── Pagamento.java
│   │   │   ├── PagamentoFactory.java
│   │   │   ├── PagamentoPix.java
│   │   │   ├── PagamentoCartao.java
│   │   │   └── PagamentoDinheiro.java
│   │   ├── observer/
│   │   │   ├── Observer.java
│   │   │   ├── PainelCozinha.java
│   │   │   ├── PainelCaixa.java
│   │   │   └── PainelCliente.java
│   │   ├── strategy/
│   │   │   ├── StrategyFila.java
│   │   │   ├── FilaFIFO.java
│   │   │   └── FilaPrioridade.java
│   │   └── builder/
│   │       └── PedidoBuilder.java
│   └── Main.java
└── README.md
```

## 🚀 Como Executar

1. Compile o projeto:
```bash
javac -d out src/**/*.java
```

2. Execute a classe principal:
```bash
java -cp out Main
```

## 📊 Fluxo de Funcionamento

1. **Criação de Pedido**: Usando o Builder Pattern, cria-se um pedido com itens, canal, prioridade e observações.

2. **Adição à Fila**: O pedido é adicionado à fila através do Singleton GerenciadorFilaSingleton.

3. **Observação de Status**: Os observadores (Cozinha, Caixa, Cliente) são notificados automaticamente quando o status muda.

4. **Processamento de Pagamento**: Usando o Factory Pattern, cria-se o tipo de pagamento apropriado.

5. **Visualização**: A View exibe todas as informações de forma organizada.

## 🎯 Objetivos Alcançados

✅ Arquitetura MVC implementada  
✅ 5 Design Patterns implementados:
   - Singleton
   - Factory
   - Observer
   - Strategy
   - Builder

## 👨‍💻 Autor

Projeto desenvolvido para demonstração de Design Patterns e Arquitetura MVC em Java.

