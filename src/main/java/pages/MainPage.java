package pages;

import helpers.ElementLocator;
import helpers.ElementType;
import org.openqa.selenium.WebDriver;
import sun.applet.Main;

public class MainPage extends AbstractPage {

    // Log in
    private ElementLocator logInSection = new ElementLocator(ElementType.CssSelector, "register-signup-tabs li.core-tab:nth-of-type(2)");
    private ElementLocator emailInput = new ElementLocator(ElementType.CssSelector, "register-signup-tabs [name = 'email']");
    private ElementLocator passwordInput = new ElementLocator(ElementType.CssSelector, "register-signup-tabs [name = 'password']");
    private ElementLocator logInButton = new ElementLocator(ElementType.CssSelector, "register-signup-tabs #submitSignupForm");
    // Flight
    private ElementLocator oneWayTicket = new ElementLocator(ElementType.Id, "lbl-flight-search-type-one-way");
    private ElementLocator departureAirport = new ElementLocator(ElementType.CssSelector, "div[name = 'departureAirportName'] input:not(.hidden)");
    private ElementLocator destinationAirport = new ElementLocator(ElementType.CssSelector, "div[name = 'destinationAirportName'] input:not(.hidden)");
    private ElementLocator airportToSelect = new ElementLocator(ElementType.XPath, "//strong[text() = '%s']");
    // Calendar
    private ElementLocator calendar = new ElementLocator(ElementType.CssSelector, ".start-date");
    private ElementLocator nextMonth = new ElementLocator(ElementType.CssSelector, "[ng-click='slideToNextMonth()']");
    private ElementLocator flightDate = new ElementLocator(ElementType.CssSelector, "li[data-id = '%s']");
    // Passenger
    private ElementLocator passengers = new ElementLocator(ElementType.CssSelector, "#label-pax-input + div.value");
    private ElementLocator adults = new ElementLocator(ElementType.CssSelector, "[value *= 'adults'] input");
    private ElementLocator teen = new ElementLocator(ElementType.CssSelector, "[value *= 'teens'] input");
    private ElementLocator addAdult = new ElementLocator(ElementType.CssSelector, "div[value *= 'adults'] .inc");
    private ElementLocator addTeen = new ElementLocator(ElementType.CssSelector, "div[value *= 'teens'] .inc");
    // Search
    private ElementLocator searchButton = new ElementLocator(ElementType.CssSelector, "[ng-click *= 'searchFlights']");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage SelectOneWayTicket(){
        Click(oneWayTicket);
        return this;
    }

    public MainPage SetDepartureAirport(String text){
        SendKeys(departureAirport, text);
        return this;
    }

    public MainPage SetDestinationAirport(String text){
        SendKeys(destinationAirport, text);
        return this;
    }

    public MainPage SelectAirport(Object text){
        Click(airportToSelect.Format(text));
        return this;
    }

    public MainPage MoveToNextMonth(){
        Click(nextMonth);
        return this;
    }

    public MainPage SelectDate(String text){
        Click(flightDate.Format(text));
        return this;
    }

    public boolean IsFlightDateNotPresent(String text){
        return IsElementNotPresent(flightDate.Format(text));
    }

    public MainPage WaitForCalendar(){
        WaitForElementToBeDisplayed(calendar, 10);
        return this;
    }

    public MainPage OpenPassengers(){
        Click(passengers);
        return this;
    }

    public MainPage AddTeen(){
        Click(addTeen);
        return this;
    }

    public MainPage AddAdult(){
        Click(addAdult);
        return this;
    }

    public String GetAdults(){
        return GetValue(adults);
    }

    public String GetTeens(){
        return GetValue(teen);
    }

    public FlightSelectPage Search(){
        Click(searchButton);
        return new FlightSelectPage(driver);
    }

    public MainPage SelectLogInSection(){
        ScrollToElement(logInSection);
        Click(logInSection);
        return this;
    }

    public MainPage SetEmail(String text){
        SendKeys(emailInput, text);
        return this;
    }

    public MainPage SetPassword(String text){
        SendKeys(passwordInput, text);
        return this;
    }

    public MainPage LogIn(){
        Click(logInButton);
        return this;
    }
}
