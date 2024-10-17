# 🐞 Bug Report: Database Connection & Data Handling Issues

## 📝 Issue Description
The system fails to retrieve and display accurate population data due to database connection issues and data handling errors.

---

## 🔄 Steps to Reproduce the Issue
1. **Run the application** and generate a population report for a city (`City.java`).
2. **Generate a continent report** using `Country.java`.
3. **Navigate** to the language statistics report section and try to generate a report using `LanguageReport.java`.

---

## ✅ Expected Result
The system should connect to the database successfully, retrieve accurate data, and display correct reports.

## ❌ Actual Result
- Intermittent **"Connection refused"** errors.
- Reports display **outdated or incorrect population data**.
- **`NullPointerException`** occurs for missing data.
- Reports generated contain **incomplete or mismatched data**.

---

## 🛠️ Additional Details
- The **port number** in `DatabaseService.java` doesn’t match the actual database port.
- Errors like **"Connection refused"** occur due to the mismatched port.
- This issue happens more frequently when **running in Docker or different environments**.

---