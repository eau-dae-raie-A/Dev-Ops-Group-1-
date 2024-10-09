🏙️ USE CASE 8: Produce a Report on All Cities in a Continent Organized by Largest to Smallest Population

📌 Characteristic Information

	•       Goal in Context:
	•       As a data analyst, I want to generate a report of all cities in a specific continent, organized by population from largest to smallest, so that I can analyze population data in cities within that continent.
	
    •       Scope:
	•       Population data for cities in a specific continent.
	
    •       Level:
	•       Primary task.
	
    •       Preconditions:
	•       The specific continent is known.
	•       The database contains current and accurate population figures for all cities in that continent.
	
    •       Success End Condition:
	•       A report with all cities in the specified continent, organized by population from largest to smallest, is produced and delivered to the researcher for analysis.
	
    •       Failed End Condition:
	•       No report is generated.
	
    •       Primary Actor:
	•       Data Analyst.
	
    •       Trigger:
	•       A request to data analyst for urban population data by continent is made for analysis.

🛠 Main Success Scenario

	•	The system receives a request for population data for cities in a specific continent.
	•	The system retrieves up-to-date population figures for all cities within the specified continent from the database.
	•	The system organizes the cities by population size, sorting them from largest to smallest.
	•	The system generates the report and delivers it to the researcher for analysis.

🚨 Extensions

	•	If population data for some cities within the continent is missing:
	•	The system proceeds with the available data.

🔀 Sub-Variations

	•	None.

⏳ Schedule

	•	Due Date: Release 1.0