import java.io.BufferedReader;
import java.io.FileReader;

public class FileAnalysis {
    public static void analtsisFile (String path){
        int lines = 0;
        int maxLength =Integer.MIN_VALUE;
        int minLength =Integer.MAX_VALUE;
        int countYB = 0;
        int countGB = 0;
        int countMozilla = 0;
        try{
            FileReader fileReader = new FileReader(path);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                lines++;
                String[] words = line.split("[\"]");
                        for (String word:words){
                            if (word.contains("Googlebot"))
                                countGB++;
                            if (word.contains("YandexBot"))
                                countYB++;
                            if (word.contains("MozillaBot")){
                                countMozilla++;
                }
                        if (line.length()>maxLength){
                            maxLength = line.length();
                        }
                        if (line.length()<minLength){
                            minLength = line.length();
                        }
                                    }
        }
            System.out.println("Количество запросов от Yandexbot:"+countYB);
            System.out.println("Количество запросов от Googlebot:"+countGB);
            //"или"+percentage(countGB,lines)+"%")
            System.out.println("Общее количество строк: " + lines);
            System.out.println("Самая длинная строка (кол-во символов): " + maxLength);
            System.out.println("Самая короткая строка (кол-во символов): " + minLength);
    }catch (Exception ex){
            ex.printStackTrace();
        }
        if (maxLength> 1024){
            throw new ExceedingCharacters("В файле встретилась строка длиннее 1024 символов");
        }
}
    public static class ExceedingCharacters extends RuntimeException {
        public ExceedingCharacters(String message) {
            super(message);
        }
    }
}

