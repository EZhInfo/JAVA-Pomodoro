package com.EZhInfo;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class PomodoroTimer {

    /**
     * input: -w -b  -время работы  -время отдыха
     * output: Таймер заведен. Пора работать
     * истекла минута
     * output: пора отдыхать
     * output: таймер закончился
     * <p>
     * input: -help
     * output: инструкция
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Введите время работы и отдыха");
        // [-w, 1, -b, 1]
        var cmd = new Scanner(System.in).nextLine().split(" ");

        int workTime = 1;
        int breakTime = 1;
        boolean isHelp = false;

        for (int i = 0; i < cmd.length; i++) {
            switch (cmd[i]) {
                case "-w" -> workTime = Integer.parseInt(cmd[++i]);
                case "-b" -> breakTime = Integer.parseInt(cmd[++i]);
                case "--help" -> {
                    System.out.println("""
                            \nPromodo - это приложение для улучшения личной эффективности труда.
                            Параметры команды:
                            -w - время работы, минут
                            -b - время отдыха, минут
                            --help - вызов справки
                            """);
                    isHelp = true;
                }
            }
        }

        if (isHelp) return;

        long startTime = System.currentTimeMillis();
        timer(workTime, breakTime);
        long stopTime = System.currentTimeMillis();
        System.out.println("Таймер работал " + (stopTime - startTime) / (1000 * 60) + " мин.");

        System.out.println("workTime = " + workTime + ", breakTime = " + breakTime);

    }

    public static void timer(int workTime, int breakTime) throws InterruptedException {
        printProgress("Work progress: ", workTime, 30);
        printProgress("Break progress: ", breakTime, 30);
    }
    private static void printProgress(String process, int time, int size) throws InterruptedException {
        int length;
        int rep;

        length = 60 * time / size;
        rep = 60 * time / length;
        int strechb = size / (3 * time);

        for (int i =1; i <= rep; i++){
            double x = i;
            x = 1.0 / 3.0 * x;
            x *= 10;
            x = Math.round(x);
            x /= 10;
            double w = time * strechb;
            double percent = (x / w) * 1000;
            x /= strechb;
            x *= 10;
            x = Math.round(x);
            x /= 10;
            percent = Math.round(percent);
            percent /= 10;

            System.out.print(process + percent + "% " + (" ").repeat(5 - (String.valueOf(percent).length())) +
                    "[" + ("#").repeat(i) + ("-").repeat(rep - i) + "]   ( " + x + " min / " + time + " min )" + "\r");
            if (true){
                TimeUnit.SECONDS.sleep(length);
            }
        }
        System.out.println();
    }
}
