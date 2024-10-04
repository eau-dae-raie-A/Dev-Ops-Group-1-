🌎 USE CASE 3: Produce a Report on Countries in a Region Organized by Largest to Smallest Population

📌 Characteristic Information

	•       Goal in Context:
	•       As a data analyst, I want to generate a report of all countries in a specific region, organized by population from largest to smallest, so that I can analyze population data within a region.
	
    •       Scope:
	•       Population data for a specific region.
	
    •       Level:
	•       Primary task.
	
    •       Preconditions:
	•       The specific region is known.
	•       The database contains current and accurate population figures for all countries within the region.
	
    •       Success End Condition:
	•       A report with all countries in the region, organized by population (from largest to smallest), is produced and delivered to the researcher for analysis.
	
    •       Failed End Condition:
	•       No report is generated.
	
    •       Primary Actor:
	•       Data Analyst.
	
    •       Trigger:
	•       A request to data analyst for population data by region is made for analysis.

🛠 Main Success Scenario

	•	A request is made for population data for a specific region.
	•	The system retrieves up-to-date population figures for all countries within the specified region from the database.
	•	The system organizes the countries by population size, sorting them from largest to smallest.
	•	The report is generated and delivered to the researcher for analysis.

🚨 Extensions

	•	If population data for some countries within the region is missing:
	•	The system proceeds with the available data.

🔀 Sub-Variations

	•	None.

⏳ Schedule

	•	Due Date: Release 1.0