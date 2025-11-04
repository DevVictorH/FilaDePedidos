package model;

import designPatterns.observer.Observer;
import model.enums.Canal;
import model.enums.Prioridade;
import model.enums.Status;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    static int contador = 1;
    private int id;
    private List<ItemPedido> itens;
    private Canal canal;
    private Prioridade prioridade;
    private String observacoes;
    private Status status;
    private List<Observer> observadores = new ArrayList<>();


    public Pedido(List<ItemPedido> itens, Canal canal, Prioridade prioridade, String observacoes) {
        this.id = contador++;
        this.itens = itens;
        this.canal = canal;
        this.prioridade = prioridade;
        this.observacoes = observacoes;
        this.status = Status.RECEBIDO;
    }


    public double calcularTotal() {
        return itens.stream().mapToDouble(ItemPedido::getSubtotal).sum();
    }


    public int estimarTempoPreparo() {
        return itens.stream().mapToInt(ItemPedido::getTempoTotal).sum();
    }


    public void adicionarObservador(Observer obs) {
        observadores.add(obs);
    }


    public void mudarStatus(Status novoStatus) {
        this.status = novoStatus;
        notificar();
    }


    private void notificar() {
        observadores.forEach(o -> o.atualizar(this));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public Canal getCanal() {
        return canal;
    }

    public void setCanal(Canal canal) {
        this.canal = canal;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Observer> getObservadores() {
        return observadores;
    }

    public void setObservadores(List<Observer> observadores) {
        this.observadores = observadores;
    }
}
