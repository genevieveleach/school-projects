/**
 * COPYRIGHT: smanna@cpp.edu
 * CS 141
 * Programming Assignment 4
 *
 * DO NOT EDIT THIS CODE.
 * You are NOT submitting this code. 
 * It is simply for running test cases against your code.
 *
 **/


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GenerateScore
{
    public static void main(String[] args) {
        File file = null;
        try {
            if (args.length > 0)
                file = new File("eval/eval.txt");
            else
                file = new File("data/test.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Scanner scanner = null;
        try {
            scanner = new Scanner(file); //file must have 20 lines
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<String[]> fileContents = new ArrayList<>();
        ArrayList<Plane> studentPlanes = new ArrayList<>();

        final int NUM_TEST_CASES = 20;
        int numberOfPoints = 0;
        for (int i = 0; i < NUM_TEST_CASES; i++) {
            try {
                System.out.print("Plane " + i + ": ");
                String[] currentLine = scanner.nextLine().trim().split(" ");
                fileContents.add(currentLine);
                if (i % 3 == 0) {
                    studentPlanes.add(new Plane(currentLine[0], currentLine[1], currentLine[2]));
                    System.out.println("Successfully created a Plane +1");
                } else if (i % 3 == 1) {
                    studentPlanes.add(new CargoPlane(currentLine[0], currentLine[1], currentLine[2], Integer.valueOf(currentLine[3])));
                    System.out.println("Successfully created a CargoPlane +1");
                } else if (i % 3 == 2) {
                    studentPlanes.add(new PrivateJet(currentLine[0], currentLine[1], currentLine[2], Integer.valueOf(currentLine[3]),  Double.valueOf(currentLine[4])));
                    System.out.println("Successfully created a PrivateJet +1");
                }
                numberOfPoints++;
            } catch (Exception e) {
                System.out.println("You had an error!");
                e.printStackTrace();
                continue;
            }
        }
        System.out.println("\n");
        for (int i = 0; i < NUM_TEST_CASES; i++) {
            try {
                String[] currentLine = fileContents.get(i);
                String modelToSet = scanner.nextLine().trim();
                if (i % 3 == 0) {
                    if (checkIsPlaneOnly(studentPlanes.get(i))) {
                        Plane Plane = studentPlanes.get(i);
                        if (Plane.getModel().equals(currentLine[0])
                                && Plane.getYear().equals(currentLine[1])
                                && Plane.getMileage().equals(currentLine[2])) {
                            numberOfPoints++;
                            System.out.println("Plane " + i + " had proper getters and constructor +1");
                        }else{
                            System.out.println("Plane " + i + " had bad getters or constructor");
                        }

                        String PlaneString = Plane.toString();
                        if (PlaneString.contains(currentLine[0]) && PlaneString.contains(currentLine[1]) && PlaneString.contains(currentLine[2])) {
                            numberOfPoints++;
                            System.out.println("Plane " + i + " had proper toString method +1");
                        } else {
                            System.out.println("Plane " + i + " had bad toString method");
                        }

                        Plane.setModel(modelToSet);
                        if (Plane.getModel().equals(modelToSet)) {
                            numberOfPoints++;
                            System.out.println("Plane " + i + " had proper setter method +1");
                        } else {
                            System.out.println("Plane " + i + " setter method did not work properly");
                        }

                    }else {
                        System.out.println("expected a Plane, but received object not of type Plane");
                    }
                } else if (i % 3 == 1) {
                    if (checkIsCargoPlaneOnly(studentPlanes.get(i))) {
                        CargoPlane Plane = (CargoPlane) studentPlanes.get(i);
                        
                        try{ 
                        	Plane.fillCargo(1000000);
                        }
                        catch(IllegalSizeException e){
                        	System.out.print("\nIllegalSizeException for Cargo Planes handled.");
                        	numberOfPoints++;
                        }
                        
                        System.out.println();
                        if (Plane.getModel().equals(currentLine[0])
                                && Plane.getYear().equals(currentLine[1])
                                && Plane.getMileage().equals(currentLine[2])
                                && Plane.getCargoCapacity() == Integer.valueOf(currentLine[3])) {
                            numberOfPoints++;
                            System.out.println("CargoPlane " + i + " had proper getters and constructor +1");
                        } else {
                            System.out.println("CargoPlane " + i + " had bad getters or constructor");
                        }

                        String PlaneString = Plane.toString();
                        if (PlaneString.contains(currentLine[0]) && PlaneString.contains(currentLine[1]) && PlaneString.contains(currentLine[2]) && PlaneString.contains(currentLine[3])) {
                            numberOfPoints++;
                            System.out.println("CargoPlane " + i + " had proper toString method +1");
                        } else {
                            System.out.println("CargoPlane " + i + " had bad toString method");
                        }

                        Plane.setModel(modelToSet);
                        if (Plane.getModel().equals(modelToSet)) {
                            numberOfPoints++;
                            System.out.println("CargoPlane " + i + " had proper setter method +1");
                        } else {
                            System.out.println("CargoPlane " + i + " setter method did not work properly");
                        }


                    } else {
                        System.out.println("expected a CargoPlane, but received object not of type CargoPlane");
                    }
                } else if (i % 3 == 2) {
                    if (checkIsPrivateJetOnly(studentPlanes.get(i))) {
                        PrivateJet Plane = (PrivateJet) studentPlanes.get(i);            
                        
                        try{ 
                        	Plane.seatPassengers(15);
                        }
                        catch(IllegalSizeException e){
                        	System.out.println("\nIllegalSizeException for Jets handled.");
                        	numberOfPoints++;
                        }
                        
                        if (Plane.getModel().equals(currentLine[0])
                                && Plane.getYear().equals(currentLine[1])
                                && Plane.getMileage().equals(currentLine[2])
                                && Plane.getSeating() == Integer.valueOf(currentLine[3])
                                && Plane.getCost() == Double.valueOf(currentLine[4])) {
                            numberOfPoints++;
                            System.out.println("PrivateJet " + i + " had proper getters and constructor +1");
                        } else {
                            System.out.println("PrivateJet " + i + " had bad getters or constructor");
                        }

                        String PlaneString = Plane.toString();
                        if (PlaneString.contains(currentLine[0]) && PlaneString.contains(currentLine[1]) && PlaneString.contains(currentLine[2])
                        		&& PlaneString.contains(currentLine[3]) && PlaneString.contains(currentLine[4])) {
                            numberOfPoints++;
                            System.out.println("PrivateJet " + i + " had proper toString method +1");
                        } else {
                            System.out.println("PrivateJet " + i + " had bad toString method");
                        }

                        Plane.setModel(modelToSet);
                        if (Plane.getModel().equals(modelToSet)) {
                            numberOfPoints++;
                            System.out.println("PrivateJet " + i + " had proper setter method +1");
                        } else {
                            System.out.println("PrivateJet " + i + " setter method did not work properly");
                        }


                    } else {
                        System.out.println("expected a PrivateJet, but received object not of type PrivateJet");
                    }
                    System.out.println();
                }

            } catch (Exception e) {
                System.out.println("You had an error!");
                e.printStackTrace();
                continue;
            }
        }
        
        
        System.out.println("\n");




        System.out.println("Your score was: " + numberOfPoints + " out of " + (4 * NUM_TEST_CASES + 13));
    }

    private static boolean checkIsPlaneOnly(Object object) {
        return (object instanceof Plane && !(object instanceof CargoPlane) && !(object instanceof PrivateJet));
    }
    private static boolean checkIsPrivateJetOnly(Object object) {
        return (!(object instanceof CargoPlane) && (object instanceof Plane) && (object instanceof PrivateJet));
    }
    private static boolean checkIsCargoPlaneOnly(Object object) {
        return (!(object instanceof PrivateJet) && (object instanceof Plane) && (object instanceof CargoPlane));
    }
}
