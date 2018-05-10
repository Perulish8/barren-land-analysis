package com.doughawley.barrenlandanalysis

import spock.lang.Specification
import spock.lang.Unroll

class CellGridSpec extends Specification {

    def "constructor - with no barren lands"() {

        when:
        CellGrid cellGrid = new CellGrid(2, 2, [] as Set)

        then:
        cellGrid.getLandType(new Cell(0, 0)) == LandType.NORMAL
        !cellGrid.hasBeenCounted(new Cell(0, 0))
        cellGrid.getLandType(new Cell(0, 1)) == LandType.NORMAL
        !cellGrid.hasBeenCounted(new Cell(0, 1))
        cellGrid.getLandType(new Cell(1, 0)) == LandType.NORMAL
        !cellGrid.hasBeenCounted(new Cell(1, 0))
        cellGrid.getLandType(new Cell(1, 1)) == LandType.NORMAL
        !cellGrid.hasBeenCounted(new Cell(1, 1))
    }

    def "constructor - with barren lands"() {

        LandArea barrenLandArea = new LandArea(new Cell(0, 1), new Cell(1, 1))

        when:
        CellGrid cellGrid = new CellGrid(2, 2, [barrenLandArea] as Set)

        then:
        cellGrid.getLandType(new Cell(0, 0)) == LandType.NORMAL
        !cellGrid.hasBeenCounted(new Cell(0, 0))
        cellGrid.canCount(new Cell(0, 0))
        cellGrid.getLandType(new Cell(0, 1)) == LandType.BARREN
        !cellGrid.hasBeenCounted(new Cell(0, 1))
        !cellGrid.canCount(new Cell(0, 1))
        cellGrid.getLandType(new Cell(1, 0)) == LandType.NORMAL
        !cellGrid.hasBeenCounted(new Cell(1, 0))
        cellGrid.canCount(new Cell(1, 0))
        cellGrid.getLandType(new Cell(1, 1)) == LandType.BARREN
        !cellGrid.hasBeenCounted(new Cell(1, 1))
        !cellGrid.canCount(new Cell(1, 1))

        when:
        cellGrid.markAsCounted(new Cell(0, 0))
        cellGrid.markAsCounted(new Cell(0, 1))
        cellGrid.markAsCounted(new Cell(1, 0))
        cellGrid.markAsCounted(new Cell(1, 1))

        then:
        cellGrid.hasBeenCounted(new Cell(0, 0))
        !cellGrid.canCount(new Cell(0, 0))
        cellGrid.hasBeenCounted(new Cell(0, 1))
        !cellGrid.canCount(new Cell(0, 1))
        cellGrid.hasBeenCounted(new Cell(1, 0))
        !cellGrid.canCount(new Cell(1, 0))
        cellGrid.hasBeenCounted(new Cell(1, 1))
        !cellGrid.canCount(new Cell(1, 1))
    }

    def "getCells"() {

        CellGrid cellGrid = new CellGrid(2, 2, [] as Set)

        when:
        List<Cell> results = cellGrid.getCells()

        then:
        results.size() == 4
        results[0] == new Cell(0, 0)
        results[1] == new Cell(0, 1)
        results[2] == new Cell(1, 0)
        results[3] == new Cell(1, 1)
    }

    def "getNeighboringCellsThatCanBeCounted - middle"() {

        CellGrid cellGrid = new CellGrid(3, 3, [] as Set)

        when:
        Set<Cell> result = cellGrid.getNeighboringCellsThatCanBeCounted(new Cell(1, 1))

        then:
        result.size() == 8
        result.contains(new Cell(0, 1))
        result.contains(new Cell(0, 2))
        result.contains(new Cell(1, 2))
        result.contains(new Cell(2, 2))
        result.contains(new Cell(2, 1))
        result.contains(new Cell(2, 0))
        result.contains(new Cell(1, 0))
    }

    @Unroll("getNeighboringCellsThatCanBeCounted on corners with cell #cell")
    def "getNeighboringCellsThatCanBeCounted on corners"() {

        CellGrid cellGrid = new CellGrid(3, 3, [] as Set)

        when:
        Set<Cell> result = cellGrid.getNeighboringCellsThatCanBeCounted(cell)

        then:
        result.size() == 3
        for (Cell neighboringCell : neighboringCells) {
            assert result.contains(neighboringCell)
        }

        where:
        cell           | neighboringCells
        new Cell(0, 0) | [new Cell(0, 1), new Cell(1, 1), new Cell(1, 0)]
        new Cell(0, 2) | [new Cell(1, 2), new Cell(1, 1), new Cell(0, 1)]
        new Cell(2, 2) | [new Cell(1, 2), new Cell(1, 1), new Cell(2, 1)]
        new Cell(2, 0) | [new Cell(1, 0), new Cell(1, 1), new Cell(2, 1)]
    }

    @Unroll("getNeighboringCellsThatCanBeCounted on edges with cell #cell")
    def "getNeighboringCellsThatCanBeCounted on edges"() {

        CellGrid cellGrid = new CellGrid(3, 3, [] as Set)

        when:
        Set<Cell> result = cellGrid.getNeighboringCellsThatCanBeCounted(cell)

        then:
        result.size() == 5
        for (Cell neighboringCell : neighboringCells) {
            assert result.contains(neighboringCell)
        }

        where:
        cell           | neighboringCells
        new Cell(0, 1) | [new Cell(0, 2), new Cell(1, 2), new Cell(1, 1), new Cell(1, 0), new Cell(0, 0)]
        new Cell(1, 2) | [new Cell(2, 2), new Cell(2, 1), new Cell(1, 1), new Cell(0, 1), new Cell(0, 2)]
        new Cell(2, 1) | [new Cell(2, 0), new Cell(1, 0), new Cell(1, 1), new Cell(1, 2), new Cell(2, 2)]
        new Cell(1, 0) | [new Cell(0, 0), new Cell(0, 1), new Cell(1, 1), new Cell(2, 1), new Cell(2, 0)]
    }

    def "getNeighboringCellsThatCanBeCounted - middle - with some barren"() {

        LandArea barrenLandArea = new LandArea(new Cell(2, 1), new Cell(2, 2))
        CellGrid cellGrid = new CellGrid(3, 3, [barrenLandArea] as Set)

        when:
        Set<Cell> result = cellGrid.getNeighboringCellsThatCanBeCounted(new Cell(1, 1))

        then:
        result.size() == 6
        result.contains(new Cell(0, 1))
        result.contains(new Cell(0, 2))
        result.contains(new Cell(1, 2))
        result.contains(new Cell(2, 0))
        result.contains(new Cell(1, 0))
    }
}