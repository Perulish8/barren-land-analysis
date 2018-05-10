package com.doughawley.barrenlandanalysis

import spock.lang.Specification

class FarmFertileLandAreaCalculatorIntegrationSpec extends Specification {

    def " use case 1"() {

        CellGrid cellGrid = new CellGrid(10, 10, [] as Set)
        FarmFertileLandAreaCalculator fertileLandAreaCalculator = new FarmFertileLandAreaCalculator(cellGrid)

        expect:
        fertileLandAreaCalculator.calculate() == [100]
    }

    def " use case 2"() {

        CellGrid cellGrid = new CellGrid(10, 10, [new LandArea(new Cell(0, 0), new Cell(9, 5))] as Set)
        FarmFertileLandAreaCalculator fertileLandAreaCalculator = new FarmFertileLandAreaCalculator(cellGrid)

        expect:
        fertileLandAreaCalculator.calculate() == [40]
    }

    def " use case 3"() {

        CellGrid cellGrid = new CellGrid(10, 10, [new LandArea(new Cell(0, 2), new Cell(9, 5))] as Set)
        FarmFertileLandAreaCalculator fertileLandAreaCalculator = new FarmFertileLandAreaCalculator(cellGrid)

        expect:
        fertileLandAreaCalculator.calculate() == [20, 40]
    }

    def " use case 4"() {

        CellGrid cellGrid = new CellGrid(10, 10, [new LandArea(new Cell(4, 0), new Cell(4, 9))] as Set)
        FarmFertileLandAreaCalculator fertileLandAreaCalculator = new FarmFertileLandAreaCalculator(cellGrid)

        expect:
        fertileLandAreaCalculator.calculate() == [40, 50]
    }

    def " use case 5"() {

        CellGrid cellGrid = new CellGrid(10, 10, [new LandArea(new Cell(4, 0), new Cell(4, 9)), new LandArea(new Cell(0, 4), new Cell(9, 4))] as Set)
        FarmFertileLandAreaCalculator fertileLandAreaCalculator = new FarmFertileLandAreaCalculator(cellGrid)

        expect:
        fertileLandAreaCalculator.calculate() == [16, 20, 20, 25]
    }


    def " use case 6"() {

        CellGrid cellGrid = new CellGrid(400, 600, [new LandArea(new Cell(0, 292), new Cell(399, 307))] as Set)
        FarmFertileLandAreaCalculator fertileLandAreaCalculator = new FarmFertileLandAreaCalculator(cellGrid)

        expect:
        fertileLandAreaCalculator.calculate() == [116800l, 116800l]
    }

    def "use case 7"() {

        Set<LandArea> barrenLandAreas = [] as Set
        barrenLandAreas << new LandArea(new Cell(48, 192), new Cell(351, 207))
        barrenLandAreas << new LandArea(new Cell(48, 392), new Cell(351, 407))
        barrenLandAreas << new LandArea(new Cell(120, 52), new Cell(135, 547))
        barrenLandAreas << new LandArea(new Cell(260, 52), new Cell(275, 547))

        CellGrid cellGrid = new CellGrid(400, 600, barrenLandAreas)
        FarmFertileLandAreaCalculator fertileLandAreaCalculator = new FarmFertileLandAreaCalculator(cellGrid)

        expect:
        fertileLandAreaCalculator.calculate() == [22816l, 192608l]
    }
}
