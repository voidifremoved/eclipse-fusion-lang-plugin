package com.rubberjam.fusion.eclipse.scanner;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import com.rubberjam.fusion.eclipse.editor.ColorManager;

public class FusionScanner extends RuleBasedScanner {

    private static final String[] KEYWORDS = {
        "abstract", "base", "bool", "break", "byte", "case", "class", "const",
        "continue", "default", "do", "double", "else", "enum", "false", "float",
        "for", "foreach", "if", "in", "int", "internal", "is", "lock", "long",
        "native", "new", "nint", "null", "override", "protected", "public",
        "resource", "return", "sbyte", "sealed", "short", "static", "string",
        "switch", "this", "throw", "throws", "true", "uint", "ushort", "virtual",
        "void", "when", "while", "assert"
    };

    public FusionScanner(ColorManager colorManager) {
        IToken keywordToken = new Token(new TextAttribute(colorManager.getColor(new RGB(127, 0, 85)), null, SWT.BOLD));
        IToken stringToken = new Token(new TextAttribute(colorManager.getColor(new RGB(42, 0, 255))));
        IToken commentToken = new Token(new TextAttribute(colorManager.getColor(new RGB(63, 127, 95))));
        IToken defaultToken = new Token(new TextAttribute(colorManager.getColor(new RGB(0, 0, 0))));

        IRule[] rules = new IRule[5];

        rules[0] = new EndOfLineRule("//", commentToken);
        rules[1] = new MultiLineRule("/*", "*/", commentToken);
        rules[2] = new SingleLineRule("\"", "\"", stringToken, '\\');
        rules[3] = new SingleLineRule("'", "'", stringToken, '\\');

        WordRule wordRule = new WordRule(new IWordDetector() {
            @Override
            public boolean isWordStart(char c) {
                return Character.isJavaIdentifierStart(c);
            }

            @Override
            public boolean isWordPart(char c) {
                return Character.isJavaIdentifierPart(c);
            }
        }, defaultToken);

        for (String keyword : KEYWORDS) {
            wordRule.addWord(keyword, keywordToken);
        }
        rules[4] = wordRule;

        setRules(rules);
    }
}
