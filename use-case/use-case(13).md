🌍 USE CASE 13: Produce a Report of the Top N Populated Cities in a Continent

📌 Characteristic Information

	•       Goal in Context:
	•       As a user, I want to generate a report of the top N populated cities in a continent, where N is provided by the user, so that I can focus on the most populous cities within that continent.
	
    •       Scope:
	•       Population data for cities within a specific continent.
	
    •       Level:
	•       Primary task.
	
    •       Preconditions:
	•       The specific continent is known.
	•       The database contains current and accurate population figures for cities within the continent.
	•       The user provides a value for N (the number of top cities to retrieve).
	
    •       Success End Condition:
	•       A report with the top N populated cities in the specified continent is produced and delivered to the user for analysis.
	
    •       Failed End Condition:
	•       No report is generated, or N is not provided.
	
    •       Primary Actor:
	•       User.
	
    •       Trigger:
	•       A request is made to retrieve the top N populated cities within a continent for analysis.

🛠 Main Success Scenario

	•	The user provides a value for N (e.g., top 10, top 20, etc.).
	•	The system retrieves up-to-date population figures for all cities within the specified continent.
	•	The system organizes the cities by population size, from largest to smallest.
	•	The system selects the top N cities based on the user’s input.
	•	The report is generated and delivered to the user for analysis.

🚨 Extensions

	•	If N is not provided by the user:
	•	The system prompts the user to provide a valid value for N.
	•	If population data for some cities within the continent is missing:
	•	The system proceeds with available data and informs the user of any missing entries in the final report.

🔀 Sub-Variations

	•	None.

⏳ Schedule

	•	Due Date: Release 1.0
