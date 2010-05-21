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
package org.vulpe.audit.controller.action;

import static org.vulpe.controller.struts.action.VulpeBaseAction.BaseActionButtons.CREATE;

import org.vulpe.audit.model.entity.AuditOccurrence;
import org.vulpe.audit.model.services.AuditServices;
import org.vulpe.common.audit.AuditOccurrenceType;
import org.vulpe.controller.annotations.Controller;
import org.vulpe.controller.annotations.Controller.ControllerType;
import org.vulpe.controller.struts.action.VulpeBaseAction;

@Controller(controllerType = ControllerType.SELECT, serviceClass = AuditServices.class, pageSize = 5)
@SuppressWarnings("serial")
public class OccurrenceSelectAction extends VulpeBaseAction<AuditOccurrence, Long> {

	@Override
	public String prepare() {
		final String prepare = super.prepare();
		hideButton(CREATE);
		return prepare;
	}

	@Override
	public String read() {
		final String read = super.read();
		hideButton(CREATE);
		return read;
	}

	public AuditOccurrenceType[] getListOccurrenceType() {
		return AuditOccurrenceType.values();
	}

}
