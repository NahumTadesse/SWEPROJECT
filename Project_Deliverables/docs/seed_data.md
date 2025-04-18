## Database Seed Data

When the application starts, the following data will already exist in the system to allow users to register, browse items, and place orders. This is not example data — it's required to make the app functional on first launch.

### User Entity

| user_id | username      | password       | email                  | is_admin |
|---------|---------------|----------------|-------------------------|----------|
| 1       | adminVault    | Admin123!      | admin@shoevault.com     | TRUE     |

### Product Entity

| product_id | name                   | description                                | price   | image               | is_sold |
|------------|------------------------|--------------------------------------------|---------|---------------------|---------|
| 101        | Nike Air Mag  | Back to the future edition                  | 15000.00  | air_mag.jpg      | FALSE   |
| 102        | New Balance        | Comfortable tennis shoe              | 300.00  | new_bal.jpg       | FALSE   |
| 103        | Nike P6000   | Comfortable Trainners    | 150.00  | p6000.jpg    | TRUE    |
| 104        | Jordan 1     | Retro Jordan 1 high top            | 110.00  | jordan1.jpg     | FALSE   |
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
