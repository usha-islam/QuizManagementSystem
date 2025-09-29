

https://github.com/user-attachments/assets/e5a4aabf-c012-4ba2-bab3-6d1fabd12359



https://github.com/user-attachments/assets/4104b498-6094-4ce1-a3e7-29baeda4874d



https://github.com/user-attachments/assets/29e9c228-e728-4802-9af9-c8be0af182fb



https://github.com/user-attachments/assets/0d59dc81-eaf6-48ad-a090-a153b50cd3f7

# Quiz Management System

## Description
This is a simple **role-based Java Quiz Management System**.  
- **Admin** users can add multiple-choice questions (MCQs) to the question bank.  
- **Student** users can take a quiz based on the question bank.  
- Data is stored in **JSON files** (`users.json` and `quiz.json`).  
- UI is built using **Java Swing**, no console input required.

---

## Features

### Admin
- Login with username/password from `users.json`  
- Add multiple-choice questions (MCQs) to `quiz.json`  
- Continuous question addition until admin chooses to exit  

### Student
- Login with username/password from `users.json`  
- Take a quiz with **10 random questions** from question bank  
- Each correct answer gives **1 mark**, no negative marking  
- Score and performance message displayed at the end

---

## Tech Stack
- **Language:** Java  
- **Data Storage:** JSON files (`users.json` & `quiz.json`)  
- **UI:** Java Swing  

---

## Setup & Run

1. Open the project in your preferred IDE (e.g., IntelliJ IDEA)  
2. Make sure **`users.json`** and **`quiz.json`** are in the project root  
3. Run **`Main.java`**  
4. Login as **Admin** or **Student**  
5. Follow UI instructions to add questions or take the quiz  

---

## Users (Sample)

- **Admin:**  
  - Username: `admin`  
  - Password: `1234`  

- **Student:**  
  - Username: `usha`  
  - Password: `1234`  

---

## JSON Files

### users.json
```json
[
  { "username": "admin", "password": "1234", "role": "admin" },
  { "username": "usha", "password": "1234", "role": "student" }
]
