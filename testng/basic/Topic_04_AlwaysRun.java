package basic;

import org.testng.annotations.*;

public class Topic_04_AlwaysRun {


    @BeforeClass
    public void beforeClass() {
        System.out.println("Before Class");
    }

    // Test case
    @Test
    public void TC_01(){
        System.out.println("TC_01");
    }

    @Test
    public void TC_02(){
        System.out.println("TC_02");
    }

    @Test
    public void TC_03(){
        System.out.println("TC_03");
    }

    // khi dùng alwaysRun = true thì afterClass lúc nào cũng được chạy để quit browser, tránh dữ liệu rác được sinh ra
    @AfterClass(alwaysRun = true)
    public void afterClass() {
        System.out.println("After Class");
    }



}
