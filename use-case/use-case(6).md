🌏 USE CASE 6: Produce a Report of the Top N Populated Countries in a Region

📌 Characteristic Information

	•       Goal in Context:
	•       As a researcher, I want to generate a report of the top N populated countries in a specific region, where N is provided by the researcher, so that I can focus on the most populous countries in that region.
	
    •       Scope:
	•       Population data for a specific region.
	
    •       Level:
	•       Primary task.

    •       Preconditions:
	•       The region and the number of top countries (N) are specified by the researcher.
	•       The database contains current and accurate population data for all countries in the specified region.
	
    •       Success End Condition:
	•       A report listing the top N most populated countries in the specified region is produced and delivered to the researcher.
	
    •       Failed End Condition:
	•       No report is generated.
	
    •       Primary Actor:
	•       Researcher.
	
    •       Trigger:
	•       A request to data analyst is made for the top N populated countries in a specific region.

🛠 Main Success Scenario

	•	The system receives the specified region and the value of N (number of countries) from the researcher for the report.
	•	The system retrieves up-to-date population data for all countries in the specified region from the database.
	•	The system organizes the countries by population size, sorting them from largest to smallest.
	•	The system generates a report listing the top N most populated countries in the region and delivers it to the researcher.

🚨 Extensions

	•	If N exceeds the number of countries in the region:
	•	The system generates a report with all available countries in the region.
	•	The system informs the researcher that the requested value for N exceeds the available number of countries.

🔀 Sub-Variations

	•	None.

⏳ Schedule

	•	Due Date: Release 1.0