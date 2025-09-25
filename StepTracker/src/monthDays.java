import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class monthDays {
    private ArrayList<Integer> days;

    public monthDays(int aMonthNumber){
        int dayInMonth;
        aMonthNumber++;
        if (aMonthNumber<1 || aMonthNumber>12){
            System.out.println("Номер месяца должен быть от 1 до 12");
        }
        switch (aMonthNumber){
            case 2: dayInMonth = 28;
            break;
            case 4:
            case 6:
            case 9:
            case 11:dayInMonth = 30;
            break;
            default:
                dayInMonth = 31;
        }
        loadDays(aMonthNumber+".txt",dayInMonth);
    }
    public Integer getDayCount(){
        return this.days.size();
    }
    public Integer getValue(Integer aDay){
        return days.get(aDay);
    }
    public void setDay(int aIndex,int aValue){
        days.set(aIndex,aValue);
    }
    public void saveDays(String fileName){
        try (FileWriter writer = new FileWriter(fileName)) {
            for (Integer day : days) {
                writer.write(day.toString()+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadDays(String fileName,int aDayInMonth){
        Scanner scanner = null;
        try {
            days = new ArrayList<>();
            for (int i = 0; i < aDayInMonth; i++) {
                days.add(0);}
            Path p = Paths.get(fileName);
            scanner = new Scanner(p);
            String line;
            int lineNo = 0;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                days.set(lineNo,Integer.parseInt(line));
                lineNo++;
            }
        } catch (Exception e) {


        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

    }
}
