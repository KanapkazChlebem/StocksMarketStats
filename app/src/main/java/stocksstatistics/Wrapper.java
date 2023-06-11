package stocksstatistics;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Wrapper {
    private String directory = System.getProperty("user.dir") + "\\files\\";

    public Wrapper() {
    }

    public void saveDatabase(String fileName, EmployeeDatabase database) {
        if (database.getEmployeeList().size()==0) System.err.println("Obecna baza danych jest pusta, nie mozna zapisać!");
        else {
            if (fileName.equals("")) {
                fileName = database.getFileName();
            } else
            if (!fileName.endsWith(".ser")) fileName += ".ser";
            database.setFileName(fileName);
            fileName = directory + fileName;
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(fileName);
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
    }

    public EmployeeDatabase readDatabase(String fileName) {
        if (fileName.equals(""))
            fileName = "DefaultDatabase";
        String databaseName = fileName + ".ser";
        EmployeeDatabase database = new EmployeeDatabase();
        database.setFileName(databaseName);
        fileName = directory + fileName;
        if (!fileName.endsWith(".ser")) fileName = fileName + ".ser";
        if (!Files.exists(Path.of(fileName))) {
            return database;
        } else
            try {
                ObjectInputStream objectInputStream = null;
                objectInputStream = new ObjectInputStream(
                        new FileInputStream(fileName));
                database = (EmployeeDatabase) objectInputStream.readObject();
                database.setFileName(databaseName);
                objectInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
       return database;
    }

}
