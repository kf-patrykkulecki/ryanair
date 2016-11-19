package pages;

import helpers.ElementLocator;
import helpers.ElementType;
import org.openqa.selenium.WebDriver;

public class FlightSelectPage extends AbstractPage {

    private ElementLocator dialogBox = new ElementLocator(ElementType.CssSelector, ".ngdialog-overlay");
    private ElementLocator dialogBoxCloseButton = new ElementLocator(ElementType.CssSelector, ".dialog-body button");

    private ElementLocator regularFare = new ElementLocator(ElementType.CssSelector, ".regular");
    private ElementLocator continueButton = new ElementLocator(ElementType.Id, "continue");

    public FlightSelectPage(WebDriver driver) {
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

    public FlightSelectPage WaitForDialogBoxToHide(){
        WaitForElementToBeNotDisplayed(dialogBox, 10);
        return this;
    }

    public FlightSelectPage CloseDialogBox(){
        Click(dialogBoxCloseButton);
        return this;
    }

    public FlightSelectPage SelectRegularFare(){
        Click(regularFare);
        return this;
    }

    public ExtrasPage Continue(){
        Click(continueButton);
        return new ExtrasPage(driver);
    }
}
