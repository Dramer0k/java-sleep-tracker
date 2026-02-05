package functions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.Chronotype;
import ru.yandex.practicum.sleeptracker.SleepSession;

import java.util.List;

class UserChronotypeTest {

    UserChronotype userChronotype = new UserChronotype();
    List<SleepSession> sleepSessions = List.of(
            new SleepSession("01.10.25 23:30;02.10.25 10:00;GOOD"),
            new SleepSession("02.10.25 23:40;03.10.25 11:00;NORMAL"),
            new SleepSession("03.10.25 23:00;04.10.25 04:00;BAD"),
            new SleepSession("03.10.25 23:00;04.10.25 04:00;BAD")
    );

    @Test
    void testUserChronotypeOWL() {
        Assertions.assertEquals(Chronotype.OWL, userChronotype.apply(sleepSessions).getResult());
    }

    @Test
    void testUserChronotypeLARK() {
        Assertions.assertNotEquals(Chronotype.LARK, userChronotype.apply(sleepSessions).getResult());
    }

}