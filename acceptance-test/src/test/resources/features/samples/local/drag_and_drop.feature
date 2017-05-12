@local
Feature: Drag And Drop

  Background:
    Given I have navigated to the local page "/drag_and_drop.html"

    @drag_and_drop
  Scenario: Drag target element to destination element
    When I drag the target element to the destination element
    Then I should see "Dropped" in the destination element