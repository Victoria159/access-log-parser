import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int count = 0;
        while (count>=0) {
            System.out.println("Введите путь к папке: ");
            String path = new Scanner(System.in).nextLine();
            File file = new File(path);
            boolean fileExists = file.exists();
            boolean isDirectory = file.isDirectory();
            if (fileExists == isDirectory) {
                System.out.println("Файл не существует или указанный путь является путём к папке, а не к файлу");
               //continue;
            } else {
                System.out.println("Путь указан верно");
                count++;
                System.out.println("Это файл номер "+ count);
                FileAnalysis.analtsisFile(path);
    }
}
    }
}
//D:\Test\access.log