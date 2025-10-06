package ru.myteam.taskmanager;

import java.util.ArrayList;

public class Epic extends Task{
     private final ArrayList<Integer> subTask;

     public Epic(int aTaskID, String aTaskName, String aDescription, String aStatus) {
        super(aTaskID, aTaskName, aDescription, aStatus);
        this.taskType = 2;
        subTask = new ArrayList<>();
    }

    public void addSubTask(Integer aTaskID){
        if (aTaskID == null) {
            System.out.println("Передали пустой номер задачи");
            return;
        }
        if (!subTask.contains(aTaskID)){
            subTask.add(aTaskID);
        } else {
            System.out.println("Задача "+aTaskID+" уже добавлена");
        }

    }

    public void delSubtask(Integer aTaskID){
        if (aTaskID == null) {
            System.out.println("Передали пустой номер задачи");
            return;
        }
        if (subTask.contains(aTaskID)){
            subTask.remove(aTaskID);
        } else {
            System.out.println("Задача "+aTaskID+" не найдена");
        }
    }

    @Override
    protected String getToString() {
        return super.getToString() + "\n"
                + "    subTask = " + subTask.toString();
    }

    public int getSubTaskCount(){
         return subTask.size();
    }
}
