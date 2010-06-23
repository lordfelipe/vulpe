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
package org.vulpe.security.authentication.model.services;

import org.vulpe.security.authentication.data.AuthenticationResponse;
import org.vulpe.security.exception.VulpeSecurityException;

/**
 * A business service to authenticate the user.
 * 
 */
public interface UserAuthenticationService {
	/**
	 * Performs authentication based on passed <code>userId</code> and
	 * <code>password</code> information.
	 * 
	 * @param userId
	 *            the user id
	 * @param password
	 *            password for the user
	 * @return <code>AuthenticationResponse</code> a response containing
	 *         authentication status and user profile information.
	 * @throws VulpeSecurityException
	 * 
	 */
	AuthenticationResponse authenticateUser(String userId, String password)
			throws VulpeSecurityException;
}
