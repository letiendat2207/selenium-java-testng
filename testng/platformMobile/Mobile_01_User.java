package platformMobile;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Mobile_01_User {

    @BeforeClass
    public void beforeClass() {
        System.out.println("Before Class");
    }

    // Test case
    @Test(groups = {"platformMobile"})
    public void User_01_CreateNewUser(){

    }

    @Test(groups = {"platformMobile"})
    public void User_02_EditUser(){

    }

    @Test(groups = {"platformMobile"})
    public void User_03_ViewUser(){

    }

    @AfterClass
    public void afterClass() {
        System.out.println("After Class");
    }
}
