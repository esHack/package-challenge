package com.mobiquity.packer.service;

import com.mobiquity.packer.util.PackageProcessor;
import com.mobiquity.packer.exception.APIException;
import com.mobiquity.packer.model.Package;
import com.mobiquity.packer.model.Thing;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Main service to read the data from the file and process the packages
 * and return list of best suitable items for each package
 *
 */
public class PackerService {

    /**
     *
     * @param filePath file path which has the list of packages
     * @return list of suitable items in each package
     * @throws APIException exception in case of file read errors/ invalid input
     */
    public List<String> pack(String filePath) throws APIException {

        FileReader fileReader = new FileReader();
        List<String> results = new ArrayList();
        //read the data from the file
        List<Package> packages= fileReader.read(filePath);
        //sort items based on weight
        packages.forEach(s -> s.getThings().sort(Comparator.comparing(Thing::getWeight)));
        //process each package and get the list of suitable items
        //if no suitable items found return -
        for (Package pack : packages){
            String result = PackageProcessor.processPackage(pack);
            if(result.isEmpty())
                result="-";
            results.add(result);
        }

        return results;
    }

}
