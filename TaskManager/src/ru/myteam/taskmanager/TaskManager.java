package ru.myteam.taskmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TaskManager {

    private static managerClass manager;

    public static void main(String[] args) {
        manager = new managerClass();
        printMenu();
    }
    private static void printMainMenu(){
        System.out.println("1. Создать обычную задачу");
        System.out.println("2. Создать эпик");
        System.out.println("3. Создать подзадачу в эпик");
        System.out.println("4. Печать всех задач");
        System.out.println("5. Удалить все задачи");
        System.out.println("6. Обновить обычную задачу");
        System.out.println("7. Удалить задачу по номеру");
    }
    private static void printMenu(){

        Scanner scanner = new Scanner(System.in);
        int cmd;
        do{
            printMainMenu();
          cmd = scanner.nextInt();
          switch (cmd) {
              case 1:
                  menu1();
                  break;
              case 2:
                  menu2();
                  break;
              case 3:
                  menu3();
                  break;
              case 4:
                  menu4();
                  break;
              case 5:
                  menu5();
                  break;
              case 6:
                  menu6();
                  break;
              case 7:
                  menu7();
                  break;
              case 0:
                  System.out.println("Выход");
                  break;
              default:
                  System.out.println("Команда нераспознана.");
          }
        }
        while (cmd != 0);
    }
    private static void menu1(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Название задачи: ");
        String taskName = scanner.nextLine();
        System.out.print("Описание задачи: ");
        String taskDesc = scanner.nextLine();
        Task task = new Task(managerClass.getNextTaskNumber(),taskName,taskDesc,"NEW");
        manager.newTask(managerClass.getCurTaskNumber(),task);
        //manager.newSimpleTask(taskName,taskDesc);
    }
    private static void menu2(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Название эпика: ");
        String taskName = scanner.nextLine();
        System.out.print("Описание эпика: ");
        String taskDesc = scanner.nextLine();
        Epic epic = new Epic(managerClass.getNextTaskNumber(),taskName,taskDesc,"NEW");
        manager.newTask(managerClass.getCurTaskNumber(), epic);
        //manager.newEpic(taskName,taskDesc);
    }
    private static void menu3(){
        int eCount = manager.getEpicCount();
        if (eCount==0) {
            System.out.println("Нет ни одного эпика. Добавить подзадачу невозможно.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Название задачи: ");
        String taskName = scanner.nextLine();
        System.out.print("Описание задачи: ");
        String taskDesc = scanner.nextLine();
        System.out.println("Введите номер эпика: ");
        System.out.println("======================================================================");
        HashMap<Integer,Task> epics = manager.getEpics();
        for (Task task: epics.values()) {
            System.out.println(task.getTaskID()+". "+task.getTaskName());
            }
        System.out.println("======================================================================");
        boolean isSuccess = false;
        int epicID;
        do {
            epicID = scanner.nextInt();
            if (epicID == 0) {
                isSuccess = true;
            } else {
                if (epics.containsKey(epicID)){
                    isSuccess = true;
                } else {
                    System.out.println("Нужно ввести номер существующего эпика.");
                }
            }
        } while (!isSuccess);
        if (epicID != 0) {
          //  manager.newSubTask(epicID, taskName, taskDesc);
            SubTask subtask = new SubTask(managerClass.getNextTaskNumber(), epicID, taskName,taskDesc,"NEW");
            manager.newTask(managerClass.getCurTaskNumber(), subtask);
        }
    }
    private static void menu4(){
        ArrayList<Task> Tasks = manager.getTasks();
        System.out.println("======================================================================");
        for (Task task : Tasks) {
            System.out.println(task.getTaskID() + " " + task.getTaskName()+ " "+ task.getStatus()+" "+ task.getTypeTaskName()+"("+task.getTaskType()+")");
        }
        System.out.println("======================================================================");
    }
    private static void menu5(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Действительно удалить все задачи? (Y/N)");
        String chr = scanner.nextLine();
        if (chr.equals("Y")){
            manager.deleteAllTask();
            System.out.println("Задачи удалены");
        } else {
            System.out.println("Задачи НЕ удалены");
        }
    }
    private static void menu6(){
        Task newTask = new Task(1,"Первая задача","","PROCESSED");
        manager.updateTask(1,newTask);
    }
    private static void menu7(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите номер удаляемой задачи");
        int lTaskID = scanner.nextInt();
        manager.deleteByKey(lTaskID);
    }
}
