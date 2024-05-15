//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class FileAnalysis {
//    public static void analtsisFile(String path) {
//        // чтение файла
//        int lines = 0;
//        int countYB = 0;
//        int countGB = 0;
//        List<ArrayList<Object>> l1 = new ArrayList<ArrayList<Object>>();
//        //Statistics test = new Statistics();
//        try {
//            FileReader fileReader = new FileReader(path);
//            BufferedReader reader = new BufferedReader(fileReader);
//            String line;
//            while ((line = reader.readLine()) != null) {
//                int length = line.length();
//
//// разбор строки
//                l1.add(new ArrayList<Object>());
//                l1.get(lines).add(0, line.substring(0, line.indexOf(" "))); //ip address
//                line = line.substring(line.indexOf(" ") + 1);
//                l1.get(lines).add(1, line.substring(0, line.indexOf(" ")));//первый -
//                line = line.substring(line.indexOf(" ") + 1);
//                l1.get(lines).add(2, line.substring(0, line.indexOf(" ")));//второй  -
//                line = line.substring(line.indexOf(" ") + 2);
//                l1.get(lines).add(3, line.substring(0, line.indexOf("] ")));//дата и время  -
//                line = line.substring(line.indexOf("] ") + 3);
//                l1.get(lines).add(4, line.substring(0, line.indexOf("\" ")));//URL  -
//                line = line.substring(line.indexOf("\" ") + 2);
//                l1.get(lines).add(5, line.substring(0, line.indexOf(" ")));//Код  -
//                line = line.substring(line.indexOf(" ") + 1);
//                l1.get(lines).add(6, line.substring(0, line.indexOf(" ")));//Размер  -
//                line = line.substring(line.indexOf(" ") + 2);
//                l1.get(lines).add(7, line.substring(0, line.indexOf("\" ")));//URI
//                line = line.substring(line.indexOf("\" ") + 3);
//                l1.get(lines).add(8, line.substring(0, line.indexOf("\"")));//User-Agent
//// разбор строки закончен
//                String firstBrackets = (String) l1.get(lines).get(8);
//
//                if (firstBrackets.indexOf("(") >= 0 & firstBrackets.indexOf(")") >= 0) {
//                    firstBrackets = firstBrackets.substring(firstBrackets.indexOf("(") + 1, firstBrackets.indexOf(")"));
//                }
//                String[] parts = firstBrackets.split(";");
//
//                if (parts.length >= 2) {
//                    String fragment = parts[1].trim();
//                    if (fragment.indexOf("/") >= 0) {
//                        fragment = fragment.substring(0, fragment.indexOf("/"));
//                    }
//                    l1.get(lines).add(9, fragment);
//                } else {
//                    l1.get(lines).add(9, firstBrackets);// имя бота
//                }
//                lines++;
//                if (length > 1024) {
//                    throw new ExceedingCharacters("длина строки № " + lines + " больше, чем 1024 символа");
//                }
//            }
//        } catch (FileNotFoundException ex1) {
//            ex1.printStackTrace();
//        } catch (IOException ex2) {
//            ex2.printStackTrace();
//        } catch (ExceedingCharacters ex3) {
//            ex3.printStackTrace();
//            return;
//        }
//        // чтение файла закончилось
//        System.out.println("Количество строк в файле: " + lines);
//        for (int i = 0; i < l1.size(); i++) {
//            if (l1.get(i).get(9).equals("YandexBot")) {
//                countYB++;
//            }
//            if (l1.get(i).get(9).equals("Googlebot")) {
//                countGB++;
//            }
//        }
//        System.out.println("YandexBot: " + countYB);
//        System.out.println("Googlebot: " + countGB);
//
//    }
//}
//
//
