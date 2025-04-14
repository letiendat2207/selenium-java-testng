package platformWeb;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Web_02_Product {

    @BeforeClass
    public void beforeClass() {
        System.out.println("Before Class");
    }

    // Test case
    @Test(groups = {"platformWeb"})
    public void Product_01_CreateNewProduct(){

    }

    @Test(groups = {"platformWeb"})
    public void Product_02_EditProduct(){

    }

    @Test(groups = {"platformWeb"})
    public void Product_03_ViewProduct(){

    }

    @AfterClass
    public void afterClass() {
        System.out.println("After Class");
    }
}
