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
package org.vulpe.audit.vraptor.controller;

import java.util.List;

import org.jfree.util.Log;
import org.vulpe.audit.model.entity.AuditOccurrence;
import org.vulpe.audit.model.entity.AuditOccurrenceType;
import org.vulpe.audit.model.services.AuditService;
import org.vulpe.commons.VulpeConstants.Controller.Button;
import org.vulpe.controller.annotations.Controller;
import org.vulpe.controller.annotations.Select;
import org.vulpe.controller.vraptor.VulpeVRaptorController;
import org.vulpe.exception.VulpeApplicationException;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;

@Resource
@Path("/audit/Occurrence")
@Controller(serviceClass = AuditService.class, select = @Select(pageSize = 5))
@SuppressWarnings("serial")
public class OccurrenceController extends VulpeVRaptorController<AuditOccurrence, Long> {

	private List<AuditOccurrence> childOccurrences = null;

	@Override
	protected void updateAfter() {
		super.updateAfter();
		hideButtons(Button.CREATE, Button.UPDATE_POST, Button.DELETE, Button.CLEAR);
	}

	@Override
	protected void onUpdate() {
		super.onUpdate();
		try {
			childOccurrences = getService(AuditService.class).findByParentAuditOccurrence(
					new AuditOccurrence(getEntity().getId()));
		} catch (VulpeApplicationException e) {
			Log.error(e);
		}
	}

	public List<AuditOccurrence> getChildOccurrences() {
		return childOccurrences;
	}

	public void setChildOccurrences(final List<AuditOccurrence> childOccurrences) {
		this.childOccurrences = childOccurrences;
	}

	@Override
	protected void prepareAfter() {
		hideButton(Button.CREATE);
		super.prepareAfter();
	}

	@Override
	protected void readAfter() {
		hideButton(Button.CREATE);
		super.readAfter();
	}

	public AuditOccurrenceType[] getListOccurrenceType() {
		return AuditOccurrenceType.values();
	}
}
