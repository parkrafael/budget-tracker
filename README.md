# CPSC 210 Personal Project

### Budget Tracker

For my CPSC 210 project, I will be creating a budget calculator that will allow the user to track their spending
and budget allowing for more conscientious spending habits.

### User Stories

Phase 1:
- As a user, be able to add expenses to a list of expenses
- As a user, be able to date when the expense was made
- As a user, be able to create names for expenses (ie. groceries, restaurant, entertainment)
- As a user, be able to view the total expenses within a specific day, month, year, or name.

Phase 2:
- As a user, be able to save expenses to file
- As a user, be able to load the list of expenses from file
- As a user, be able to create multiple accounts with individual list of expenses

Phase 3:
#### Instructions for Grader:

- you can add expense by going to the "add expense" tab and entering your details then pressing enter
- you can locate my visual component in the login menu
- you can save the state of my application by pressing the save button in the main menu
- you can load the state of my application by either...
    - By entering an existing user in the login menu
    - You can create a new json file by entering a new name that does not already exist in the data package
    - Pressing the load button in the main menu

Phase 4: Task 2

Thu Dec 01 20:39:15 PST 2022
Created a new List of Expenses, name: Ricky

Thu Dec 01 20:39:29 PST 2022
Expense created

Thu Dec 01 20:39:29 PST 2022
Added Expense to Ricky's List of Expenses

Thu Dec 01 20:39:34 PST 2022
Viewed Ricky's purchases by day

Thu Dec 01 20:39:38 PST 2022
Viewed Ricky's purchases by month

Thu Dec 01 20:39:42 PST 2022
Viewed Ricky's purchases by year

Thu Dec 01 20:39:47 PST 2022
Viewed Ricky's purchases by name

Thu Dec 01 20:39:49 PST 2022
Saved Ricky's List of Expenses.

Thu Dec 01 20:39:50 PST 2022
Loaded Ricky's List of Expenses.

Phase 4: Task 3

- Make the application more robust by handling more exceptions instead of just relying on REQUIRES statements
  (ie. getExpenseAtDay, getExpenseAtMonth, getExpenseAtYear)
- Improve the cohesion of the application by creating a new class named Logger to store all our logEvent functions
- Improve the coupling in the BudgetCalculator class by abstracting out similar print statements in the console user 
  interface and making them methods to create uniformly formatted output

