package serialization;

import java.io.IOException;
import java.util.List;

public interface Serializer<T> {
    public List<T> read(String path) throws IOException, ClassNotFoundException;
    public void write(String path, List<T> objects) throws IOException;
}
