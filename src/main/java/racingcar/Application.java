package racingcar;

import camp.nextstep.edu.missionutils.Randoms;

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

    public static int checkNum(String num) throws IllegalArgumentException {
        int number;
        try {
            number = Integer.parseInt(num);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("IllegalArgumentException");
        }
        if (number <= 0) throw new IllegalArgumentException("IllegalArgumentException");
        return number;
    }
    
    // 각 라운드 별로 각각의 Car 객체가 전진할지 여부를 결정
    public static void roundResult(ArrayList<Car> cars) {
        for (Car car : cars) {
            int i = Randoms.pickNumberInRange(0, 9);
            if (i >= 4) {
                car.setDistance(car.getDistance() + 1);
            }
        }
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
        int n = 0;
        try {
            n = checkNum(num);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // Car 객체 목록 생성
        ArrayList<Car> carList = new ArrayList<>();
        for (String carName : cars) {
            Car n_car = new Car(carName);
            carList.add(n_car);
        }
        
        // 입력받은 n번 만큼 round 진행
        for (int i = 0; i < n; i++) {
            roundResult(carList);
        }
    }
}
