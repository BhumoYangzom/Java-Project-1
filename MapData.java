import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
//import java.util.Arrays;
//import java.util.Collections;

/**
 * MapData class reads a file and produces the maximums, minimums, and averages
 * for the variables srad, tair, ta9m. It also produces the total for srad.
 * 
 * @author Yangzomd Dolma
 *
 */
public class MapData
{
    /**
     * The final size of the arrays for srad, tair, and ta9m.
     */
    private final int SIZE = 1000;
    /**
     * The number of stations.
     */
    // private static final int NUMBER_OF_MISSING_OBSERVATIONS = 10;
    /**
     * The index number for the station id in an array.
     */
    private final int STID_POSITION = 0;
    /**
     * The index number for the air temperature data in an array.
     */
    private final int T_AIR_POSITION = 4;
    /**
     * The index number for the solar radiation data in an array.
     */
    private final int S_RAD_POSITION = 13;
    /**
     * The index number for the air temperature at 9 meters in an array.
     */
    private final int TA9M_POSITION = 14;
    /**
     * The name for the station.
     */
    private final String MESONET = "Mesonet";
    /**
     * Stores the solar radiation data values from the file.
     */

    Observation[] sradData;
    /**
     * Stores the air temperature data values from the file.
     */
    Observation[] tairData;
    /**
     * Stores the air temperature at 9 meters from the file.
     */
    Observation[] ta9mData;
    /**
     * The number of stations.
     */
    // private Integer numberOfStations = null;
    /**
     * The name for the directory.
     */
    String directory;
    /**
     * The minimum air temperature value.
     */
    Observation tairMin;
    /**
     * The maximum value for air temperature.
     */
    Observation tairMax;
    /**
     * The average value for air temperature.
     */
    Observation tairAverage;
    /**
     * The minimum value for air temperature at 9 meters.
     */
    Observation ta9mMin;
    /**
     * The maximum value for air temperature at 9 meters.
     */
    Observation ta9mMax;
    /**
     * The average value for air temperature at 9 meters.
     */
    Observation ta9mAverage;
    /**
     * The minimum value for solar radiation.
     */
    Observation sradMin;
    /**
     * The maximum value for solar radiation.
     */
    Observation sradMax;
    /**
     * The average value for solar radiation.
     */
    Observation sradAverage;
    /**
     * The total for solar radiation.
     */
    Observation sradTotal;
    /**
     * The year when the file was created.
     */
    private int year;
    /**
     * The month of file creation.
     */
    private int month;
    /**
     * The day of file creation.
     */
    private int day;
    /**
     * The hour of file creation.
     */
    private int hour;
    /**
     * The minute of file creation.
     */
    private int minute;
    /**
     * An observation object.
     */
    Observation temp;

    /**
     * MapData constructor taking in all the fields.
     * 
     * @param year      The year of file name
     * @param month     The year of the file name.
     * @param day       The day of the file name creation.
     * @param hour      The hour of the file name.
     * @param minute    The minute of the file creation.
     * @param directory The directory where the file is found.
     */

    public MapData(int year, int month, int day, int hour, int minute, String directory)
    {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.directory = directory;
        sradData = new Observation[SIZE];
        tairData = new Observation[SIZE];
        ta9mData = new Observation[SIZE];

    }

    /**
     * The year of file creation.
     * 
     * @return the year.
     */

    public int getYear()
    {
        return this.year;
    }

    /**
     * The month of the file creation.
     * 
     * @return the month.
     */

    public int getMonth()
    {
        return this.month;
    }

    /**
     * The day of the file creation.
     * 
     * @return the day.
     */

    public int getDay()
    {
        return this.day;
    }

    /**
     * The hour of file creation.
     * 
     * @return the hour.
     */

    public int getHour()
    {
        return this.hour;
    }

    /**
     * @return the directory name where the file is found.
     */

    public String getDirectory()
    {
        return this.directory;
    }

    /**
     * 
     * @return Filename that follows the format: yyyyMMddHHmm.mdf. For example a
     *         file name of 201808010700.mdf is formed of year= 2018, month = 08,
     *         day = 01, hour = 07 and minute = 00.
     */

    public String createFileName()
    {
        // changing int types for year, month, day, hour and minute to string.
        // String year = Integer.toString(this.year);
        // String month = Integer.toString(this.month);
        // String day = Integer.toString(this.day);
        // String hour = Integer.toString(this.hour);
        // String minute = Integer.toString(this.minute);

        // filename will be make of YearDayHourMinute in their numerical values.

        return String.format("%s%s%s%s%s.mdf", Integer.toString(this.year), String.format("0%d", month),
                Integer.toString(this.day), Integer.toString(this.hour), Integer.toString(this.minute));

    }

