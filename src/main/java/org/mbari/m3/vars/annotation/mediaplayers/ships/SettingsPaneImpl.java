package org.mbari.m3.vars.annotation.mediaplayers.ships;

import javafx.scene.layout.Pane;
import org.mbari.m3.vars.annotation.mediaplayers.SettingsPane;

/**
 * @author Brian Schlining
 * @since 2017-12-29T09:48:00
 */
public class SettingsPaneImpl implements SettingsPane {
    @Override
    public String getName() {
        return "Real-time (Ship)";
    }

    @Override
    public Pane getPane() {
        return null;
    }

    @Override
    public void load() {

    }

    @Override
    public void save() {

    }
}
