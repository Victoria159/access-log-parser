import java.io.*;
import java.time.Duration;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int count = 0;
        while (count >= 0) {
            // проверка началась
            System.out.println("Введите путь к папке: ");
            String path = new Scanner(System.in).nextLine();
            File file = new File(path);
            boolean fileExists = file.exists();
            boolean isDirectory = file.isDirectory();
            if (fileExists == isDirectory) {
                System.out.println("Файл не существует или указанный путь является путём к папке, а не к файлу");
                continue;
            } else {
                System.out.println("Путь указан верно");
                count++;
                System.out.println("Это файл номер " + count);
            }
            //построчное чтение файла
            int cnt_line = 0;
            int cnt_y = 0;
            int cnt_g = 0;
            Statistics test2 = new Statistics();
            try {
                FileReader fileReader = new FileReader(path);
                BufferedReader reader = new BufferedReader(fileReader);
                String line;
                while ((line = reader.readLine()) != null) {
                    int length = line.length();
                    cnt_line += 1;
                    if (length > 1024) {
                        throw new ExceedingCharacters("Длина строки номер " + cnt_line + " больше, чем 1024 символа.");
                    }
                    if (line.indexOf("(") > 0 && line.indexOf(")") > 0) {

                        test2.addEntry(new LogEntry(line));
                        //Нахождение User-Agent
                        String firstBrackets = line.substring(line.lastIndexOf("\"", line.indexOf("(")), line.indexOf("\"", line.indexOf("(")) + 1);
                        String[] parts = firstBrackets.split(";");
                        if (parts.length >= 2) {
                            String fragment = parts[1];
                            if (fragment.indexOf("/") > 0) {
                                String bot = (fragment.substring(0, fragment.indexOf("/")));
                                //очистка от пробелов и нахождение ботов
                                if (bot.replace(" ", "").equals("YandexBot")) {
                                    cnt_y++;
                                }
                                if (bot.replace(" ", "").equals("Googlebot")) {
                                    cnt_g++;
                                }
                            }
                        }
                    }
                }
            } catch (FileNotFoundException ex1) {
                System.out.println("Файл не существует");
                ex1.printStackTrace();
            } catch (IOException ex2) {
                System.out.println("Ошибка при вводе данных");
                ex2.printStackTrace();
            }
            System.out.println(test2.getAvgTotalVisitPerHour());
            System.out.println(test2.getAvgErrorPerHour());
            System.out.println(test2.userVisit);
            System.out.println(test2.getAvgVisitUniqUser());
        }
    }
}


//D:\Test\access.log