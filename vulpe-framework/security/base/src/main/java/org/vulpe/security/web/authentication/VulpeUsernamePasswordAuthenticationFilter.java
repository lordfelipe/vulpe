/**
 * Vulpe Framework - Quick and Smart ;)
 * Copyright (C) 2011 Active Thread
 *
 * Este programa é software livre; você pode redistribuí-lo e/ou
 * modificá-lo sob os termos da Licença Pública Geral GNU, conforme
 * publicada pela Free Software Foundation; tanto a versão 2 da
 * Licença como (a seu critério) qualquer versão mais nova.
 *
 * Este programa é distribuído na expectativa de ser útil, mas SEM
 * QUALQUER GARANTIA; sem mesmo a garantia implícita de
 * COMERCIALIZAÇÃO ou de ADEQUAÇÃO A QUALQUER PROPÓSITO EM
 * PARTICULAR. Consulte a Licença Pública Geral GNU para obter mais
 * detalhes.
 *
 * Você deve ter recebido uma cópia da Licença Pública Geral GNU
 * junto com este programa; se não, escreva para a Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307, USA.
 */
/**
 * Vulpe Framework - Quick and Smart ;)
 * Copyright (C) 2011 Active Thread
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.vulpe.security.web.authentication;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.vulpe.commons.VulpeConstants.Controller.URI;
import org.vulpe.commons.VulpeConstants.Security;
import org.vulpe.commons.VulpeConstants.View.Layout;
import org.vulpe.commons.util.VulpeReflectUtil;

/**
 *
 * @author <a href="mailto:felipe@vulpe.org">Geraldo Felipe</a>
 * @version 1.0
 * @since 1.0
 */
public class VulpeUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Override
	protected void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain, Authentication authResult)
			throws IOException, ServletException {
		super.successfulAuthentication(request, response, chain, authResult);
		changeSavedRequest(request, response);
	}

	/**
	 *
	 * @param request
	 */
	public void changeSavedRequest(final HttpServletRequest request, HttpServletResponse response) {
		final SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request,
				response);
		if (savedRequest != null && !savedRequest.getRedirectUrl().contains(URI.AUTHENTICATOR)) {
			final String url = savedRequest.getRedirectUrl();
			if (url.contains(Layout.JS_CONTEXT) || url.contains(Layout.THEMES_CONTEXT)
					|| url.contains(Layout.CSS_CONTEXT) || url.contains(Layout.IMAGES_CONTEXT)
					|| url.contains(Layout.SUFFIX_JSP)) {
				VulpeReflectUtil.setFieldValue(savedRequest, "redirectUrl", "index.jsp");
			}
			new HttpSessionRequestCache().saveRequest(request, response);
		}
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException failed) throws IOException,
			ServletException {
		super.unsuccessfulAuthentication(request, response, failed);
		request.getSession().setAttribute(Security.USER_AUTHENTICATION, request.getParameter(getUsernameParameter()));
	}
}
