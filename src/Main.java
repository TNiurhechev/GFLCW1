import model.Souvenir;

import java.io.IOException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        run();
    }

    public static void run() throws IOException, ClassNotFoundException {
        new DataOperator().display();
    }
}
