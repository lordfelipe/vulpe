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
package org.vulpe.commons.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Detail configuration
 *
 * @author <a href="mailto:fabio.viana@vulpe.org">F�bio Viana</a>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
public @interface DetailConfig {
	/**
	 * Quantity of news details
	 */
	int newDetails() default 0;

	/**
	 * Quantity of news details on start
	 */
	int startNewDetails() default 0;

	/**
	 * Attributes to despise details
	 */
	String[] despiseFields();

	/**
	 * View Definition interface
	 */
	String view() default "";

	/**
	 * Detail name
	 */
	String name();

	/**
	 * Detail atribute name
	 */
	String propertyName() default "";

	/**
	 * Parent Detail Name
	 */
	String parentDetailName() default "";

	/**
	 * Quantity of details
	 *
	 * @return
	 */
	Quantity quantity() default @Quantity;
}