package ru.myteam.taskmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InMemoryTaskManager implements ITaskManager {

    private static int taskNumber;
    private final HashMap<Integer,Task> Tasks;
    private final ArrayList<Integer> History;

    public HashMap<Integer,Task> getEpics(){
        HashMap<Integer,Task> map = new HashMap<>();
        for (Map.Entry<Integer, Task> task : Tasks.entrySet()) {
            if (task.getValue().getTaskType() == TaskType.EPIC){
                map.put(task.getKey(),task.getValue());
            }
        }
        return map;
    }

    public HashMap<Integer,Task> getTasks(){
        return Tasks;
    }

    public static int getCurTaskNumber() {
        return taskNumber;
    }

    public static int getNextTaskNumber() {
        return ++taskNumber;
    }

    public InMemoryTaskManager(){
        taskNumber = 0;
        Tasks = new HashMap<>();
        History = new ArrayList<>();
    }

    @Override
    public void newTask(int aTaskID, Task aTask){
        Tasks.put(aTaskID, aTask);
        if (aTask.getTaskType() == TaskType.SUB_TASK){
            int lEpic = ((SubTask) aTask).getParentTaskID();
            ((Epic)Tasks.get(lEpic)).addSubTask(aTaskID);
        }
    }
    public int getEpicCount(){
        int rez = 0;
        for (Task task : Tasks.values()) {
            if (task.getTaskType() == TaskType.EPIC){
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

    @Override
    public void deleteAllTask(){
        Tasks.clear();
    }

    @Override
    public void updateTask(Integer aTaskID, Task task){
        Tasks.put(aTaskID,task);
    }

    @Override
    public void deleteByKey(Integer aTaskID){
        if (Tasks.containsKey(aTaskID)) {
            Task task = Tasks.get(aTaskID);
            switch (task.getTaskType()){
                case TASK:
                    Tasks.remove(aTaskID);
                    break;
                case SUB_TASK:
                    int lEpicID = ((SubTask)Tasks.get(aTaskID)).getParentTaskID();
                    ((Epic) Tasks.get(lEpicID)).delSubtask(aTaskID);
                    Tasks.remove(aTaskID);
                    break;
                case EPIC:
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

    @Override
    public Task getTask(Integer aTaskID) {
        if (Tasks.containsKey(aTaskID)) {
            addHistory(aTaskID);
            return Tasks.get(aTaskID);
        } else {
            return null;
        }
    }

    private void addHistory(Integer aTaskID){
        if (History.size() == 10){
            History.remove(0);
        }
        History.add(aTaskID);
    }

    public ArrayList<Integer> getHistory() {
        return History;
    }
}

