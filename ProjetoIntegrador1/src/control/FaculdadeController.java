/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import DAO.FaculdadeDAO;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.util.ArrayList;
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
import model.FaculdadeGenerica;

/**
 *
 * @author arthurcvm
 */
public class FaculdadeController {
    @FXML
    private JFXTextField pesquisarField;
    @FXML
    private TableView<FaculdadeGenerica> faculdadeTable;
    @FXML
    private TableColumn<FaculdadeGenerica, String> faculdadeColumn;
    
    private ArrayList<FaculdadeGenerica> faculdadeGenericaList = new ArrayList<FaculdadeGenerica>();
    private ObservableList<FaculdadeGenerica> genericas;
    
    private BorderPane primaryStage;
    
    /**
     * Inicializa a classe controlle. Este método é chamado automaticamente
     * após o arquivo fxml ter sido carregado.
     */
    @FXML
    public void initialize() {
        // Inicializa a tablela de faculdade com suas colunas.
        faculdadeColumn.setCellValueFactory(cellData -> cellData.getValue().getNome());        
    }

    public void setPrimaryStage(BorderPane primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setFaculdadeGenericaList(ArrayList<FaculdadeGenerica> faculdadeGenericaList) {
        this.faculdadeGenericaList = faculdadeGenericaList;
        this.genericas = FXCollections.observableArrayList(this.faculdadeGenericaList);
        faculdadeTable.setItems(genericas); 
    }
    
    @FXML
    private void adicionar(){
        Faculdade faculdade = new Faculdade();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FaculdadeForm.fxml")); //Carrega o arquivo FXML
            AnchorPane page = (AnchorPane) loader.load();
            Stage stage = new Stage(); //Cria um novo Stage
            stage.initOwner(primaryStage.getScene().getWindow()); //Seta um stage pai
            stage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            stage.setScene(scene);
            
            FaculdadeForm controller = loader.getController(); //Puxa a referência do controller instanciado
            controller.setDialogStage(stage);
            controller.setFaculdade(faculdade);
            
            stage.showAndWait(); //Exibe janela e pausa esta thread
            
            if(faculdade.getNome() != null){
                FaculdadeDAO dao = new FaculdadeDAO();
                dao.insert(faculdade);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        recarregar();
    }
    
    @FXML
    private void excluir(){
        Faculdade faculdade = this.faculdadeTable.getSelectionModel().getSelectedItem().getFaculdade();
        //Aqui exclui a Faculdade do banco
        FaculdadeDAO dao = new FaculdadeDAO();
        dao.delete(faculdade.getIdFaculdade());

        recarregar();


    }
    
    @FXML
    private void editar(){
        Faculdade faculdade = this.faculdadeTable.getSelectionModel().getSelectedItem().getFaculdade();
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FaculdadeForm.fxml")); //Carrega o arquivo FXML
            AnchorPane page = (AnchorPane) loader.load();
            Stage stage = new Stage(); //Cria um novo Stage
            stage.initOwner(primaryStage.getScene().getWindow()); //Seta um stage pai
            stage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            stage.setScene(scene);
            
            FaculdadeForm controller = loader.getController(); //Puxa a referência do controller instanciado
            controller.setDialogStage(stage);
            controller.setFaculdade(faculdade);
            
            stage.showAndWait(); //Exibe janela e pausa esta thread
            
            if(faculdade.getNome() != null){

                
                

                FaculdadeDAO edit = new FaculdadeDAO();
                edit.edit(faculdade);

            }
        
        } catch (IOException e) {
            e.printStackTrace();
        }
        recarregar();
    }
    
    @FXML
    private void detalhes(){
        Faculdade faculdade = this.faculdadeTable.getSelectionModel().getSelectedItem().getFaculdade();
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/FaculdadeForm.fxml")); //Carrega o arquivo FXML
            AnchorPane page = (AnchorPane) loader.load();
            Stage stage = new Stage(); //Cria um novo Stage
            stage.initOwner(primaryStage.getScene().getWindow()); //Seta um stage pai
            stage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            stage.setScene(scene);
            
            FaculdadeForm controller = loader.getController(); //Puxa a referência do controller instanciado
            controller.setDialogStage(stage);
            controller.setFaculdade(faculdade);
            controller.setBlock();
            
            stage.showAndWait(); //Exibe janela e pausa esta thread
        
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void recarregar() {
        FaculdadeDAO dao = new FaculdadeDAO();
        this.faculdadeGenericaList = dao.listaGen();
        this.genericas = FXCollections.observableArrayList(this.faculdadeGenericaList);
        faculdadeTable.setItems(genericas); 
    }
}
