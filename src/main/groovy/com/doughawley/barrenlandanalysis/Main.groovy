package com.doughawley.barrenlandanalysis

class Main {

    static void main(String[] args) {

        try {
            if (args.length != 1) {
                throw new IllegalArgumentException("Expected 1 argument, but received ${args.length}")
            }

            CommandLineParser parser = new CommandLineParser()
            Set<LandArea> landAreas = parser.parse(args[0])

            CellGrid cellGrid = new CellGrid(400, 600, landAreas)
            List<Integer> fertileLandAreas = new FarmFertileLandAreaCalculator(cellGrid).calculate()

            println fertileLandAreas.join(' ')

        } catch (Exception e) {
            System.err.println(e.getMessage())
        }
    }
}
