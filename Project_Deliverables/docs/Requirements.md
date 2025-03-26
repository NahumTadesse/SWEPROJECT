## Version 1 Requirements

### Milestone: Initial System Setup and Core Features Implementation
**Planned Completion: **

#### Epic 1: User Authentication
- **ID:** EPC-1
- **Priority:** Must Have
- **Estimated Effort:** 4 days
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
  - **Description:** Users must be able to login using their username and password to access the system.

  ### Story 3: Admin User Creation
  - **ID:** REQ-3
  - **Priority:** Must Have
  - **Estimated Effort:** 1 day
  - **Type:** Functional
  - **Description:** Existing admins must be able to convert a regular user account into an admin account.

#### Epic 2: Inventory Display and Management
- **ID:** EPC-2
- **Priority:** Must Have
- **Estimated Effort:** 4-5 days
- **Type:** Functional
- **Description:** User must see a list of all available inventory sorted by highest price to lowest price.

  ### Story 1: Display Available Inventory
  - **ID:** REQ-4
  - **Priority:** Must Have
  - **Estimated Effort:** 1 day
  - **Type:** Functional
  - **Description:** Upon login, users must see a list of all available inventory, sorted from highest to lowest price, excluding sold items.

  ### Story 2: Inventory Item Details
  - **ID:** REQ-5
  - **Priority:** Must Have
  - **Estimated Effort:** 1-2 day
  - **Type:** Functional
  - **Description:** Each inventory item must display a name, at least one picture, price in dollars in a decimal/currency format that is base-10, and a brief description.

  ### Story 3: Search Inventory
  - **ID:** REQ-6
  - **Priority:** Must Have
  - **Estimated Effort:** 1 day
  - **Type:** Functional
  - **Description:** The user should be able to search the inventory by typing in a search box.
  - 
#### Epic 3: Checkout and Payment Processing
- **ID:** EPC-3
- **Priority:** Must Have
- **Estimated Effort:** 3-4 days
- **Type:** Functional

  ### Story 1: Add to Shopping Cart
  - **ID:** REQ-7
  - **Priority:** Must Have
  - **Estimated Effort:** 1 day
  - **Type:** Functional
  - **Description:** Users must be able to add multiple items to a shopping cart from the inventory list.
    
  ### Story 2: Checkout Process
  - **ID:** REQ-8
  - **Priority:** Must Have
  - **Estimated Effort:** 1 day
  - **Type:** Functional
  - **Description:** Users cannot click the Checkout button if the shopping cart is empty. Upon clicking, users must see a list of items, a subtotal, and options to remove items or continue shopping.

  ### Story 3: Payment Information and Confirmation
  - **ID:** REQ-9
  - **Priority:** Must Have
  - **Estimated Effort:** 1-2 days
  - **Type:** Functional
  - **Description:** Users must enter shipping and credit card information to proceed with payment. This includes the card's expiration, CVV, and the user's shipping address.

  ### Story 4: Order Completion and Receipt Display
  - **ID:** REQ-10
  - **Priority:** Must Have
  - **Estimated Effort:** 1 day
  - **Type:** Functional
  - **Description:** After confirming the order, the system must display a receipt, remove the items from inventory, and prepare an email receipt (simulation).
  ## Version 2 Requirements

### Epic 4: Enhanced Inventory Management
- **ID:** EPC-4
- **Priority:** Could Have
- **Estimated Effort:** 2 days
- **Type:** Functional

  #### Story 1: Implement Multiple Images per Inventory Item
  - **ID:** REQ-11
  - **Priority:** Could Have
  - **Estimated Effort:** 1 day
  - **Type:** Functional
  - **Description:** It would be great if there was a simple user interface to do that, but I need some process regardless if a user interface is too much work for Version 1.

### Epic 5: Administrative Enhancements
- **ID:** EPC-5
- **Priority:** Should Have
- **Estimated Effort:** 2-3 days
- **Type:** Functional

  #### Story 1: Advanced Sales Reporting
  - **ID:** REQ-12
  - **Priority:** Should Have
  - **Estimated Effort:** 1 day
  - **Type:** Functional
  - **Description:** An administrator must be able to run a sales report that shows everything purchased and who purchased it. I would LOVE it if I could click on a sold item and it shows me the receipt related to that.

  #### Story 2: Export Sales Report to CSV
  - **ID:** REQ-13
  - **Priority:** Should Have
  - **Estimated Effort:** 1 day
  - **Type:** Functional
  - **Description:** An administrator needs to be able to export the sales report to CSV. This will let the admin analyze the data better in something like Excel.

  #### Story 3: Manual Inventory Addition
  - **ID:** REQ-14
  - **Priority:** Should Have
  - **Estimated Effort:** 1-2 days
  - **Type:** Functional
  - **Description:** Finally, administrators must have some way to add inventory into the system. How will they add new inventory to the database? They need some approach. I would love it if they could open a page and enter the information (and choose a picture) and it gets added to the database. If that is too hard, they could just enter it into the database manually.

### Epic 6: Enhancing User Purchase Experience
- **ID:** EPC-6
- **Priority:** Could Have
- **Estimated Effort:** 1 day
- **Type:** Functional

  #### Story 1: Enhanced Checkout Experience
  - **ID:** REQ-15
  - **Priority:** Could Have
  - **Estimated Effort:** 1 day
  - **Type:** Functional
  - **Description:** The user cannot click Checkout if the shopping cart is empty. After clicking Checkout, the user must see a list of everything in the cart and a subtotal cost in US dollars. From this list, the user can remove items from the cart. If the user removes everything from the cart, take the user back to the main screen automatically.

