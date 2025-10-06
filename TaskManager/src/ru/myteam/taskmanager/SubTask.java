package ru.myteam.taskmanager;

public class SubTask extends Task{
    private final int parentTaskID;
    public SubTask(int aTaskID, int aParentTaskId, String aTaskName, String aDescription, String aStatus) {
        super(aTaskID, aTaskName, aDescription, aStatus);
        parentTaskID = aParentTaskId;
        taskType = 1;
    }

    public int getParentTaskID() {
        return parentTaskID;
    }

    @Override
    protected String getToString() {
        return super.getToString() + "\n"
                + "    parentTaskID = " + parentTaskID;
    }
}
