/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.crimes;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.crimes.db.EventsDao;
import it.polito.tdp.crimes.model.Adiacenza;
import it.polito.tdp.crimes.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	private Model model;
	private EventsDao dao;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="boxAnno"
	private ComboBox<Integer> boxAnno; // Value injected by FXMLLoader

	@FXML // fx:id="boxMese"
	private ComboBox<Integer> boxMese; // Value injected by FXMLLoader

	@FXML // fx:id="boxGiorno"
	private ComboBox<Integer> boxGiorno; // Value injected by FXMLLoader

	@FXML // fx:id="btnCreaReteCittadina"
	private Button btnCreaReteCittadina; // Value injected by FXMLLoader

	@FXML // fx:id="btnSimula"
	private Button btnSimula; // Value injected by FXMLLoader

	@FXML // fx:id="txtN"
	private TextField txtN; // Value injected by FXMLLoader

	@FXML // fx:id="txtResult"
	private TextArea txtResult; // Value injected by FXMLLoader

	@FXML
	void doCreaReteCittadina(ActionEvent event) {

		this.txtResult.clear();

		Integer anno = this.boxAnno.getValue();

		if (anno == null) {
			this.txtResult.appendText("Seleziona un anno");
			return;
		}

		this.model.creaGrafo(anno);

		this.txtResult.appendText("Numero di vertici: " + model.numVertici() + "\n");
		this.txtResult.appendText("Numero di archi: " + model.numArchi() + "\n");

		for (Integer vertice : model.getGrafo().vertexSet()) {
			this.txtResult.appendText("\n");
			for (Adiacenza a : model.getVerticiAdiacenti(vertice)) {
				this.txtResult.appendText("Vertice: " + a.getId() + " || " + a.getPeso() + "\n");
			}
		}
		
		this.boxMese.getItems().addAll(dao.getMese(anno));
		this.boxGiorno.getItems().addAll(dao.getGiorno(anno));

	}

	@FXML
	void doSimula(ActionEvent event) {
		
		Integer giorno = this.boxGiorno.getValue();
		Integer mese = this.boxMese.getValue();
		Integer anno = this.boxAnno.getValue();
		
		if(giorno == null || mese == null || anno == null) {
			this.txtResult.appendText("Seleziona correttamente i campi!!!");
			return;
		}
		
		try {
		Integer N = Integer.parseInt(this.txtN.getText());
		}catch(Exception e) {
			this.txtResult.appendText("Inserisci correttamente il numero di agenti!!!");
			return;
		}

	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert boxAnno != null : "fx:id=\"boxAnno\" was not injected: check your FXML file 'Scene.fxml'.";
		assert boxMese != null : "fx:id=\"boxMese\" was not injected: check your FXML file 'Scene.fxml'.";
		assert boxGiorno != null : "fx:id=\"boxGiorno\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnCreaReteCittadina != null : "fx:id=\"btnCreaReteCittadina\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtN != null : "fx:id=\"txtN\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

		this.dao = new EventsDao();
		this.boxAnno.getItems().addAll(this.dao.getAnni());

	}

	public void setModel(Model model) {
		this.model = model;
	}
}
