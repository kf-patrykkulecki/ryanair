package pages;

import helpers.ElementLocator;
import helpers.ElementType;
import org.openqa.selenium.WebDriver;

public class ExtrasPage extends AbstractPage {

    private ElementLocator dialogBox = new ElementLocator(ElementType.CssSelector, ".ngdialog-overlay");
    private ElementLocator dialogBoxCloseButton = new ElementLocator(ElementType.CssSelector, "[ng-click *= 'closeThisDialog']");

    private ElementLocator checkOutFromCart = new ElementLocator(ElementType.CssSelector, "button[ng-class *= 'btn_checkout']");
    private ElementLocator flightCard = new ElementLocator(ElementType.CssSelector, "flight-card");

    public ExtrasPage(WebDriver driver) {
        super(driver);
    }

    public boolean DialogBoxPresent(){
        try {
            WaitForElementToBeDisplayed(dialogBox, 20);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public ExtrasPage WaitForDialogBoxToHide(){
        WaitForElementToBeNotDisplayed(dialogBox, 10);
        return this;
    }

    public ExtrasPage WaitForFlightCardToDisplay(){
        WaitForElementToBeDisplayed(flightCard, 20);
        return this;
    }

    public PaymentPage CheckOutFromCart(){
        Click(checkOutFromCart);
        return new PaymentPage(driver);
    }

    public ExtrasPage CloseDialogBox(){
        Click(dialogBoxCloseButton);
        return this;
    }
}
