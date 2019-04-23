package com.mobiquity.packer.service;

import com.mobiquity.packer.exception.APIException;
import com.mobiquity.packer.model.Package;
import com.mobiquity.packer.model.Thing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * Class to handle file read operations
 */
public class FileReader {

    private Pattern pattern = Pattern.compile("\\((\\d+),(\\d+\\.?\\d*?),€?(\\d+)\\)?");

    /**
     * Method to read the file from the path specified and parse it to list of packages
     *
     * @param filePath file path
     * @return list of packages from the file
     * @throws APIException in case of any exceptions
     */
    public List<Package> read(String filePath) throws APIException {

        List<Package> packages = new ArrayList<>();
        try {
            for (String readLine : Files.readAllLines(Paths.get(filePath))) {
                packages.add(formPackage(readLine));
            }
        } catch (IOException e) {
            throw new APIException("Error while reading the file", e);
        }
        return packages;
    }

    /**
     *
     * Method to parse each line and transform it to package
     * @param line each line from the file
     * @return Package from the input data
     * @throws APIException
     */
    public Package formPackage(String line) throws APIException {
        Package aPackage = new Package();
        try {
            List<Thing> things = new ArrayList<>();
            //Multiply with 100 support int in algorithm
            aPackage.setMaxWeight(Integer.parseInt(line.split(":")[0].trim()) * 100);

            Matcher matcher = pattern.matcher(line.split(":")[1].replace(" ", ""));
            while (matcher.find()) {
                int id = Integer.valueOf(matcher.group(1));
                int weight = (int) (Double.parseDouble(matcher.group(2)) * 100);
                int cost = (int) (Double.parseDouble(matcher.group(3)));
                Thing thing = new Thing(id, weight, cost);
                things.add(thing);
            }
            aPackage.setThings(things);
        }catch (Exception ex){
            throw new APIException("Invalid input format, correct the input file ", ex );
        }
        return aPackage;
    }
}
