# 🍽️ Food Ordering System – LLD Specification

Design a backend system that allows users to browse restaurants, add items to cart, place orders, and track delivery.

---

## 🎯 Functional Requirements

### 👤 User
- Can **view restaurants** and their menu
- Can **add/remove items** from cart
- Can **place an order** (one restaurant at a time)
- Can **view current & past orders**

### 🏪 Restaurant
- Each restaurant has:
    - A **menu** (list of food items)
    - A **name**, ID, and address
    - A list of **orders placed**

### 📦 Order Flow
1. User adds items to cart
2. User places the order
3. System creates an `Order` with:
    - Ordered items
    - Total price
    - Status: `PLACED` → `PREPARING` → `OUT_FOR_DELIVERY` → `DELIVERED`
4. User can **track status** of current order

---

## 📦 Core Entities

### User
- `id`, `name`
- Cart: `List<CartItem>`
- Order history: `List<Order>`

### Restaurant
- `id`, `name`, `List<MenuItem> menu`

### MenuItem
- `id`, `name`, `price`, `category`

### CartItem
- `menuItem`
- `quantity`

### Order
- `id`, `userId`, `restaurantId`, `List<CartItem>`
- `totalAmount`
- `status` (enum)

---

## 🧠 Bonus (Optional Later)
- Filter restaurants by location or cuisine
- Add ratings/reviews
- Discount coupons

---

## 🧱 In-Memory Storage
- `Map<Integer, User>`
- `Map<Integer, Restaurant>`
- `Map<Integer, Order>`

---

## 🔐 Access Control
- User should only access their own cart/orders
- (Optional) Simulate login context via `AuthContext`

---

Ready to start with the **entity modeling** just like before?

We can begin with `User`, `Restaurant`, `MenuItem`, `CartItem`, and `Order`. Want to do the first pass or shall I scaffold it?
