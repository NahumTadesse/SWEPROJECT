# Sample Data

## User Entity

| user_id | username            | password       | email                  | is_admin |
|---------|---------------------|----------------|-------------------------|----------|
| 1       | moonwalker23        | jazzHands123   | mia.smith@gmail.com     | FALSE    |
| 2       | sneakerdad92        | bootKing22     | elliot.davis@yahoo.com  | FALSE    |
| 3       | adminPower          | coffeeQueen!   | jasmine.lee@outlook.com | TRUE     |
| 4       | kickslover08        | fireTaco99     | cameron.west@hotmail.com| FALSE    |

---

## Product Entity

| product_id | name                   | description                                | price   | image               | is_sold |
|------------|------------------------|--------------------------------------------|---------|---------------------|---------|
| 101        | Air Jordan 4 Fire Red  | Classic Chicago colorway                   | 220.00  | fire_red_4.jpg      | FALSE   |
| 102        | Yeezy Boost 700        | Chunky sole with wave design               | 300.00  | yeezy_700.jpg       | FALSE   |
| 103        | Nike SB Dunk Low Pro   | Premium leather in black/white contrast    | 150.00  | dunk_low_pro.jpg    | TRUE    |
| 104        | New Balance 550 UNC    | Retro low-top with UNC blue                | 110.00  | nb_550_unc.jpg      | FALSE   |

---

## CartItem Entity

| cart_id | user_id | product_id |
|---------|---------|------------|
| 1       | 1       | 101        |
| 2       | 2       | 102        |
| 3       | 4       | 104        |

---

## Order Entity

| order_id | user_id | subtotal | tax_amount | shipping_cost | shipping_method     | shipping_address             | phone_number     | grand_total |
|----------|---------|----------|------------|----------------|----------------------|-------------------------------|------------------|--------------|
| 5001     | 3       | 150.00   | 9.00       | 4.95           | 3 Day First Class    | 88 Jordan Blvd, ATL, GA 30303 | 678-555-1234     | 163.95       |

---

## OrderItem Entity

| order_item_id | order_id | product_id |
|----------------|----------|------------|
| 1              | 5001     | 103        |

---

## Payment Entity

| payment_id | order_id | card_last_four | card_exp_month | card_exp_year | cvv | paid_total |
|------------|----------|----------------|----------------|----------------|-----|-------------|
| 9001       | 5001     | 4916           | 08             | 2027           | 263 | 163.95      |
