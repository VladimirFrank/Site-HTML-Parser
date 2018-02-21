package collections;

import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidParameterException;
import java.util.StringJoiner;

/**
 * Created by sbt-filippov-vv on 20.02.2018.
 */
public class FileStringWriter {

    public void writeRandomStringsInFile(String path, long count){

        if(count < 1 || path == null) throw new InvalidParameterException("PATH String must be non null " +
                "and expected COUNT > 0, your count = " + count);

        String word = "WOLOLO_";

        StringJoiner stringJoiner = new StringJoiner(",");

        for(int i = 0; i < count; i++) stringJoiner.add(word + String.valueOf(i));

        try(PrintWriter printWriter = new PrintWriter(path)) {
            printWriter.write(stringJoiner.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
