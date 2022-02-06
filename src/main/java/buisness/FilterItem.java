package buisness;

import model.FilterRozetka;
import pages.HomePage;
import pages.SearchResultPage;

public class FilterItem {
    HomePage homePage;
    SearchResultPage searchResultPage;
    private static final long DEFAULT_WAITING_TIME = 90;

    public void applyFilters(FilterRozetka filterRozetka) throws InterruptedException {
        homePage = new HomePage();

        homePage.enterTextToSearchField(filterRozetka.getItemName());
        homePage.waitForPageLoadComplete(DEFAULT_WAITING_TIME);

        searchResultPage = new SearchResultPage();
        searchResultPage.waitForPageLoadComplete(DEFAULT_WAITING_TIME);

        searchResultPage.scrollTo(searchResultPage.getPriceFilterEl());
        searchResultPage.waitVisibilityOfElement(DEFAULT_WAITING_TIME,searchResultPage.getBrandLabelHeader());

        searchResultPage.selectBrandCustom(filterRozetka.getBrand());

        searchResultPage.scrollTo(searchResultPage.getsTopOfPage())
                        .clickBuyButtonFirst()
                        .clickOpenCartButton();

    }
}
