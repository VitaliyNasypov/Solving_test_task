package ru.solution;

import java.util.*;
import java.util.stream.Collectors;

public class Run {
    public static void main(String[] args) {
        Run run = new Run();
        String[] inputData = run.inputData();
        String indexRemoveGasStation = "-1";
        String maxDistance = "-1";
        if (inputData[0].length() == 3 && !inputData[1].isEmpty()) {
            List<Integer> gasStationCoordinates = run.sort(inputData[1]);
            List<Integer> removeGasStation = run.removeGasStation(gasStationCoordinates,
                    inputData[0]);
            indexRemoveGasStation = run.findIndexRemovePoint(inputData[1], removeGasStation,
                    gasStationCoordinates);
            maxDistance = run.findMaxDistance(gasStationCoordinates);
        }
        run.outputData(new String[]{maxDistance, indexRemoveGasStation});
    }

    // Шаг1. Получил данные для обработки
    private String[] inputData() {
        Scanner scanner = new Scanner(System.in);
        String inputFirst = scanner.nextLine();
        String inputSecond = scanner.nextLine();
        return new String[]{inputFirst, inputSecond};
    }

    // Шаг 2. Отсортировал координаты станций в порядке возрастания
    private List<Integer> sort(String inputSecond) {
        List<Integer> gasStationCoordinates = new LinkedList<>();
        for (String s : inputSecond.split(" ")) {
            int value = Integer.parseInt(s);
            gasStationCoordinates.add(value);
        }
        Collections.sort(gasStationCoordinates);
        return gasStationCoordinates;
    }

    // Шаг 3. Возвращаю список станций для удаления
    private List<Integer> removeGasStation(List<Integer> gasStationCoordinates, String inputFirst) {
        // Шаг 3.1. Создал объекты org.solution.GasStation
        // Создал отрезки, теперь знаю длину их. Далее отсортировал по длине в порядке возрастания
        List<GasStation> gasStationList = new LinkedList<>();
        for (int i = 1; i < gasStationCoordinates.size(); i++) {
            gasStationList.add(new GasStation(gasStationCoordinates.get(i - 1),
                    gasStationCoordinates.get(i)));
        }
        Collections.sort(gasStationList);
        // Шаг 3.2. Добавил в List станции с самой маленькой длинной, которые будут удалены
        // Первую точку и последнюю не удаляем
        int k = Integer.parseInt(inputFirst.split(" ")[1]);
        List<Integer> listRemove = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (gasStationList.get(i).getEndPoint()
                    != gasStationCoordinates.get(gasStationCoordinates.size() - 1)) {
                listRemove.add(gasStationList.get(i).getEndPoint());
            } else {
                listRemove.add(gasStationList.get(i).getStartPoint());
            }
        }
        return listRemove;
    }

    // Шаг 4. Теперь я нахожу индексы удалённых станций в строке, которую получил на входе
    private String findIndexRemovePoint(String inputSecond, List<Integer> listRemove,
                                        List<Integer> gasStationCoordinates) {
        String[] arrayString = inputSecond.split(" ");
        HashMap<String, Integer> indexPointGasStation = new HashMap<>();
        for (int i = 0; i < arrayString.length; i++) {
            indexPointGasStation.put(arrayString[i], i);
        }
        for (int i = 0; i < listRemove.size(); i++) {
            String point = String.valueOf(listRemove.get(i));
            if (indexPointGasStation.containsKey(point)) {
                int index = indexPointGasStation.get(point) + 1;
                listRemove.set(i, Integer.parseInt(String.valueOf(index)));
                indexPointGasStation.remove(point);
                gasStationCoordinates.remove((Integer) Integer.parseInt(point));
            }
        }
        return listRemove.stream().sorted().map(String::valueOf)
                .collect(Collectors.joining(" "));
    }

    // Шаг 5. Ищу максимальную длину оставшихся станций
    private String findMaxDistance(List<Integer> gasStationCoordinates) {
        int maxDistance = 0;
        for (int i = 1; i < gasStationCoordinates.size(); i++) {
            int distance = gasStationCoordinates.get(i) - gasStationCoordinates.get(i - 1);
            if (distance > maxDistance) {
                maxDistance = distance;
            }
        }
        return String.valueOf(maxDistance);
    }

    // Шаг 6. Возвращаю результат в консоль
    private void outputData(String[] output) {
        Arrays.stream(output).forEach(System.out::println);
    }
}