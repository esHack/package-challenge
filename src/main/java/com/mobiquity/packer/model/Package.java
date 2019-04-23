package com.mobiquity.packer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Package {

    private int maxWeight;
    private List<Thing> things;

}
