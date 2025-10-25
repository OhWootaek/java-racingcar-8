package racingcar;

import java.util.ArrayList;
import java.util.List;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class Application {
    public static void checkCar(ArrayList<String> cars) throws IllegalArgumentException {
        for (String car : cars) {
            if (car.trim().isEmpty() || car.trim().length() > 5) {
                throw new IllegalArgumentException("IllegalArgumentException");
            }
        }
    }

    public static void checkNum(String num) throws IllegalArgumentException {
        int number;
        try {
            number = Integer.parseInt(num);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("IllegalArgumentException");
        }
        if (number <= 0) throw new IllegalArgumentException("IllegalArgumentException");
    }

    public static void main(String[] args) {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");

        // 차 이름 입력
        String input = readLine();
        ArrayList<String> cars = new ArrayList<>(List.of(input.split(",")));
        try {
            checkCar(cars);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // 경주 횟수 입력
        System.out.println("시도할 횟수는 몇 회인가요?");
        String num = readLine();
        try {
            checkNum(num);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
