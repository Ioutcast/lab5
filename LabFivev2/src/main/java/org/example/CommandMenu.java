package org.example;


import lombok.Getter;
import lombok.ToString;
import org.example.UpdateWorker.UpdateOrganizationField.setOrganizationField;
import org.example.UpdateWorker.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static org.example.XmlWriteToFile.xmlWriterToFile;


/**
 * Класс по работе с командами
 * обработка, реализация
 * класс, где находится карта
 */
@ToString

public class CommandMenu {
    private static String ScriptFileName = "";
    private static File newXml = new File("newXml.xml");

    @Getter
    public final Map<Integer, Worker> mapCollection = new LinkedHashMap<>();
    private static final LocalDate dateCollection = LocalDate.now();
    public static boolean isAutomatic = false;

    public void help() {
        System.out.println("help : вывести справку по доступным командам");
        System.out.println("info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.");
        System.out.println("show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        System.out.println("insert null {element} : добавить новый элемент с заданным ключом");
        System.out.println("update id {element} : обновить значение элемента коллекции, id которого равен заданному");
        System.out.println(" remove_key null : удалить элемент из коллекции по его ключу");
        System.out.println("clear : очистить коллекцию");
        System.out.println("save : сохранить коллекцию в файл");
        System.out.println("execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        System.out.println("exit : завершить программу (без сохранения в файл)");
        System.out.println("remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный");
        System.out.println("remove_greater_key null : удалить из коллекции все элементы, ключ которых превышает заданный");
        System.out.println("remove_lower_key null : удалить из коллекции все элементы, ключ которых меньше, чем заданный");
        System.out.println("remove_any_by_end_date endDate : удалить из коллекции один элемент, значение поля endDate которого эквивалентно заданному");
        System.out.println("filter_greater_than_organization organization : вывести элементы, значение поля organization которых больше заданного");
        System.out.println("print_field_descending_organization : вывести значения поля organization всех элементов в порядке убывания");
    }

    public void info() {
        System.out.println("Тип коллекции: " + mapCollection.getClass().getSimpleName());
        System.out.println("Дата инициализации: " + dateCollection.toString());
        System.out.println("Количество элементов: " + mapCollection.size());
    }

    public void show() {
        if (mapCollection.size() != 0) {
            System.out.println("Вывод колекции:");
            for (Map.Entry<Integer, Worker> entry : mapCollection.entrySet()) {
                System.out.println("Ключ " + entry.getKey());
                System.out.println("Элемент коллекции " + entry.getValue().toString());
            }
        } else System.out.println("Размер коллекции == 0");
    }

    /**
     * @param words
     * @param arguments
     * @throws IOException
     */
    public void insert(ArrayList<String> words, List<String> arguments) throws IOException {
        if (mapCollection.containsKey(Integer.parseInt(words.get(0)))) {
            System.out.println("Такой элемент уже существует");
        } else update(words, arguments, isAutomatic);
    }

    /**
     * clear collection
     */
    public void clear() {
        mapCollection.clear();
        System.out.println("Коллекция очищена");
    }


    public void printFieldDescendingOrganization() {
        boolean k = true;
        ArrayList<Organization> organizations = new ArrayList();
        for (Map.Entry<Integer, Worker> entry : mapCollection.entrySet()) {
            organizations.add(entry.getValue().getOrganization());
        }
        for (Organization organization : organizations) {
            if (organization == null) {
                k = false;
                System.out.println("Сортировка не выполена как сравнивать organization = null?");
            }
        }
        if (k) {
            Comparator<Organization> comparator = Comparator.comparingLong(Organization::getEmployeesCount);
            ArrayList<Organization> sorted_organizations = (ArrayList<Organization>) organizations.stream().sorted(comparator).collect(Collectors.toList());
            for (Organization organization : sorted_organizations) {
                System.out.println(organization);
            }
        }
    }

    /**
     * @param words
     * @param arguments
     * @param isAuto
     * @throws IOException
     */
    public void update(ArrayList<String> words, List<String> arguments, boolean isAuto) throws IOException {
        BufferedInputStream bf = new BufferedInputStream(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(bf, StandardCharsets.UTF_8));
        Worker worker = new Worker();

        UpdateWorkerNameClass.UpdateWorkerName(worker, arguments, isAuto, reader);

        UpdateWorkerCoordinatesClass.UpdateWorkerCoordinates(worker, arguments, isAuto, reader);

        UpdateWorkerSalaryClass.UpdateWorkerSalary(worker, arguments, isAuto, reader);

        UpdateWorkerStartDateClass.UpdateWorkerStartDate(worker, arguments, isAuto, reader);

        UpdateWorkerEndDateClass.UpdateWorkerEndDate(worker, arguments, isAuto, reader);

        UpdateWorkerStatusClass.UpdateWorkerStatus(worker, arguments, isAuto, reader);

        UpdateWorkerOrganization(worker, arguments, isAuto, reader);

        mapCollection.put(Integer.parseInt(words.get(0)), worker);


    }

    /**
     * @param worker
     * @param arguments
     * @param isAuto
     * @throws IOException
     */
    public void UpdateWorkerOrganization(Worker worker, List<String> arguments, boolean isAuto, BufferedReader reader) throws IOException {
        Organization organization = setOrganizationDefault(arguments, isAuto, reader);
        worker.setOrganization(organization);
    }

    /**
     * @param arguments
     * @param isAuto1
     * @return
     * @throws IOException
     */
    public Organization createOrganization(List<String> arguments, boolean isAuto1) throws IOException {
        BufferedInputStream bf = new BufferedInputStream(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(bf, StandardCharsets.UTF_8));
        Organization organization = setOrganizationDefault(arguments, isAuto1, reader);
        return organization;
    }

    /**
     * @param arguments
     * @param isAuto1
     * @param reader
     * @return
     * @throws IOException
     */
    private Organization setOrganizationDefault(List<String> arguments, boolean isAuto1, BufferedReader reader) throws IOException {
        System.out.println("Ввод организации. Организация null? Если да  введите -> \"\", нет -> любое слово ");
        Organization organization = null;
        boolean key = true;
        if (isAuto1) {
            if (arguments.remove(0).equals(""))
                key = false;
        } else if (reader.readLine().equals(""))
            key = false;
        if (key) {
            Long employeesCount = null;
            System.out.println("Введите employeesCount ");
            employeesCount = setOrganizationField.setEmployeesCount(arguments, isAuto1, reader, employeesCount);


            OrganizationType type = null;
            System.out.println("Введите OrganizationType (COMMERCIAL/PUBLIC/PRIVATE_LIMITED_COMPANY/OPEN_JOINT_STOCK_COMPANY)");
            type = setOrganizationField.setOrganizationType(arguments, isAuto1, reader, type);

            String street = "";
            System.out.println("Введите street");
            street = setOrganizationField.setStreet(arguments, isAuto1, reader, street);

            String zipCode = "";
            System.out.println("Введите zipCode");
            zipCode = setOrganizationField.setZipCode(arguments, isAuto1, reader, zipCode);

            Double locationX = null;
            System.out.println("Введите locationX");
            locationX = setOrganizationField.setLocationX(arguments, isAuto1, reader, locationX);

            Integer locationY = null;
            System.out.println("Введите locationY");
            locationY = setOrganizationField.setLocationY(arguments, isAuto1, reader, locationY);

            String locationName = "";
            System.out.println("Введите locationName");
            locationName = setOrganizationField.setLocationName(arguments, isAuto1, reader, locationName);

            organization = new Organization(employeesCount, type, new Address(street, zipCode, new Location(locationX, locationY, locationName)));
        }

        return organization;
    }

    /**
     * @param words
     * @param arguments
     * @param isAuto
     * @throws NumberFormatException
     */
    public void removeLowerKey(ArrayList<String> words, List<String> arguments, boolean isAuto) throws NumberFormatException {
        // https://javarevisited.blogspot.com/2017/08/how-to-remove-key-value-pair-from-map-iteration-java-example.html?m=1
        if (mapCollection.size() != 0) {
            Set<Map.Entry<Integer, Worker>> setOfEntries = mapCollection.entrySet();
            Iterator<Map.Entry<Integer, Worker>> iterator = setOfEntries.iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, Worker> entry = iterator.next();
                Integer value = entry.getKey();
                if (isAuto)
                    if (value.compareTo(Integer.valueOf(arguments.get(0))) < 0) {
                        System.out.println("removeing : " + entry);
                        iterator.remove();
                    } else if (value.compareTo(Integer.valueOf(words.get(0))) < 0) {
                        System.out.println("removeing : " + entry);
                        iterator.remove();
                    }
            }
            arguments.remove(0);
        } else System.out.println("Размер коллекции == 0");
    }

    /**
     * @param words
     * @param arguments
     * @param isAuto
     * @throws NumberFormatException
     */
    public void removeGreaterKey(ArrayList<String> words, List<String> arguments, boolean isAuto) throws NumberFormatException {
        // https://javarevisited.blogspot.com/2017/08/how-to-remove-key-value-pair-from-map-iteration-java-example.html?m=1
        if (mapCollection.size() != 0) {
            Set<Map.Entry<Integer, Worker>> setOfEntries = mapCollection.entrySet();
            Iterator<Map.Entry<Integer, Worker>> iterator = setOfEntries.iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, Worker> entry = iterator.next();
                Integer value = entry.getKey();
                if (isAuto)
                    if (value.compareTo(Integer.valueOf(arguments.get(0))) > 0) {
                        System.out.println("removeing : " + entry);
                        iterator.remove();
                    } else if (value.compareTo(Integer.valueOf(words.get(0))) > 0) {
                        System.out.println("removeing : " + entry);
                        iterator.remove();
                    }
            }
            arguments.remove(0);
        } else System.out.println("Размер коллекции == 0");
    }

