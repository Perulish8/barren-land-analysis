package com.doughawley.barrenlandanalysis

import groovy.transform.ToString

@ToString(includePackage = false)
class FarmFertileLandAreaCalculator {

    CellGrid cellGrid

    FarmFertileLandAreaCalculator(CellGrid cellGrid) {
        this.cellGrid = cellGrid
    }

    List<Integer> calculate() {
        List<Integer> fertileLandAreas = []
        for (Cell cell : cellGrid.cells) {
            if (cellGrid.canCount(cell)) {
                fertileLandAreas << countCellAndNeighboringCellsThatCanBeCounted(cell)
            }
        }
        return fertileLandAreas.sort()
    }

    private int countCellAndNeighboringCellsThatCanBeCounted(Cell cell) {

        int count = 0

        cellGrid.markAsCounted(cell)
        count++

        Set<Cell> neighboringCellsToCount = cellGrid.getNeighboringCellsThatCanBeCounted(cell)
        while (neighboringCellsToCount) {

            Set<Cell> nextNeighboringCells = [] as Set

            for (Cell neighboringCell : neighboringCellsToCount) {
                if (cellGrid.canCount(neighboringCell)) {
                    cellGrid.markAsCounted(neighboringCell)
                    count++
                }
                nextNeighboringCells.addAll(cellGrid.getNeighboringCellsThatCanBeCounted(neighboringCell))
            }

            neighboringCellsToCount = nextNeighboringCells
        }

        return count
    }
}
