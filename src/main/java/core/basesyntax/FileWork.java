package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

public class FileWork {
    public String[] readFromFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        Path path = Path.of(fileName);
        
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path.toFile()))) {
            String fileContent = bufferedReader.readLine();
            
            while (fileContent != null) {
                String [] splitRow = fileContent.toLowerCase().split("[\\s.,?!]+");
                for (String word : splitRow) {
                    if (word.startsWith("w")) {
                        builder.append(word).append(" ");
                    }
                }
                fileContent = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File was not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't open a file", e);
        }
        
        if (builder.isEmpty()) {
            return new String[0];
        }
        String [] result = builder.toString().split(" ");
        Arrays.sort(result);
        
        return result;
    }
}
