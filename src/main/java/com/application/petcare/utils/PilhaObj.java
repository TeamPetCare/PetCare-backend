package com.application.petcare.utils;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class PilhaObj<T> {

    private T[] pilha;
    private int topo;

    public PilhaObj(int capacidade) {
        pilha = (T[]) new Object[capacidade];
        topo = -1;
    }

    public Boolean isEmpty() {
        return topo == -1;
    }

    public Boolean isFull() {
        return topo == (pilha.length-1);
    }

    public void push(T info) {
        if(topo < (pilha.length - 1)){
            pilha[topo + 1] = info;
            topo++;
        }else{
            throw new IllegalStateException("Pilha cheia");
        }
    }

    public T pop() {
        if(topo >= 0){
            return pilha[topo--];
        }else{
            throw new IllegalStateException("Pilha vazia");
        }
    }

    public Object peek() {
        if(topo == -1){
            return -1;
        }
        return pilha[topo];
    }

    public void exibe() {
        if (topo >= 0) {
            int topoProvisorio = topo;
            String exibe = "";
            while (topoProvisorio >= 0) {
                exibe += pilha[topoProvisorio];
                topoProvisorio--;
            }
        } else {
            System.out.println("Pilha vazia");
        }
    }

    public int getTopo() {
        return topo;
    }
}