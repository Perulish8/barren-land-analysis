package com.doughawley.barrenlandanalysis

import spock.lang.Specification

class CellSpec extends Specification {

    def "constructor"() {

        when:
        Cell result = new Cell(5, 7)

        then:
        result.x == 5
        result.y == 7
    }
}
