/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author csarp
 */
public class Professor extends Pessoa{
    private int faculdade;

    public int getFaculdade() {
        return faculdade;
    }

    public void setFaculdade(int faculdade) {
        this.faculdade = faculdade;
    }
    
    //olhar se da erro quando usar
    public void setLogin(){
        this.login = this.getCpf();
    }
    
}
