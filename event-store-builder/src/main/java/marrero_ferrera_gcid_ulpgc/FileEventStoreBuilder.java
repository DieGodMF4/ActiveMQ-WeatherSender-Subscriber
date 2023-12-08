package marrero_ferrera_gcid_ulpgc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileEventStoreBuilder {
    private static final String basePath = "eventstore/prediction.Weather/";

    public void storeMessage(String jsonString) {
        // Process the JSON string and extract necessary information (source and date)
        String source = getSource(jsonString);
        String date = extractDate(jsonString);

        // Directory structure
        String directoryPath = "eventstore/prediction.Weather/" + source + "/" + date;
        String fileName = "events.json"; // You can use a more descriptive name

        // Full path of the file
        String fullPath = Paths.get(directoryPath, fileName).toString();

        // Write JSON string to file
        writeJsonToFile(fullPath, jsonString);

        System.out.println("JSON data written to file: " + fullPath);
    }

    private void writeJsonToFile(String filePath, String jsonString) {
        try {
            // Create a directory if it doesn't exist
            Path directoryPath = Paths.get(filePath).getParent();
            Files.createDirectories(directoryPath);

            // Create a File object
            File file = new File(filePath);

            // Use Jackson's ObjectMapper to write JSON string to the file
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Enable pretty printing

            // Write JSON string to the file
            objectMapper.writeValue(file, jsonString);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getSource(String jsonString) {
        // Logic to extract source from JSON string
        // Replace this with your actual logic
        return "source_placeholder";
    }

    private String extractDate(String jsonString) {
        // Logic to extract date from JSON string
        // Replace this with your actual logic
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(new Date());
    }
}

