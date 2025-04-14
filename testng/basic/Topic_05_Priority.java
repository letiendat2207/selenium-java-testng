package basic;

import org.testng.annotations.*;

public class Topic_05_Priority {

    // Before
    @BeforeClass
    public void beforeClass() {
        System.out.println("Before Class");
    }

    // Test case
    @Test(priority = 1)
    public void TC_01(){
        System.out.println("TC_01");
    }

    @Test(priority = 2)
    public void TC_02(){
        System.out.println("TC_02");
    }

    @Test(priority = 3)
    public void TC_03(){
        System.out.println("TC_03");
    }

    // After
    @AfterClass
    public void afterClass() {
        System.out.println("After Class");
    }



}
