package com.napier.devops;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Country class, ensuring that all getter and setter methods
 * function as expected and that the Country object's fields can be correctly set and retrieved.
 */
public class CountryTest {

    // Instance of Country class for testing
    private Country country;

    /**
     * Sets up a new Country object before each test.
     */
    @BeforeEach
    public void setUp() {
        // Initialize a new Country object before each test
        country = new Country();
    }

    /**
     * Tests setting and getting the country code.
     */
    @Test
    public void testSetAndGetCode() {
        country.setCode("NZL");
        assertEquals("NZL", country.getCode(), "Country code should be 'NZL'");
    }

    /**
     * Tests setting and getting the country's name.
     */
    @Test
    public void testSetAndGetName() {
        country.setName("New Zealand");
        assertEquals("New Zealand", country.getName(), "Country name should be 'New Zealand'");
    }

    /**
     * Tests setting and getting the country's continent.
     */
    @Test
    public void testSetAndGetContinent() {
        country.setContinent("Oceania");
        assertEquals("Oceania", country.getContinent(), "Continent should be 'Oceania'");
    }

    /**
     * Tests setting and getting the country's region.
     */
    @Test
    public void testSetAndGetRegion() {
        country.setRegion("Australasia");
        assertEquals("Australasia", country.getRegion(), "Region should be 'Australasia'");
    }

    /**
     * Tests setting and getting the country's surface area.
     */
    @Test
    public void testSetAndGetSurfaceArea() {
        country.setSurfaceArea(268838.0);
        assertEquals(268838.0, country.getSurfaceArea(), 0.001, "Surface area should be 268838.0");
    }

    /**
     * Tests setting and getting the country's population.
     */
    @Test
    public void testSetAndGetPopulation() {
        country.setPopulation(5000000);
        assertEquals((Object) 5000000, country.getPopulation(), "Population should be 5,000,000");
    }

    /**
     * Tests setting and getting the country's local name.
     */
    @Test
    public void testSetAndGetLocalName() {
        country.setLocalName("Aotearoa");
        assertEquals("Aotearoa", country.getLocalName(), "Local name should be 'Aotearoa'");
    }

    /**
     * Tests setting and getting the country's form of government.
     */
    @Test
    public void testSetAndGetGovernmentForm() {
        country.setGovernmentForm("Constitutional monarchy");
        assertEquals("Constitutional monarchy", country.getGovernmentForm(), "Government form should be 'Constitutional monarchy'");
    }

    /**
     * Tests setting and getting the country's head of state.
     */
    @Test
    public void testSetAndGetHeadOfState() {
        country.setHeadOfState("King Charles III");
        assertEquals("King Charles III", country.getHeadOfState(), "Head of state should be 'King Charles III'");
    }

    /**
     * Tests setting and getting the country's alternate code (code2).
     */
    @Test
    public void testSetAndGetCode2() {
        country.setCode2("NZ");
        assertEquals("NZ", country.getCode2(), "Country code2 should be 'NZ'");
    }

    /**
     * Tests setting and getting the capital city object.
     */
    @Test
    public void testSetAndGetCapitalCity() {
        City capitalCity = new City();
        capitalCity.setName("Wellington");
        country.setCapitalCity(capitalCity);
        assertEquals("Wellington", country.getCapitalCity().getName(), "Capital city should be 'Wellington'");
    }

    /**
     * Tests setting and getting the country's year of independence.
     */
    @Test
    public void testSetAndGetIndepYear() {
        country.setIndepYear(1907);
        assertEquals((Object) 1907, country.getIndepYear(), "Independence year should be 1907");
    }

    /**
     * Tests setting and getting the country's life expectancy.
     */
    @Test
    public void testSetAndGetLifeExpectancy() {
        country.setLifeExpectancy(81.5);
        assertEquals(81.5, country.getLifeExpectancy(), 0.001, "Life expectancy should be 81.5");
    }

    /**
     * Tests setting and getting the country's Gross National Product (GNP).
     */
    @Test
    public void testSetAndGetGnp() {
        country.setGnp(205300.0);
        assertEquals(205300.0, country.getGnp(), 0.001, "GNP should be 205300.0");
    }

    /**
     * Tests setting and getting the country's previous GNP.
     */
    @Test
    public void testSetAndGetGnpOld() {
        country.setGnpOld(200000.0);
        assertEquals(200000.0, country.getGnpOld(), 0.001, "Old GNP should be 200000.0");
    }

    /**
     * Tests setting and getting the capital city's ID.
     */
    @Test
    public void testSetAndGetCapital() {
        country.setCapital(1);
        assertEquals((Object) 1, country.getCapital(), "Capital ID should be 1");
    }

    /**
     * Tests the toString method for correct string representation of a Country object.
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