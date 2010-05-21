/**
 * Vulpe Framework - Copyright (c) Active Thread
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
package org.vulpe.security.authorization.model.dao.impl.db4o;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.vulpe.common.Constants;
import org.vulpe.common.ValidationUtil;
import org.vulpe.common.cache.VulpeCacheHelper;
import org.vulpe.model.dao.impl.db4o.VulpeBaseCRUDDAODB4OImpl;
import org.vulpe.security.authorization.model.dao.AuthorizationDAO;
import org.vulpe.security.model.entity.Role;
import org.vulpe.security.model.entity.SecureResource;
import org.vulpe.security.model.entity.SecureResourceRole;

@Repository("AuthorizationDAO")
@Transactional
public class AuthorizationDAOImpl extends VulpeBaseCRUDDAODB4OImpl<SecureResource, Long> implements
		AuthorizationDAO {

	private transient final Map<String, SecureResource> secureObjects = new HashMap<String, SecureResource>();

	private transient final Map<SecureResource, List<Role>> secureObjectToRoles = new HashMap<SecureResource, List<Role>>();

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.vulpe.security.authorization.model.dao.
	 * AuthorizationDAO#getSecureObject(java.lang.String)
	 */
	public SecureResource getSecureObject(final String securityObjectName) {
		reloadAuthorizationInfo();
		final SecureResource secureResource = secureObjects.get(securityObjectName);
		if (secureResource == null) {
			for (SecureResource secureResource2 : secureObjects.values()) {
				if (securityObjectName.contains(secureResource2.getResourceName().replaceAll("\\*",
						""))) {
					return secureResource2;
				}
			}
		}
		return secureResource;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.vulpe.security.authorization.model.dao. AuthorizationDAO
	 * #getSecureObjectRoles(org.vulpe.security .model.entity.SecureResource)
	 */
	public List<Role> getSecureObjectRoles(final SecureResource secureObject) {
		return secureObjectToRoles.get(secureObject);
	}

	@SuppressWarnings("unchecked")
	private void reloadAuthorizationInfo() {
		// List<SecureResource> secureResources = getList(new SecureResource());
		final Map<String, Object> cachedClasses = VulpeCacheHelper.getInstance().get(
				Constants.CACHED_CLASS);
		final List<SecureResource> secureResources = (List<SecureResource>) cachedClasses
				.get(SecureResource.class.getSimpleName());
		if (secureResources != null) {
			for (SecureResource secureResource : secureResources) {
				secureObjects.put(secureResource.getResourceName(), secureResource);
				if (ValidationUtil.getInstance()
						.isNotEmpty(secureResource.getSecureResourceRoles())) {
					final List<Role> roles = new ArrayList<Role>();
					for (SecureResourceRole secureResourceRole : secureResource
							.getSecureResourceRoles()) {
						roles.add(secureResourceRole.getRole());
					}
					secureObjectToRoles.put(secureResource, roles);
				}
			}
		}
	}
}