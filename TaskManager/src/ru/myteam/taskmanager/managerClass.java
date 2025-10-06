package ru.myteam.taskmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class managerClass {
    private static int taskNumber;
    private final HashMap<Integer,Task> Tasks;

    public HashMap<Integer,Task> getEpics(){
        HashMap<Integer,Task> map = new HashMap<>();
        for (Map.Entry<Integer, Task> task : Tasks.entrySet()) {
            if (task.getValue().getTaskType() == 2){
              map.put(task.getKey(),task.getValue());
            }
        }
        return map;
    }

    public ArrayList<Task> getTasks(){
        return new ArrayList<>(Tasks.values());
    }

    public static int getCurTaskNumber() {
        return taskNumber;
    }

    public static int getNextTaskNumber() {
        return ++taskNumber;
    }

    public managerClass(){
        taskNumber = 0;
        Tasks = new HashMap<>();
    }
    public void newTask(int aTaskID, Task aTask){
        Tasks.put(aTaskID, aTask);
        if (aTask.getTaskType() == 1){
            int lEpic = ((SubTask) aTask).getParentTaskID();
            ((Epic)Tasks.get(lEpic)).addSubTask(aTaskID);
        }
    }
public int getEpicCount(){
        int rez = 0;
        for (Task task : Tasks.values()) {
            if (task.getTaskType() == 2){
                rez++;
            }
        }
        return rez;
    }

    protected String getToString(){
        return    "    taskNumber = " + taskNumber + ",\n"
                + "    Tasks      = {\n" + Tasks + "\n}";

    }

    public String toString(){
        return getClass() + " {\n"
                + getToString() + "\n"
                + "}";
    }

    public void deleteAllTask(){
        Tasks.clear();
    }

    public void updateTask(Integer aTaskID, Task task){
        Tasks.put(aTaskID,task);
    }

    public void deleteByKey(Integer aTaskID){
        if (Tasks.containsKey(aTaskID)) {
            Task task = Tasks.get(aTaskID);
            switch (task.getTaskType()){
                case 0:
                    Tasks.remove(aTaskID);
                    break;
                case 1:
                    int lEpicID = ((SubTask)Tasks.get(aTaskID)).getParentTaskID();
                    ((Epic) Tasks.get(lEpicID)).delSubtask(aTaskID);
                    Tasks.remove(aTaskID);
                    break;
                case 2:
                    if (((Epic) task).getSubTaskCount()>0) {
                       System.out.println("Сначала необходимо удалить подзадачи");
                    } else {
                       Tasks.remove(aTaskID);
                    }
                    break;
            }
        } else {
            System.out.println("Указанной задачи нет в списке.");
        }
    }
}
