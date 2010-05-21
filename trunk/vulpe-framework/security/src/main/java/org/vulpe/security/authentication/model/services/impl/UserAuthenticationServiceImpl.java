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
package org.vulpe.security.authentication.model.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.vulpe.security.authentication.data.AuthenticationResponse;
import org.vulpe.security.authentication.model.dao.UserAuthenticationDAO;
import org.vulpe.security.authentication.model.services.UserAuthenticationService;
import org.vulpe.security.exception.VulpeSecurityException;

/**
 * It uses <code>UserAuthenticationDAO</code> for authentication.
 * 
 * @author <a href="mailto:felipe.matos@activethread.com.br">Felipe Matos</a>
 * @version 1.0
 * @since 1.0
 * 
 */
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

	@Qualifier("UserAuthenticationDAO")
	@Autowired
	private transient UserAuthenticationDAO authenticationDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.vulpe.security.authentication.model.services.
	 * UserAuthenticationService#authenticateUser(java.lang.String,
	 * java.lang.String)
	 */
	public AuthenticationResponse authenticateUser(final String userId, final String password)
			throws VulpeSecurityException {
		return authenticationDAO.authenticateUser(userId, password);
	}

	public void setAuthenticationDAO(final UserAuthenticationDAO authenticationDAO) {
		this.authenticationDAO = authenticationDAO;
	}

}