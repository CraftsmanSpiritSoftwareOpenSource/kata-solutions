Feature: A checkout which can scan items and apply configurable pricing rules
  For each checkout transaction a checkout is created with a set of pricing rules.
  A checkout will scan individually priced items and provide the total cost.
  Items can be scanned in any order and the total price calculated after any and/or all items are scanned.

  Scenario Outline: For a set of pricing rules scanned item total is correct
    Given a set of pricing rules: <PricingRules>
    And a new checkout transaction
    When the each item from <Items> is scanned
    Then the correct prices: <Prices> is calculated
    Examples:
    | PricingRules            | Items           | Prices                    |
    | rules.basic             | items.ordered   | prices.ordered.basic      |
    | rules.discount.category | items.ordered   | prices.ordered.category   |
    | rules.basic             | items.unordered | prices.unordered.basic    |
    | rules.discount.category | items.unordered | prices.unordered.category |