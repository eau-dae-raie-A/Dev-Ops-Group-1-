🌐 USE CASE 4: Produce a Report of the Top N Populated Countries in the World

📌 Characteristic Information

	•       Goal in Context:
	•       As a researcher, I want to generate a report of the top N populated countries in the world, where N is provided by the researcher, so that I can view the most populous countries globally.
	
    •       Scope:
	•       Global population data.
	
    •       Level:
	•       Primary task.
	
    •       Preconditions:
	•       The number of top countries (N) is specified by the researcher.
	•       The database contains current and accurate population data for all countries.
	
    •       Success End Condition:
	•       A report listing the top N most populated countries globally is produced and provided to the researcher.
	
    •       Failed End Condition:
	•       No report is generated.
	
    •       Primary Actor:
	•       Researcher.
	
    •       Trigger:
	•       A request to data analyst is made for the top N populated countries in the world.

🛠 Main Success Scenario

	•	The researcher specifies the value of N (number of countries) for the report.
	•	The system retrieves up-to-date population data for all countries from the database.
	•	The system organizes the countries by population, sorting them from largest to smallest.
	•	The system generates a report listing the top N most populated countries based on researcher input and delivers it to the researcher.

🚨 Extensions

    •	If N exceeds the total number of countries:
    •	The system generates a report with all available countries.
    •	The system informs the researcher that the requested value for N exceeds the total number of available countries.

🔀 Sub-Variations

	•	None.

⏳ Schedule

	•	Due Date: Release 1.0