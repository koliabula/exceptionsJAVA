import java.util.Date;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * project
 */


/*Напишите приложение, которое будет запрашивать у пользователя следующие данные в произвольном порядке, разделенные пробелом:
Фамилия Имя Отчество датарождения номертелефона пол

Форматы данных:
фамилия, имя, отчество - строки
датарождения - строка формата dd.mm.yyyy
номертелефона - целое беззнаковое число без форматирования
пол - символ латиницей f или m.

Приложение должно проверить введенные данные по количеству. Если количество не совпадает с требуемым, вернуть код ошибки, обработать его и показать пользователю сообщение, что он ввел меньше и больше данных, чем требуется.

Приложение должно попытаться распарсить полученные значения и выделить из них требуемые параметры. Если форматы данных не совпадают, нужно бросить исключение, соответствующее типу проблемы. Можно использовать встроенные типы java и создать свои. Исключение должно быть корректно обработано, пользователю выведено сообщение с информацией, что именно неверно.

Если всё введено и обработано верно, должен создаться файл с названием, равным фамилии, в него в одну строку должны записаться полученные данные, вида

<Фамилия><Имя><Отчество><датарождения> <номертелефона><пол>

Однофамильцы должны записаться в один и тот же файл, в отдельные строки.

Не забудьте закрыть соединение с файлом.

При возникновении проблемы с чтением-записью в файл, исключение должно быть корректно обработано, пользователь должен увидеть стектрейс ошибки.

Данная промежуточная аттестация оценивается по системе "зачет" / "не зачет"

"Зачет" ставится, если слушатель успешно выполнил
"Незачет"" ставится, если слушатель успешно выполнил

Критерии оценивания:
Слушатель напишите приложение, которое будет запрашивать у пользователя следующие данные в произвольном порядке, разделенные пробело
 */



public class project {

    public static void main(String[] args) {
        String [] dataUser = dataentry(); 
        MyReaderFunction(dataUser);

    }


    public static void MyReaderFunction(String[] sAr){


            try (FileWriter fileWriter = new FileWriter(sAr[0].split(" ")[0] + ".txt", true)) {
                for (String s : sAr) {
                    fileWriter.write(s+" ");
                }
                    fileWriter.write("\n");
            }
            catch (IOException e){
                System.out.println(e.getMessage());
            }
        
    }

    public static String [] dataentry (){  
        Scanner in = new Scanner(System.in);
        String [] dataArray= {" фамилия, имя, отчество (через пробел)", 
                                "дату рождения (строка формата dd.mm.yyyy)", 
                                "номер телефона с кодом (целое беззнаковое число без форматирования (12 цифр))",  //я из беларуси, поэтому 12 цифр
                                "пол (символ латиницей f или m)"};
         String [] dataUser = new String[dataArray.length];
         int[] randIndex = randomArrayIndex();

        for (int i = 0; i < dataArray.length; ) {
            System.out.println("Введите "+ dataArray[randIndex[i]] + "\n");
            dataUser[randIndex[i]] = in.nextLine();
            try {
                dataAnalysis(dataUser[randIndex[i]], randIndex[i]);
                i++;
            }
            catch(NullElException e){

                System.out.println("\n"+e.getMessage());
                System.out.println("Попробуйте ещё раз:");
            }
            catch(FIOException e){

                System.out.println("\n"+e.getMessage());
                System.out.println("Попробуйте ещё раз:");
            }
            catch(FormatDateException e){
                System.out.println("\n"+e.getMessage());
                System.out.println("Попробуйте ещё раз:");
            }
            catch(NumberException e){
                System.out.println("\n"+e.getMessage());
                System.out.println("Попробуйте ещё раз:");
            }
            catch(GenderException e){
                System.out.println("\n"+e.getMessage());
                System.out.println("Попробуйте ещё раз:");
            }

        } 
        return dataUser;      
    }

    static int[] randomArrayIndex() {
        int[] arr = {0, 1, 2, 3};
        Random random = new Random();
        int[] newArray = new int[arr.length];
        List<Integer> indexes = new ArrayList<>(arr.length);
        int count = 0;
        while (true) {
            int var = random.nextInt(arr.length);
            if (!indexes.contains(var)) {
                indexes.add(var);
                newArray[var] = arr[count++];
            }
            if (count == arr.length) {
                break;
            }
        }
        //System.out.println(Arrays.toString(newArray));
        return newArray;
    }


    public static void dataAnalysis(String s, int i){
        if(s == ""){
            throw new NullElException("Вы ничего не ввели");
        }
        else {
            if (i == 0) {
                String[] str = s.trim().split(" "); // проверку на лишние пробелы не делал
                if (str.length != 3) {
                    throw new FIOException("Вы ввели " + str.length + " слова");
                }
            }
            if (i == 1) {
                DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                try {
                    Date date = format.parse(s);
                } catch (ParseException e) {
                    throw new FormatDateException("Неверный формат даты");
                }
            
            }

            if(i == 2){
                try {
                    long number = Long.parseLong(s);
                    if(number < 0) {

                        throw new NumberException("Номер введён некорректно");        
                    }  
                } catch (NumberFormatException e) {
                    throw new NumberException("Номер введён некорректно");
                }
            }

            if(i == 3){
                
                if (!s.equals("f") & !s.equals("m")) {
                    System.out.println(s);
                    throw new GenderException("Нужно ввести 'f' или 'm'");
                } 
            }

        }
        
    }
}