    /**
     * The parseFile method reads through the lines of data in a file, stores each
     * line in an array and splits the line.
     * 
     * @throws IOException
     */
    public void parseFile() throws IOException
    {
        // stores the filename
        String FileName = createFileName();
        // String FileName = "201808010700.mdf";

        @SuppressWarnings("resource")
        BufferedReader br = new BufferedReader(new FileReader(FileName));
        // throw out the first three lines.
        br.readLine();
        br.readLine();
        br.readLine();

        // counts the number of lines of data read.
        int count = 0;

        // three observation objects are created.

        // loops through the line of data and store them in the appropriate array.
        while (br.readLine() != null)
        {
            String line = br.readLine();
            // splits the line of data and stores in an array tokens
            String[] tokens = line.trim().split("\\s+");
            Observation srad = new Observation(Double.parseDouble(tokens[S_RAD_POSITION]), tokens[STID_POSITION]);
            sradData[count] = srad;
            Observation tair = new Observation(Double.parseDouble(tokens[T_AIR_POSITION]),
                    tokens[STID_POSITION].toString());
            tairData[count] = tair;
            Observation ta9m = new Observation(Double.parseDouble(tokens[TA9M_POSITION]),
                    tokens[STID_POSITION].toString());
            ta9mData[count] = ta9m;

            count++;
        }
        Observation[] newArray1 = new Observation[count];
        Observation[] newArray2 = new Observation[count];
        Observation[] newArray3 = new Observation[count];

        for (int i = 0; i < count; ++i)
        {
            newArray1[i] = sradData[i];
            newArray2[i] = tairData[i];
            newArray3[i] = ta9mData[i];
        }
        sradData = newArray1;
        tairData = newArray2;
        ta9mData = newArray3;

    }

    /**
     * @return The average of the solar radiation.
     */

    public Observation getSradAverage()
    {

        double average = 0.0;
        double sradTotal = 0.0;

        for (int i = 0; i < sradData.length; ++i)
        {
            Observation data = sradData[i];
            if (data.isValid())
            {
                sradTotal += data.getValue();
            }

        }
//System.out.println("This is srad total: " +sradTotal);
        average = sradTotal / sradData.length;
        temp = new Observation(average, MESONET);
        this.sradAverage = temp;

        return this.sradAverage;
    }

    /**
     * Looks for the maximum value from the data for solar radiation.
     * 
     * @return The maximum value for solar radiation.
     * @throws IOException
     */

    public Observation getSradMax()
    {
        Observation currentMax = sradData[0];

        for (int i = 0; i < sradData.length; ++i)
        {
            if (sradData[i].isValid() && sradData[i].getValue() > currentMax.getValue())
            {
                currentMax = sradData[i];
            }
        }
        this.sradMax = currentMax;

        return this.sradMax;
    }

    /**
     * Looks for the minimum value for solar radiation from the file.
     * 
     * @return The minimum value for solar radiation.
     * @throws IOException
     */

    public Observation getSradMin()
    {

        // int index = 0;

        double min = Double.MAX_VALUE;

        for (int i = 0; i < sradData.length; ++i)
        {
            Observation data = sradData[i];
            if (data.isValid() && data.getValue() < min)
            {
                temp = data;
            }
            this.sradMin = temp;
        }

        return this.sradMin;
    }

    /**
     * The sum of all the solar radiation values from the file.
     * 
     * @return The total value of the solar radiation.
     * @throws IOException
     */

    public Observation getSradTotal()
    {

        double sradSum = 0.0;

        for (int i = 0; i < sradData.length; ++i)
        {
            Observation data = sradData[i];
            if (data.isValid())
            {
                sradSum += data.getValue();
                temp = new Observation(sradSum, MESONET);

            }
        }
        this.sradTotal = temp;

        return this.sradTotal;
    }

    /**
     * Calculates the average value for the variable Ta9m in the file.
     * 
     * @return The average value of ta9m.
     */

    public Observation getTa9mAverage()
    {

        double average = 0.0;
        double ta9mTotal = 0.0;

        for (int i = 0; i < ta9mData.length; ++i)
        {
            Observation data = ta9mData[i];
            if (data.isValid())
            {
                ta9mTotal += ta9mData[i].getValue();
            }

        }

        average = ta9mTotal / ta9mData.length;
        temp = new Observation(average, MESONET);

        this.ta9mAverage = temp;

        return this.ta9mAverage;

    }

    /**
     * Looks for the maximum value for the ta9m variable in the file.
     * 
     * @return The maximum value for the ta9m variable.
     * @throws IOException
     */

    public Observation getTa9mMax()
    {

        Observation currentMax = ta9mData[0];

        for (int i = 0; i < ta9mData.length; ++i)
        {
            if (ta9mData[i].isValid() && ta9mData[i].getValue() > currentMax.getValue())
            {
                currentMax = ta9mData[i];
            }
        }
        this.ta9mMax = currentMax;

        return this.ta9mMax;
    }

    /**
     * Looks for the minimum value for the ta9m variable in the file.
     * 
     * @return The minimum value found for the ta9m variable.
     */

