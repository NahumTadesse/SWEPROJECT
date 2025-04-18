# Sample Data

## User Entity

| user_id | username            | password       | email                  | is_admin |
|---------|---------------------|----------------|-------------------------|----------|
| 1       | sneakerhead123      | password1   | brooklopez@gmail.com     | FALSE    |
| 2       | airJordan1       | password2   | lebronjames23@yahoo.com  | FALSE    |
| 3       | ShoeVault2          | whatisthepassword   | lilwayne@outlook.com | TRUE     |
| 4       | sneaker       | uglyshoes22     | willSmith@gmail.com| FALSE    |

---

## Product Entity

| product_id | name                   | description                                | price   | image               | is_sold |
|------------|------------------------|--------------------------------------------|---------|---------------------|---------|
| 101        | Nike Air Mag  | Back to the future edition                  | 15000.00  | air_mag.jpg      | FALSE   |
| 102        | New Balance        | Comfortable tennis shoe              | 300.00  | new_bal.jpg       | FALSE   |
| 103        | Nike P6000   | Comfortable Trainners    | 150.00  | p6000.jpg    | TRUE    |
| 104        | Jordan 1     | Retro Jordan 1 high top            | 110.00  | jordan1.jpg     | FALSE   |

---

## CartItem Entity

| cart_id | user_id | product_id |
|---------|---------|------------|
| 1       | 1       | 101        |
| 2       | 2       | 102        |
| 3       | 4       | 104        |

---

## Order Entity

| order_id | user_id | subtotal | tax_amount | shipping_cost | shipping_method     | street_address       | city     | state | phone_number     | grand_total |
|----------|---------|----------|------------|----------------|----------------------|-----------------------|----------|-------|------------------|--------------|
| 5001     | 3       | 150.00   | 9.00       | 4.95           | 3 Day    | 88 Jordan Blvd        | Atlanta  | GA    | 678-555-1234     | 163.95       |


---

## OrderItem Entity

| order_item_id | order_id | product_id |
|----------------|----------|------------|
| 1              | 5001     | 103        |

---

## Payment Entity

| payment_id | order_id | card_number | card_exp_month | card_exp_year | cvv | paid_total |
|------------|----------|----------------|----------------|----------------|-----|-------------|
| 9001       | 5001     | 4916           | 08             | 2027           | 263 | 163.95      |
