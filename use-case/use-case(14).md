🌍 USE CASE 14: Produce a Report of the Top N Populated Cities in a Region

📌 Characteristic Information

	•       Goal in Context:
	•       As a researcher, I want to generate a report of the top N populated cities in a region, where N is provided by the researcher, so that I can compare city populations within that region.
	
    •       Scope:
	•       Population data for cities within a specific region.
	
    •       Level:
	•       Primary task.
	
    •       Preconditions:
	•       The specific region is known.
	•       The database contains current and accurate population figures for cities within the region.
	•       The researcher provides a value for N (the number of top cities to retrieve).
	
    •       Success End Condition:
	•       A report with the top N populated cities in the specified region is produced and delivered to the researcher for analysis.
	
    •       Failed End Condition:
	•       No report is generated, or N is not provided.
	
    •       Primary Actor:
	•       Researcher.
	
    •       Trigger:
	•       A request from data analyst is made to retrieve the top N populated cities within a region for analysis.

🛠 Main Success Scenario

	•	The system receives the value for N (e.g., top 10, top 20, etc.) from the researcher.
	•	The system retrieves up-to-date population figures for all cities within the specified region from the database.
	•	The system organizes the cities by population size, sorting them from largest to smallest.
	•	The system selects the top N cities based on the researcher’s input.
	•	The system generates the report and delivers it to the researcher for analysis.

🚨 Extensions

	•	If N is not provided by the researcher:
	•	The system prompts the researcher to provide a valid value for N.
	•	If population data for some cities within the region is missing:
	•	The system proceeds with the available data.

🔀 Sub-Variations

	•	None.

⏳ Schedule

	•	Due Date: Release 1.0
