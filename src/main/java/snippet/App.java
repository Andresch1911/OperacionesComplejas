package snippet;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class App extends Application {
		private Label label1, label2;
		private TextField numeroAText, numeroBText, numeroCText, numeroDText, resultadoAText, resultadoBText;		
		private ComboBox<String> operacionCombo;
		
		private DoubleProperty operadorA = new SimpleDoubleProperty();
		private DoubleProperty operadorB = new SimpleDoubleProperty();
		private DoubleProperty operadorC = new SimpleDoubleProperty();
		private DoubleProperty operadorD = new SimpleDoubleProperty();
		private DoubleProperty resultado = new SimpleDoubleProperty();
		private DoubleProperty resultadoB = new SimpleDoubleProperty();
		private StringProperty operador = new SimpleStringProperty();
		private StringProperty operador2 = new SimpleStringProperty();
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		numeroAText = new TextField();
		numeroAText.setMaxWidth(40);
		
		numeroBText = new TextField();
		numeroBText.setMaxWidth(40);
		
		label1 = new Label("+");
		label2 = new Label("i");
		
		
		HBox arriba = new HBox(5, numeroAText, label1, numeroBText, label2);
		arriba.setAlignment(Pos.BASELINE_CENTER);
		
		
		numeroCText = new TextField();
		numeroCText.setMaxWidth(40);
		
		numeroDText = new TextField();
		numeroDText.setMaxWidth(40);		
		
		label1 = new Label("+");
		label2 = new Label("i");
		
		HBox medio = new HBox(5, numeroCText, label1, numeroDText, label2);
		medio.setAlignment(Pos.BASELINE_CENTER);
				
		
		
		
		resultadoAText = new TextField();
		resultadoAText.setMaxWidth(40);
		resultadoAText.setDisable(false);
		
		resultadoBText = new TextField();
		resultadoBText.setMaxWidth(40);
		resultadoBText.setDisable(false);
		
		label1 = new Label("+");
		label2 = new Label("i");
		
		HBox abajo = new HBox(5, resultadoAText, label1, resultadoBText, label2);
		abajo.setAlignment(Pos.BASELINE_CENTER);
		
		
		Separator sepa = new Separator(Orientation.HORIZONTAL);
		
		
		VBox centro = new VBox(5,arriba, medio, sepa, abajo);
		centro.setAlignment(Pos.CENTER);
		
		
		operacionCombo = new ComboBox<String>();
		operacionCombo.getItems().addAll("+", "-", "/", "*");
		
		VBox izquierda = new VBox(5,operacionCombo);
		izquierda.setAlignment(Pos.CENTER_LEFT);			
		
		HBox app = new HBox(5,izquierda,centro);
		app.setAlignment(Pos.CENTER);
				
		Scene scene = new Scene(app, 320,200);
		
		primaryStage.setTitle("Iniciar Sesion");
		primaryStage.setScene(scene);
		primaryStage.show();

		
		Bindings.bindBidirectional(numeroAText.textProperty(), operadorA, new NumberStringConverter());
		Bindings.bindBidirectional(numeroBText.textProperty(), operadorB, new NumberStringConverter());
		Bindings.bindBidirectional(resultadoAText.textProperty(), resultado, new NumberStringConverter());
		Bindings.bindBidirectional(numeroCText.textProperty(), operadorC, new NumberStringConverter());
		Bindings.bindBidirectional(numeroDText.textProperty(), operadorD, new NumberStringConverter());
		Bindings.bindBidirectional(resultadoBText.textProperty(), resultadoB, new NumberStringConverter());
		operador.bind(operacionCombo.getSelectionModel().selectedItemProperty());
		operador2.bind(operacionCombo.getSelectionModel().selectedItemProperty());
		// listeners
		
		operador.addListener((o, ov, nv) -> onOperadorChanged(nv));
		operador2.addListener((o, ov, nv) -> onOperadorChanged2(nv));

		operacionCombo.getSelectionModel().selectFirst();
		

	}

	private void onOperadorChanged(String nv) {

		switch(nv) {
		case "+":
			resultado.bind(operadorA.add(operadorB));
			break;
		case "-":
			resultado.bind(operadorA.subtract(operadorB));			
			break;
		case "*": 
			resultado.bind(operadorA.multiply(operadorB));
			break;
		case "/":
			resultado.bind(operadorA.divide(operadorB));
			break;
		}
		
	}
	private void onOperadorChanged2(String nv) {

		switch(nv) {
		case "+":
			resultadoB.bind(operadorC.add(operadorD));
			break;
		case "-":
			resultadoB.bind(operadorC.subtract(operadorD));			
			break;
		case "*": 
			resultadoB.bind(operadorC.multiply(operadorD));
			break;
		case "/":
			resultadoB.bind(operadorC.divide(operadorD));
			break;
		}
		
	}

	public static void main(String[] args) {
		launch(args);

	}

}
