package com.rubberjam.fusion.eclipse;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class FusionKeywordsTest {

    @Test
    void keywordsArePresent() {
        assertTrue(FusionKeywords.KEYWORDS.length > 0);
        assertTrue(Arrays.asList(FusionKeywords.KEYWORDS).contains("class"));
        assertTrue(Arrays.asList(FusionKeywords.KEYWORDS).contains("foreach"));
    }

    @Test
    void keywordsAreUnique() {
        Set<String> seen = new HashSet<>();
        for (String keyword : FusionKeywords.KEYWORDS) {
            assertFalse(seen.contains(keyword), "Duplicate keyword: " + keyword);
            seen.add(keyword);
        }
    }
}
