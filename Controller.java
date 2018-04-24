package museum;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.collections.ObservableList;
import java.text.NumberFormat;

public class Controller {

    private Vehicles vhcl[] = new Vehicles[50];
    private int vehicleCount = 0;


    // Used for setting the visibility of different scenes (not very efficient but I needed something for now)

    // ------- These are all used in the vehicle form -------
    private void loadVehicleTypeValues(){
        vehicleType.getItems().addAll("Car", "Motorbike");
    }
    @FXML private ChoiceBox<String> vehicleType;
    @FXML private HBox carBox;
    @FXML private HBox bikeBox;

    @FXML private TextField vehicleName;
    @FXML private TextField regPlate;
    @FXML private TextField yearOfManufacture;
    @FXML private TextField value;
    @FXML private Label statusLabel;
    @FXML private TextField vehicleColour;

    //Car form initialisers
    @FXML private ChoiceBox<String> engineType;
    @FXML private TextField noOfDoors;
    @FXML private TextField noOfSeats;
    @FXML private TextField engineSize;

    //Bike form initialisers
    @FXML private ChoiceBox<String> bikeType;
    @FXML private TextField bikeEngineSize;
    @FXML private TextField yearSearch;
    @FXML public void vehicleAdder(){
        if (vehicleType.getItems().isEmpty())
            loadVehicleTypeValues();
        vehicleForm.setVisible(true);
        yearListerForm.setVisible(false);
        listEngines.setVisible(false);
        carLister.setVisible(false);
        bikeLister.setVisible(false);
        welcomeScreen.setVisible(false);
    }

    @FXML private VBox vehicleForm;
    @FXML private void showVehicleForm() {
        String vehicle = vehicleType.getValue();
        try{
            switch (vehicle) {
                case "Car":
                    carBox.setVisible(true);
                    bikeBox.setVisible(false);
                    if (engineType.getItems().isEmpty())
                        engineType.getItems().addAll("Petrol", "Diesel");
                    break;
                case "Motorbike":
                    bikeBox.setVisible(true);
                    carBox.setVisible(false);
                    if (bikeType.getItems().isEmpty())
                        bikeType.getItems().addAll("Tourer", "Sport", "Trails");
                    break;
                default:
                    break;
            }
        }
        catch (java.lang.RuntimeException e){
            statusLabel.setText("No value selected!");
        }
    }
    // --------------------------------------------------------

    @FXML private Pane welcomeScreen;
    @FXML private VBox yearListerForm;
    @FXML private void showYearLister(){
        yearListerForm.setVisible(true);
        vehicleForm.setVisible(false);
        listEngines.setVisible(false);
        carLister.setVisible(false);
        bikeLister.setVisible(false);
        welcomeScreen.setVisible(false);
    }

    @FXML private VBox listEngines;
    @FXML private void showOneLitreEngines(){
        listEngines.setVisible(true);
        yearListerForm.setVisible(false);
        vehicleForm.setVisible(false);
        carLister.setVisible(false);
        bikeLister.setVisible(false);
        welcomeScreen.setVisible(false);
        oneLitreEngine();
    }

    @FXML private VBox bikeLister;
    @FXML private void showBikeLister(){
        bikeLister.setVisible(true);
        carLister.setVisible(false);
        listEngines.setVisible(false);
        yearListerForm.setVisible(false);
        vehicleForm.setVisible(false);
        welcomeScreen.setVisible(false);
        bikeLister();
    }

    @FXML private VBox carLister;
    @FXML private void showCarLister(){
        carLister.setVisible(true);
        listEngines.setVisible(false);
        yearListerForm.setVisible(false);
        vehicleForm.setVisible(false);
        bikeLister.setVisible(false);
        welcomeScreen.setVisible(false);
        carLister();
    }

    @FXML private void vehicleSubmit() {
        String name = "";
        String reg = "";
        String colour = "";
        int year = 0;
        int vhclValue = 0;

        try {

            if (!vehicleName.getText().isEmpty())
                name = vehicleName.getText();
            if (!regPlate.getText().isEmpty())
                reg = regPlate.getText();
            if (vehicleColour.getText().isEmpty())
                colour = vehicleColour.getText();
            if (!vehicleColour.getText().isEmpty())
                colour = vehicleColour.getText();
            if (Integer.parseInt(yearOfManufacture.getText()) != 0)
                year = Integer.parseInt(yearOfManufacture.getText());
            if (Integer.parseInt(value.getText()) != 0)
                vhclValue = Integer.parseInt(value.getText());

            String vehicle = vehicleType.getValue();

            if (vehicle.equals("Car")) {
                String engine = "";
                int doors = 0;
                int seats = 0;
                int engineS = 0;

                if (!engineType.getValue().isEmpty())
                    engine = engineType.getValue();
                if (Integer.parseInt(noOfDoors.getText()) != 0)
                    doors = Integer.parseInt(noOfDoors.getText());
                if (Integer.parseInt(noOfSeats.getText()) != 0)
                    seats = Integer.parseInt(noOfSeats.getText());
                if (Integer.parseInt(engineSize.getText()) != 0)
                    engineS = Integer.parseInt(engineSize.getText());
                vhcl[vehicleCount] = new Car(reg, colour, name, year, vhclValue, engine, doors, seats, engineS);
                vehicleCount++;

                noOfDoors.clear();
                noOfSeats.clear();
                engineSize.clear();
            }

            else {
                String bike = "";
                int bikeEngineS = 0;

                if (!bikeType.getValue().isEmpty())
                    bike = bikeType.getValue();

                if (Integer.parseInt(bikeEngineSize.getText()) != 0)
                    bikeEngineS = Integer.parseInt(bikeEngineSize.getText());

                vhcl[vehicleCount] = new Bike(reg, colour, name, year, vhclValue, bike, bikeEngineS);
                vehicleCount++;
                bikeEngineSize.clear();
            }

            vehicleName.clear();
            regPlate.clear();
            vehicleColour.clear();
            yearOfManufacture.clear();
            value.clear();
            statusLabel.setText("");

        }
        catch (RuntimeException e) {
            statusLabel.setText("There are values missing from the form, please check your input and try again.");
        }

    }

