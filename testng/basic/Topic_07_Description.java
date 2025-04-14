package basic;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Description {

    // Before
    @BeforeClass
    public void beforeClass() {
        System.out.println("Before Class");
    }

    // Test case
    @Test(description = "This feature is Login")
    public void TC_01(){
        System.out.println("TC_01");
    }

    @Test(description = "This feature is Payment")
    public void TC_02(){
        System.out.println("TC_02");
    }

    @Test(description = "This feature is Sign out")
    public void TC_03(){
        System.out.println("TC_03");
    }

    // After
    @AfterClass
    public void afterClass() {
        System.out.println("After Class");
    }



}
