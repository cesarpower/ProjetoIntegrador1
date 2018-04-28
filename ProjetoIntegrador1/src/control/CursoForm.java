/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import model.Curso;

/**
 *
 * @author arthurcvm
 */
public class CursoForm {
    @FXML
    private JFXTextField nomeField;
    @FXML
    private JFXComboBox faculdadeBox;
    @FXML
    private JFXTextField qtdSemestresField;
    
    private Stage dialogStage;
    private Curso curso;
    
    /**
     * Inicializa a classe controlle. Este método é chamado automaticamente
     * após o arquivo fxml ter sido carregado.
     */
    @FXML
    public void initialize() {        
        // Carregando lista de estados na Box.
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
        if(curso.getNomeCurso() != null){
            this.nomeField.setText(curso.getNomeCurso());
            //this.faculdadeBox.setValue();
            this.qtdSemestresField.setText(String.valueOf(curso.getQtdSemestres()));
        }
    }
    
    public void setBlock(){
        this.nomeField.setEditable(false);
        this.qtdSemestresField.setEditable(false);
        this.faculdadeBox.setEditable(false);
        
        curso.setNomeCurso(null);
    }
    
    @FXML
    private void cancelar(){
        curso.setNomeCurso(null);
        
        this.dialogStage.close(); //fecha a janela
    }
    
    @FXML
    private void cadastrar(){
        this.curso.setNomeCurso(nomeField.getText());
//        this.curso.setFaculdade(0);
        this.curso.setQtdSemestres(Integer.valueOf(qtdSemestresField.getText()));
        //this.aluno.setSenha(senhaField.getText())
        
        this.dialogStage.close(); //fecha a janela
    }
}