    public Observation getTa9mMin()
    {

        Observation currentMin = ta9mData[0];

        for (int i = 0; i < ta9mData.length; ++i)
        {
            if (ta9mData[i].isValid() && ta9mData[i].getValue() < currentMin.getValue())
            {
                currentMin = ta9mData[i];
            }
        }
        this.ta9mMin = currentMin;

        return this.ta9mMin;
    }

    /**
     * Calculates the average value for the air Temperature value in the file.
     * 
     * @return The average value for air temperature.
     * @throws IOException
     */

    public Observation getTairAverage()
    {

        double average = 0.0;
        double tairTotal = 0.0;
        for (int i = 0; i < tairData.length; ++i)
        {
            Observation data = tairData[i];
            if (data.isValid())
            {

                tairTotal += data.getValue();

            }
        }

        average = tairTotal / tairData.length;

        temp = new Observation(average, MESONET);
        this.tairAverage = temp;

        return this.tairAverage;

    }

    /**
     * Looks for the maximum value for the air Temperature value in the file.
     * 
     * @return The maximum value for the air temperature.
     * @throws IOException
     */

    public Observation getTairMax()
    {

        Observation currentMax = tairData[0];

        for (int i = 0; i < tairData.length; ++i)
        {
            if (tairData[i].isValid() && tairData[i].getValue() > currentMax.getValue())
            {
                currentMax = tairData[i];
            }
        }
        this.tairMax = currentMax;

        return this.tairMax;

    }

    /**
     * Looks for the minimum value for the air temperature in the file.
     * 
     * @return The minimum value for air temperature.
     * @throws IOException
     */

    public Observation getTairMin()
    {

        Observation currentMin = tairData[0];

        for (int i = 0; i < sradData.length; ++i)
        {
            if (tairData[i].isValid() && tairData[i].getValue() < currentMin.getValue())
            {
                currentMin = tairData[i];
            }
        }
        this.tairMin = currentMin;

        return this.tairMin;

    }

    /**
     * Find the statistics of air temperature including its maximum, the minimum,
     * and the average.
     */

    private void calculateAirTemperatureStatistics()
    {
        String output = String.format("========================================================\n"
                + "Maximum Air Temperature[1.5m] = %.1f C at %s\n" + "Minimum Air Temperature[1.5m] = %.1f C at %s\n"
                + "Average Air Temperature[1.5m] = %.1f C at %s\n"
                + "========================================================\n", getTairMax().getValue(),
                getTairMax().getStid(), getTairMin().getValue(), getTairMin().getStid(), getTairAverage().getValue(),
                MESONET);
        System.out.print(output);
        /*
         * String output = "========================================================" +
         * "\n"; output += "Maximum Air Temperature[1.5m] = " + getTairMax().getValue()
         * + " C at " + getTairMax().getStid() + "\n"; output +=
         * "Minimum Air Temperature[1.5m] = " + getTairMin().getValue() + " C at " +
         * getTairMin().getStid() + "\n"; output += "Average Air Temperature[1.5m] = " +
         * getTairAverage().getValue() + " C at " + MESONET + "\n"; output +=
         * "========================================================" + "\n";
         * System.out.print(output);
         */

    }

    /**
     * The statistics of air temperature at 9 meters including its maximum, minimum
     * and the average.
     */
    private void calculateTa9mTemperatureStatistics()
    {
        String output = String.format("========================================================\n"
                + "Maximum Air Temperature[9.0m] = %.1f C at %s\n" + "Minimum Air Temperature[9.0m] = %.1f C at %s\n"
                + "Average Air Temperature[9.0m] = %.1f C at %s\n"
                + "========================================================\n", getTa9mMax().getValue(),
                getTa9mMax().getStid(), getTa9mMin().getValue(), getTa9mMin().getStid(), getTa9mAverage().getValue(),
                MESONET);
        System.out.print(output);

    }

    /**
     * The statistics of solar radiation including its maximum, minimum and average.
     */

    private void calculateSolarRadiationStatistics()
    {
        String output = String.format(
                "========================================================\n"
                        + "Maximum Solar Radiation[1.5m] = %.1f W/m^2 at %s\n"
                        + "Minimum Solar Radiation[1.5m] = %.1f W/m^2 at %s\n"
                        + "Average Solar Radiation[1.5m] = %.1f W/m^2 at %s\n"
                        + "========================================================\n",
                getSradMax().getValue(), getSradMax().getStid(), getSradMin().getValue(), getSradMin().getStid(),
                getSradAverage().getValue(), MESONET);
        System.out.print(output);

    }

    /**
     * Changes the values to string.
     * 
     * @return A string value.
     */

    public String toString()
    {
        String output = "========================================================" + "\n";
        output += "=== " + this.year + "-" + this.month + "-" + this.hour + ":" + this.minute + "===";
        System.out.println(output);
        calculateAirTemperatureStatistics();
        calculateTa9mTemperatureStatistics();
        calculateSolarRadiationStatistics();
        String lastLine = " ";
        return lastLine;
    }

}
