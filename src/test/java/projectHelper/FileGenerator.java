package projectHelper;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileGenerator {


    public static PrintWriter writerFile(String text) throws FileNotFoundException {
        return new PrintWriter(text);
    }


}