    // The different scenes used in the application
    @FXML private ListView<String> vehicleYearLister;
    @FXML private void yearLister(){
        vehicleYearLister.getItems().clear();
        ObservableList<String> list = vehicleYearLister.getItems();

        for (int count = 0; count < vehicleCount; count++)
        {
            if (vhcl[count].getManufactureYear() == Integer.parseInt(yearSearch.getText()))
            {
                if (vhcl[count] instanceof Car)
                {
                    Car carObject = (Car) vhcl[count];
                    list.add(getCarInfo(carObject));
                }
                else if (vhcl[count] instanceof Bike)
                {
                    Bike bikeObject = (Bike) vhcl[count];
                    list.add(getBikeInfo(bikeObject));
                }
            }
        }

        if (list.size() == 0)
            list.add("No results found!");

    }

    @FXML private ListView<String> vehicleEngineLister;
    @FXML private void oneLitreEngine() {
        vehicleEngineLister.getItems().clear();
        ObservableList<String> list = vehicleEngineLister.getItems();

        for (int count = 0; count < vehicleCount; count++)
        {
            if (vhcl[count] instanceof Car)
            {
                Car carObject = (Car) vhcl[count];
                if (carObject.carEngineSize() >= 1)
                {
                    list.add(getCarInfo(carObject));
                }
            }

            else if (vhcl[count] instanceof Bike)
            {
                Bike bikeObject = (Bike) vhcl[count];
                if (bikeObject.bikeEngineSize() >= 1000)
                {
                    list.add(getBikeInfo(bikeObject));
                }
            }
        }

        if (list.size() == 0)
            list.add("No results found!");
    }


    @FXML private ListView<String> vehicleBikeLister;
    @FXML private Label bikeCounter;
    @FXML private void bikeLister() {
        vehicleBikeLister.getItems().clear();
        ObservableList<String> list = vehicleBikeLister.getItems();

        int vehiclesTotal = 0;
        for (int count = 0; count < vehicleCount; count++) {

            if (vhcl[count] instanceof Bike) {
                Bike bikeObject = (Bike) vhcl[count];
                vehiclesTotal ++;
                list.add(getBikeInfo(bikeObject));
            }
        }
        if (vehiclesTotal == 0)
            list.add("No results found!");
        else if (vehiclesTotal == 1)
            bikeCounter.setText(vehiclesTotal + " bike found");
        else
            bikeCounter.setText(vehiclesTotal + " bikes found.");
    }

    @FXML private ListView<String> vehicleCarLister;
    @FXML private Label carCounter;
    @FXML private void carLister() {
        vehicleCarLister.getItems().clear();
        ObservableList<String> list = vehicleCarLister.getItems();

        int vehiclesTotal = 0;
        for (int count = 0; count < vehicleCount; count++) {
            if (vhcl[count] instanceof Car)
            {
                Car carObject = (Car) vhcl[count];
                vehiclesTotal ++;
                list.add(getCarInfo(carObject));
            }
        }
        if (vehiclesTotal == 0)
            list.add("No results found!");
        else if (vehiclesTotal == 1)
            carCounter.setText(vehiclesTotal + " car found");
        else
            carCounter.setText(vehiclesTotal + " cars found.");
    }

    // Methods for gathering the relevant to display in the ListViews
    private String getVehicleInfo(Vehicles vehicle){
        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        return "Name - " + vehicle.getName() +
                "\nLicence Plate: " + vehicle.getReg() +
                "\nYear of Manufacture: " + vehicle.getManufactureYear() +
                "\nValue: " + formatter.format(vehicle.getValue())+
                "\nColour: " + vehicle.getColour();
    }

    private String getCarInfo(Car vehicle){

        return getVehicleInfo(vehicle)+
                "\nEngine Type: " + vehicle.getEngineType() +
                "\nNumber of doors: " + vehicle.getTotalDoors() +
                "\nNumber of seats: " + vehicle.getTotalSeats() +
                "\nEngine Size: " + vehicle.carEngineSize();
    }

    private String getBikeInfo(Bike vehicle){

        return getVehicleInfo(vehicle)+
                "\nBike type: " + vehicle.getBikeType() +
                "\nEngine Size (cc): " + vehicle.bikeEngineSize();
    }
}

