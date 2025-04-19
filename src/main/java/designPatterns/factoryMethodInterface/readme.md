# 🏭 Factory Method Pattern

## 📂 Category
**Creational Design Pattern**

## 🧠 Definition
The **Factory Method Pattern** provides an interface for creating objects in a superclass but allows subclasses to alter the type of objects that will be created.

It delegates the instantiation logic to child classes, promoting loose coupling and adherence to the Open/Closed Principle.

## 🎯 Purpose
- Encapsulate object creation logic
- Allow a class to defer instantiation to its subclasses
- Promote flexibility and extensibility

## ✅ Key Characteristics
- Defines a common interface for object creation
- Lets subclasses decide which concrete class to instantiate
- Avoids tight coupling to specific classes

## 📌 When to Use
- You have a base class that needs to instantiate objects, but you want to let subclasses decide which class to instantiate
- You want to hide the instantiation logic from the client
- You’re dealing with code that might need to be extended in the future with new types

## 🌟 Benefits
- Supports Open/Closed Principle
- Promotes code reuse and decoupling
- Easier to add new types without modifying existing code

## 🔁 Real-World Examples
- Notification systems (Email, SMS, Push)
- Document creation (PDF, Word, HTML)
- UI components (Buttons for Windows, Mac, Linux)
- Payment processing gateways (Stripe, PayPal)

## 🧩 Related Patterns
- **Abstract Factory** – a super-factory of multiple related objects
- **Strategy** – different behavior, but not object creation
- **Prototype** – cloning existing objects instead of creating new ones

---
