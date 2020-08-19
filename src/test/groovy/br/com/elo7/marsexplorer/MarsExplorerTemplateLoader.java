package br.com.elo7.marsexplorer;

import br.com.elo7.marsexplorer.model.Direction;
import br.com.elo7.marsexplorer.model.Probe;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class MarsExplorerTemplateLoader implements TemplateLoader {
    @Override
    public void load() {
        Fixture.of(Probe.class).addTemplate("valid", new Rule() {{
            add("direction", random(Direction.class));
        }});
    }
}
