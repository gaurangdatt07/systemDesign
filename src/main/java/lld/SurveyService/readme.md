# 📋 Survey System - Low Level Design

---

## 📌 Objective

Design a system that allows users to take surveys (quizzes), supports multiple attempts, tracks the best score per survey, and allows admins to manage surveys and view submissions.

---

## 👨‍💼 Roles

### 🧑 User
- Can view available surveys
- Can submit a survey (multiple times)
- Only the **highest score** per survey is retained
- Can see their own past scores

### 👩‍💼 Admin
- Can create surveys and questions
- Can view all user submissions
- Can track highest score and attempt history per user per survey

---

## 📦 Core Entities

### `User`
- `id`
- `name`
- `role` → USER or ADMIN

### `Survey`
- `id`
- `title`
- `List<Question>`

### `Question`
- `id`
- `text`
- `List<Option>`

### `Option`
- `id`
- `text`
- `score`

### `Attempt`
- `attemptId`
- `surveyId`
- `userId`
- `Map<QuestionId, OptionId>`
- `totalScore`
- `timestamp`

---

## 🧠 Core Features & Requirements

### ✅ User Flow
- A user can submit any survey any number of times
- Only the **highest score** per survey per user is kept as their best score
- All attempts are logged

### ✅ Admin Flow
- Can create surveys and add questions/options
- Can view:
    - All users and which surveys they attempted
    - All attempt histories for a specific survey or user

---

## 🔐 Access Control

| Role  | Permissions |
|-------|-------------|
| USER  | Submit surveys, view own scores |
| ADMIN | Create surveys, view all submissions |

---

## 🗃️ In-Memory Data Model (suggested)

- `Map<Integer, Survey>`
- `Map<Integer, List<Attempt>> userId → attempts`
- `Map<Pair<userId, surveyId>, Integer> → highestScore`

---

## 🔄 Key Logic

- On `submitSurvey()`:
    - Create new `Attempt` object
    - Calculate total score from chosen options
    - Update best score if this score is higher

---

## 🎯 Evaluation Focus

This problem tests:
- Entity design
- Session/attempt tracking
- Score comparison & override logic
- Role-based access control
- Clean service separation

---

## 🧱 Suggested Services

### `SurveyService`
- `createSurvey(Survey s)`
- `addQuestion(int surveyId, Question q)`

### `UserService`
- `submitSurvey(int surveyId, Attempt attempt)`
- `getUserAttempts(int userId)`
- `getBestScores(int userId)`

### `AdminService`
- `viewAllSubmissions()`
- `viewSurveyStats(int surveyId)`
- `viewUserHistory(int userId)`

---

## 🧪 Bonus Features (optional)

- Time-bound surveys
- Export attempt history
- Leaderboards

---
