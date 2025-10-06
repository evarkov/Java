package mytesthome;

import java.util.Scanner;

public class Main {
    private static stepTracker Track;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int userInput;

        Track = new stepTracker();

        do {
           printMenu();
           userInput = in.nextInt();
           switch (userInput){
               case 1:
                   //System.out.println("Выбрали 1");
                   menu1();
                   break;
               case 2:
                   menu2();
                   break;
               case 3:
                   menu3();
                   break;
               case 4:
                   //System.out.println("Выбрали 4");
                   break;
               default:
                   System.out.println("Команда не распознана");
           }
        }
        while (userInput!=4);

    }
    private static void printMenu(){

        System.out.println("Выберите пункт меню");
        System.out.println("1. Ввести количество шагов за день");
        System.out.println("2. Статистика за месяц");
        System.out.println("3. Изменить цель по кол-ву шагов за день.");
        System.out.println("4. Выход");
    }

    private static void menu3(){
        System.out.println("Сейчас установлена цель шагов за день:"+Track.getStepGoal());
        System.out.print("Введите новую цель :");
        Scanner in = new Scanner(System.in);
        int newGoal = in.nextInt();
        if (newGoal>0) {
            Track.setStepGoal(newGoal);
            Track.saveStepGoal();
            System.out.println("Новая цель установлена:"+Track.getStepGoal());
        }
        else {
            System.out.println("Цель не может быть отрицательной и 0");
        }
    }
    private static void menu1(){
        System.out.print("Введите номер месяца от 1 до 12:");
        Scanner in = new Scanner(System.in);
        int aMonth = in.nextInt();
        if (aMonth<1||aMonth>12) {
            System.out.println("Месяц должен быть от 1 до 12");
            return;
        }
        Integer dayCount = Track.getMonthDayCount(aMonth-1);
        System.out.print("Введите номер дня от 1 до "+dayCount+":");
        int aDay = in.nextInt();
        if (aDay<1||aDay>dayCount) {
            System.out.println("День должен быть от 1 до "+dayCount);
            return;
        }
        System.out.print("Введите количество шагов:");
        int aStep = in.nextInt();
        if (aStep<0) {
            System.out.println("Количество шагов должно быть больше 0");
            return;
        }
        Track.setDayStep(aMonth-1,aDay-1,aStep);
        Track.saveMonth(aMonth-1);
    }
    private static void menu2(){
        System.out.print("Введите номер месяца от 1 до 12:");
        Scanner in = new Scanner(System.in);
        int aMonth = in.nextInt();
        if (aMonth<1||aMonth>12) {
            System.out.println("Месяц должен быть от 1 до 12");
            return;
        }
            System.out.println("Месяц :" + (aMonth)+ " - шагов: "+Track.getMonthStepCount(aMonth-1));
            System.out.println("Максимальное число шагов :" + Track.getMonthStepMax(aMonth-1));
            System.out.println("Среднее число шагов :" + Track.getMonthStepAvg(aMonth-1));
            System.out.println("Пройдено :" + (double)Track.getMonthStepCount(aMonth-1)*0.75+ " метров");
            System.out.println("Потрачено каллорий :" + (double)Track.getMonthStepCount(aMonth-1)*50/1000+ " ккал");
            for(int day = 0;day< Track.getMonthDayCount(aMonth-1);day++){
                System.out.print(" "+(day+1)+" день :" + Track.getDayStep(aMonth-1,day)+";");


        }
    }
}
