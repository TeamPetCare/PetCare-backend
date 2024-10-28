package com.application.petcare.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ListaObj <T>{
    private T[] vetor;
    private int nroElem;

    public ListaObj(int tamanho) {
        vetor = (T[]) new Object[tamanho];
        nroElem = 0;
    }

    public void adiciona(T elemento) {
        if(elemento != null){
            if(nroElem < vetor.length){
                vetor[nroElem++] = elemento;
            } else {
                throw new IllegalStateException("Lista cheia");
            }
        }else {
            throw new IllegalStateException("Não é um objeto válido");
        }

    }

    public void addAll(List v){
        for (int i = 0; i < v.size(); i++) {
            adiciona( (T) v.get(i));
        }
    }

    public void set(int indice, T elemento) {
        if (indice >= 0 && indice < nroElem) {
            vetor[indice] = elemento;
        } else {
            throw new IndexOutOfBoundsException("Índice fora dos limites da lista");
        }
    }

    public List convertToList(){
        List list = new ArrayList<>();

        for (int i = 0; i < nroElem; i++) {
            list.add(vetor[i]);
        }
        return list;
    }

    public int busca(T elementoBuscado) {
        for (int i = 0; i < nroElem; i++) {
            if(vetor[i].equals(elementoBuscado)){
                return i;
            }
        }
        return -1;
    }

    public boolean removePeloIndice(int indice) {
        if(indice >= 0 && indice < nroElem){
            for (int i = indice; i < nroElem - 1; i++) {
                vetor[i] = vetor[i + 1];
            }
            vetor[nroElem - 1] = null;
            nroElem--;
            return true;
        }
        return false;
    }

    public boolean removeElemento(T elementoARemover) {
        return removePeloIndice(busca(elementoARemover));
    }

    public int getTamanho() {
        return nroElem;
    }

    public T getElemento(int indice) {
        if(indice >= 0 && indice < nroElem){
            return vetor[indice];
        }
        return null;
    }

    public void limpa() {
        for (int i = 0; i < nroElem; i++) {
            vetor[i] = null;
        }
        nroElem = 0;
    }

    public void exibe() {
        String stringExibir = "";
        for (int i = 0; i < nroElem; i++) {
            if (i == 0) {
                stringExibir += "[" + vetor[i];
            } else {
                stringExibir += ", " + vetor[i];
            }
            if(i == nroElem -1){
                stringExibir += vetor[i] + "]";
            }
        }
        if(nroElem == 0){
            stringExibir = "A Lista está vazia";
        }
        System.out.println(stringExibir);
    }

    public T[] getVetor() {
        return vetor;
    }
}
