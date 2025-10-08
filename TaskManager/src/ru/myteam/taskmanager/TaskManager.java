package ru.myteam.taskmanager;

import java.util.HashMap;
import java.util.Scanner;

public class TaskManager {

    private static InMemoryTaskManager manager;

    public static void main(String[] args) {
        manager = new InMemoryTaskManager();
        /*/----------------------------------------------
        Task task;
        task = new Task(InMemoryTaskManager.getNextTaskNumber(),"q1","q1",TaskStatus.NEW);
        manager.newTask(InMemoryTaskManager.getCurTaskNumber(),task);
        task = new Task(InMemoryTaskManager.getNextTaskNumber(),"q2","q2",TaskStatus.NEW);
        manager.newTask(InMemoryTaskManager.getCurTaskNumber(),task);

        manager.getTask(1);
        manager.getTask(2);
        manager.getTask(2);
        manager.getTask(2);
        manager.getTask(2);
        manager.getTask(2);
        manager.getTask(2);
        manager.getTask(2);
        manager.getTask(2);
        manager.getTask(2);
        manager.getTask(2);

        *///----------------------------------------------
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
        System.out.println("8. Просмотр задачи");
        System.out.println("9. Просмотр Истории");
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
              case 8:
                  menu8();
                  break;
              case 9:
                  menu9();
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
        Task task = new Task(InMemoryTaskManager.getNextTaskNumber(),taskName,taskDesc,TaskStatus.NEW);
        manager.newTask(InMemoryTaskManager.getCurTaskNumber(),task);
        //manager.newSimpleTask(taskName,taskDesc);
    }
    private static void menu2(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Название эпика: ");
        String taskName = scanner.nextLine();
        System.out.print("Описание эпика: ");
        String taskDesc = scanner.nextLine();
        Epic epic = new Epic(InMemoryTaskManager.getNextTaskNumber(),taskName,taskDesc,TaskStatus.NEW);
        manager.newTask(InMemoryTaskManager.getCurTaskNumber(), epic);
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
            SubTask subtask = new SubTask(InMemoryTaskManager.getNextTaskNumber(), epicID, taskName,taskDesc,TaskStatus.NEW);
            manager.newTask(InMemoryTaskManager.getCurTaskNumber(), subtask);
        }
    }
    private static void menu4(){
        HashMap<Integer,Task> Tasks = manager.getTasks();
        System.out.println("======================================================================");
        for (Task task : Tasks.values()) {
            System.out.println(task.getTaskID() + " " + task.getTaskName()+ " "+ task.getStatus()+" "+ task.getTypeTaskName()+"("+task.getTaskType()+")"+" "+task.getDescription());
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
        Task newTask = new Task(1,"Первая задача","",TaskStatus.PROCESSED);
        manager.updateTask(1,newTask);
    }
    private static void menu7(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите номер удаляемой задачи");
        int lTaskID = scanner.nextInt();
        manager.deleteByKey(lTaskID);
    }

    private static void menu8(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите номер задачи для просмотра: ");
        int lTaskID = scanner.nextInt();
        Task task = manager.getTask(lTaskID);
        if (task != null) {
            System.out.println("======================================================================");
            System.out.println(task.getTaskID() + " " + task.getTaskName()+ " "+ task.getStatus()+" "+ task.getTypeTaskName()+"("+task.getTaskType()+")"+" "+task.getDescription());
            System.out.println("======================================================================");
        } else {
            System.out.println("Нужно ввести номер существующей задачи.");
        }
    }

    private static void menu9(){
        System.out.println("======================================================================");
        Task task;

        for (Integer taskID : manager.getHistory()) {
            task = manager.getTasks().get(taskID);

            System.out.println(task.getTaskID() + " " + task.getTaskName()+ " "+ task.getStatus()+" "+ task.getTypeTaskName()+"("+task.getTaskType()+")"+" "+task.getDescription());
        }
        System.out.println("======================================================================");
    }
}
