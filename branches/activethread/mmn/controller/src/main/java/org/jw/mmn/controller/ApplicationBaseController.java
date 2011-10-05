package org.jw.mmn.controller;

import java.io.Serializable;

import org.jw.mmn.commons.ApplicationConstants.Core;
import org.jw.mmn.core.controller.CongregationController;
import org.jw.mmn.core.model.entity.Congregation;
import org.vulpe.commons.VulpeConstants.View;
import org.vulpe.commons.VulpeConstants.Controller.URI;
import org.vulpe.controller.annotations.ExecuteAlways;
import org.vulpe.controller.struts.VulpeStrutsController;
import org.vulpe.model.entity.VulpeEntity;

@SuppressWarnings( { "serial", "unchecked" })
public class ApplicationBaseController<ENTITY extends VulpeEntity<ID>, ID extends Serializable & Comparable>
		extends VulpeStrutsController<ENTITY, ID> {

	public Congregation getCongregation() {
		return ever.<Congregation> getAuto(Core.SELECTED_CONGREGATION);
	}

	@ExecuteAlways
	public void validateSelectedCongregation() {
		if (!vulpe.controller().currentName().contains("frontend/Index")
				&& !vulpe.controller().currentName().contains("backend/Index")
				&& !ever.containsKey(Core.SELECTED_CONGREGATION)
				&& !this.getClass().getName().equals(CongregationController.class.getName())) {
			if (getRequest().getRequestURI().endsWith(URI.AJAX)) {
				vulpe.controller().ajax(true);
			}
			final String currentLayout = vulpe.sessionAttribute(View.CURRENT_LAYOUT);
			final String url = "FRONTEND".equals(currentLayout) ? "/frontend/Index"
					: "/backend/Index";
			vulpe.controller().redirectTo(url, vulpe.controller().ajax());
		}
	}
}
