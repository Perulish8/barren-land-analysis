package com.doughawley.barrenlandanalysis

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString(includePackage = false)
class Cell {

    int x
    int y

    Cell(int x, int y) {
        this.x = x
        this.y = y
    }
}
