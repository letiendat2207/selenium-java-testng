package platformWeb;

import org.testng.annotations.*;

public class Web_01_User {

    @BeforeClass
    public void beforeClass() {
        System.out.println("Before Class");
    }

    // Test case
    @Test(groups = {"platformWeb"})
    public void User_01_CreateNewUser(){

    }

    @Test(groups = {"platformWeb"})
    public void User_02_EditUser(){

    }

    @Test(groups = {"platformWeb"})
    public void User_03_ViewUser(){

    }

    @AfterClass
    public void afterClass() {
        System.out.println("After Class");
    }
}
