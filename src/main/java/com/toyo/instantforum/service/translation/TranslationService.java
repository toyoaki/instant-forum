package com.toyo.instantforum.service.translation;

import java.util.List;

public interface TranslationService {

    boolean isAcceptedLanguage(String language);

    List<String> translateFromPt(List<String> textsInPortuguese, String destinationLanguage);

}
