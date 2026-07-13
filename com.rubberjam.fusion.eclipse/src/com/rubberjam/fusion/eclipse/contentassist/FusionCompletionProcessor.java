package com.rubberjam.fusion.eclipse.contentassist;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;

public class FusionCompletionProcessor implements IContentAssistProcessor {

    private static final String[] PROPOSALS = {
        "abstract", "base", "bool", "break", "byte", "case", "class", "const",
        "continue", "default", "do", "double", "else", "enum", "false", "float",
        "for", "foreach", "if", "in", "int", "internal", "is", "lock", "long",
        "native", "new", "nint", "null", "override", "protected", "public",
        "resource", "return", "sbyte", "sealed", "short", "static", "string",
        "switch", "this", "throw", "throws", "true", "uint", "ushort", "virtual",
        "void", "when", "while", "assert"
    };

    @Override
    public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int offset) {
        String text = viewer.getDocument().get();
        int start = offset;
        while (start > 0 && Character.isJavaIdentifierPart(text.charAt(start - 1))) {
            start--;
        }
        String prefix = text.substring(start, offset);

        List<ICompletionProposal> proposals = new ArrayList<>();
        for (String proposal : PROPOSALS) {
            if (proposal.startsWith(prefix)) {
                proposals.add(new CompletionProposal(proposal, start, offset - start, proposal.length()));
            }
        }
        return proposals.toArray(new ICompletionProposal[0]);
    }

    @Override
    public IContextInformation[] computeContextInformation(ITextViewer viewer, int offset) {
        return null;
    }

    @Override
    public char[] getCompletionProposalAutoActivationCharacters() {
        return new char[] { '.' };
    }

    @Override
    public char[] getContextInformationAutoActivationCharacters() {
        return null;
    }

    @Override
    public String getErrorMessage() {
        return null;
    }

    @Override
    public IContextInformationValidator getContextInformationValidator() {
        return null;
    }
}
