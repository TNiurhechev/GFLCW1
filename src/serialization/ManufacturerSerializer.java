package serialization;

import model.Manufacturer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ManufacturerSerializer implements Serializer<Manufacturer> {
    @Override
    public List<Manufacturer> read(String path) throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(path));
        List<Manufacturer> manufacturers = (List<Manufacturer>) inputStream.readObject();
        inputStream.close();
        return manufacturers;
    }

    @Override
    public void write(String path, List<Manufacturer> manufacturers) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(path));
        for (Manufacturer manufacturer : manufacturers)
            outputStream.writeObject(manufacturer);
        outputStream.flush();
        outputStream.close();
    }
}
