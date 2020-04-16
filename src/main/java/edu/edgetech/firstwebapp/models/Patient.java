package edu.edgetech.firstwebapp.models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Patient {
    private String lastName;
    private String firstName;
    private int temperature;
    private int systolic;
    private int diastolic;
    private int height;
    private int weight;
    private int age;
    private int howYaFeelin;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getSystolic() {
        return systolic;
    }

    public void setSystolic(int systolic) {
        this.systolic = systolic;
    }

    public int getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(int diastolic) {
        this.diastolic = diastolic;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHowYaFeelin() {
        return howYaFeelin;
    }

    public void setHowYaFeelin(int howYaFeelin) {
        this.howYaFeelin = howYaFeelin;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "lastName=" + lastName +
                ", firstName='" + firstName + '\'' +
                ", temperature=" + temperature +
                ", systolic=" + systolic +
                ", diastolic=" + diastolic +
                ", height=" + height +
                ", weight=" + weight +
                ", age=" + age +
                ", howYaFeelin=" + howYaFeelin +
                '}';
    }

    public Patient(String lastName, String firstName, int temperature, int systolic, int diastolic, int height, int weight, int age, int howYaFeelin) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.temperature = temperature;
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.howYaFeelin = howYaFeelin;
    }

    /**
     * loadRecords
     *
     * @param fileName use this file to read in the students to be added to our students list
     */
    public static List<Patient> loadRecords(String fileName) {

        List<Patient> patients = new ArrayList<>();

        // Read in the patients.csv file
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;

            // consume the header line
            br.readLine();
            // Read each line of the file and create a Student object for that line
            while ((line = br.readLine()) != null) {
                String[] aLine = line.split(",");
                // The patient object has the following fields
                //    							Last,   First,      Temp,                       Systolic,
                Patient patient = new Patient(aLine[0], aLine[1], Integer.parseInt(aLine[2]), Integer.parseInt(aLine[3]),
                        //   Diastolic,                  Height,                    Weight,
                        Integer.parseInt(aLine[4]), Integer.parseInt(aLine[5]), Integer.parseInt(aLine[6]),
                        //         Age,                        how they Feel
                        Integer.parseInt(aLine[7]), Integer.parseInt(aLine[8]));
                patients.add(patient);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage()); // handle exception
        }
        return patients;
    }
}