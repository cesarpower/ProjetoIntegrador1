/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Faculdade;
import model.Turma;
import model.TurmaGenerica;

/**
 *
 * @author Mateus
 */
public class TurmaController {
    @FXML
    private JFXTextField pesquisarField;
    @FXML
    private JFXComboBox faculdadeBox;
    @FXML
    private TableView<TurmaGenerica> turmaTable;
    @FXML
    private TableColumn<TurmaGenerica, String> descricaoColumn;
    @FXML
    private TableColumn<TurmaGenerica, String> cursoColumn;
    @FXML
    private TableColumn<TurmaGenerica, Integer> chColumn;
    @FXML
    private TableColumn<TurmaGenerica, Integer> semestreColumn;
    
    private ArrayList<TurmaGenerica> turmaGenericaList = new ArrayList<TurmaGenerica>();
    private ObservableList<TurmaGenerica> genericas;
    
    private ArrayList<Faculdade> faculdadeList = new ArrayList();
    
    private ArrayList<String> faculdadeNomes = new ArrayList();
    private ArrayList<Integer> faculdadeIds = new ArrayList();
    
    private BorderPane primaryStage;
    
    /**
     * Inicializa a classe controlle. Este método é chamado automaticamente
     * após o arquivo fxml ter sido carregado.
     */
    @FXML
    public void initialize() {
        descricaoColumn.setCellValueFactory(cellData -> cellData.getValue().getDescricao());
        cursoColumn.setCellValueFactory(cellData -> cellData.getValue().getCurso());
        //chColumn.setCellValueFactory(cellData -> cellData.getValue().getCh());
        semestreColumn.setCellValueFactory(cellData -> cellData.getValue().getSemestre().asObject());
        
        faculdadeBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                int index = faculdadeBox.getSelectionModel().getSelectedIndex(); //Pega o index da seleção pra buscar no array de IDs
                int faculdadeId = faculdadeIds.get(index);
            }
            
            //Aqui filtra as turmas pelo id da faculdade e retorna um arraylist atualizado
        });
    }

    public void setPrimaryStage(BorderPane primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setTurmaGenericaList(ArrayList<TurmaGenerica> turmaGenericaList) {
        this.turmaGenericaList = turmaGenericaList;
        this.genericas = FXCollections.observableArrayList(this.turmaGenericaList);
        turmaTable.setItems(genericas);
    }

    public void setFaculdadeList(ArrayList<Faculdade> faculdadeList) {
        this.faculdadeList = faculdadeList;
        
        for(Faculdade f: faculdadeList){
            faculdadeNomes.add(f.getNome());
            faculdadeIds.add(f.getIdFaculdade());
        }
        
        faculdadeBox.setItems(FXCollections.observableArrayList(faculdadeNomes));
    }
    
    @FXML
    private void adicionar(){
        Turma turma = new Turma();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TurmaForm.fxml")); //Carrega o arquivo FXML
            AnchorPane page = (AnchorPane) loader.load();
            Stage stage = new Stage(); //Cria um novo Stage
            stage.initOwner(primaryStage.getScene().getWindow()); //Seta um stage pai
            stage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            stage.setScene(scene);
            
            TurmaForm controller = loader.getController(); //Puxa a referência do controller instanciado
            controller.setDialogStage(stage);
            controller.setTurma(turma);
            
            stage.showAndWait(); //Exibe janela e pausa esta thread
            
            if(turma.getDescricao() != null){
                //salva no banco
            }
        
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void excluir(){
        Turma turma = turmaTable.getSelectionModel().getSelectedItem().getTurma();
        //Exclui no banco
    }
    
    @FXML
    private void editar(){
        Turma turma = turmaTable.getSelectionModel().getSelectedItem().getTurma();
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TurmaForm.fxml")); //Carrega o arquivo FXML
            AnchorPane page = (AnchorPane) loader.load();
            Stage stage = new Stage(); //Cria um novo Stage
            stage.initOwner(primaryStage.getScene().getWindow()); //Seta um stage pai
            stage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            stage.setScene(scene);
            
            TurmaForm controller = loader.getController(); //Puxa a referência do controller instanciado
            controller.setDialogStage(stage);
            controller.setTurma(turma);
            
            stage.showAndWait(); //Exibe janela e pausa esta thread
            
            if(turma.getDescricao() != null){
                //salva no banco
            }
        
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void detalhes(){
        Turma turma = turmaTable.getSelectionModel().getSelectedItem().getTurma();
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TurmaForm.fxml")); //Carrega o arquivo FXML
            AnchorPane page = (AnchorPane) loader.load();
            Stage stage = new Stage(); //Cria um novo Stage
            stage.initOwner(primaryStage.getScene().getWindow()); //Seta um stage pai
            stage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            stage.setScene(scene);
            
            TurmaForm controller = loader.getController(); //Puxa a referência do controller instanciado
            controller.setDialogStage(stage);
            controller.setTurma(turma);
            controller.setBlock();
            
            stage.showAndWait(); //Exibe janela e pausa esta thread
        
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
