package controlador;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Product;
import model.ProductDAO;

public class ProductsController{

	//Objecte per gestionar la persistència de les dades
	private ProductDAO products;

	//Elements gràfics de la UI
	private Stage ventana;
	@FXML private TextField idTextField;
	@FXML private TextField nomTextField;
	@FXML private TextField priceTextField;
	@FXML private TextField stockTextField;
	@FXML private TextField initialDateTextField;
	@FXML private TextField finalDateTextField;

	private ValidationSupport vs;

	/**
	 * Inicialitza la classe. JAVA l'executa automàticament després de carregar el fitxer fxml
	 */
	@FXML private void initialize() {
		//Obrir el fitxer de persones
		products = new ProductDAO();
		products.load();

		//Validació dades
		//https://github.com/controlsfx/controlsfx/issues/1148
		//produeix error si no posem a les VM arguments això: --add-opens=javafx.graphics/javafx.scene=ALL-UNNAMED
		vs = new ValidationSupport();
		vs.registerValidator(idTextField, true, Validator.createEmptyValidator("ID obligatori"));
		vs.registerValidator(nomTextField, true, Validator.createEmptyValidator("Nom obligatori"));
		vs.registerValidator(priceTextField, true, Validator.createEmptyValidator("Preu obligatori"));
		vs.registerValidator(stockTextField, true, Validator.createEmptyValidator("Stock obligatori"));
		vs.registerValidator(initialDateTextField, true, Validator.createEmptyValidator("Fecha inicial del producto obligatori"));
		vs.registerValidator(finalDateTextField, true, Validator.createEmptyValidator("Fecha final del producto obligatori"));
        //https://howtodoinjava.com/regex/java-regex-validate-email-address/
	}

	public Stage getVentana() {
		return ventana;
	}

	public void setVentana(Stage ventana) {
		this.ventana = ventana;
	}

	@FXML private void onKeyPressedId(KeyEvent e) throws IOException {

		if (e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.TAB){
			//Comprovar si existeix la persona indicada en el control idTextField
			Product product = products.search(Integer.parseInt(idTextField.getText()));
			if(product != null){ 
				//posar els valors per modificarlos
				nomTextField.setText(product.getName());
				priceTextField.setText(product.getSalePrice().toString());
				stockTextField.setText(product.getStock().toString());
				initialDateTextField.setText(product.getInitialCatalogDate().toString());
				finalDateTextField.setText(product.getEndCatalogDate().toString());
			}else{ 
				//nou registre
				nomTextField.setText("");
				priceTextField.setText("");
				stockTextField.setText("");
				initialDateTextField.setText("");
				finalDateTextField.setText("");
			}
		}
	}
	 
	@FXML private void onActionGuardar(ActionEvent e) throws IOException {
		DateTimeFormatter dateFormater = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		//verificar si les dades són vàlides				
		if(isDatosValidos()){
			Product product = new Product(Integer.parseInt(idTextField.getText()), nomTextField.getText(),Integer.parseInt(priceTextField.getText()),
			Integer.parseInt(stockTextField.getText()), LocalDate.parse(initialDateTextField.getText(), dateFormater),LocalDate.parse(finalDateTextField.getText(), dateFormater));
			
			
			products.add(product);
			limpiarFormulario();
			products.getMap();
		}
	}

	@FXML private void onActionEliminar(ActionEvent e) throws IOException {

		if(isDatosValidos()){
			if(products.delete(Integer.parseInt(idTextField.getText()))==null){
				limpiarFormulario();
				products.getMap();
			}
		}
	}

	@FXML private void onActionSortir(ActionEvent e) throws IOException {

		sortir();

		ventana.close();
	}

	public void sortir(){
		products.save();
		products.getMap();
	}

	private boolean isDatosValidos() {

		//Comprovar si totes les dades són vàlides
		if (vs.isInvalid()) {
			String errors = vs.getValidationResult().getMessages().toString();
			// Mostrar finestra amb els errors
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.initOwner(ventana);
			alert.setTitle("Camps incorrectes");
			alert.setHeaderText("Corregeix els camps incorrectes");
			alert.setContentText(errors);
			alert.showAndWait();
		
			return false;
		}

		return true;

	}

	private void limpiarFormulario(){
		idTextField.setText("");
		nomTextField.setText("");
		priceTextField.setText("");
		stockTextField.setText("");
		initialDateTextField.setText("");
		finalDateTextField.setText("");
	}
}
