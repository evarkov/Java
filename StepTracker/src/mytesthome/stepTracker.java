package mytesthome;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;

public class stepTracker {
    private final HashMap<Integer, monthDays> monthSteps;
    private static Integer stepGoal;
    public stepTracker(){
        loadStepGoal();
        monthSteps = new HashMap<>();
        for (int i = 0; i < 12; i++) {
            monthSteps.put(i,new monthDays(i));
        }
    }

    public void setStepGoal(Integer aNewStepGoal){
        stepGoal = aNewStepGoal;
    }
    public Integer getStepGoal(){
        return stepGoal;
    }
    private void loadStepGoal(){
        Scanner scanner = null;
        try {
            Path p = Paths.get("stepgoal.txt");

            scanner = new Scanner(p);
            String line = scanner.nextLine();

            setStepGoal(Integer.parseInt(line));
        } catch (Exception e) {
            setStepGoal(10000);
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
    public void saveStepGoal(){
        String content = stepGoal.toString();

        try (FileWriter writer = new FileWriter("stepgoal.txt")) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public Integer getMonthDayCount(Integer aMonth){
        return monthSteps.get(aMonth).getDayCount();
    }
    public Integer getDayStep(Integer aMonth,Integer aDay){
        return monthSteps.get(aMonth).getValue(aDay);
    }
    public void setDayStep(Integer aMonth,Integer aDay,Integer aValue){
        monthSteps.get(aMonth).setDay(aDay,aValue);
    }
    public void saveMonth(Integer aMonth){
        monthSteps.get(aMonth).saveDays(aMonth + 1 + ".txt");
    }
    public Integer getMonthStepCount(Integer aMonth) {return  monthSteps.get(aMonth).getMonthStepCount();}
    public Integer getMonthStepMax(Integer aMonth) {return  monthSteps.get(aMonth).getMonthStepMax();}
    public Integer getMonthStepAvg(Integer aMonth) {return  monthSteps.get(aMonth).getMonthStepAvg();}
}
