package financeManagementSystem.controllers;

import financeManagementSystem.model.Category;
import financeManagementSystem.model.FinanceManagementSystem;
import financeManagementSystem.utils.CategoryDataBaseManagement;
import financeManagementSystem.utils.UserDataBaseManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;


public class AddSubCategory {
    @FXML
    public TextField catName;
    @FXML
    public TextField catParentId;
    @FXML
    public TextArea catDescription;
    @FXML
    public Button addCat;
    @FXML
    public Button cancel;

    private FinanceManagementSystem fms;
    private int parentId;

    public AddSubCategory() throws IOException {
    }

    public void setFms(FinanceManagementSystem fms) {
        this.fms = fms;
    }

    public void setCatParentId(int parentId){
        this.parentId = parentId;
    }

    public void addSubCategory(ActionEvent actionEvent) throws IOException {
        CategoryDataBaseManagement.insertCategory(new Category(catName.getText(), catDescription.getText(), CategoryDataBaseManagement.getCategoryIdCount(), LocalDate.now(), LocalDate.now(), String.valueOf(parentId)));

        int id;
        if(LoginPage.getIsItIndividual())
            id=LoginPage.getIndividual().getId();
        else
            id=LoginPage.getCompany().getId();

        UserDataBaseManagement.addResponsibleCategories(id, CategoryDataBaseManagement.getCategoryIdCount());
        CategoryDataBaseManagement.addCategoryIdCount();

        URL url = new File("src/main/java/financeManagementSystem/controllers/CategoryManagement.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        CategoryManagement categoryManagement = loader.getController();
        categoryManagement.setFms(fms);

        categoryManagement.fillCatWithData();

        Stage stage = (Stage) addCat.getScene().getWindow();

        stage.setTitle("Finance Management System");
        stage.setScene(new Scene(root));
        stage.show();
    }


    public void cancel(ActionEvent actionEvent) throws IOException {
        URL url = new File("src/main/java/financeManagementSystem/controllers/CategoryManagement.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();

        CategoryManagement categoryManagement = loader.getController();
        categoryManagement.setFms(fms);

        Stage stage = (Stage) addCat.getScene().getWindow();

        stage.setTitle("Finance Management System");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
