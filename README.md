#  Java OOP Design
* ![](challenge1.png)

This project is a console-based Library Management System developed using Java and fundamental Object-Oriented Programming (OOP) principles.
The goal is to apply inheritance, polymorphism, encapsulation, and abstraction using real-life library concepts (books, members, librarians, authors).
🚀 Features

Entity Management

Books, Members, Authors, Librarians

Polymorphic Book Types

Novel, StudyBook, Journal, Magazine

Library Operations

Borrowing books

Returning books

Member verification

Late fee calculation

Consistent State Updates

Book status & member borrow count updated automatically

Console Interaction

Simple interactive menu using Scanner

🧱 Project Structure
Library/
 ├── model/         # Entities and rules
 │     ├── Person.java
 │     ├── Member.java
 │     ├── Librarian.java
 │     ├── Author.java
 │     ├── Book.java
 │     ├── Novel.java
 │     ├── StudyBook.java
 │     ├── Journal.java
 │     ├── Magazine.java
 │     ├── Library.java
 │     └── BookStatus.java
 ├── console/
 │     └── Main.java
 └── README.md

🧩 OOP Concepts Used
Concept	How It’s Used
Inheritance	Member, Author, Librarian extend Person
Polymorphism	Each book type overrides getLendingPeriodDays()
Abstraction	Person and Book are abstract base classes
Encapsulation	Library manages collections safely (private fields, getters/setters)
Enums	BookStatus defines fixed states (AVAILABLE, BORROWED)
📥 Installation & Running
✔ Compile
javac Library/console/Main.java

✔ Run
java Library.console.Main

📋 How to Use

When the app starts, you see an interactive menu:

➕ Add Book

User selects book type

Instance created dynamically

Added to Library collection

📕 Borrow Book

Member is validated

Available books listed

Book is borrowed via issueBook()

Member's borrow count updated

📘 Return Book

Book returned via returnBook()

Late fee calculated (if any)

🧍 Verify Member

Checks borrow limits, restrictions, etc.

🗂 Example Book Types
Book Type	Lending Days
StudyBook	2 days
Novel	7 days
Journal	5 days
Magazine	3 days
🎯 Purpose of the Project

This system helps you practice:

Abstract class design

Polymorphic behavior

Optional overriding

Object relationships

State consistency

Basic console-driven user interaction

📝 Notes

The project runs entirely in Java SE (no frameworks).

All data is stored in memory (no database).

Designed for OOP learning & practice.

📄 License

This project is for educational purposes.
