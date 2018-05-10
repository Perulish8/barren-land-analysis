package com.doughawley.barrenlandanalysis

import spock.lang.Specification

class CommandLineParserSpec extends Specification {

    CommandLineParser parser = new CommandLineParser()

    def "parse - invalid"() {

        when:
        parser.parse("{\"0 399 307\"}")

        then:
        IllegalArgumentException exception = thrown()
        exception.message == 'Command line argument \'{"0 399 307"}\' does not match regex ([0-9]{1,3})\\s([0-9]{1,3})\\s([0-9]{1,3})\\s([0-9]{1,3})+'
    }

    def "parse - single"() {

        when:
        Set<LandArea> result = parser.parse('{"0 292 399 307"}')

        then:
        result.size() == 1
        result.contains(new LandArea(new Cell(0, 292), new Cell(399, 307)))
    }

    def "parse - multiple"() {

        when:
        Set<LandArea> result = parser.parse('{"48 192 351 207", "48 392 351 407", "120 52 135 547", "260 52 275 547"}')

        then:
        result.size() == 4
        result.contains(new LandArea(new Cell(48, 192), new Cell(351, 207)))
        result.contains(new LandArea(new Cell(48, 392), new Cell(351, 407)))
        result.contains(new LandArea(new Cell(120, 52), new Cell(135, 547)))
        result.contains(new LandArea(new Cell(260, 52), new Cell(275, 547)))
    }
}
