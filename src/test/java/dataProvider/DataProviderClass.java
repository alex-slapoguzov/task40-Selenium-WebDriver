package dataProvider;

import org.testng.annotations.DataProvider;

public class DataProviderClass {

    @DataProvider(name = "dataForLogin")
    public Object[][] loginDataProvider() {
        return new Object[][]{
                {"EugenBorisik", "qwerty"},
                {"EugenBorisik", "123456789"},
                {"EugenBorisik", "2 02 03 27"},
                {"EugenBorisik", "qwerty555"}
        };
    }
}
