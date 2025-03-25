## Version 1 Requirements

### Milestone: Initial System Setup and Core Features Implementation
**Planned Completion: [Date]**

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
  - **Description:** Each inventory item must display a name, at least one picture, price in dollars, and a brief description.

  ### Story 3: Search Inventory
  - **ID:** REQ-6
  - **Priority:** Must Have
  - **Estimated Effort:** 1 day
  - **Type:** Functional
  - **Description:** The user should be able to search the inventory by typing in a search box.

  ### Story 4: Add to Shopping Cart
  - **ID:** REQ-7
  - **Priority:** Must Have
  - **Estimated Effort:** 1 day
  - **Type:** Functional
  - **Description:** Users must be able to add multiple items to a shopping cart from the inventory list.

#### Epic 3: Checkout and Payment Processing
- **ID:** EPC-3
- **Priority:** Must Have
- **Estimated Effort:** 3-4 days
- **Type:** Functional

  ### Story 1: Checkout Process
  - **ID:** REQ-8
  - **Priority:** Must Have
  - **Estimated Effort:** 1 day
  - **Type:** Functional
  - **Description:** Users cannot click the Checkout button if the shopping cart is empty. Upon clicking, users must see a list of items, a subtotal, and options to remove items or continue shopping.

  ### Story 2: Payment Information and Confirmation
  - **ID:** REQ-9
  - **Priority:** Must Have
  - **Estimated Effort:** 1-2 days
  - **Type:** Functional
  - **Description:** Users must enter shipping and credit card information to proceed with payment. This includes the card's expiration, CVV, and the user's shipping address.

  ### Story 3: Order Completion and Receipt Display
  - **ID:** REQ-10
  - **Priority:** Must Have
  - **Estimated Effort:** 1 day
  - **Type:** Functional
  - **Description:** After confirming the order, the system must display a receipt, remove the items from inventory, and prepare an email receipt (simulation).
