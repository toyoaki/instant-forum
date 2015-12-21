package com.toyo.instantforum.service.translation;

import org.springframework.stereotype.Service;

import com.ibm.watson.developer_cloud.language_translation.v2.LanguageTranslation;

@Service
public class WatsonTranslationServiceImpl implements WatsonTranslationService {

    // TODO: externalizar para arquivo de properties e utilizar @Value
    private String username = "6a194aff-52e0-43e8-8bea-7c8bed1e56ab";
    private String password = "0WQqQPoUfxyq";

    public LanguageTranslation getLanguageTranslationService() {
	LanguageTranslation service = new LanguageTranslation();
	service.setUsernameAndPassword(username, password);
	return service;
    }

}
