package com.rubberjam.fusion.eclipse.scanner;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.swt.SWT;

import com.rubberjam.fusion.eclipse.FusionKeywords;
import com.rubberjam.fusion.eclipse.editor.ColorManager;
import com.rubberjam.fusion.eclipse.editor.FusionSyntaxColors;

public class FusionScanner extends RuleBasedScanner {

    public FusionScanner(ColorManager colorManager) {
        IToken keywordToken = new Token(new TextAttribute(colorManager.getColor(FusionSyntaxColors.keyword()), null, SWT.BOLD));
        IToken stringToken = new Token(new TextAttribute(colorManager.getColor(FusionSyntaxColors.stringLiteral())));
        IToken commentToken = new Token(new TextAttribute(colorManager.getColor(FusionSyntaxColors.comment())));
        IToken defaultToken = new Token(new TextAttribute(colorManager.getColor(FusionSyntaxColors.defaultText())));

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

        for (String keyword : FusionKeywords.KEYWORDS) {
            wordRule.addWord(keyword, keywordToken);
        }
        rules[4] = wordRule;

        setRules(rules);
    }
}
