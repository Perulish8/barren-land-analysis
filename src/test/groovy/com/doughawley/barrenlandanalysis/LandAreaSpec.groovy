package com.doughawley.barrenlandanalysis

import spock.lang.Specification
import spock.lang.Unroll

class LandAreaSpec extends Specification {

    @Unroll("constructor with bottomLeft #bottomLeft, upperRight #upperRight")
    def "constructor"() {

        when:
        LandArea result = new LandArea(bottomLeft, upperRight)

        then:
        result.bottomLeft == bottomLeft
        result.upperRight == upperRight

        where:
        bottomLeft     | upperRight     | expectedMessage
        new Cell(5, 5) | new Cell(5, 7) | "Cell bottomLeft Cell(5, 5) has an x coordinate greater than Cell upperRight Cell(5, 7)"
        new Cell(5, 5) | new Cell(5, 7) | "Cell bottomLeft Cell(5, 5) has an x coordinate greater than Cell upperRight Cell(5, 7)"
        new Cell(3, 3) | new Cell(5, 3) | "Cell bottomLeft Cell(3, 3) has a y coordinate greater than Cell upperRight Cell(5, 3)"
    }

    @Unroll("constructor - invalid with bottomLeft #bottomLeft, upperRight #upperRight")
    def "constructor - invalid"(Cell bottomLeft, Cell upperRight, String expectedMessage) {

        when:
        new LandArea(bottomLeft, upperRight)

        then:
        IllegalStateException exception = thrown()
        exception.message == expectedMessage

        where:
        bottomLeft     | upperRight     | expectedMessage
        null           | new Cell(5, 5) | "Cell bottomLeft must not be null"
        new Cell(5, 5) | null           | "Cell upperRight must not be null"
        new Cell(5, 5) | new Cell(3, 7) | "Cell bottomLeft Cell(5, 5) has an x coordinate greater than Cell upperRight Cell(3, 7)"
        new Cell(3, 3) | new Cell(5, 2) | "Cell bottomLeft Cell(3, 3) has a y coordinate greater than Cell upperRight Cell(5, 2)"
    }
}
