package test;

import buisness.CartData;
import buisness.FilterItem;
import model.FilterRozetka;
import model.FiltersRozetka;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.WebDriverSingleton;
import utils.XMLToObject;

import java.util.List;

public class Parallel {

    @DataProvider(name = "data")//, parallel = true)
    public static Object[] getData() {
        XMLToObject xmlToObject = new XMLToObject();
        FiltersRozetka filtersRozetka = xmlToObject.convert();
        List<FilterRozetka> list = filtersRozetka.getFilters();
        return list.toArray();
    }


    @Test(dataProvider = "data")
    public void parallel(FilterRozetka filterRozetka) throws Exception {
        FilterItem filterItem = new FilterItem();
        filterItem.applyFilters(filterRozetka);

        Assert.assertTrue(new CartData().getSumOrder(filterRozetka.getId()) > filterRozetka.getSumLimit());
    }


    @AfterMethod(alwaysRun = true)
    public void close() {
        WebDriverSingleton.close();
    }
}

