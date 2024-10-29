package com.application.petcare.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

@Getter
@Setter
public class ListaObj<T> implements Iterable<T> {
    private T[] vetor;
    private int nroElem;

    public ListaObj(int tamanho) {
        vetor = (T[]) new Object[tamanho];
        nroElem = 0;
    }

    public void adiciona(T elemento) {
        if (elemento != null) {
            if (nroElem < vetor.length) {
                vetor[nroElem++] = elemento;
            } else {
                throw new IllegalStateException("Lista cheia");
            }
        } else {
            throw new IllegalStateException("Não é um objeto válido");
        }
    }

    public void addAll(List<T> v) {
        for (T item : v) {
            adiciona(item);
        }
    }

    public void set(int indice, T elemento) {
        if (indice >= 0 && indice < nroElem) {
            vetor[indice] = elemento;
        } else {
            throw new IndexOutOfBoundsException("Índice fora dos limites da lista");
        }
    }

    public List<T> convertToList() {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < nroElem; i++) {
            list.add(vetor[i]);
        }
        return list;
    }

    public int busca(T elementoBuscado) {
        for (int i = 0; i < nroElem; i++) {
            if (vetor[i].equals(elementoBuscado)) {
                return i;
            }
        }
        return -1;
    }

    public boolean removePeloIndice(int indice) {
        if (indice >= 0 && indice < nroElem) {
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
        if (indice >= 0 && indice < nroElem) {
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
        StringBuilder stringExibir = new StringBuilder();
        for (int i = 0; i < nroElem; i++) {
            if (i == 0) {
                stringExibir.append("[").append(vetor[i]);
            } else {
                stringExibir.append(", ").append(vetor[i]);
            }
            if (i == nroElem - 1) {
                stringExibir.append("]");
            }
        }
        if (nroElem == 0) {
            stringExibir = new StringBuilder("A Lista está vazia");
        }
        System.out.println(stringExibir);
    }

    public T[] getVetor() {
        return vetor;
    }

    // Implementação do Iterator
    @Override
    public Iterator<T> iterator() {
        return new ListaObjIterator();
    }

    private class ListaObjIterator implements Iterator<T> {
        private int posicaoAtual = 0;

        @Override
        public boolean hasNext() {
            return posicaoAtual < nroElem;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Não há mais elementos.");
            }
            return vetor[posicaoAtual++];
        }
    }
}
