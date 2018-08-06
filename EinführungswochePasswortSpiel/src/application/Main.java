package application;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import util.Data;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Main extends Application {

	private BorderPane root;
	// Stage, welche als Sockel für das AnchorPane dient
	private Stage primaryStage;

	private StackPane stackPane;

	private Label label, win, timer;

	private Button enter, start;

	private TextField textField;

	private GridPane gridPane;
	
	private boolean timerOn=true;
	
	private int counter=0;

	long time;
	
	Thread t = new Thread();

	@Override
	public void start(Stage primaryStage) {
		try {
			time = System.currentTimeMillis();

			root = new BorderPane();
			stackPane = new StackPane();
			gridPane = new GridPane();
			label = new Label("Passwort :");
			label.setTextFill(Color.web("#EE0000"));
			label.setFont(new Font(30.0));
			win = new Label("Sehr gut, sie sind angemeldet.");
			win.setTextFill(Color.web("#ff7f24"));
			win.setFont(new Font(30.0));
			textField = new TextField("AntwortenDerFragen");
			textField.setFont(new Font(20.0));
			textField.setPrefSize(500.0, 40.0);
			enter = new Button("Einloggen");
			enter.setDefaultButton(false);
			start = new Button("Start");
			start.setDefaultButton(true);
			timer= new Label("0");
			timer.setFont(new Font(100.0));
			
			start.setPrefSize(200.0, 20.0);
			start.setFont(new Font(20.0));

			enter.setPrefSize(200.0, 20.0);
			enter.setFont(new Font(20.0));

			root.setPrefSize(1000.0, 600.0);

			gridPane.getChildren().add(label);
			gridPane.getChildren().add(textField);
			gridPane.getChildren().add(enter);
//			gridPane.getChildren().add(timer);

			gridPane.setColumnIndex(label, 0);
			gridPane.setColumnIndex(textField, 1);
			gridPane.setRowIndex(enter, 1);
			gridPane.setColumnIndex(timer,1);
			gridPane.setRowIndex(timer, 2);
			// gridPane.setColumnSpan(enter, 2);

			gridPane.setHgap(10.0);
			gridPane.setVgap(10.0);
			gridPane.setPadding(new Insets(250.0, 10.0, 10.0, 150.0));

			stackPane.getChildren().add(gridPane);
			stackPane.getChildren().add(win);
			stackPane.getChildren().add(start);

			stackPane.getChildren().get(1).setVisible(false);
			stackPane.getChildren().get(0).setVisible(false);

			root.setCenter(stackPane);

			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			this.primaryStage = primaryStage;
			this.primaryStage.setScene(scene);
			this.primaryStage.setResizable(false);
			this.primaryStage.setTitle("Was war mein Passwort?");
			this.primaryStage.show();

			enter.setOnAction(new EventHandler() {
				@Override
				public void handle(Event arg0) {
					if (textField.getText().equals(Data.PASSWORD)) {
						timerOn=false;
						stackPane.getChildren().get(1).setVisible(true);
						stackPane.getChildren().get(0).setVisible(false);
						long neededTimeSeconds = (System.currentTimeMillis() - time) / 1000;
						long neededTimeMinutes = (neededTimeSeconds / 60);
						neededTimeSeconds = +(neededTimeSeconds % 60);
						win.setText(win.getText() + " \n" + "Ihre benötigte Zeit betrug "
								+ (neededTimeMinutes + " Minuten und " + neededTimeSeconds + " Sekunden.") + "\n"
								+ "Das Passwort war " + Data.PASSWORD + ".");
					} else if (textField.getText().contains("Admin")) {

						stackPane.getChildren().get(1).setVisible(true);
						stackPane.getChildren().get(0).setVisible(false);
						long neededTimeSeconds = (System.currentTimeMillis() - time) / 1000;
						long neededTimeMinutes = (neededTimeSeconds / 60);
						neededTimeSeconds = +(neededTimeSeconds % 60);
						win.setText("Sie haben es leider nicht geschafft!" + " \n" + "Ihre benötigte Zeit betrug "
								+ (neededTimeMinutes + " Minuten und " + neededTimeSeconds + " Sekunden.") + "\n"
								+ "Das Passwort war " + Data.PASSWORD + ".\nSie haben folgendes gehabt: "
								+ textField.getText().replace("Admin", ""));

					}
				}
			});
			start.setOnAction(new EventHandler() {
				@Override
				public void handle(Event arg0) {
					new Thread(t).start();
					stackPane.getChildren().get(2).setVisible(false);
					stackPane.getChildren().get(0).setVisible(true);
					time = System.currentTimeMillis();
					enter.setDefaultButton(true);
					

//						timer.setText(Integer.toString(Thread.counter));
						
				}

			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws SQLException {
		launch(args);
	}

	public BorderPane getRoot() {

		return root;
	}

	/**
	 * Gibt die Sockel "Primär" Stage zurück
	 * 
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	/**
	 * Setzt die Sockel "Primär" Stage
	 * 
	 * @param primaryStage
	 *            - zu setzende Stage
	 */
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

}
