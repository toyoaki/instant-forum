package com.toyo.instantforum.service.translation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.ibm.watson.developer_cloud.language_translation.v2.LanguageTranslation;
import com.ibm.watson.developer_cloud.language_translation.v2.model.Translation;
import com.ibm.watson.developer_cloud.language_translation.v2.model.TranslationResult;

@Service
public class TranslationServiceImpl implements TranslationService {

    private static final List<String> ACCEPTED_TRANSLATION_LANG = Arrays.asList("en");

    @Autowired
    private WatsonTranslationService watsonTranslationService;

    public boolean isAcceptedLanguage(String language) {
	return !StringUtils.isEmpty(language) && ACCEPTED_TRANSLATION_LANG.contains(language);
    }

    public List<String> translateFromPt(List<String> textsInPortuguese, String destinationLanguage) {
	List<String> result = new ArrayList<>();

	if (!CollectionUtils.isEmpty(textsInPortuguese) && isAcceptedLanguage(destinationLanguage)) {

	    LanguageTranslation service = watsonTranslationService.getLanguageTranslationService();

	    String[] textsArray = new String[textsInPortuguese.size()];
	    textsInPortuguese.toArray(textsArray);

	    TranslationResult translationResult = service.translate(textsArray, "pt", destinationLanguage);

	    for (Translation translation : translationResult.getTranslations()) {
		result.add(translation.getTranslation());
	    }
	}
	
	return result;
    }

}
