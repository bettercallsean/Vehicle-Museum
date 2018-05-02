package museum;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.Serializable;

class Vehicles implements Serializable {
	private static final long serialVersionUID = 1L;

	private String licencePlate, colour, name;
	private int yearOfManufacture, value;

	Vehicles (String lp, String c, String vn, int yom, int v) {
		licencePlate = lp;
		colour = c;
		name = vn;
		yearOfManufacture = yom;
		value = v;
	}

	String getReg() {
		return licencePlate;
	}

	String getColour() {
		return colour;
	}

	String getName() {
		return name;
	}

	int getManufactureYear() {
		return yearOfManufacture;
	}

	int getValue() {
		return value;
	}
}

class Car extends Vehicles {
	private String engineType;
	private int noOfDoors, noOfSeats;
	private double engineSize;

	Car (String lp, String c, String nm, int yom, int v, String engine, int doors, int seats, double size) {
		super (lp, c, nm, yom, v);
		engineType = engine;
		noOfDoors = doors;
		noOfSeats = seats;
		engineSize = size;
	}

	String getEngineType() {
		return engineType;
	}

	int getTotalDoors() {
		return noOfDoors;
	}

	int getTotalSeats() {
		return noOfSeats;
	}

	double carEngineSize() {
		return engineSize;
	}

}

class Bike extends Vehicles {
	private String bikeType;
	private int engineSize;

	Bike (String lp, String c, String vn, int yom, int v, String type, int engine) {
		super (lp, c, vn, yom, v);
		bikeType = type;
		engineSize = engine;
	}

	String getBikeType() {
		return bikeType;
	}

	int bikeEngineSize() {
		return engineSize;
	}

}

public class Museum extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception{

		Parent root = FXMLLoader.load(getClass().getResource("layout.fxml"));
		primaryStage.setTitle("Vehicle Museum");
		primaryStage.setScene(new Scene(root, 700, 500));
		primaryStage.show();
	}


	public static void main(String[] args) {
		launch(args);
	}
}