package com.doughawley.barrenlandanalysis

import java.util.regex.Matcher
import java.util.regex.Pattern

class CommandLineParser {

    private static final String REGEX = '([0-9]{1,3})\\s([0-9]{1,3})\\s([0-9]{1,3})\\s([0-9]{1,3})+'

    Set<LandArea> parse(String argument) {

        Set<LandArea> landAreas = [] as Set

        Matcher matcher = Pattern.compile(REGEX).matcher(argument)

        if (!matcher.find()) {
            throw new IllegalArgumentException("Command line argument '${argument}' does not match regex ${REGEX}")
        } else {
            landAreas << new LandArea(
                    new Cell(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2))),
                    new Cell(Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4))))
        }

        while (matcher.find()) {
            landAreas << new LandArea(
                    new Cell(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2))),
                    new Cell(Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4))))
        }

        return landAreas
    }

}
