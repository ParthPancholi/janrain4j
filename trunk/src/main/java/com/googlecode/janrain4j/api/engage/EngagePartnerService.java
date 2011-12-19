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

import java.util.Collection;
import java.util.Map;

/**
 * The <code>EngagePartnerService</code> provides access to the Janrain Engage Partner API.
 * <p>
 * Note that methods might throw a {@link com.googlecode.janrain4j.api.engage.ErrorResponeException} in case your
 * plan (Basic, Plus or Pro) is not sufficient for making the particular
 * Janrain Engage Partner API call.
 *
 * @author Sergey Tsymbler
 * @see <a href="https://rpxnow.com/docs/partner">Janrain Engage Documentation</a>
 * @since 1.2
 */
public interface EngagePartnerService {

    /**
     * Creates a new Engage application. New applications can be immediately used for authenticating with OpenID-enabled
     * providers (Google, AOl, etc.), but non-OpenID providers, such as Facebook, require additional configuration.
     * After calling this function, use set_properties to enable these additional features.
     *
     * @param name Display name for the new Engage application.
     * @param domain Fully-qualified domain for the new Engage application. If an application has already been provisioned with this FQDN, an error will be returned.
     * @param adminEmail Administrative email address to be associated with the new Engage application.
     * @return The application data Janrain Engage created.
     * @throws com.googlecode.janrain4j.api.engage.EngageFailureException If any unknown error occurs while communicating with the Janrain Engage Partner API.
     * @throws com.googlecode.janrain4j.api.engage.ErrorResponeException If the Janrain Engage Partner API returns an error response.
     * @see <a href="https://rpxnow.com/docs/partner#create">Janrain Engage Partner API Documentation: create</a>
     * @since 1.2
     */
    ApplicationResponse createApplication(String name, String domain, String adminEmail) throws EngageFailureException, ErrorResponeException;

    /**
     * Deletes an existing Engage application. This operation completely destroys the Engage application and is irreversible
     *
     * @param apiKey Appliction's apiKey
     * @throws com.googlecode.janrain4j.api.engage.EngageFailureException If any unknown error occurs while communicating with the Janrain Engage Partner API.
     * @throws com.googlecode.janrain4j.api.engage.ErrorResponeException If the Janrain Engage Partner API returns an error response.
     * @see <a href="https://rpxnow.com/docs/partner#delete">Janrain Engage Partner API Documentation: delete</a>
     * @since 1.2
     */
    void deleteApplication(String apiKey) throws EngageFailureException, ErrorResponeException;

    /**
     * Automates the server-side component of the provider-specific domain verification mechanism. This method should be called before setProviderProperties to complete the domain verification process.
     *
     * @param apiKey Engage API Key
     * @param providerName Provider name to configure
     * @param applicationDomainProperties Application domain properties as map of key/value pairs. Refer to Janrain documentation for application domain verification
     * @throws com.googlecode.janrain4j.api.engage.EngageFailureException If any unknown error occurs while communicating with the Janrain Engage Partner API.
     * @throws com.googlecode.janrain4j.api.engage.ErrorResponeException If the Janrain Engage Partner API returns an error response.
     * @see <a href="https://rpxnow.com/docs/partner#verify_domain">Janrain Engage Partner API Documentation: verify_domain</a>
     * @since 1.2
     */
    void verifyApplicationDomain(String apiKey, String providerName, Map<String, String> applicationDomainProperties) throws EngageFailureException, ErrorResponeException;

    /**
     * Add a domain to an existing Engage application. This call can be used to add additional domains to an existing Engage application.
     *
     * @param apiKey Engage API Key
     * @param domain Domain to add
     * @throws com.googlecode.janrain4j.api.engage.EngageFailureException If any unknown error occurs while communicating with the Janrain Engage Partner API.
     * @throws com.googlecode.janrain4j.api.engage.ErrorResponeException If the Janrain Engage Partner API returns an error response.
     * @see <a href="https://rpxnow.com/docs/partner#add_domain">Janrain Engage Partner API Documentation: add_domain</a>
     * @since 1.2
     */
    void addDomain(String apiKey, String domain) throws EngageFailureException, ErrorResponeException;

    /**
     * Configure Engage application properties for an existing Engage application. This call can be used to add bind given provider to an existing Engage application.
     * For example, after calling createApplication, this function could be used to set up a binding to a Facebook application.
     *
     * @param apiKey Engage API Key
     * @param providerName Provider name to configure
     * @param providerProperties Provider properties as map of key/value pairs. Refer to Janrain documentation for provider specific properties
     * @throws com.googlecode.janrain4j.api.engage.EngageFailureException If any unknown error occurs while communicating with the Janrain Engage Partner API.
     * @throws com.googlecode.janrain4j.api.engage.ErrorResponeException If the Janrain Engage Partner API returns an error response.
     * @see <a href="https://rpxnow.com/docs/partner#set_properties">Janrain Engage Partner API Documentation: set_properties</a>
     * @since 1.2
     */
    void setProviderProperties(String apiKey, String providerName, Map<String, String> providerProperties) throws EngageFailureException, ErrorResponeException;

    /**
     * Sets Engage application’s permissions for a provider. Permissions that can be set depend on the provider, service level and whether the provider has been configured or not.
     * Permissions will be set if and only if they are passed in the permissions parameter. Each request replaces the existing set of permissions.
     * The old ones are cleared and the new ones set.
     *
     * @param apiKey Engage API Key
     * @param providerName Provider name to configure
     * @param providerPermissions Provider permissions as collection of String values. Refer to Janrain documentation for provider specific properties
     * @throws com.googlecode.janrain4j.api.engage.EngageFailureException If any unknown error occurs while communicating with the Janrain Engage Partner API.
     * @throws com.googlecode.janrain4j.api.engage.ErrorResponeException If the Janrain Engage Partner API returns an error response.
     * @see <a href="https://rpxnow.com/docs/partner#set_provider_permissions">Janrain Engage Partner API Documentation: set_provider_permissions</a>
     * @since 1.2
     */
    void setProviderPermissions(String apiKey, String providerName, Collection<String> providerPermissions) throws EngageFailureException, ErrorResponeException;
}
