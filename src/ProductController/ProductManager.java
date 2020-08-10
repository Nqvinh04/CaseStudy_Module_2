package ProductController;


import ProductModel.Product;
import ProductStorage.FileProduct;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.io.File;
import java.net.URL;
import java.util.*;

public class ProductManager implements Initializable {
    @FXML
    TableView<Product> tableView;

    @FXML
    TableColumn<Product, Integer> idColumn;

    @FXML
    TableColumn<Product, String> nameColumn;

    @FXML
    TableColumn<Product, String> expColumn;

    @FXML
    TableColumn<Product, Float> priceColumn;

    @FXML
    TableColumn<Product, Integer> amountColumn;

    @FXML
    Button editButton;

    @FXML
    Button saveButton;


    @FXML
    TextField idText;

    @FXML
    TextField nameText;

    @FXML
    TextField expText;

    @FXML
    TextField priceText;

    @FXML
    TextField amountText;

    @FXML
    TextField searchText;
    List<Product> productList = new ArrayList<>();

    @FXML
    ComboBox<String> comboBox;
    String[] comboboxList = {"Mã Sản Phẩm", "Tên Sản Phẩm", "Ngày Sản Xuất"};


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            readFile();
        } catch (Exception e){
            e.printStackTrace();
        }

        idColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        expColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("exp"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("amount"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Product, Float>("price"));
        tableView.getItems().addAll(productList);
        comboBox.getItems().addAll(comboboxList);
    }

    public void loadProduct(){
        tableView.getItems().clear();
        for (Product product: productList){
            tableView.getItems().add(product);
        }
    }

    // Thêm sản phẩm
    public void addProduct() throws Exception {
        Product product = new Product();
        product.setId(idText.getText());
        product.setName(nameText.getText());
        product.setExp(expText.getText());
        product.setPrice(priceText.getText());
        product.setAmount(amountText.getText());
        for (Product products : productList) {
            if (products.getId().equals(idText.getText()) || idText.getText().equals("")){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Cảnh Báo Hệ Thống");
                alert.setContentText("Mã Sản Phẩm đã tồn tại. Vui lòng nhập mã khác!!!");
                alert.show();
                return;
            }
        }
        productList.add(product);
        loadProduct();
        writeFile();
    }

    //delete
    public void deleteProduct(){
        Product product = tableView.getSelectionModel().getSelectedItem();
        productList.remove(product);
        loadProduct();
    }


    //Search product
    public void idProduct(String idProducts) {
        String id  = idProducts.toLowerCase();
        tableView.getItems().clear();
        for (Product product : productList) {
            if (product.getId().toLowerCase().equals(id)) {
                tableView.getItems().add(product);
            }
        }
    }

    public void nameProduct(String nameProducts) {
        String name = nameProducts.toLowerCase();
        tableView.getItems().clear();
        for (Product product : productList) {
            if (product.getName().toLowerCase().contains(name)) {
                tableView.getItems().add(product);
            }
        }
    }

    public void expProduct(String expProducts) {
        String exp = expProducts.toLowerCase();
        tableView.getItems().clear();
        for (Product product : productList) {
            if (product.getExp().contains(exp)) {
                tableView.getItems().add(product);
            }
        }
    }


    public void search() {
        String search = searchText.getText();
        int selectedIndex = comboBox.getSelectionModel().getSelectedIndex();
        if (search.equals("")) {
            loadProduct();
        } else {
            switch (selectedIndex) {
                case 0:
                    idProduct(search);
                    break;
                case 1:
                    nameProduct(search);
                    break;
                case 2:
                    expProduct(search);
                    break;
            }
        }
    }


    // Edit ra
    public void edit(){
            Product product = tableView.getSelectionModel().getSelectedItem();
            if (product == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Cảnh Báo Hệ Thống");
                alert.setContentText("Không có sa");
            } else {
                idText.setText(product.getId());
                nameText.setText(product.getName());
                expText.setText(product.getExp());
                priceText.setText(product.getPrice());
                amountText.setText(product.getAmount());

                //idText.setDisable(true);
                saveButton.setDisable(false);
            }
        }
    
    
    public void save(){
        Product product = tableView.getSelectionModel().getSelectedItem();
        product.setName(nameText.getText());
        product.setExp(expText.getText());
        product.setPrice(priceText.getText());
        product.setAmount(amountText.getText());
        saveButton.setDisable(true);
        idText.setDisable(false);
        editButton.setDisable(false);
        loadProduct();
    }
    // Ghi file
    public void writeFile() throws Exception {
        FileProduct<Product> fileProduct = new FileProduct<>();
        fileProduct.write("D:\\CaseStudy_Module_2\\src\\ProductFile\\Product.txt", productList);
    }

    // Đọc file
    public void readFile(){
        FileProduct<Product> fileProduct = new FileProduct<>();
        productList.clear();
        productList.addAll(fileProduct.readFile("D:\\CaseStudy_Module_2\\src\\ProductFile\\Product.txt"));
    }
}
