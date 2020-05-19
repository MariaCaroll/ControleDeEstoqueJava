package model;

import java.util.Date;

/**
*
* @author Maria Lima
*/
public class ModelVendas {

    private int idVenda;
    private int cliente;
    private Date venDataVenda;
    private Double venValorLiquido;
    private Double venValorBruto;
    private Double venDesconto;

    /**
    * Construtor
    */
    public ModelVendas(){}

    /**
    * seta o valor de idVenda
    * @param pIdVenda
    */
    public void setIdVenda(int pIdVenda){
        this.idVenda = pIdVenda;
    }
    /**
    * @return pk_idVenda
    */
    public int getIdVenda(){
        return this.idVenda;
    }

    /**
    * seta o valor de cliente
    * @param pCliente
    */
    public void setCliente(int pCliente){
        this.cliente = pCliente;
    }
    /**
    * @return fk_cliente
    */
    public int getCliente(){
        return this.cliente;
    }

    /**
    * seta o valor de venDataVenda
    * @param pVenDataVenda
    */
    public void setVenDataVenda(Date pVenDataVenda){
        this.venDataVenda = pVenDataVenda;
    }
    /**
    * @return venDataVenda
    */
    public Date getVenDataVenda(){
        return this.venDataVenda;
    }

    /**
    * seta o valor de venValorLiquido
    * @param pVenValorLiquido
    */
    public void setVenValorLiquido(Double pVenValorLiquido){
        this.venValorLiquido = pVenValorLiquido;
    }
    /**
    * @return venValorLiquido
    */
    public Double getVenValorLiquido(){
        return this.venValorLiquido;
    }

    /**
    * seta o valor de venValorBruto
    * @param pVenValorBruto
    */
    public void setVenValorBruto(Double pVenValorBruto){
        this.venValorBruto = pVenValorBruto;
    }
    /**
    * @return venValorBruto
    */
    public Double getVenValorBruto(){
        return this.venValorBruto;
    }

    /**
    * seta o valor de venDesconto
    * @param pVenDesconto
    */
    public void setVenDesconto(Double pVenDesconto){
        this.venDesconto = pVenDesconto;
    }
    /**
    * @return venDesconto
    */
    public Double getVenDesconto(){
        return this.venDesconto;
    }

    @Override
    public String toString(){
        return "ModelVendas {" + "::idVenda = " + this.idVenda + "::cliente = " + this.cliente + "::venDataVenda = " + this.venDataVenda + "::venValorLiquido = " + this.venValorLiquido + "::venValorBruto = " + this.venValorBruto + "::venDesconto = " + this.venDesconto +  "}";
    }
}