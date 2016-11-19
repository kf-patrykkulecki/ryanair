package steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.CardDetails;
import helpers.FlightData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.ExtrasPage;
import pages.FlightSelectPage;
import pages.MainPage;
import pages.PaymentPage;

import java.util.List;

public class TestSteps {

    private MainPage mainPage;
    private FlightSelectPage flightSelectPage;
    private ExtrasPage extrasPage;
    private PaymentPage paymentPage;
    private WebDriver driver;

    public TestSteps() {
    }

    @Before
    public void StartTest(){

        mainPage = new MainPage(driver);
        flightSelectPage = new FlightSelectPage(driver);
        extrasPage = new ExtrasPage(driver);
        paymentPage = new PaymentPage(driver);
        mainPage.Start();
    }

    @After
    public void FinishTest(){
        mainPage.Stop();
    }

    @Given("^User logs in to ryanair.com$")
    public void LogIn(){
        mainPage.SelectLogInSection()
                .SetEmail("test1234ryan@yopmail.com")
                .SetPassword("Test1234")
                .LogIn();
    }

    @Given("^User makes a flight booking$")
    public void BookFlight(List<FlightData> list){
        FlightData data = list.get(0);

        mainPage.SelectOneWayTicket()
                .SetDepartureAirport(data.from)
                .SelectAirport(data.from)
                .SetDestinationAirport(data.to)
                .SelectAirport(data.to)
                .WaitForCalendar();

        while(mainPage.IsFlightDateNotPresent(data.date)){
            mainPage.MoveToNextMonth();
        }
        mainPage.SelectDate(data.date)
                .OpenPassengers();

        if(data.adults != mainPage.GetAdults()){
            mainPage.AddAdult();
        }

        if(data.teens != mainPage.GetTeens())
        {
            mainPage.AddTeen();
        }
    }

    @When("^User selects fare level$")
    public void SelectFareLevel(List<FlightData> list) {
        FlightData data = list.get(0);

        flightSelectPage = mainPage.OpenPassengers().Search();

        if(flightSelectPage.DialogBoxPresent()){
            flightSelectPage.CloseDialogBox().WaitForDialogBoxToHide();
        }

        if(data.fareLevel.toLowerCase().equals("regular")){
            flightSelectPage.SelectRegularFare();
        }
    }

    @When("^User does not select extra things$")
    public void SelectNothingExtra(){
        extrasPage = flightSelectPage.Continue().WaitForFlightCardToDisplay();

        paymentPage = extrasPage.CheckOutFromCart();

        if(extrasPage.DialogBoxPresent()){
            extrasPage.CloseDialogBox().WaitForDialogBoxToHide();
        }
    }

    @When("^User pays for booking using card$")
    public void PayForBooking(List<CardDetails> list){
        CardDetails data = list.get(0);

        paymentPage.WaitForPaymentForToBeDisplayed();

        paymentPage.SelectPassengerTitle(1, "Mr")
                .SetPassengerFirstName(1, "John")
                .SetPassengerLastName(1, "Doe")
                .SelectPassengerTitle(2, "Mr")
                .SetPassengerFirstName(2, "Adam")
                .SetPassengerLastName(2, "Doe")
                .SelectPassengerTitle(3, "Mr")
                .SetPassengerFirstName(3, "Frank")
                .SetPassengerLastName(3, "Doe")
                .SelectMobilePhoneCountry("Poland")
                .SetMobilePhone("123456789")
                .SelectCardType(data.type)
                .SetCardNumber(data.cardNumber)
                .SelectCardExpiryMonth(data.month)
                .SelectCardExpiryYear(data.year)
                .SetCardSecurityCode(data.code)
                .SetCardHolderName(data.cardHolder)
                .SetAddressLine1("Line1")
                .SetCity("City")
                .SelectTerms()
                .PayNow();
    }

    @Then("^Error message should be displayed$")
    public void CheckErrorMessage(){
        Assert.assertEquals(paymentPage.ErrorMessage(),
                "As your payment was not authorised we could not complete your reservation. Please ensure that the information was correct or use a new payment to try again");
    }
}
