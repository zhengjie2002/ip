package tony.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;

import tony.ui.Ui;

/**
 * Utility class for file operations.
 * The <code>FileUtils</code> class provides methods to create, load, read, append, and clear files.
 */
public class FileUtils {
    /** The file path where the file operations are performed. */
    private String filePath;

    private File file;

    private final Ui ui = new Ui();

    public FileUtils(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates a new file at the specified path if it does not already exist.
     * If the parent directories do not exist, they are created as well.
     */
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
            ui.printCreateFileError(e.getMessage());
        }
    }

    /**
     * Loads the file at the specified path.
     * If the file does not exist, it is created.
     */
    public void loadFile() {
        file = new File(filePath);
        if (!file.exists()) {
            createFile();
        }
    }

    /**
     * Reads the contents of the file and returns them as a list of strings.
     * Each string in the list represents a line in the file.
     *
     * @return A list of strings representing the file's contents, or an empty list if the file does not exist or an error occurs.
     */
    public ArrayList<String> readFile() {
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try {
            return (ArrayList<String>) Files.readAllLines(file.toPath(), Charset.defaultCharset());
        } catch (IOException e) {
            ui.printReadFileError(e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Appends the specified text to the file.
     * A new line is added after the text.
     *
     * @param textToAdd The text to append to the file.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void appendToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.write(System.lineSeparator());
        fw.close();
    }

    /**
     * Clears the contents of the file.
     *
     * @throws IOException If an I/O error occurs while clearing the file.
     */
    public void clearFile() throws IOException {
        FileWriter fw = new FileWriter(filePath, false);
        fw.close();
    }
}