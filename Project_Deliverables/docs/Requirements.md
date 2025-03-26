## Version 1 Requirements

### Milestone: ShoeVault Launch 

#### Epic 1: User Authentication
- **ID:** EPIC-1
- **Priority:** Must Have
- **Estimated Effort:** 5 days
- **Type:** Functional

  ### Story 1: User Account Registration
  - **ID:** REQ-1
  - **Priority:** Must Have
  - **Estimated Effort:** 1.5 day
  - **Type:** Functional
  - **Description:** The system must allow new users to self-register, creating their own account with a unique username and a minimum 6-character password.

  ### Story 2: User Login
  - **ID:** REQ-2
  - **Priority:** Must Have
  - **Estimated Effort:** 1.5 day
  - **Type:** Functional
  - **Description:** Login up username and password

  ### Story 3: Admin User Creation
  - **ID:** REQ-3
  - **Priority:** Must Have
  - **Estimated Effort:** 1 day
  - **Type:** Functional
  - **Description:** Admins must be able to convert a regular user account into an admin account.

  ### Story 4: Export Sales Report to CSV
  - **ID:** REQ-4
  - **Priority:** Should Have
  - **Estimated Effort:** 1 day
  - **Type:** Functional
  - **Description:** An administrator needs to be able to export the sales report to CSV. This will let the admin analyze the data better in something like Excel.

#### Epic 2: Inventory Display and Management
- **ID:** EPIC-2
- **Priority:** Must Have
- **Estimated Effort:** 3-4 days
- **Type:** Functional
- **Description:** User must see a list of all available inventory sorted by highest price to lowest price.

  ### Story 1: Display Available Inventory
  - **ID:** REQ-5
  - **Priority:** Must Have
  - **Estimated Effort:** 1 day
  - **Type:** Functional
  - **Description:** Users must see a list of all available inventory, sorted from highest to lowest price.

  ### Story 2: Inventory Item Details
  - **ID:** REQ-6
  - **Priority:** Must Have
  - **Estimated Effort:** 1-2 day
  - **Type:** Functional
  - **Description:** Each inventory item must display a name, at least one picture, price in dollars in a decimal/currency format that is base-10, and a brief description.

  ### Story 3: Search Inventory
  - **ID:** REQ-7
  - **Priority:** Must Have
  - **Estimated Effort:** 1 day
  - **Type:** Functional
  - **Description:** The user can search for inventory by searching
  - 
#### Epic 3: Checkout and Payment Processing
- **ID:** EPIC-3
- **Priority:** Must Have
- **Estimated Effort:** 4-5 days
- **Type:** Functional

  ### Story 1: Add to Shopping Cart
  - **ID:** REQ-8
  - **Priority:** Must Have
  - **Estimated Effort:** 1 day
  - **Type:** Functional
  - **Description:** Users must be able to add multiple items to a shopping cart from the inventory list.
    
  ### Story 2: Checkout Process
  - **ID:** REQ-9
  - **Priority:** Must Have
  - **Estimated Effort:** 1 day
  - **Type:** Functional
  - **Description:** Users cannot click the Checkout button if the shopping cart is empty.If it is not empty users must see a list of items, a subtotal, and options to remove items or continue shopping.

  ### Story 3: Payment Information and Confirmation
  - **ID:** REQ-10
  - **Priority:** Must Have
  - **Estimated Effort:** 1-2 days
  - **Type:** Functional
  - **Description:** Users must enter shipping and credit card information to proceed with payment. This includes the card's expiration, CVV, and shipping address.

  ### Story 4: Order Completion and Receipt Display
  - **ID:** REQ-11
  - **Priority:** Must Have
  - **Estimated Effort:** 1 day
  - **Type:** Functional
  - **Description:** After confirming the order, the system must display a receipt, remove the items from inventory, and prepare an email receipt.
  ## Version 2 Requirements

### Epic 4: Updates for Version 2
- **ID:** EPIC-4
- **Priority:** Could Have
- **Estimated Effort:** 2 days
- **Type:** Functional

  #### Story 1: Update the User Interface
  - **ID:** REQ-12
  - **Priority:** Could Have
  - **Estimated Effort:** 1 day
  - **Type:** Functional
  - **Description:** Craete a simple user interface to make it simpler to make user admins and track who has admin abilites.
### Epic 5: Administrative Enhancements
- **ID:** EPIC-5
- **Priority:** Should Have
- **Estimated Effort:** 2-3 days
- **Type:** Functional

  #### Story 1: Advanced Sales Reporting
  - **ID:** REQ-13
  - **Priority:** Should Have
  - **Estimated Effort:** 1 day
  - **Type:** Functional
  - **Description:** An administrator must be able to run a sales report that shows everything purchased and who purchased it. The client would lie to press on a sold item and it shows me the receipt related to that.

  #### Story 3: Manual Inventory Addition
  - **ID:** REQ-14
  - **Priority:** Should Have
  - **Estimated Effort:** 1-2 days
  - **Type:** Functional
  - **Description:** Finally, administrators must have some way to add inventory into the system. How will they add new inventory to the database? They need some approach. I would love it if they could open a page and enter the information (and choose a picture) and it gets added to the database. If that is too hard, they could just enter it into the database manually.

### Epic 6: Enhancing User Purchase Experience
- **ID:** EPIC-6
- **Priority:** Could Have
- **Estimated Effort:** 1 day
- **Type:** Functional

  #### Story 1: Enhanced Checkout Experience
  - **ID:** REQ-15
  - **Priority:** Could Have
  - **Estimated Effort:** 1 day
  - **Type:** Functional
  - **Description:** If the user removes everything from the cart, take the user back to the main screen automatically.

