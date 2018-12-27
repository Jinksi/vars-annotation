package org.mbari.m3.vars.annotation.ui.imageanno;

import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import org.mbari.m3.vars.annotation.model.Association;
import org.mbari.m3.vars.annotation.ui.shared.ImageViewExt;

import java.util.List;

/**
 * 1. Create
 * 2. Provide a toolbar UI for when a mode is selected to enable
 *    this widget
 * 3. Resize this layer when imageview is resized. Nodes should be
 *    scaled appropriately.
 * 4. Create appropriate association
 * 5. Display association if present in annotation
 */
public interface LayerController {

    AnchorPane getRoot();

    void draw(ImageViewExt imageViewExt, List<Association> associations, Color color);

    ToolBar getToolBar();

    void clear();

    void setDisable(boolean disable);

    boolean isDisabled();

}