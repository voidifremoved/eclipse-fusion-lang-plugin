package com.rubberjam.fusion.eclipse.editor;

import org.eclipse.ui.editors.text.TextEditor;

public class FusionEditor extends TextEditor {

    private ColorManager colorManager;

    public FusionEditor() {
        super();
        colorManager = new ColorManager();
        setSourceViewerConfiguration(new FusionSourceViewerConfiguration(colorManager));
    }

    @Override
    public void dispose() {
        colorManager.dispose();
        super.dispose();
    }
}
