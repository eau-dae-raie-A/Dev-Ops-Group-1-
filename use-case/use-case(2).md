🌍 USE CASE 2: Produce a Report on Countries in a Continent Organized by Largest to Smallest Population

📌 Characteristic Information

	•       Goal in Context:
	•       As a data analyst, I want to generate a report of all countries in a specific continent, organized by population from largest to smallest, so that I can compare populations within a continent.
	
    •       Scope:
	•       Population data for a specific continent.
	
    •       Level:
	•       Primary task.
	
    •       Preconditions:
	•       The specific continent is known.
	•       The database contains current and accurate population figures for all countries within the continent.

    •       Success End Condition:
	•       A report with all countries in the continent, organized by population (from largest to smallest), is produced and delivered to the requesting researcher.
	
    •       Failed End Condition:
	•       No report is generated.
	
    •       Primary Actor:
	•       Data Analyst.

	•       Trigger:
	•       A request to data analyst for population data by continent is made (e.g., for comparison, analysis).

🛠 Main Success Scenario

	•	A request is made for population data for a specific continent.
    •	The system retrieves up-to-date population figures for all countries within the specified continent from the database.
	•	The system organizes the countries by population size, sorting them from largest to smallest.
	•	The report is generated and delivered to the researcher for comparison and analysis.

🚨 Extensions

	•	If population data for some countries within the continent is missing:
	•	The system proceeds with the available data.

🔀 Sub-Variations

	•	None.

⏳ Schedule

	•	Due Date: Release 1.0