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
package org.vulpe.controller.commons;

import org.springframework.stereotype.Component;

@Component
public class I18NService {

	public String getText(final String key) {
		return MultipleResourceBundle.getInstance().getString(key);
	}

	public String getText(final String key, final Object... args) {
		String text = getText(key);
		int count = 0;
		for (Object object : args) {
			final String token = "{" + count + "}";
			text = text.replace(token, object.toString());
			++count;
		}
		return text;
	}
	
	public String getText(final String key, final String... args) {
		String text = getText(key);
		int count = 0;
		for (String string : args) {
			final String token = "{" + count + "}";
			text = text.replace(token, string);
			++count;
		}
		return text;
	}
}
