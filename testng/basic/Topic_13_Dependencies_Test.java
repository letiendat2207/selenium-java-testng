package basic;

import org.bouncycastle.pqc.jcajce.provider.Falcon;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Dependencies_Test {

    // Test case
    @Test
    public void TC_01_Register(){
        System.out.println("Register new Account");
    }

    @Test(dependsOnMethods = "TC_01_Register")
    public void TC_02_Login(){
        System.out.println("Login to System");
        Assert.assertTrue(false);
    }

    @Test(dependsOnMethods = {"TC_01_Register", "TC_02_Login"})
    public void TC_03_Order(){
        System.out.println("Order Product");
    }

    @Test
    public void TC_04_Pay(){
        System.out.println("Pay Product");
    }

    @Test
    public void TC_05_Ship(){
        System.out.println("Ship Product");
    }

}
