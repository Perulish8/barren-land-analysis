package com.doughawley.barrenlandanalysis

class CellGrid {

    int width
    int height

    CellMetadata[][] cellMetadata

    CellGrid(int width, int height, Set<LandArea> barrenLandAreas) {
        this.width = width
        this.height = height
        this.cellMetadata = new CellMetadata[width][height]
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                this.cellMetadata[x][y] = new CellMetadata(landType: LandType.NORMAL, counted: false)
            }
        }
        for (LandArea barrenLandArea : barrenLandAreas) {
            for (int x = barrenLandArea.bottomLeft.x; x <= barrenLandArea.upperRight.x; x++) {
                for (int y = barrenLandArea.bottomLeft.y; y <= barrenLandArea.upperRight.y; y++) {
                    this.cellMetadata[x][y].landType = LandType.BARREN
                }
            }
        }
    }

    List<Cell> getCells() {
        List<Cell> cells = []
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells << new Cell(x, y)
            }
        }
        return cells
    }

    Set<Cell> getNeighboringCellsThatCanBeCounted(Cell cell) {

        Set<Cell> neighboringCells = [] as Set

        if (cell.x > 0) {
            if (cell.y > 0) {
                neighboringCells << new Cell(cell.x - 1, cell.y - 1)
            }
            neighboringCells << new Cell(cell.x - 1, cell.y)
            if (cell.y < height - 1) {
                neighboringCells << new Cell(cell.x - 1, cell.y + 1)
            }
        }
        if (cell.y > 0) {
            neighboringCells << new Cell(cell.x, cell.y - 1)
        }
        if (cell.y < height - 1) {
            neighboringCells << new Cell(cell.x, cell.y + 1)
        }
        if (cell.x < width - 1) {
            if (cell.y > 0) {
                neighboringCells << new Cell(cell.x + 1, cell.y - 1)
            }
            neighboringCells << new Cell(cell.x + 1, cell.y)
            if (cell.y < height - 1) {
                neighboringCells << new Cell(cell.x + 1, cell.y + 1)
            }
        }

        Set<Cell> filteredNeighboringCells = [] as Set
        for (Cell neighboringCell : neighboringCells) {
            if (canCount(neighboringCell)) {
                filteredNeighboringCells << neighboringCell
            }
        }

        return filteredNeighboringCells
    }

    boolean canCount(Cell cell) {
        CellMetadata cellMetadata = get(cell)
        return cellMetadata.landType == LandType.NORMAL && !cellMetadata.counted
    }

    LandType getLandType(Cell cell) {
        return get(cell).landType
    }

    boolean hasBeenCounted(Cell cell) {
        return get(cell).counted
    }

    void markAsCounted(Cell cell) {
        get(cell).counted = true
    }

    private CellMetadata get(Cell cell) {
        return this.cellMetadata[cell.x][cell.y]
    }

    private static class CellMetadata {
        LandType landType
        Boolean counted
    }
}
