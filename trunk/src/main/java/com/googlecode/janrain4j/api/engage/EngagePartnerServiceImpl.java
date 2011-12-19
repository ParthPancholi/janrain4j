/* Copyright 2011 Sergey Tsymbler
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.googlecode.janrain4j.api.engage;

import com.googlecode.janrain4j.api.engage.response.ApplicationResponse;
import com.googlecode.janrain4j.conf.Config;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sergey Tsymbler
 * @since 1.2
 */
public class EngagePartnerServiceImpl extends AbstractService implements EngagePartnerService {

    public static final String API_PARTNER_URI = "https://rpxnow.com/partner/v2/app/";

    public static final String CREATE_METHOD = "create";
    public static final String DELETE_METHOD = "delete";
    public static final String VERIFY_APP_DOMAIN_METHOD = "verify_domain";
    public static final String ADD_DOMAIN_METHOD = "add_domain";
    public static final String SET_PROVIDER_PROPERTIES_METHOD = "set_properties";
    public static final String SET_PROVIDER_PERMISSIONS_METHOD = "set_provider_permissions";

    public static final String PARTNER_KEY_PARAM = "partnerKey";
    public static final String EMAIL_PARAM = "email";
    public static final String NAME_PARAM = "name";
    public static final String DOMAIN_PARAM = "domain";
    public static final String PROVIDER_PARAM = "provider";
    public static final String PERMISSIONS_PARAM = "permissions";


    EngagePartnerServiceImpl() {
    }

    EngagePartnerServiceImpl(Config config) {
        super(config);
    }

    public ApplicationResponse createApplication(String name, String domain, String adminEmail) throws EngageFailureException, ErrorResponeException {
        Map<String, String> params = new HashMap<String, String>();
        params.put(NAME_PARAM, name);
        params.put(DOMAIN_PARAM, domain);
        params.put(EMAIL_PARAM, adminEmail);

        String jsonResponse = apiCall(CREATE_METHOD, params);
        return new ApplicationResponse(jsonResponse);
    }

    public void deleteApplication(String apiKey) throws EngageFailureException, ErrorResponeException {
        Map<String, String> params = new HashMap<String, String>();
        params.put(API_KEY_PARAM, apiKey);
        apiCall(DELETE_METHOD, params);
    }

    public void verifyApplicationDomain(String apiKey, String providerName, Map<String, String> applicationDomainProperties) throws EngageFailureException, ErrorResponeException {
        Map<String, String> params = new HashMap<String, String>();
        params.put(API_KEY_PARAM, apiKey);
        params.put(PROVIDER_PARAM, providerName);
        params.putAll(applicationDomainProperties);
        apiCall(VERIFY_APP_DOMAIN_METHOD, params);
    }

    public void addDomain(String apiKey, String domain) throws EngageFailureException, ErrorResponeException {
        Map<String, String> params = new HashMap<String, String>();
        params.put(API_KEY_PARAM, apiKey);
        params.put(DOMAIN_PARAM, domain);
        apiCall(ADD_DOMAIN_METHOD, params);
    }

    public void setProviderProperties(String apiKey, String providerName, Map<String, String> providerProperties) throws EngageFailureException, ErrorResponeException {
        Map<String, String> params = new HashMap<String, String>();
        params.put(API_KEY_PARAM, apiKey);
        params.put(PROVIDER_PARAM, providerName);
        params.putAll(providerProperties);
        apiCall(SET_PROVIDER_PROPERTIES_METHOD, params);
    }

    public void setProviderPermissions(String apiKey, String providerName, Collection<String> providerPermissions) throws EngageFailureException, ErrorResponeException {
        Map<String, String> params = new HashMap<String, String>();
        params.put(API_KEY_PARAM, apiKey);
        params.put(PROVIDER_PARAM, providerName);
        params.put(PERMISSIONS_PARAM, StringUtils.collectionToCommaDelimitedString(providerPermissions));
        apiCall(SET_PROVIDER_PERMISSIONS_METHOD, params);    }

    @Override
    protected String apiCall(String method, Map<String, String> partialParams) throws EngageFailureException, ErrorResponeException {
        partialParams.put(PARTNER_KEY_PARAM, getConfig().getPartnerApiKey());
        return super.apiCall(method, partialParams);
    }

    @Override
    protected String getBaseUrl() {
        return API_PARTNER_URI;
    }
}
