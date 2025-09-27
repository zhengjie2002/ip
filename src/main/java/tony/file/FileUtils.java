package tony.file;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

import tony.ui.Ui;

public class FileUtils {
    private String filePath;
    private File file;
    private final Ui ui = new Ui();

    public FileUtils(String filePath) {
        this.filePath = filePath;
    }

    private void createFile() {
        try {
            if (file.exists()) {
                return;
            }
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
        } catch (IOException e) {
            ui.printErrorCreatingFile(e.getMessage());
        }
    }

    public void loadFile() {
        file = new File(filePath);
        if (!file.exists()) {
            createFile();
        }
    }

    public ArrayList<String> readFile() {
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try {
            return (ArrayList<String>) Files.readAllLines(file.toPath(), Charset.defaultCharset());
        } catch (IOException e) {
            ui.printErrorReadingFile(e.getMessage());
            return new ArrayList<>();
        }
    }

    public void appendToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.write(System.lineSeparator());
        fw.close();
    }
}
