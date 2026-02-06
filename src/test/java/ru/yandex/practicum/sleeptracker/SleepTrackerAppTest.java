package ru.yandex.practicum.sleeptracker;

import functions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;

public class SleepTrackerAppTest {

    static List<SleepSession> sleepSessions;
    MaxSessionDuration maxSessionDuration = new MaxSessionDuration();
    BadSleepSession badSleepSession = new BadSleepSession();
    AvgSessionDuration avgSessionDuration = new AvgSessionDuration();
    MinSessionDuration minSessionDuration = new MinSessionDuration();
    SessionCounter sessionCounter = new SessionCounter();
    SleeplessSessions sleeplessSessions = new SleeplessSessions();
    UserChronotype userChronotype = new UserChronotype();

    @BeforeAll
    public static void beforeAll() {
        sleepSessions = List.of(
                new SleepSession("01.10.25 23:00;02.10.25 02:00;GOOD"),
                new SleepSession("02.10.25 23:00;03.10.25 01:00;NORMAL"),
                new SleepSession("03.10.25 23:00;04.10.25 04:00;BAD")
        );
    }

    @Test
    @DisplayName("Средняя продолжительность сна")
    void testAvgSessionDuration() {
        Assertions.assertEquals(200, avgSessionDuration.apply(sleepSessions).getResult());
        Assertions.assertNotEquals(220, avgSessionDuration.apply(sleepSessions).getResult());
    }

    @Test
    @DisplayName("Количество сна с плохим качеством")
    void testBadSleepSessionCount() {
        Assertions.assertEquals(1, badSleepSession.apply(sleepSessions).getResult());
        Assertions.assertNotEquals(3, badSleepSession.apply(sleepSessions).getResult());
    }

    @Test
    @DisplayName("Максимальная длинна сессии")
    void testMaxSessionDuration() {
        Duration duration = Duration.ofMinutes(300);
        Assertions.assertEquals(duration.toMinutes(), maxSessionDuration.apply(sleepSessions).getResult());
        Assertions.assertNotEquals(duration.plusMinutes(10).toMinutes(), maxSessionDuration.apply(sleepSessions).getResult());
    }

    @Test
    @DisplayName("Минимальная длинна сессии")
    void testMinSessionDuration() {
        Duration duration = Duration.ofMinutes(120);
        Assertions.assertEquals(duration.toMinutes(), minSessionDuration.apply(sleepSessions).getResult());
        Assertions.assertNotEquals(duration.plusMinutes(10).toMinutes(), minSessionDuration.apply(sleepSessions).getResult());
    }

    @Test
    @DisplayName("Количество сессий")
    void testSessionCounter() {
        Assertions.assertEquals(3, sessionCounter.apply(sleepSessions).getResult());
        Assertions.assertNotEquals(4, sessionCounter.apply(sleepSessions).getResult());
    }

    @Test
    @DisplayName("Бессонные ночи")
    void testSleeplessSessionsCount() {
        long sleeplessSessionsCount = 0L;
        Assertions.assertEquals(sleeplessSessionsCount, sleeplessSessions.apply(sleepSessions).getResult());
        Assertions.assertNotEquals(sleeplessSessionsCount + 1, sleeplessSessions.apply(sleepSessions).getResult());
        List<SleepSession> sleepSessionsNew = List.of(
                new SleepSession("01.10.25 00:00;01.10.25 10:00;GOOD"),
                new SleepSession("02.10.25 07:00;02.10.25 19:00;NORMAL"),
                new SleepSession("03.10.25 23:00;04.10.25 04:00;BAD"),
                new SleepSession("01.11.25 19:00;01.11.25 23:00;BAD")
        );
        Assertions.assertEquals(30L, sleeplessSessions.apply(sleepSessionsNew).getResult());
        List<SleepSession> sleepSessionsNew2 = List.of(
                new SleepSession("01.10.25 23:00;02.10.25 02:00;GOOD"),
                new SleepSession("02.10.25 07:00;02.10.25 19:00;NORMAL"),
                new SleepSession("03.10.25 23:00;04.10.25 04:00;BAD"),
                new SleepSession("01.11.25 19:00;01.11.25 23:00;BAD")
        );
        Assertions.assertEquals(29L, sleeplessSessions.apply(sleepSessionsNew2).getResult());
    }

    @Test
    @DisplayName("Проверка на хронотип")
    void testUserChronotype() {
        Assertions.assertEquals(Chronotype.PIGEON, userChronotype.apply(sleepSessions).getResult());
        Assertions.assertNotEquals(Chronotype.LARK, userChronotype.apply(sleepSessions).getResult());
        List<SleepSession> sleepSessions = List.of(
                new SleepSession("01.10.25 23:30;02.10.25 10:00;GOOD"),
                new SleepSession("02.10.25 23:30;03.10.25 10:00;GOOD"),
                new SleepSession("03.10.25 21:00;04.10.25 06:00;BAD"),
                new SleepSession("04.10.25 21:00;05.10.25 06:00;BAD")
        );
        Assertions.assertEquals(Chronotype.PIGEON, userChronotype.apply(sleepSessions).getResult());
    }
}