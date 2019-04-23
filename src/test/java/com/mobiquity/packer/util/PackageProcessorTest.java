package com.mobiquity.packer.util;

import com.mobiquity.packer.model.Package;
import com.mobiquity.packer.model.Thing;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PackageProcessorTest {

    @Test
    public void processPackage() {

        Package aPackage = new Package();
        List<Thing> things = new ArrayList();
        things.add(new Thing(1,5338,45));
        things.add(new Thing(2,8862,98));
        things.add(new Thing(3,7848,3));
        things.add(new Thing(4,7230,76));
        things.add(new Thing(5,3018,9));
        things.add(new Thing(6,4634,48));
        aPackage.setMaxWeight(8100);
        aPackage.setThings(things);

        String result= PackageProcessor.processPackage(aPackage);
        assertEquals(result, "4");
    }
}