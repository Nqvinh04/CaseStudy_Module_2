package ProductStorage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileProduct<T> {
    public FileProduct() {
    }

    public List<T> readFile(String filePath) {

        File file = new File(filePath);
        List<T> list = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object obj = null;
            while ((obj = objectInputStream.readObject()) != null){
                list.add((T) obj);
            }
            fileInputStream.close();
            objectInputStream.close();
        } catch (EOFException ignored) {
        } catch (IOException | ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return list;
    }

    public void write(String filePath, List<T> list) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        for (T object : list){
            objectOutputStream.writeObject(object);
        }
        fileOutputStream.close();
        objectOutputStream.close();
    }
}
