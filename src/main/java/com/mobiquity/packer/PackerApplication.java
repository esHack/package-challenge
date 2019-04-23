package com.mobiquity.packer;

import com.mobiquity.packer.exception.APIException;
import com.mobiquity.packer.service.PackerService;

import java.io.File;

public class PackerApplication {

    public static void main(String[] args) {

        PackerService packerService = new PackerService();
        try {
            packerService.pack(new File(PackerApplication.class.getClassLoader().
                    getResource("input.txt").getFile()).getPath()).forEach(System.out::println);

        } catch (APIException e) {
            e.printStackTrace();
        }
    }
}
