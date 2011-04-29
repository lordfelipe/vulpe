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
package org.vulpe.config.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to configure Project properties.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PACKAGE)
public @interface VulpeProject {

	/**
	 * Project Name.
	 */
	String name();

	/**
	 * Project Package.
	 */
	String projectPackage();

	/**
	 * Character Encoding.
	 */
	String characterEncoding() default "UTF-8";

	/**
	 * Default Date pattern.
	 */
	String datePattern() default "dd/MM/yyyy";

	/**
	 * Default Date Time pattern.
	 */
	String dateTimePattern() default "dd/MM/yyyy HH:mm:ss";

	/**
	 * Default Locale Code.
	 */
	String localeCode() default "en_US";

	/**
	 * I18N message files name.
	 */
	String[] i18n() default { "VulpeResources", "VulpeSecurityResources", "ApplicationResources" };

	/**
	 * I18N Manager.
	 */
	String i18nManager() default "org.vulpe.controller.commons.MultipleResourceBundle";

	/**
	 * Configure View properties.
	 */
	VulpeView view() default @VulpeView;

	/**
	 * Configure File Upload properties.
	 */
	VulpeUpload upload() default @VulpeUpload;

	/**
	 * Configure Mobile properties.
	 */
	VulpeMobile mobile() default @VulpeMobile;

	/**
	 * Configure Code Generator properties
	 */
	VulpeCodeGenerator codeGenerator() default @VulpeCodeGenerator;
	
	/**
	 * Configure Hot Keys properties
	 */
	VulpeHotKeys hotKeys() default @VulpeHotKeys;

	/**
	 * Project Theme.
	 */
	String theme() default "default";

	/**
	 * Audit enable/disable.
	 */
	boolean audit() default true;

	/**
	 * Security enable/disable.
	 */
	boolean security() default true;

	/**
	 * Debug mode enable/disable.
	 */
	boolean debug() default false;

}
