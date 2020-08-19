package br.com.elo7.marsexplorer;

import br.com.elo7.marsexplorer.model.Direction;
import br.com.elo7.marsexplorer.model.Land;
import br.com.elo7.marsexplorer.model.Probe;
import br.com.elo7.marsexplorer.model.Size;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class MarsExplorerTemplateLoader implements TemplateLoader {
    @Override
    public void load() {
        Fixture.of(Probe.class).addTemplate("valid", new Rule() {{
            add("direction", random(Direction.class));
        }});

        Fixture.of(Land.class).addTemplate("valid", new Rule() {{
            add("size", one(Size.class, "valid"));
        }});

        Fixture.of(Size.class).addTemplate("valid", new Rule() {{
            add("x", random(Integer.class, range(1, 999)));
            add("y", random(Integer.class, range(1, 999)));
        }});
    }
}
