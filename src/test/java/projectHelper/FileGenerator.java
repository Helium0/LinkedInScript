package projectHelper;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileGenerator {


    public static PrintWriter writerFile() throws FileNotFoundException {
        return new PrintWriter("invited_persons.txt");
    }


}