    /**
     * @param words
     * @param arguments
     * @param isAuto
     */
    public void removeAnyByEndDate(ArrayList<String> words, List<String> arguments, boolean isAuto) {
        // https://javarevisited.blogspot.com/2017/08/how-to-remove-key-value-pair-from-map-iteration-java-example.html?m=1
        if (mapCollection.size() != 0) {
            Set<Map.Entry<Integer, Worker>> setOfEntries = mapCollection.entrySet();
            Iterator<Map.Entry<Integer, Worker>> iterator = setOfEntries.iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, Worker> entry = iterator.next();
                String value = String.valueOf(entry.getValue().getEndDate());
                if (isAuto) {
                    if (value.equals(arguments.remove(0) + "T" + arguments.remove(0))) {
                        System.out.println("removing : " + entry);
                        iterator.remove();
                        break;
                    } else if (value.equals(words.get(0) + "T" + words.get(1))) {
                        System.out.println("removing : " + entry);
                        iterator.remove();
                        break;
                    }
                }
            }
        } else System.out.println("Размер коллекции == 0");
    }

    /**
     * @param arguments
     * @param isAuto
     * @throws IOException
     */
    public void filterGreaterThanOrganization(List<String> arguments, boolean isAuto) throws IOException {
        if (mapCollection.size() != 0) {
            Organization organization = createOrganization(arguments, isAuto);
            if (organization == null) {
                System.out.println("null organization не сравниваем, введите команду занов");
            } else {
                System.out.println("элементы, значение поля organization которых больше заданного:");
                for (Map.Entry<Integer, Worker> entry : mapCollection.entrySet()) {
                    if (entry.getValue().getOrganization().compareTo(organization) > 0) {
                        System.out.println("Ключ " + entry.getKey());
                        System.out.println("Элемент коллекции" + entry.getValue().toString());
                    }
                }
            }
        } else System.out.println("Размер коллекции == 0");
    }

    /**
     * @throws UnsupportedEncodingException
     * @throws TransformerException
     * @throws ParserConfigurationException
     */
    public void save() throws IOException, TransformerException, ParserConfigurationException {
        xmlWriterToFile(mapCollection, newXml);
        System.out.println("Коллекция сохранена в файл.");
    }

    /**
     * @param arguments
     * @param isAuto
     * @return
     * @throws IOException
     */
    public Map create(List<String> arguments, boolean isAuto) throws IOException {

        BufferedInputStream bf = new BufferedInputStream(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(bf, StandardCharsets.UTF_8));

        Worker worker = new Worker();

        UpdateWorkerNameClass.UpdateWorkerName(worker, arguments, isAuto, reader);

        UpdateWorkerCoordinatesClass.UpdateWorkerCoordinates(worker, arguments, isAuto, reader);

        UpdateWorkerSalaryClass.UpdateWorkerSalary(worker, arguments, isAuto, reader);

        UpdateWorkerStartDateClass.UpdateWorkerStartDate(worker, arguments, isAuto, reader);

        UpdateWorkerEndDateClass.UpdateWorkerEndDate(worker, arguments, isAuto, reader);

        UpdateWorkerStatusClass.UpdateWorkerStatus(worker, arguments, isAuto, reader);

        UpdateWorkerOrganization(worker, arguments, isAuto, reader);


        Map<Integer, Worker> mapCreater = new LinkedHashMap<>();
        mapCreater.put(1, worker);
        return mapCreater;
    }

    /**
     * @param arguments
     * @param isAuto
     * @throws IOException
     */
    public void removeLower(List<String> arguments, boolean isAuto) throws IOException {
        if (mapCollection.size() != 0) {
            Map<Integer, Worker> mapRemoveMethod = create(arguments, isAuto);
            Set<Map.Entry<Integer, Worker>> setOfEntries = mapCollection.entrySet();
            Iterator<Map.Entry<Integer, Worker>> iterator = setOfEntries.iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, Worker> entry = iterator.next();
                Worker worker1 = entry.getValue();
                if (worker1.compareTo(mapRemoveMethod.get(1)) < 0) {
                    System.out.println("removing : " + entry);
                    iterator.remove();
                }
            }
        } else System.out.println("Размер коллекции == 0");
    }

    /**
     * @param words
     * @param arguments
     * @param isAuto
     * @return
     */
    public State removeKey(ArrayList<String> words, List<String> arguments, boolean isAuto) {
        State state;
        try {
            if (isAuto) {
                if (mapCollection.containsKey(Integer.parseInt(arguments.remove(0)))) {
                    mapCollection.remove(Integer.parseInt(arguments.remove(0)));
                    System.out.println("Удаление прошло успешно");
                    state = State.OK;
                } else {
                    System.out.println("Данного ключа нет");
                    state = State.ERROR;
                }
            } else {
                if (mapCollection.containsKey(Integer.parseInt(words.get(0)))) {
                    mapCollection.remove(Integer.parseInt(words.get(0)));
                    System.out.println("Удаление прошло успешно");
                    state = State.OK;
                } else {
                    System.out.println("Данного ключа нет");
                    state = State.ERROR;
                }
            }
        } catch (NumberFormatException e) {
            System.out.print("Неверный формат ключа");
            state = State.ERROR;
        }
        return state;
    }

    /**
     * @param words
     * @throws Exception
     */
    public void executeScript(ArrayList<String> words) throws Exception {
        if (!ScriptFileName.equals("")) {
            System.out.println("Не будем создавать рекурсию");
        } else {
            String FILENAME = words.get(0);
            ScriptFileName = FILENAME;
            String scriptCommand = "";
            try (BufferedInputStream bf = new BufferedInputStream(new FileInputStream(FILENAME))) {
                int ch;
                while ((ch = bf.read()) != -1) {
                    scriptCommand = scriptCommand.concat(String.valueOf((char) ch));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] scriptCommandArr = scriptCommand.split("\n");
            for (int i = 0; i < scriptCommandArr.length; i++) {
                commandManager(scriptCommandArr[i].replace("\r", ""), new ArrayList<>(), false);
            }

        }
    }

    public State commandManager(String command, List<String> arguments, boolean mode) throws Exception {
        isAutomatic = mode;
        String[] line = command.split(" ");
        ArrayList<String> words = new ArrayList<>(Arrays.asList(line));

        switch (words.remove(0)) {

            case "help":
                CheckByLengthMoreWords(words, 0);
                help();
                return State.OK;

            case "info":
                CheckByLengthMoreWords(words, 0);
                info();
                return State.OK;

            case "show":
                CheckByLengthMoreWords(words, 0);
                show();
                return State.OK;

            case "insert":
                CheckByLengthWords(!(words.size() == 1), "неожиданное количество параметров");
                if (parseSecondArgInteger(words)) {
                    insert(words, arguments);
                } else return State.ERROR;
                return State.OK;

            case "update":
                CheckByLengthWords(!(words.size() == 1), "неожиданное количество параметров");
                if (mapCollection.containsKey(Integer.parseInt(words.get(0)))) {
                    update(words, arguments, isAutomatic);
                    return State.OK;
                } else {
                    System.out.println("такого ключа нет. Введине команду заново.");
                    return State.ERROR;
                }

            case "remove_key":
                State state = null;
                CheckByLengthWords(!(words.size() == 1), "неожиданное количество параметров");
                if (parseSecondArgInteger(words)) {
                    state = removeKey(words, arguments, isAutomatic);
                } else return State.ERROR;
                return state;

            case "clear":
                CheckByLengthMoreWords(words, 0);
                clear();
                return State.OK;
            case "save":
                CheckByLengthMoreWords(words, 0);
                save();
                return State.OK;

            case "execute_script":
                CheckByLengthWords(!(words.size() == 1), "неожиданное количество параметров");
                executeScript(words);
                return State.OK;

            case "remove_lower":
                CheckByLengthWords(!(words.size() == 0), "неожиданное количество параметров");
                removeLower(arguments, isAutomatic);
                return State.OK;

            case "remove_greater_key":
                CheckByLengthWords(!(words.size() == 1), "неожиданное количество параметров");
                if (parseSecondArgInteger(words)) {
                    removeGreaterKey(words, arguments, isAutomatic);
                } else return State.ERROR;
                return State.OK;

            case "remove_lower_key":
                CheckByLengthWords(!(words.size() == 1), "неожиданное количество параметров");
                if (parseSecondArgInteger(words)) {
                    removeLowerKey(words, arguments, isAutomatic);
                } else return State.ERROR;
                return State.OK;

            case "remove_any_by_end_date":
                CheckByLengthWords(!(words.size() == 2), "неожиданное количество параметров");
                removeAnyByEndDate(words, arguments, isAutomatic);
                return State.OK;

            case "filter_greater_than_organization":
                CheckByLengthMoreWords(words, 0);
                filterGreaterThanOrganization(arguments, isAutomatic);
                return State.OK;

            case "print_field_descending_organization":
                CheckByLengthMoreWords(words, 0);
                printFieldDescendingOrganization();
                return State.OK;
            default:
                System.out.println("Some problems with input your command");
                return State.NOT_FOUND_COMMAND;
        }

    }

    /**
     * @param words
     * @return
     */
    private boolean parseSecondArgInteger(ArrayList<String> words) {
        try {
            Integer.parseInt(words.get(0));
        } catch (NumberFormatException e) {
            System.out.println("Данные введены некорректно. Повторите ввод.");
            return false;
        }
        return true;
    }

    /**
     * @param b
     * @param s
     * @throws Exception
     */
    private void CheckByLengthWords(boolean b, String s) throws Exception {
        if (b)
            throw new Exception(s);
    }

    /**
     * @param words
     * @param length
     * @throws Exception
     */
    private void CheckByLengthMoreWords(ArrayList<String> words, int length) throws Exception {
        CheckByLengthWords(words.size() > length, "Проверь количество параметров");
    }

    enum State {
        OK,
        NOT_FOUND_COMMAND,
        ERROR,
        WORK,
        BROKEN
    }

}

