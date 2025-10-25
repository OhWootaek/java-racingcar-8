package racingcar;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    @Test
    void 기능_테스트() {
        assertRandomNumberInRangeTest(
            () -> {
                run("pobi,woni", "1");
                assertThat(output()).contains("pobi : -", "woni : ", "최종 우승자 : pobi");
            },
            MOVING_FORWARD, STOP
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("pobi,javaji", "1"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 공동_우승_테스트() {
        assertRandomNumberInRangeTest(
            () -> {
                run("pobi,woni", "1");
                assertThat(output()).contains("pobi : -", "woni : -", "최종 우승자 : pobi, woni");
            },
            MOVING_FORWARD, MOVING_FORWARD
        );
    }

    @Test
    void 여러_차수_진행_테스트() {
        assertRandomNumberInRangeTest(
            () -> {
                run("pobi,woni,jun", "2");
                assertThat(output()).contains(
                    "실행 결과",
                    "pobi : -",
                    "woni : ",
                    "jun : -",
                    "",
                    "pobi : --",
                    "woni : -",
                    "jun : -",
                    "최종 우승자 : pobi"
                    );
                },
                MOVING_FORWARD, STOP, MOVING_FORWARD,
                MOVING_FORWARD, MOVING_FORWARD, STOP
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "pobi,,jun",
            "pobi, ,jun"
    })
    void 이름_공백_예외_테스트(String carNames) {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException(carNames, "1"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "a",
            "1.5",
            "0",
            "-1"
    })
    void 시도_횟수_예외_테스트(String trialCount) {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("pobi,woni", trialCount))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
