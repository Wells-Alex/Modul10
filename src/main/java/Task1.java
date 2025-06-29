import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task1 {
    public static void main(String[] args) {
        String regex = "^((\\d{3}-\\d{3}-\\d{4}) | (\\(\\d{3}\\) \\d{3}-\\d{4}))$";
        Pattern pattern = Pattern.compile(regex);
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/file.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {

                line = line.trim();

                Matcher matcher = pattern.matcher(line);
                if (matcher.matches()) {
                    System.out.println(line);
                }
            }

        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}