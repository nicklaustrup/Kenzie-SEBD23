package com.kenzie.trees.tollroad;

// import java.util.Comparator;
// import java.util.Map;
// import java.util.TreeMap;

import java.util.Comparator;
import java.util.TreeMap;

/**
 * Application to demonstrate a TreeMap storing key:value pairs in sorted
 * order based on natural ordering of keys and also a different ordering
 * of the same keys based on the results of a Comparator.
 */
public class TollRoad {
    private final TreeMap<String, Vehicle> vehiclesByDescription;
    private final TreeMap<String, Vehicle> vehiclesByState;
    private final Comparator<String> comparator;



    /**
     * TollRoad constructor creates two TreeMaps to store data with
     * different keys. The second uses a Comparator to store based
     * on the state code of each vehicle.
     */
    public TollRoad() {
        // TODO 2: Create the two TreeMap objects, the second of which must be
        // TODO 2: given an appropriate Comparator object when constructed.
       comparator = Comparator.comparing(o -> o.substring(1, 4));
//       Comparator<String> stateComparator = new Comparator<String>() {
//           @Override
//           public int compare(String o1, String o2) {
//               return o1.substring(0, 2).compareTo(o2.substring(0, 2));
//           }
//       };


        vehiclesByDescription = new TreeMap<>();
        vehiclesByState = new TreeMap<>(comparator);



    }

    /**
     * Adds a toll with the given vehicle's description. If the vehicle has
     * already passed through a toll reader, its toll count is updated.
     * If this is the first time the vehicle has passed through a toll
     * reader, a new Vehicle object is created and added to the TreeMaps.
     *
     * @param description The Vehicle's description.
     */
    public void addToll(String description) {
        // TODO 3: Complete this method as described in the exercise.
//       Vehicle vehicle = vehiclesByDescription.get(description);
//
//        if (vehicle == null) {
//           vehicle = new Vehicle(description);
//        } else {
//           vehicle.addToll();
//        }
//        vehiclesByDescription.put(description, vehicle);
//        vehiclesByState.put(description, vehicle);

        Vehicle v = new Vehicle(description);
        if (this.vehiclesByDescription.containsKey(description)) {
            v = this.vehiclesByDescription.get(description);
            v.addToll();
        } else {
            this.vehiclesByDescription.put(description, v);
            this.vehiclesByState.put(description, v);
        }

    }

    /**
     * Builds and returns a String containing the current Vehicles
     * sorted by description.
     *
     * @return String containing the current vehicles, sorted by description.
     */
    public String getVehicleReportByDescription() {
        StringBuilder sb = new StringBuilder();
        for (Vehicle p : this.vehiclesByDescription.values()) {
            sb.append(p);
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Builds and returns a String containing the current Vehicles
     * sorted by state.
     *
     * @return String containing the current vehicles, sorted by state.
     */
    public String getVehicleReportByState() {
        StringBuilder sb = new StringBuilder();
        for (Vehicle p : this.vehiclesByState.values()) {
            sb.append(p);
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Main method to demonstrate the Toll Road and Vehicle classes.
     *
     * Expected output:
     *
     * Vehicle Tolls By Description:
     * Description: CCO123ABC, Toll Count: 2
     * Description: CIA432LMN, Toll Count: 3
     * Description: SFL456DEF, Toll Count: 2
     * Description: TCO789XYZ, Toll Count: 4
     * Description: TIA765QRS, Toll Count: 1
     *
     * Vehicle Tolls By State:
     * Description: CCO123ABC, Toll Count: 2
     * Description: TCO789XYZ, Toll Count: 4
     * Description: SFL456DEF, Toll Count: 2
     * Description: CIA432LMN, Toll Count: 3
     * Description: TIA765QRS, Toll Count: 1
     *
     * @param args Command line arguments; ignored in this application.
     */
    public static void main(String[] args) {
        // Create the TollRoad, add some test data, and show the results.
        TollRoad tollRoad = new TollRoad();
        tollRoad.addToll("CCO123ABC");
        tollRoad.addToll("CIA432LMN");
        tollRoad.addToll("TCO789XYZ");
        tollRoad.addToll("SFL456DEF");
        tollRoad.addToll("TCO789XYZ");
        tollRoad.addToll("CIA432LMN");
        tollRoad.addToll("TCO789XYZ");
        tollRoad.addToll("TIA765QRS");
        tollRoad.addToll("CCO123ABC");
        tollRoad.addToll("SFL456DEF");
        tollRoad.addToll("CIA432LMN");
        tollRoad.addToll("TCO789XYZ");

        System.out.println("Vehicle Tolls By Description:");
        System.out.println(tollRoad.getVehicleReportByDescription());

        System.out.println("Vehicle Tolls By State:");
        System.out.println(tollRoad.getVehicleReportByState());
    }
}
