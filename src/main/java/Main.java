// 1.Организовать ввод и хранение данных пользователей. ФИО возраст и пол
// 2. вывод в формате Фамилия И.О. возраст пол
// 3. добавить возможность выхода или вывода списка отсортированного по возрасту!)
// 4. *реализовать сортировку по возрасту с использованием индексов
// 5. *реализовать сортировку по возрасту и полу с использованием индексов

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Добро пожаловать в систему!");
        while (true) {
            System.out.println("______________________________\n!!!МЕНЮ!!!\n1)Ввести данные нового пользователя" +
                    "\n2)Увидеть список пользователей\n3)Отсортировать список по возрасту (без индексации)" +
                    "\n4)Отсортировать список по возрасту (с индексацией)" +
                    "\n5)Отсортировать список по полу (с индексацией)" +
                    "\n6)Выход");
            Scanner scanner = new Scanner(System.in);
            int choice = parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    StringBuilder builderUser = new StringBuilder();
                    System.out.print("Давайте добавим нового пользователя. Введите фамилию: ");
                    String surname = scanner.nextLine();
                    builderUser.append(surname + " ");
                    System.out.print("Введите имя: ");
                    String name = scanner.nextLine();
                    builderUser.append(name + " ");
                    System.out.print("Введите отчество: ");
                    String middleName = scanner.nextLine();
                    builderUser.append(middleName + " ");
                    System.out.print("Введите возраст: ");
                    String age = scanner.nextLine();
                    builderUser.append(age + " ");
                    System.out.print("Если пол мужской, то ничего не пишите и нажмите Enter, если женский, то введите любое слово или число: ");
                    String gender = scanner.nextLine();
                    if (gender.isEmpty()) {
                        builderUser.append("мужской");
                    } else builderUser.append("женский");
                    try (FileWriter fw = new FileWriter("data.txt", true)) {
                        fw.append("\n");
                        fw.write(builderUser.toString());
                        fw.flush();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }

                    break;

                case 2:
                    System.out.println("Данные о пользователях: ");
                    BufferedReader br = new BufferedReader(new FileReader("data.txt"));
                    String str;
                    while ((str = br.readLine()) != null) {
                        if (!str.isEmpty()) {
                            String[] strings = str.replaceAll("\\s{2,}", " ").split(" ");
                            StringBuilder builder1 = new StringBuilder();
                            String s1 = builder1.append("Фамилия: ").append(strings[0])
                                    .append(" Имя: ").append(strings[1].charAt(0))
                                    .append(" Отчество: ").append(strings[2].charAt(0))
                                    .append(" Возраст: ").append(strings[3])
                                    .append(" Пол: ").append(strings[4]).toString();
                            System.out.println(s1);
                        }
                    }
                    br.close();
                    break;

                case 3:
                    System.out.println("Сортирую список пользователей по возрасту (без индексации)");
                    ArrayList<String[]> users = new ArrayList<>();

                    BufferedReader br1 = new BufferedReader(new FileReader("data.txt"));
                    String str1;
                    while ((str1 = br1.readLine()) != null) {
                        if (!str1.isEmpty()) {
                            String[] strings = str1.replaceAll("\\s{2,}", " ").split(" ");
                            String user[] = {strings[0], strings[1], strings[2], strings[3], strings[4]};
                            users.add(user);
                        }
                    }
                    SortByAge(users);
                    br1.close();
                    break;

                case 4:
                    System.out.println("Сортирую список пользователей по возрасту (с индексацией)");
                    ArrayList<Integer[]> ageHelper = new ArrayList<>();

                    BufferedReader br2 = new BufferedReader(new FileReader("data.txt"));
                    String str2;
                    int i = 1;
                    while ((str2 = br2.readLine()) != null) {
                        if (!str2.isEmpty()) {
                            String[] strings = str2.replaceAll("\\s{2,}", " ").split(" ");
                            Integer[] ageFinder = {i++, Integer.parseInt(strings[3])};
                            ageHelper.add(ageFinder);
                        }
                    }
                    SortByAgeWithIndexes(ageHelper);
                    br2.close();
                    break;

                case 5:
                    System.out.println("Сортирую список пользователей по полу (с индексацией)");
                    ArrayList<String[]> _gender = new ArrayList<>();

                    BufferedReader br3 = new BufferedReader(new FileReader("data.txt"));
                    String str3;
                    int j = 1;
                    while ((str3 = br3.readLine()) != null) {
                        if (!str3.isEmpty()) {
                            String[] strings = str3.replaceAll("\\s{2,}", " ").split(" ");
                            String[] genderLine = {String.valueOf(j++), (strings[3]), strings[4]};
                            _gender.add(genderLine);
                        }
                    }
                    SortByGenderWithIndexes(_gender);
                    br3.close();
                    break;

                case 6:
                    System.out.println("Спасибо за ваш вклад в развитие нашего сервиса");
                    scanner.close();
                    exit(0);
            }
        }
    }

    public static void SortByAge(ArrayList<String[]> arrayList) {
        arrayList.sort(new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return Integer.parseInt(o1[3]) - Integer.parseInt(o2[3]);
            }
        });
        for (String[] mystr : arrayList) {
            System.out.println("Фамилия: " + mystr[0] + " " + " Имя: " + mystr[1].charAt(0) + " "
                    + " Отчество: " + mystr[2].charAt(0) + " " +
                    " Возраст: " + mystr[3] + " Пол: " + mystr[4]);
        }
    }

    public static void SortByAgeWithIndexes(ArrayList<Integer[]> integers) {
        integers.sort(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {

                return o1[1] - o2[1];
            }
        });
        for (Integer[] myinteger : integers) {
            System.out.println("ID" + " " + myinteger[0] + " " + "Возраст" + " " + myinteger[1]);
        }
    }

    public static void SortByGenderWithIndexes(ArrayList<String[]> genderStrings) {
        genderStrings.sort(new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {

                return (int) o2[2].charAt(0) - (int) o1[2].charAt(0);
            }
        });


        for (String[] genderString : genderStrings) {
            System.out.println("ID" + " " + genderString[0] + " " + "Возраст" + " " + genderString[1] + " " +
                    "Пол" + " " + genderString[2]);
        }
    }
}


