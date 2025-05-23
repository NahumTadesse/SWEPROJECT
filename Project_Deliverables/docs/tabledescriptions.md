## Field Descriptions

| Entity       | Field Name         | Type     | Description                              | Related To |
|--------------|--------------------|----------|------------------------------------------|------------|
| **User**     | user_id            | PK       | User's unique ID                         |            |
|              | username           | Text     | Login name (must be unique)              |            |
|              | password           | Text     | User's login password 6 char min          |            |
|              | is_admin           | Boolean  | Decides if a user has admin privilges            |            |
| **Product**  | product_id         | PK       | Unique ID for each product               |            |
|              | name               | Text     | Name of the product                      |            |
|              | description        | Text     | Short description                        |            |
|              | price              | Double | Price in dollars                            |            |
|              | image              | Text     | Image of the product                 |            |
|              | is_sold            | Boolean  | True if already purchased                |            |
| **ItemsinCart** | cart_id         | PK       | Unique cart item ID                      |            |
|              | user_id            | FK       | User who owns this cart item             | User       |
|              | product_id         | FK       | Product added to cart                    | Product    |
| **Order**    | order_id           | PK       | Unique ID for the order                  |            |
|              | user_id            | FK       | Who placed the order              | User       |
|              | subtotal           | Double | Total of item prices                     |            |
|              | tax_amount         | Double | Tax amount (6%)                          |            |
|              | shipping_cost      | Double  | Shipping fee based on method             |            |
|              | shipping_method    | Text     | Which shipping method did user select           |            |
|              | shipping_address   | Text     | Where to ship the items                  |            |
|              | phone_number       | Text     | Phone number for contact                 |            |
|              | grand_total        | Double | Final total with tax and shipping        |            |
| **OrderItem**| order_item_id      | PK       | Unique ID for this item in an order      |            |
|              | order_id           | FK       | Order this item belongs to               | Order      |
|              | product_id         | FK       | Product that was purchased               | Product    |
| **Payment**  | payment_id         | PK       | Unique ID for the payment                |            |
|              | order_id           | FK       | Order this payment is for                | Order      |
|              | card_number    | Text     | Card number of the user          |            |
|              | card_exp_month     | Int      | Expiration month of card                 |            |
|              | card_exp_year      | Int      | Expiration year of card                  |            |
|              | cvv                | Int      | CVV security code                        |            |
|              | paid_total         | Double | This is the amount that will show on the recipt|
