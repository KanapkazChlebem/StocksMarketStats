package stocksstatistics;

import java.io.*;

public class Wrapper {
    private String curDirectiory = System.getProperty("user.dir");


    public void saveDatabase(String fileName, EmployeeDatabase database) {
        if (fileName == null) fileName = "DefaultDatabase";
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName + ".ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            //write
            objectOutputStream.writeObject(database);
            //close stream
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public EmployeeDatabase createOrReadDatabase(String fileName) {
        if (fileName == null) fileName = "DefaultDatabase";
        EmployeeDatabase database = new EmployeeDatabase();
        try {
            ObjectInputStream objectInputStream = null;
            objectInputStream = new ObjectInputStream(
                    new FileInputStream(fileName + ".ser"));
            database = (EmployeeDatabase) objectInputStream.readObject();
        } catch (Exception e) {
            return database;
        }
        return database;
    }

}
