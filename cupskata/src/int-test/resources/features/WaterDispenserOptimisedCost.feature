Feature: Dispensing water with the cheapest combination of cups
  A water dispenser can dispense up to 14 litres of water.
  Water can only be dispensed in whole litre increments.
  For any request the water must be dispensed using the cheapest combination of cups.
    There are 4 cups of different sizes: 2 litre, 3 litre, 4 litre and 5 litre.
    Only one of each can be dispensed for a single request.
    The cost of each type of cup is as follows: 2 ltr = 1, 3 ltr = 2, 4 ltr = 4 and 5 ltr = 8

  Background:
    Given A water dispenser

  Scenario Outline: returns an error if input out of range
    When Receiving a request for <AskedForWater> Litres of water
    Then An error <Error> is returned
    Examples:
      | AskedForWater | Error |
      | -100          | The requested input is not within the valid range (1->14): -100 |
      | -1            | The requested input is not within the valid range (1->14): -1   |
      | 0             | The requested input is not within the valid range (1->14): 0    |
      | 15            | The requested input is not within the valid range (1->14): 15   |
      | 100           | The requested input is not within the valid range (1->14): 100  |

  Scenario Outline: Calculate cost for a given request
    When Receiving a request for <AskedForWater> Litres of water
    Then The Cost is calculated as <ExpectedCost>
    Examples:
      | AskedForWater | ExpectedCost |
      | 1             | 1            |
      | 2             | 1            |
      | 3             | 2            |
      | 4             | 3            |
      | 5             | 3            |
      | 6             | 5            |
      | 7             | 6            |
      | 8             | 7            |
      | 9             | 7            |
      | 10            | 11           |
      | 11            | 13           |
      | 12            | 14           |
      | 13            | 15           |
      | 14            | 15           |