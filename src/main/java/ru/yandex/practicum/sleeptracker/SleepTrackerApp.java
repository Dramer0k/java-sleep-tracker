package ru.yandex.practicum.sleeptracker;

import functions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.function.Function;

public class SleepTrackerApp {
    static List<SleepSession> sessionList;
    static SleepTrackerApp stApp = new SleepTrackerApp();
    private final List<Function<List<SleepSession>, SleepAnalysisResult>> ANALYTIC_FUNCTION = List.of(
            new SessionCounter(),
            new MinSessionDuration(),
            new MaxSessionDuration(),
            new AvgSessionDuration(),
            new BadSleepSession(),
            new SleeplessSessions(),
            new UserChronotype()
    );

    public static void main(String[] args) {

        try (FileReader fileReader = new FileReader(args[0], StandardCharsets.UTF_8)) {
            BufferedReader br = new BufferedReader(fileReader);
            sessionList = br.lines().map(SleepSession::new).toList();
        } catch (IOException e) {
            System.out.println("Лог файл не найден!");
        }

        List<SleepAnalysisResult> result = stApp.analyzeSessions(sessionList);
        result.forEach(System.out::println);
    }

    private List<SleepAnalysisResult> analyzeSessions(List<SleepSession> session) {
        return ANALYTIC_FUNCTION.stream()
                .map(function -> function.apply(session))
                .toList();
    }
}