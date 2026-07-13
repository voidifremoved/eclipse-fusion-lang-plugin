package com.rubberjam.fusion.eclipse.editor;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import com.rubberjam.fusion.eclipse.scanner.FusionScanner;
import com.rubberjam.fusion.eclipse.contentassist.FusionCompletionProcessor;

public class FusionSourceViewerConfiguration extends SourceViewerConfiguration {

    private FusionScanner scanner;
    private ColorManager colorManager;

    public FusionSourceViewerConfiguration(ColorManager colorManager) {
        this.colorManager = colorManager;
    }

    protected FusionScanner getFusionScanner() {
        if (scanner == null) {
            scanner = new FusionScanner(colorManager);
        }
        return scanner;
    }

    @Override
    public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
        PresentationReconciler reconciler = new PresentationReconciler();

        DefaultDamagerRepairer dr = new DefaultDamagerRepairer(getFusionScanner());
        reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
        reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

        return reconciler;
    }

    @Override
    public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
        ContentAssistant assistant = new ContentAssistant();

        IContentAssistProcessor processor = new FusionCompletionProcessor();
        assistant.setContentAssistProcessor(processor, IDocument.DEFAULT_CONTENT_TYPE);

        assistant.enableAutoActivation(true);
        assistant.setAutoActivationDelay(500);
        assistant.setProposalPopupOrientation(IContentAssistant.PROPOSAL_OVERLAY);

        return assistant;
    }
}
