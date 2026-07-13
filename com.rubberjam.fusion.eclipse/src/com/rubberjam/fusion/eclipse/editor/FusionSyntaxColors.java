package com.rubberjam.fusion.eclipse.editor;

import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * Syntax colors that adapt to the current SWT light/dark theme.
 */
public final class FusionSyntaxColors {

    private FusionSyntaxColors() {
    }

    public static RGB keyword() {
        if (isDarkTheme()) {
            return new RGB(204, 120, 192);
        }
        return new RGB(127, 0, 85);
    }

    public static RGB stringLiteral() {
        if (isDarkTheme()) {
            return new RGB(106, 171, 115);
        }
        return new RGB(42, 0, 255);
    }

    public static RGB comment() {
        if (isDarkTheme()) {
            return new RGB(128, 128, 128);
        }
        return new RGB(63, 127, 95);
    }

    public static RGB defaultText() {
        if (isDarkTheme()) {
            return new RGB(187, 187, 187);
        }
        return new RGB(0, 0, 0);
    }

    private static boolean isDarkTheme() {
        Display display = Display.getCurrent();
        if (display == null) {
            return false;
        }
        return display.isSystemDarkTheme();
    }
}
