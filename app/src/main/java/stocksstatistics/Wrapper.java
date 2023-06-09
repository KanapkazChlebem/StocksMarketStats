package stocksstatistics;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Wrapper {
    private String directory = System.getProperty("user.dir")+ "\\";

    public Wrapper() {
    }

    public void saveDatabase(String fileName, EmployeeDatabase database) {
        if (fileName==null) fileName="DefaultDatabase";
        fileName=directory+fileName+".ser";
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

    public EmployeeDatabase createOrReadDatabase(String fileName) {
        if (fileName == null) fileName = "DefaultDatabase";
        fileName=directory+fileName;
        EmployeeDatabase database = new EmployeeDatabase();
        if (!fileName.endsWith(".ser")) fileName=fileName+".ser";
        if (!Files.exists(Path.of(fileName))) {
            try {
                File file = new File(fileName);
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else
            try {
                ObjectInputStream objectInputStream = null;
                objectInputStream = new ObjectInputStream(
                        new FileInputStream(fileName));
                database = (EmployeeDatabase) objectInputStream.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
        return database;
    }

}
