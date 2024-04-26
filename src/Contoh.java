import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Contoh {
    public static void main(String[] args) throws IOException {
//   cara standar
//        try {
//            FileInputStream file = new FileInputStream("input.txt");
//        } catch (FileNotFoundException e) {
//            System.err.println("file not found");
//        }
        // cara non-standar
        // kasih throw aja ke PSVM nya.. wkwkw
            FileInputStream file = new FileInputStream("src/input.txt");
        System.err.println((char) file.read());

            System.err.println("error");

        System.out.println("tai");
    }
}
