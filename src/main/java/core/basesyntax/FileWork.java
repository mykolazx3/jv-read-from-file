package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class FileWork {
    private static final char SPECIFIED_CHARACTER = 'w';
    private static final String CHARACTER_DIVIDER = "\\W+";

    public String[] readFromFile(String fileName) {

        StringBuilder stringBuilder = new StringBuilder();
        File file = new File(fileName);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(System.lineSeparator());
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }

        if (stringBuilder.toString().isEmpty()) {
            return new String[0];
        }

        String [] splitedWords = stringBuilder.toString().toLowerCase().split(CHARACTER_DIVIDER);
        stringBuilder = new StringBuilder();

        for (String selectedWord: splitedWords) {
            if (selectedWord.charAt(0) == SPECIFIED_CHARACTER) {
                stringBuilder.append(selectedWord).append(" ");
            }
        }

        if (stringBuilder.toString().isEmpty()) {
            return new String[0];
        }

        String [] result = stringBuilder.toString().split(" ");
        Arrays.sort(result);
        return result;
    }
}
