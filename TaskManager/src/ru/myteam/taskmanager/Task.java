package ru.myteam.taskmanager;

public class Task {
    private int taskID;
    private String taskName;
    private String description;
    private String status;
    protected int taskType;

    public int getTaskType() {
        return taskType;
    }

    public int getTaskID() {
        return taskID;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public Task(int aTaskID, String aTaskName, String aDescription, String aStatus){
        taskID = aTaskID;
        taskName = aTaskName;
        description = aDescription;
        status = aStatus;
        taskType = 0;
    }

    protected String getToString(){
        return    "    taskID       = " + taskID + ",\n"
                + "    taskName     = " + taskName + ",\n"
                + "    description  = " + description + ",\n"
                + "    status       = " + status + ",\n"
                + "    taskType     = " + taskType;
    }

    @Override
    public String toString(){
        return getClass() + " {\n"
                + getToString()+"\n"
                + "}";
    }

    public String getTypeTaskName(){
         switch (this.taskType){
            case 0:
                return "Задача";
            case 1:
                return "Подзадача";
            case 2:
                return "Эпик";
            default:
                return "";

        }
    }

}
