# 🐞 Bug Report

## 📝 Issue Description
The system fails to retrieve and display accurate population data due to database connection issues and data handling errors.

---

## 🔄 Steps to Reproduce the Issue
1. **Run the application** and generate a population report for a city (`City.java`).
2. **Generate a continent report** using `Country.java`.
3. **Navigate** to the language statistics report section and generate a report using `LanguageReport.java`.

---

## ✅ Expected Result
The system should connect to the database, retrieve accurate data, and display correct reports.

---

## ❌ Actual Result
- Intermittent **"Connection refused"** errors.
- Reports display **outdated or incorrect population data**.
- **`NullPointerException`** occurs when data is missing.
- Population reports contain **incomplete or mismatched data**.

---

## 🛠️ Additional Details
- The **database port** in `DatabaseService.java` is inconsistent.
- Missing **exception handling** in `getCitiesByContinent` and `getTopPopulatedCities`.
- Errors vary by environment, including **`NullPointerException`** and **`SQLIntegrityConstraintViolationException`**.

---