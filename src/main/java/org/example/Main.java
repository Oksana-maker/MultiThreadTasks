package org.example;
//1. Напишіть програму, яка обчислює суму чисел великого масиву.
// Розділіть обчислення суми на кілька потоків, де кожен потік обробляє
// окремий діапазон елементів масиву. Порівняйте час виконання програми з
// одним потоком і декількома потоками. Переконайтесь, що правильно синхронізуєте
// доступ до змінних, які зберігають суму.
//
//        array[0] + arrary[1] + ...
//
//        2. Створити програму, яка симулює гонку автомобілів.
//        Кожен автомобіль має свою швидкість і початкову позицію.
//        Створити потоки для кожного автомобіля, які будуть переміщати автомобілі
//        на певні відстані залежно від їх швидкості.
//        Перевірити, який автомобіль першим долатиме фінішну лінію.

//        3. Додадкове завдання: Напишіть клас, реалізуючий інтерфейс Runnable,
//        метод run(), який обчислює файл на жорсткому диску та виводить у вказаний
//        потік будь-які дані.
//        Для виведення кожної "порції" даних має бути використано кілька операцій
//        виведення. Операції виведення повинні бути розділені викликами sleep(10).
//        Запишіть 10 класів цього класу в різних потоках так, щоб вони вивели
//        екземпляри в один і той самий потік виведення. Вивід інформації повинен
//        бути синхронізований так,
//        щоб у результуючому вихідному потоці порції даних не "перемішувалися".
//        Створіть запис загальних даних із потоку в окремому файлі на жорсткому диску.


import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

record Car(String name, int speed, int position) {
    public int setSpeed() {
        Random random = new Random();
        int sp = random.nextInt(5, 30);
        return sp;
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        //task_1();
        race();
    }

    public static void threadRace(Car car) {
        Thread threadCar = new Thread(() -> {
            System.out.println("Started " + car.name() + " " + LocalDateTime.now());
            for (int i = 0; i < 1000; i++) {
                try {
                    int sp = car.setSpeed();
                    Thread.sleep(sp);
                    i++;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Finished " + car.name() + " " + LocalDateTime.now());
        });
        threadCar.start();
    }

    public static void race() {
        List<Car> cars = List.of(new Car("Car 1", 0, 0),
                new Car("Car 2", 0, 0),
                new Car("Car 3", 0, 0)
        );
        for (Car car : cars) {
            threadRace(car);
        }
    }

    public static void task_1() throws InterruptedException {
        counterThread();
        counter();
    }

    public static void counterThread() throws InterruptedException {
        Object monitor = new Object();
        long[] amount1 = new long[1];
        long[] amount2 = new long[1];
        long[] amount3 = new long[1];
        long[] amount4 = new long[1];
        long[] amount5 = new long[1];
        long[] amount6 = new long[1];
        long[] amount7 = new long[1];
        long[] amount8 = new long[1];
        Thread thread_1 = new Thread(() -> {
            synchronized (monitor) {
                for (int i = 0; i < 1000_001; i++) {
                    amount1[0] += i;
                }
            }

        });

        Thread thread_2 = new Thread(() -> {
            synchronized (monitor) {
                for (int i = 1000_001; i < 2000_001; i++) {
                    amount2[0] += i;
                }
            }
        });
        Thread thread_3 = new Thread(() -> {
            synchronized (monitor) {
                for (int i = 2000_001; i < 3000_001; i++) {
                    amount3[0] += i;
                }
            }

        });

        Thread thread_4 = new Thread(() -> {
            synchronized (monitor) {
                for (int i = 3000_001; i < 4000_001; i++) {
                    amount4[0] += i;
                }
            }
        });
        Thread thread_5 = new Thread(() -> {
            synchronized (monitor) {
                for (int i = 4000_001; i < 5000_001; i++) {
                    amount5[0] += i;
                }
            }

        });

        Thread thread_6 = new Thread(() -> {
            synchronized (monitor) {
                for (int i = 5000_001; i < 6000_001; i++) {
                    amount6[0] += i;
                }
            }
        });

        Thread thread_7 = new Thread(() -> {
            synchronized (monitor) {
                for (int i = 6000_001; i < 7000_001; i++) {
                    amount7[0] += i;
                }
            }

        });

        Thread thread_8 = new Thread(() -> {
            synchronized (monitor) {
                for (int i = 7000_001; i < 8000_001; i++) {
                    amount8[0] += i;
                }
            }
        });
        thread_1.start();
        thread_2.start();
        thread_3.start();
        thread_4.start();
        thread_5.start();
        thread_6.start();
        thread_7.start();
        thread_8.start();
        thread_1.join();
        thread_2.join();
        thread_3.join();
        thread_4.join();
        thread_5.join();
        thread_6.join();
        thread_7.join();
        thread_8.join();
        System.out.println(amount1[0] + amount2[0] + amount3[0] + amount4[0]
                + amount5[0] + amount6[0] + amount7[0] + amount8[0] + " : " + LocalDateTime.now() + " ->Thread");
    }

    public static void counter() {
        long amount = 0;
        for (int i = 0; i <= 8000_000; i++) {
            amount += i;
        }
        System.out.println(amount + " : " + LocalDateTime.now());
    }


}