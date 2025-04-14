package basic;

import org.testng.annotations.*;

public class Topic_01_Annotations {

    // Before
    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Before Suite");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("Before Class");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("Before Test");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Before Method");
    }

    // After
    @AfterSuite
    public void afterSuite() {
        System.out.println("After Suite");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("After Class");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("After Test");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("After Method");
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
}
