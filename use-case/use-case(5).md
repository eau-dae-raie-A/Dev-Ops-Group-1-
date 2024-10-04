🌍 USE CASE 5: Produce a Report of the Top N Populated Countries in a Continent

📌 Characteristic Information

	•       Goal in Context:
	•       As a researcher, I want to generate a report of the top N populated countries in a specific continent, where N is provided by the researcher, so that I can analyze the most populous countries in the continent.
    
    •       Scope:
	•       Population data for a specific continent.

    •       Level:
	•       Primary task.
    
    •       Preconditions:
	•       The continent and the number of top countries (N) are specified by the user.
	•       The database contains current and accurate population data for all countries in the specified continent.
    
    •       Success End Condition:
	•       A report listing the top N most populated countries in the specified continent is produced and delivered to the user.
	
    •       Failed End Condition:
	•       No report is generated.

    •       Primary Actor:
	•       Researcher.

    •       Trigger:
	•       A request to data analyst is made for the top N populated countries in a specific continent.

🛠 Main Success Scenario

	•	The system receives the specified continent and the value of N (number of countries) from the researcher for the report.
	•	The system retrieves up-to-date population data for all countries in the specified continent from the database.
	•	The system organizes the countries by population size, sorting them from largest to smallest.
	•	The system generates a report listing the top N most populated countries in the continent and delivers it to the researcher.

🚨 Extensions

	•	If N exceeds the number of countries in the continent:
	•	The system generates a report with all available countries in the continent.
	•	The system informs the researcher that the requested value for N exceeds the available number of countries.

🔀 Sub-Variations

	•	None.

⏳ Schedule

	•	Due Date: Release 1.0