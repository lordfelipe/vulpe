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
package org.vulpe.security.authentication.model.dao.impl.jpa;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.vulpe.exception.VulpeApplicationException;
import org.vulpe.model.dao.impl.jpa.VulpeBaseDAOJPA;
import org.vulpe.security.authentication.data.VulpeAuthenticationResponse;
import org.vulpe.security.authentication.model.dao.VulpeUserAuthenticationDAO;
import org.vulpe.security.commons.VulpeSecurityConstants;
import org.vulpe.security.exception.VulpeSecurityException;
import org.vulpe.security.exception.VulpeSecurityInactiveUserException;
import org.vulpe.security.exception.VulpeSecurityInvalidPasswordException;
import org.vulpe.security.exception.VulpeSecurityUserNotFoundException;
import org.vulpe.security.model.entity.User;

/**
 *
 * This <code>VulpeUserAuthenticationDAO</code> implementation uses data
 * available in memory to authenticate the user.
 *
 * @author <a href="mailto:felipe@vulpe.org">Geraldo Felipe</a>
 * @version 1.0
 * @since 1.0
 *
 */
@Repository("VulpeUserAuthenticationDAO")
@Transactional
public class VulpeUserAuthenticationDAOJPA extends VulpeBaseDAOJPA<User, Long> implements
		VulpeUserAuthenticationDAO {
	protected static final Logger LOG = Logger.getLogger(VulpeUserAuthenticationDAOJPA.class);

	/*
	 * (non-Javadoc)
	 *
	 * @seeorg.vulpe.security.authentication.model.dao.UserAuthenticationDAO#
	 * authenticateUser(java.lang.String, java.lang.String)
	 */
	public VulpeAuthenticationResponse authenticateUser(final String username, final String password)
			throws VulpeSecurityException {
		User user = new User();
		user.setUsername(username);
		try {
			final List<User> users = read(user);
			if (users != null && !users.isEmpty()) {
				user = users.get(0);
			}
		} catch (VulpeApplicationException e) {
			LOG.error(e);
		}
		if (user == null || user.getId() == null) {
			throw new VulpeSecurityUserNotFoundException(username);
		}
		if (!password.equals(user.getPassword())) {
			throw new VulpeSecurityInvalidPasswordException();
		}
		if (!user.isActive()) {
			throw new VulpeSecurityInactiveUserException(username);
		}
		return new VulpeAuthenticationResponse(VulpeSecurityConstants.AUTHENTICATION_SUCCESS, user);
	}

}
