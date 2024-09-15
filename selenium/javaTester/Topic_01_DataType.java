package javaTester;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class Topic_01_DataType {

    int var;

    char c = 'A';
    byte bNumber = 10;
    short sNumber = 100;
    int iNumber = 70000;
    long lNumber = 1000000;

    float fNumber = 10.3f;
    double dNumber = 15.5d;
    boolean sex = true;

    String[] employeeName = {"Nguyễn Văn Tâm", "Lê Thị Hạnh", "Hoàng Đức Vinh"};
    int[] employeeAge = {30, 20, 25};

    Object employeeAddress = "123 Tan Thang";
    Object employeeId = 3403;
    String name = "Đạt";

    //Class - Interface - Collection
    //Class
    FirefoxDriver ffDriver = new FirefoxDriver();
    //Interface
    WebDriver driver = new CrhomeDriver();
    //Collection
    List<WebElement> textboxes = driver.findElements(By.cssSelector(""));
    ArrayList<String> studentAddress = new ArrayList<String>();

    public static void main(String[] args) {
        int var1 = 100;
        int var2 = var1;

        System.out.println("var1 = " + var1);   //var1 = 100
        System.out.println("var2 = " + var2);   //var2 = 100

        System.out.println("Change the value of var1 from " + var1 + " to 200");
        var1 = 200;
        System.out.println("var1 = " + var1); //var1 = 200
        System.out.println("var2 = " + var2); //var2 = 100
        System.out.println("\n");

        //class
        //Instance 1
        Topic_01_DataType i1 = new Topic_01_DataType();
        i1.var = 10;
        System.out.println("i1 = " + i1.var);

        //Instance 2
        Topic_01_DataType i2 = i1;
        System.out.println("i2 = " + i2.var);

        //Change value of i1
        System.out.println("Change value of i1 from " + i1.var + " to 50");
        i1.var = 50;
        System.out.println("i1 = " + i1.var);
        System.out.println("i2 = " + i2.var);
    }
}
