package basic;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Skip {

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

    @Test(enabled = false)
    public void TC_02(){
        System.out.println("TC_02");
    }

    @Test(priority = 3)
    public void TC_03(){
        System.out.println("TC_03");
    }

    //@Test(priority = 4)
    public void TC_04(){
        System.out.println("TC_03");
    }

    // After
    @AfterClass
    public void afterClass() {
        System.out.println("After Class");
    }



}
