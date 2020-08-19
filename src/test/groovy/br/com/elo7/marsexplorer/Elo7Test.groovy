package br.com.elo7.marsexplorer

import br.com.elo7.marsexplorer.validation.MarsExplorerValidator
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader
import spock.lang.Specification

class Elo7Test extends Specification {
    def validator = new MarsExplorerValidator();

    def setup() {
        FixtureFactoryLoader.loadTemplates("br.com.elo7.marsexplorer")
    }
}
