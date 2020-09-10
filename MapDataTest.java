import java.io.IOException;

import org.junit.Assert;

import org.junit.Test;

/**
 * Test for MapData
 * 
 * @author apotsangyangzom
 *
 */
public class MapDataTest
{
    /**
     * A MapData Constructor
     */
    MapData map = new MapData(2018, 8, 30, 17, 45, "data");

    /**
     * Test for getYear()
     */
    @Test
    public void testGetYear()
    {
        Assert.assertEquals(2018, map.getYear());
    }

    /**
     * Test for getMonth()
     */
    @Test
    public void testGetMonth()
    {
        Assert.assertEquals(8, map.getMonth());
    }

    /**
     * Test for getDay()
     */
    @Test
    public void testGetDay()
    {
        Assert.assertEquals(30, map.getDay());
    }

    /**
     * Test for getHour()
     * 
     */
    @Test
    public void testGetHour()
    {
        Assert.assertEquals(17, map.getHour());
    }

    /**
     * Test for getDirectory()
     */
    @Test
    public void testGetDirectory()
    {
        Assert.assertEquals("data", map.getDirectory());
    }

    /**
     * Test for createFileName()
     */
    @Test
    public void testCreateFileName()
    {
        String filename = "201808301745.mdf";
        String actual = map.createFileName();
        Assert.assertEquals("Calculation failed", filename, actual);
    }

    /**
     * Test for getSradAverage()
     * 
     * @throws IOException
     */
    @Test
    public void testGetSradAverage() throws IOException
    {
        map.parseFile();
        double average = 829.0;
        double actual = map.getSradAverage().getValue();
        Assert.assertEquals("Failed to calculate or an error occured", average, actual, 0.1);

    }

    /**
     * Test for getSradMax()
     * 
     * @throws IOException
     */
    @Test
    public void testGetSradMax() throws IOException
    {
        map.parseFile();
        double tair = 906.0;
        double actual = map.getSradMax().getValue();
        Assert.assertEquals("Calculation failed", tair, actual, 0.01);

    }

    /**
     * Test for getSradMin()
     * 
     * @throws IOException
     */
    @Test
    public void testGetSradMin() throws IOException
    {
        map.parseFile();
        double minimum = 874.0;
        double actual = map.getSradMin().getValue();
        Assert.assertEquals("An error occurred or calculation failed", minimum, actual, 0.01);

    }

    /**
     * Test for getSradTotal()
     * 
     * @throws IOException
     */
    @Test
    public void testGetSradTotal() throws IOException
    {
        map.parseFile();
        double total = 49741.0;
        double actual = map.getSradTotal().getValue();
        Assert.assertEquals("", total, actual, 0.01);

    }

    /**
     * Test for getTa9mAverage()
     * 
     * @throws IOException
     */
    @Test
    public void testGetTa9mAverage() throws IOException
    {
        map.parseFile();
        double average = 31.7;
        double actual = map.getTa9mAverage().getValue();
        Assert.assertEquals("Calculation failure", average, actual, 0.1);

    }

    /**
     * Test for getTa9mMax()
     * 
     * @throws IOException
     */
    @Test
    public void testGetTa9mMax() throws IOException
    {
        map.parseFile();
        double max = 34.9;
        double actual = map.getTa9mMax().getValue();
        Assert.assertEquals("Calculation error", max, actual, 0.01);

    }

    /**
     * Test for getTa9mMin()
     * 
     * @throws IOException
     */
    @Test
    public void testGetTa9mMin() throws IOException
    {
        map.parseFile();
        double min = 22.5;
        double actual = map.getTa9mMin().getValue();
        Assert.assertEquals("Calculation error", min, actual, 0.01);

    }

    /**
     * Test for getTairAverage()
     * 
     * @throws IOException
     */
    @Test
    public void testGetTairAverage() throws IOException
    {
        map.parseFile();
        double average = 32.0;
        double actual = map.getTairAverage().getValue();
        Assert.assertEquals("Calculation error", average, actual, 0.01);

    }

    /**
     * Test for getTairMax()
     * 
     * @throws IOException
     */
    @Test
    public void testGetTairMax() throws IOException
    {
        map.parseFile();
        double max = 36.5;
        double actual = map.getTairMax().getValue();
        Assert.assertEquals("Calculation failed", max, actual, 0.01);

    }

    /**
     * Test for getTairMin()
     * 
     * @throws IOException
     */
    @Test
    public void testGetTairMin() throws IOException
    {
        map.parseFile();
        double min = 22.5;
        double actual = map.getTa9mMin().getValue();
        Assert.assertEquals("Calculation failed", min, actual, 0.01);
    }

    /*
     * @Test public void testToString() { Assert.assertEquals(" ", map.toString());
     * }
     */

}
