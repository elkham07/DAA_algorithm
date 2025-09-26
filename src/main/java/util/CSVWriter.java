package util;

import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {
    private final String file;

    public CSVWriter(String file) {
        this.file = file;
    }

    public void write(String algo, int n, long time, Metrics m) {
        try (FileWriter fw = new FileWriter(file, true)) {
            fw.write(algo + "," + n + "," + time + "," + m.depth + "," + m.comparisons + "," + m.swaps + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
