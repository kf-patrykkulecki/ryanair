package pages;

import helpers.ElementLocator;
import helpers.ElementType;
import org.openqa.selenium.WebDriver;

public class PaymentPage extends AbstractPage {

    private ElementLocator paymentForm = new ElementLocator(ElementType.CssSelector, "[name = 'bookingPaymentForm'] > div.main-area");
    // Passenger data
    private ElementLocator passengerTitle = new ElementLocator(ElementType.CssSelector, "passenger-input-group:nth-of-type(%s) [name *= title]");
    private ElementLocator passengerFirstName = new ElementLocator(ElementType.CssSelector, "passenger-input-group:nth-of-type(%s) [name *= firstName]");
    private ElementLocator passengerLastName = new ElementLocator(ElementType.CssSelector, "passenger-input-group:nth-of-type(%s) [name *= lastName]");
    private ElementLocator mobilePhoneCountry = new ElementLocator(ElementType.CssSelector, "[name = 'phoneNumberCountry']");
    private ElementLocator mobilePhone = new ElementLocator(ElementType.CssSelector, "input[name = 'phoneNumber']");
    // Card data
    private ElementLocator cardType = new ElementLocator(ElementType.CssSelector, "[name = 'cardType']");
    private ElementLocator cardNumber = new ElementLocator(ElementType.CssSelector, "[name = 'cardNumber']");
    private ElementLocator cardExpiryMonth = new ElementLocator(ElementType.CssSelector, "[name = 'expiryMonth']");
    private ElementLocator cardExpiryYear = new ElementLocator(ElementType.CssSelector, "[name = 'expiryYear']");
    private ElementLocator cardSecurityCode = new ElementLocator(ElementType.CssSelector, "[name = 'securityCode']");
    private ElementLocator cardHolderName = new ElementLocator(ElementType.CssSelector, "[name = 'cardHolderName']");
    // Address data
    private ElementLocator addressLine1 = new ElementLocator(ElementType.CssSelector, "[name *= 'nameAddressLine1']");
    private ElementLocator city = new ElementLocator(ElementType.CssSelector, "[name *= 'nameCity']");
    private ElementLocator terms = new ElementLocator(ElementType.CssSelector, ".terms label");

    private ElementLocator payNowButton = new ElementLocator(ElementType.Id, "pay-now-btn");

    private ElementLocator errorMessage = new ElementLocator(ElementType.CssSelector, ".error .info-text");

    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    public PaymentPage WaitForPaymentForToBeDisplayed(){
        WaitForElementToBeDisplayed(paymentForm, 20);
        return this;
    }

    public PaymentPage SelectCardType(String text){
        SelectValue(cardType, text);
        return this;
    }

    public PaymentPage SetCardNumber(String text){
        SendKeys(cardNumber, text);
        return this;
    }

    public PaymentPage SelectCardExpiryMonth(String text){
        SelectValue(cardExpiryMonth, text);
        return this;
    }

    public PaymentPage SelectCardExpiryYear(String text){
        SelectValue(cardExpiryYear, text);
        return this;
    }

    public PaymentPage SetCardSecurityCode(String text){
        SendKeys(cardSecurityCode, text);
        return this;
    }

    public PaymentPage SetCardHolderName(String text){
        SendKeys(cardHolderName, text);
        return this;
    }

    public PaymentPage PayNow(){
        Click(payNowButton);
        return this;
    }

    public PaymentPage SelectPassengerTitle(int pos, String text){
        SelectValue(passengerTitle.Format(pos), text);
        return this;
    }

    public PaymentPage SetPassengerFirstName(int pos, String text){
        SendKeys(passengerFirstName.Format(pos), text);
        return this;
    }

    public PaymentPage SetPassengerLastName(int pos, String text){
        SendKeys(passengerLastName.Format(pos), text);
        return this;
    }

    public PaymentPage SelectMobilePhoneCountry(String text) {
        SelectValue(mobilePhoneCountry, text);
        return this;
    }

    public PaymentPage SetMobilePhone(String text){
        SendKeys(mobilePhone, text);
        return this;
    }

    public PaymentPage SetAddressLine1(String text){
        SendKeys(addressLine1, text);
        return this;
    }

    public PaymentPage SetCity(String text){
        SendKeys(city, text);
        return this;
    }

    public PaymentPage SelectTerms(){
        Click(terms);
        return this;
    }

    public String ErrorMessage(){
        WaitForElementToBeDisplayed(errorMessage, 30);
        return GetText(errorMessage);
    }
}
