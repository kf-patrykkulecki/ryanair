Feature: Check validation message for invalid card number
  when booking flight tickets on Ryanair.com

  Scenario: Check validation message
    Given User logs in to ryanair.com
      And User makes a flight booking
        |From   |To   |Date       |Adults |Teens|
        |Wroclaw|Paris|05-02-2017 |2      |1    |
    When User selects fare level
      |FareLevel|
      |Regular  |
      And User does not select extra things
      And User pays for booking using card
        |Type       |CardNumber       |Month|Year|Code|CardHolder|
        |MasterCard |5555555555555557 |10   |2018|265 |Dad       |
    Then Error message should be displayed
