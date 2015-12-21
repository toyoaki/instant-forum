package com.toyo.instantforum.service.translation;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.ibm.watson.developer_cloud.language_translation.v2.LanguageTranslation;
import com.ibm.watson.developer_cloud.language_translation.v2.model.Translation;
import com.ibm.watson.developer_cloud.language_translation.v2.model.TranslationResult;

public class TranslationServiceImplTest {

    @Mock
    private WatsonTranslationService watsonTranslationService;

    @Mock
    private LanguageTranslation languageTranslation;

    @Mock
    private TranslationResult translationResult;

    @InjectMocks
    private TranslationServiceImpl translationServiceImpl;

    @Before
    public void initMocks() {
	MockitoAnnotations.initMocks(this);
    }

    @Test
    public void isAcceptedLanguageNull() {
	Assert.assertFalse(translationServiceImpl.isAcceptedLanguage(null));
    }

    @Test
    public void isAcceptedLanguageEmpty() {
	Assert.assertFalse(translationServiceImpl.isAcceptedLanguage(""));
    }

    @Test
    public void isAcceptedLanguageNo() {
	Assert.assertFalse(translationServiceImpl.isAcceptedLanguage("abc"));
    }

    @Test
    public void isAcceptedLanguageYes() {
	Assert.assertTrue(translationServiceImpl.isAcceptedLanguage("en"));
    }

    @Test
    public void translateFromPtEmptyParams() {
	List<String> translations = translationServiceImpl.translateFromPt(null, null);
	Assert.assertTrue(translations.isEmpty());
    }

    @Test
    public void translateFromPtInvalidLanguage() {
	List<String> translations = translationServiceImpl.translateFromPt(Arrays.asList("pão", "leite"), "qwe");
	Assert.assertTrue(translations.isEmpty());
    }

    @Test
    public void translateFromPt() {
	// Given

	Mockito.when(watsonTranslationService.getLanguageTranslationService()).thenReturn(languageTranslation);
	Mockito.when(
		languageTranslation.translate((String[]) Matchers.any(), (String) Matchers.any(),
			(String) Matchers.any())).thenReturn(translationResult);

	Translation translation1 = new Translation();
	translation1.setTranslation("bread");

	Translation translation2 = new Translation();
	translation2.setTranslation("milk");

	Mockito.when(translationResult.getTranslations()).thenReturn(Arrays.asList(translation1, translation2));

	// When

	List<String> translations = translationServiceImpl.translateFromPt(Arrays.asList("pão", "leite"), "en");

	// Then
	Assert.assertEquals("bread", translations.get(0));
	Assert.assertEquals("milk", translations.get(1));
    }

}
