package com.mobiquity.packer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Thing {

    private int id;
    private int weight;
    private int cost;
}
