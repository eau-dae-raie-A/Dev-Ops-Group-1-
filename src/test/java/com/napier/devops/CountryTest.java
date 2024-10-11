package com.napier.devops;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Country class.
 * This class verifies the functionality of the Country class, including setting and getting
 * various attributes such as code, name, continent, region, and more.
 */
public class CountryTest {

    // Instance of Country class for testing
    private Country country;

    /**
     * Initializes a new Country object before each test.
     * This ensures that each test has a fresh instance of the Country class to work with.
     */
    @BeforeEach
    public void setUp() {
        country = new Country();
    }

    /**
     * Test for setting and getting the country code.
     * Verifies that the country code is correctly stored and retrieved.
     */
    @Test
    public void testSetAndGetCode() {
        country.setCode("NZL");
        assertEquals("NZL", country.getCode(), "Country code should be 'NZL'");
    }

    /**
     * Test for setting and getting the country's name.
     * Confirms that the country name is accurately stored and retrieved.
     */
    @Test
    public void testSetAndGetName() {
        country.setName("New Zealand");
        assertEquals("New Zealand", country.getName(), "Country name should be 'New Zealand'");
    }

    /**
     * Test for setting and getting the country's continent.
     * Ensures that the continent is properly stored and can be retrieved.
     */
    @Test
    public void testSetAndGetContinent() {
        country.setContinent("Oceania");
        assertEquals("Oceania", country.getContinent(), "Continent should be 'Oceania'");
    }

    /**
     * Test for setting and getting the country's region.
     * Verifies that the region is correctly stored and retrieved.
     */
    @Test
    public void testSetAndGetRegion() {
        country.setRegion("Australasia");
        assertEquals("Australasia", country.getRegion(), "Region should be 'Australasia'");
    }

    /**
     * Test for setting and getting the country's surface area.
     * Confirms that the surface area is stored and retrieved accurately with a tolerance for floating-point comparisons.
     */
    @Test
    public void testSetAndGetSurfaceArea() {
        country.setSurfaceArea(268838.0);
        assertEquals(268838.0, country.getSurfaceArea(), 0.001, "Surface area should be 268838.0");
    }

    /**
     * Test for setting and getting the country's population.
     * Verifies that the population is correctly stored and retrieved.
     */
    @Test
    public void testSetAndGetPopulation() {
        country.setPopulation(5000000);
        assertEquals((Object) 5000000, country.getPopulation(), "Population should be 5,000,000");
    }

    /**
     * Test for setting and getting the country's local name.
     * Ensures that the local name is accurately stored and retrieved.
     */
    @Test
    public void testSetAndGetLocalName() {
        country.setLocalName("Aotearoa");
        assertEquals("Aotearoa", country.getLocalName(), "Local name should be 'Aotearoa'");
    }

    /**
     * Test for setting and getting the country's form of government.
     * Verifies that the government form is correctly stored and retrieved.
     */
    @Test
    public void testSetAndGetGovernmentForm() {
        country.setGovernmentForm("Constitutional monarchy");
        assertEquals("Constitutional monarchy", country.getGovernmentForm(), "Government form should be 'Constitutional monarchy'");
    }

    /**
     * Test for setting and getting the country's head of state.
     * Confirms that the head of state is accurately stored and retrieved.
     */
    @Test
    public void testSetAndGetHeadOfState() {
        country.setHeadOfState("King Charles III");
        assertEquals("King Charles III", country.getHeadOfState(), "Head of state should be 'King Charles III'");
    }

    /**
     * Test for setting and getting the country's secondary code.
     * Ensures that the secondary code is correctly stored and retrieved.
     */
    @Test
    public void testSetAndGetCode2() {
        country.setCode2("NZ");
        assertEquals("NZ", country.getCode2(), "Country code2 should be 'NZ'");
    }

    /**
     * Test for setting and getting the capital city object.
     * Verifies that the capital city is correctly stored and retrieved by comparing city names.
     */
    @Test
    public void testSetAndGetCapitalCity() {
        City capitalCity = new City();
        capitalCity.setName("Wellington");
        country.setCapitalCity(capitalCity);
        assertEquals("Wellington", country.getCapitalCity().getName(), "Capital city should be 'Wellington'");
    }

    /**
     * Test for setting and getting the country's independence year.
     * Confirms that the independence year is stored and retrieved correctly.
     */
    @Test
    public void testSetAndGetIndepYear() {
        country.setIndepYear(1907);
        assertEquals((Object) 1907, country.getIndepYear(), "Independence year should be 1907");
    }

    /**
     * Test for setting and getting the country's life expectancy.
     * Verifies that the life expectancy is stored and retrieved accurately with a tolerance for floating-point comparisons.
     */
    @Test
    public void testSetAndGetLifeExpectancy() {
        country.setLifeExpectancy(81.5);
        assertEquals(81.5, country.getLifeExpectancy(), 0.001, "Life expectancy should be 81.5");
    }

    /**
     * Test for setting and getting the country's Gross National Product (GNP).
     * Ensures that the GNP is correctly stored and retrieved.
     */
    @Test
    public void testSetAndGetGnp() {
        country.setGnp(205300.0);
        assertEquals(205300.0, country.getGnp(), 0.001, "GNP should be 205300.0");
    }

    /**
     * Test for setting and getting the country's old Gross National Product (GNP).
     * Confirms that the old GNP is correctly stored and retrieved.
     */
    @Test
    public void testSetAndGetGnpOld() {
        country.setGnpOld(200000.0);
        assertEquals(200000.0, country.getGnpOld(), 0.001, "Old GNP should be 200000.0");
    }

    /**
     * Test for setting and getting the country's capital ID.
     * Verifies that the capital ID is correctly stored and retrieved.
     */
    @Test
    public void testSetAndGetCapital() {
        country.setCapital(1);
        assertEquals((Object) 1, country.getCapital(), "Capital ID should be 1");
    }

    /**
     * Test for the toString method to ensure correct string representation.
     * Assembles expected output and compares it to the actual string output from toString.
     */
    @Test
    public void testToString() {
        country.setCode("NZL");
        country.setName("New Zealand");
        country.setContinent("Oceania");
        country.setRegion("Australasia");
        country.setSurfaceArea(268838.0);
        country.setIndepYear(1907);
        country.setPopulation(5000000);
        country.setLifeExpectancy(81.5);
        country.setGnp(205300.0);
        country.setGnpOld(200000.0);
        country.setLocalName("Aotearoa");
        country.setGovernmentForm("Constitutional monarchy");
        country.setHeadOfState("King Charles III");
        country.setCapital(1);
        country.setCode2("NZ");

        String expected = "Country{code='NZL', name='New Zealand', continent='Oceania', region='Australasia', surfaceArea=268838.0, indepYear=1907, population=5000000, lifeExpectancy=81.5, gnp=205300.0, gnpOld=200000.0, localName='Aotearoa', governmentForm='Constitutional monarchy', headOfState='King Charles III', capital=1, code2='NZ'}";
        assertEquals(expected, country.toString(), "toString should return the correct representation of the Country object");
    }
}
