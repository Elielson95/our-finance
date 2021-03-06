package br.ucsal.pdm.ourfinances;

import java.io.Serializable;
import java.util.Date;

public class Launch implements Serializable {

    private int id;
    private Double valor;
    private String data;
    private String descricao;
    private String tipo;

    public Launch() {

    }

    public Launch(int id, Double valor, String data, String descricao, String tipo) {
        this.id = id;
        this.valor = valor;
        this.data = data;
        this.descricao = descricao;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}