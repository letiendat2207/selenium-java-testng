package platformMobile;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Mobile_02_Payment {

    @BeforeClass
    public void beforeClass() {
        System.out.println("Before Class");
    }

    // Test case
    @Test(groups = {"platformMobile"})
    public void Payment_01_Cheques(){

    }

    @Test(groups = {"platformMobile"})
    public void Payment_02_Card(){

    }

    @Test(groups = {"platformMobile"})
    public void Payment_03_Cash(){

    }

    @AfterClass
    public void afterClass() {
        System.out.println("After Class");
    }
}
