## Database Seed Data

When the application starts, the following data will already exist in the system to allow users to register, browse items, and place orders. This is not example data — it's required to make the app functional on first launch.

### User Entity

| user_id | username      | password       | email                  | is_admin |
|---------|---------------|----------------|-------------------------|----------|
| 1       | adminVault    | Admin123!      | admin@shoevault.com     | TRUE     |

### Product Entity

| product_id | name                   | description                            | price   | image             | is_sold |
|------------|------------------------|----------------------------------------|---------|-------------------|---------|
| 101        | Air Jordan 1 OG        | Retro classic in Chicago red           | 220.00  | jordan1.jpg       | FALSE   |
| 102        | Adidas Yeezy Boost 350 | Iconic Kanye West sneaker              | 300.00  | yeezy350.jpg      | FALSE   |
| 103        | Nike Dunk Low Panda    | Black and white versatile sneaker      | 160.00  | dunkpanda.jpg     | FALSE   |
| 104        | New Balance 550 UNC    | University blue low-top sneaker        | 110.00  | nb550unc.jpg      | FALSE   |
| 105        | Converse Run Star Hike | Platform sole with star branding       | 95.00   | runstarhike.jpg   | FALSE   |

### ShippingType Entity

| shipping_type_id | name       | cost  |
|------------------|------------|-------|
| 1                | Overnight  | 29.00 |
| 2                | 3-Day      | 19.00 |
| 3                | Ground     | 0.00  |

### Summary of Seed Requirements

| Entity         | Why It's Seeded                                 |
|----------------|--------------------------------------------------|
| User           | Provides an admin account for initial access     |
| Product        | Populates the storefront with available items    |
| ShippingType   | Supports checkout and price calculation logic    |
