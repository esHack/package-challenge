package com.mobiquity.packer.service;

import com.mobiquity.packer.exception.APIException;
import com.mobiquity.packer.model.Package;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FileReaderTest {

    @Test
    public void formPackageGivenAnInputString() throws APIException {

        FileReader fileReader = new FileReader();
        String line = "81: (1,53.38,€45) (2,88.62,€98)";
        Package aPackage = fileReader.formPackage(line);
        assertEquals(aPackage.getThings().get(0).getId(),1);
    }

    @Test(expected =APIException.class)
    public void throwExceptionGivenAnInvalidInputString() throws APIException {

        FileReader fileReader = new FileReader();
        String line = "81q: (1,53.38,€45) (2,88.62,€98)";
        Package aPackage = fileReader.formPackage(line);
        assertEquals(aPackage.getThings().get(0).getId(),1);
    }
}