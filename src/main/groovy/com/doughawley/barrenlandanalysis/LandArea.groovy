package com.doughawley.barrenlandanalysis

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString(includePackage = false)
class LandArea {

    Cell bottomLeft
    Cell upperRight

    LandArea(Cell bottomLeft, Cell upperRight) {
        if (null == bottomLeft) {
            throw new IllegalStateException("Cell bottomLeft must not be null")
        }
        this.bottomLeft = bottomLeft
        if (null == upperRight) {
            throw new IllegalStateException("Cell upperRight must not be null")
        }
        this.upperRight = upperRight

        if (bottomLeft.x > upperRight.x) {
            throw new IllegalStateException("Cell bottomLeft ${bottomLeft} has an x coordinate greater than Cell upperRight ${upperRight}")
        }

        if (bottomLeft.y > upperRight.y) {
            throw new IllegalStateException("Cell bottomLeft ${bottomLeft} has a y coordinate greater than Cell upperRight ${upperRight}")
        }
    }
}
