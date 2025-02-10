import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BobFileManager {

    private static final String FILE_PATH = "./data/bobTasks.txt";

    public static void writeToFile(String textToAdd) throws BobException {
        File file = new File(FILE_PATH);
        File parentDir = file.getParentFile();

        // Ensure the directory exists
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                 writer.write(textToAdd);
        } catch (IOException e) {
            throw new BobException("Error Saving");
        }
    }
}
