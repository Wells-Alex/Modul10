import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Task2 {
    private String name;
    private int age;

    public Task2() {}

    public Task2(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String[] args) {
        List<Task2> users = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Paths.get("src/main/java/file2.txt"));
            if (lines.isEmpty()) {
                System.out.println("Файл пустой");
                return;
            }
            String[] headers = lines.get(0).split(" ");

            for (int i = 1; i < lines.size(); i++) {
                String[] values = lines.get(i).split(" ");

                String name = "";
                int age = 0;

                for (int j = 0; j < headers.length; j++) {
                    if ("name".equals(headers[j])) {
                        name = values[j];
                    } else if ("age".equals(headers[j])) {
                        age = Integer.parseInt(values[j]);
                    }
                }

                users.add(new Task2(name, age));
            }

            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File("src/main/java/user.json"), users);

            System.out.println("Файл user.json создан");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}