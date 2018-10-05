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

	Vehicles (String licencePlate, String colour, String name, int yearOfManufacture, int value) {
		this.licencePlate = licencePlate;
		this.colour = colour;
		this.name = name;
		this.yearOfManufacture = yearOfManufacture;
		this.value = value;
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

	Car (String lp, String c, String nm, int yom, int v, String engineType, int noOfDoors, int noOfSeats, double engineSize) {
		super (lp, c, nm, yom, v);
		this.engineType = engineType;
		this.noOfDoors = noOfDoors;
		this.noOfSeats = noOfSeats;
		this.engineSize = engineSize;
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

	Bike (String lp, String c, String vn, int yom, int v, String bikeType, int engineSize) {
		super (lp, c, vn, yom, v);
		this.bikeType = bikeType;
		this.engineSize = engineSize;
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