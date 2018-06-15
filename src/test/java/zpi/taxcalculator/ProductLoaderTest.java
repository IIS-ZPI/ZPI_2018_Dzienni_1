package zpi.taxcalculator;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import zpi.taxcalculator.model.Product;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class ProductLoaderTest {

    Class loader = ProductLoader.class;
    Method method;
    ProductLoader productLoader = Mockito.mock(ProductLoader.class);


    @Before
    public void setUp() throws Exception {
    method = loader.getDeclaredMethod("convertFileLineToProductObject", String.class);
    method.setAccessible(true);
    }

    @Test
    public void convertingShouldReturnCorrectProduct() throws InvocationTargetException, IllegalAccessException {
        Product product = new Product("marchewka", Product.ProductType.GROCERIES, 3f);
        Product testProduct = (Product) method.invoke(productLoader, "marchewka,groceries,3");

        assertEquals(product, testProduct);
    }
    //A lot of incorrect arguments here
    @Test(expected = IllegalArgumentException.class)
    public void convertingShouldThrowExceptionBecauseOfNegativePrice() throws Throwable {
        try {
            Product testProduct = (Product) method.invoke(productLoader, "marchewka,groceries,-5");
        }
        catch(InvocationTargetException e){
            throw e.getTargetException();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertingShouldThrowExceptionBecauseOfTooManyArguments() throws Throwable {
        try {
            Product testProduct = (Product) method.invoke(productLoader, "marchewka,groceries,5,7");
        }
        catch(InvocationTargetException e){
            throw e.getTargetException();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertingShouldThrowExceptionBecauseOfNotEnoughArguments() throws Throwable {
        try {
            Product testProduct = (Product) method.invoke(productLoader, "marchewka,groceries");
        }
        catch(InvocationTargetException e){
            throw e.getTargetException();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertingShouldThrowExceptionBecauseOfWrongProductType() throws Throwable {
        try {
            Product testProduct = (Product) method.invoke(productLoader, "marihuanen,illegalDrug,5");
        }
        catch(InvocationTargetException e){
            throw e.getTargetException();
        }
    }

    @Ignore @Test
    public void getData() {
    }
}