import model.TaxData;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.statements.ExpectException;
import org.mockito.Matchers;
import org.mockito.Mockito;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class DataLoaderTest {

    Class loader = DataLoader.class;
    Method method;
    DataLoader dataLoader = Mockito.mock(DataLoader.class);

    @Before public void setUp() throws Exception {
        method = loader.getDeclaredMethod("convertFileLineToTaxDataObject",String.class);
        method.setAccessible(true);
    }

    @Test public void convertFileLineToTaxDataObjectShouldReturnCorrectData() throws Exception {
        TaxData test = new TaxData("Alabama", 4, "b", "b", "e", "b", "b", "b");
        TaxData data = (TaxData) method.invoke(dataLoader,"Alabama\t4\tb\tb\te\tb\tb\tb");

        assertEquals(test, data);
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertingFilesShouldThrowIllegalArgumentExceptionWithWrongArgument() throws Exception, Throwable{
        try{
            TaxData data = (TaxData) method.invoke(dataLoader,"Alabama\t4\tb\tb\te\tb\tb\tw");
        }
        catch(InvocationTargetException e){
            throw e.getTargetException();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertingFilesShouldThrowIllegalArgumentExceptionWithTooManyArguments() throws Exception, Throwable{
        try{
            TaxData data = (TaxData) method.invoke(dataLoader,"Alabama\t4\tb\tb\te\tb\tb\tb\tb");
        }
        catch(InvocationTargetException e){
            throw e.getTargetException();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void convertingFilesShouldThrowIllegalArgumentExceptionWithNotEnoughArguments() throws Exception, Throwable{
        try{
            TaxData data = (TaxData) method.invoke(dataLoader,"Alabama\t4\tb\tb\te\tb\tb");
        }
        catch(InvocationTargetException e){
            throw e.getTargetException();
        }
    }
}
