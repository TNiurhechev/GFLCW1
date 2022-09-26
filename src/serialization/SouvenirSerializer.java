package serialization;

import model.Manufacturer;
import model.Souvenir;

import java.io.*;
import java.util.List;

public class SouvenirSerializer implements Serializer<Souvenir> {
    @Override
    public List<Souvenir> read(String path) throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(path));
        List<Souvenir> souvenirs = (List<Souvenir>) inputStream.readObject();
        inputStream.close();
        return souvenirs;
    }

    @Override
    public void write(String path, List<Souvenir> souvenirs) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(path));
        for (Souvenir souvenir : souvenirs)
            outputStream.writeObject(souvenir);
        outputStream.flush();
        outputStream.close();
    }
}
