package com.doughawley.barrenlandanalysis

import spock.lang.Specification

class MainIntegrationSpec extends Specification {

    ByteArrayOutputStream output = new ByteArrayOutputStream()
    ByteArrayOutputStream error = new ByteArrayOutputStream()

    def setup() {
        System.setOut(new PrintStream(output))
        System.setErr(new PrintStream(error))
    }

    def "main - invalid - 0 arguments"() {

        when:
        Main.main([] as String[])

        then:
        output.toString('UTF-8') == ''
        error.toString('UTF-8') == 'Expected 1 argument, but received 0\n'
    }

    def "main - invalid - 2 arguments"() {

        when:
        Main.main(['arg1', 'arg2'] as String[])

        then:
        output.toString('UTF-8') == ''
        error.toString('UTF-8') == 'Expected 1 argument, but received 2\n'
    }

    def "main - valid with arguments {\"0 292 399 307\"}"() {

        when:
        Main.main(['{"0 292 399 307"}'] as String[])

        then:
        output.toString('UTF-8') == '116800 116800\n'
        error.toString('UTF-8') == ''
    }

    def "main - valid with arguments {\"48 192 351 207\", \"48 392 351 407\", \"120 52 135 547\", \"260 52 275 547\"}"() {

        when:
        Main.main(['{"48 192 351 207", "48 392 351 407", "120 52 135 547", "260 52 275 547"}'] as String[])

        then:
        output.toString('UTF-8') == '22816 192608\n'
        error.toString('UTF-8') == ''
    }
}
