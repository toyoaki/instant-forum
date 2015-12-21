package com.toyo.instantforum.service.translation;

import com.ibm.watson.developer_cloud.language_translation.v2.LanguageTranslation;

public interface WatsonTranslationService {

    LanguageTranslation getLanguageTranslationService();

}
