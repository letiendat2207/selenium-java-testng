package basic;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_02_Assert {
    @Test
    public void login(){
        String myName = "Lê Tiến Đạt";
        String myNickName = "Lê Tiến Đạt";

        Assert.assertEquals(myName, myNickName);

        boolean status = 10 < 5;
        System.out.println("Status = " + status);
        Assert.assertFalse(status);
    }
}
