import java.util.*;

public class main {
    private String модель;
    private int ОЗУ;
    private int объемЖД;
    private String операционнаяСистема;
    private String цвет;

    // Конструктор
    public main(String модель, int ОЗУ, int объемЖД, String операционнаяСистема, String цвет) {
        this.модель = модель;
        this.ОЗУ = ОЗУ;
        this.объемЖД = объемЖД;
        this.операционнаяСистема = операционнаяСистема;
        this.цвет = цвет;
    }

    // Геттеры для доступа к полям
    public String getМодель() {
        return модель;
    }

    public int getОЗУ() {
        return ОЗУ;
    }

    public int getОбъемЖД() {
        return объемЖД;
    }

    public String getОперационнаяСистема() {
        return операционнаяСистема;
    }

    public String getЦвет() {
        return цвет;
    }

    // Метод для отображения информации о ноутбуке
    @Override
    public String toString() {
        return "Модель: " + модель + ", ОЗУ: " + ОЗУ + "ГБ, Объем ЖД: " + объемЖД + "ГБ, Операционная система: " + операционнаяСистема + ", Цвет: " + цвет;
    }

    // Метод для фильтрации списка ноутбуков по заданным критериям
    public static List<main> фильтроватьНоутбуки(Set<main> ноутбуки, Map<String, Object> критерии) {
        List<main> результат = new ArrayList<>();
        for (main ноутбук : ноутбуки) {
            boolean соответствует = true;
            for (Map.Entry<String, Object> entry : критерии.entrySet()) {
                String критерий = entry.getKey();
                Object значение = entry.getValue();
                switch (критерий) {
                    case "ОЗУ":
                        if (ноутбук.getОЗУ() < (int) значение) {
                            соответствует = false;
                        }
                        break;
                    case "ОбъемЖД":
                        if (ноутбук.getОбъемЖД() < (int) значение) {
                            соответствует = false;
                        }
                        break;
                    case "ОперационнаяСистема":
                        if (!ноутбук.getОперационнаяСистема().equalsIgnoreCase((String) значение)) {
                            соответствует = false;
                        }
                        break;
                    case "Цвет":
                        if (!ноутбук.getЦвет().equalsIgnoreCase((String) значение)) {
                            соответствует = false;
                        }
                        break;
                }
            }
            if (соответствует) {
                результат.add(ноутбук);
            }
        }
        return результат;
    }

    public static void main(String[] args) {
        main ноутбук1 = new main("Модель 1", 8, 512, "Windows", "Черный");
        main ноутбук2 = new main("Модель 2", 16, 1024, "macOS", "Серебро");
        main ноутбук3 = new main("Модель 3", 8, 256, "Linux", "Черный");

        Set<main> ноутбуки = new HashSet<>();
        ноутбуки.add(ноутбук1);
        ноутбуки.add(ноутбук2);
        ноутбуки.add(ноутбук3);

        Scanner scanner = new Scanner(System.in);

        Map<String, Object> критерии = new HashMap<>();
        System.out.println("Введите критерии фильтрации:");
        System.out.print("1 - ОЗУ, 2 - Объем ЖД, 3 - Операционная система, 4 - Цвет: ");
        int выбор = scanner.nextInt();
        switch (выбор) {
            case 1:
                System.out.print("Введите минимальное значение ОЗУ: ");
                int минимальнаяОЗУ = scanner.nextInt();
                критерии.put("ОЗУ", минимальнаяОЗУ);
                break;
            case 2:
                System.out.print("Введите минимальный объем ЖД: ");
                int минимальныйОбъемЖД = scanner.nextInt();
                критерии.put("ОбъемЖД", минимальныйОбъемЖД);
                break;
            case 3:
                System.out.print("Введите операционную систему: ");
                String операционнаяСистема = scanner.next();
                критерии.put("ОперационнаяСистема", операционнаяСистема);
                break;
            case 4:
                System.out.print("Введите цвет: ");
                String цвет = scanner.next();
                критерии.put("Цвет", цвет);
                break;
            default:
                System.out.println("Неверный ввод.");
        }

        List<main> результат = фильтроватьНоутбуки(ноутбуки, критерии);
        System.out.println("Результаты фильтрации:");
        if (результат.isEmpty()) {
            System.out.println("Нет ноутбуков, удовлетворяющих критериям.");
        } else {
            for (main ноутбук : результат) {
                System.out.println(ноутбук);
            }
        }
    }
}
