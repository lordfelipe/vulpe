package org.jw.mmn.frontend.controller;

import java.util.List;

import org.jw.mmn.commons.ApplicationConstants.Core;
import org.jw.mmn.controller.ApplicationBaseController;
import org.jw.mmn.core.model.entity.Congregation;
import org.jw.mmn.core.model.entity.Group;
import org.jw.mmn.core.model.services.CoreService;
import org.jw.mmn.frontend.model.entity.Index;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.vulpe.commons.VulpeConstants.View;
import org.vulpe.controller.annotations.Controller;
import org.vulpe.controller.annotations.ExecuteAlways;
import org.vulpe.controller.commons.VulpeControllerConfig.ControllerType;
import org.vulpe.exception.VulpeApplicationException;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Component("frontend.IndexController")
@SuppressWarnings( { "serial", "unchecked" })
@Controller(type = ControllerType.FRONTEND)
public class IndexController extends ApplicationBaseController<Index, Long> {

	public void teste() {
		controlResultForward();
	}

	protected List<Congregation> congregations;

	@Override
	protected void postConstruct() {
		super.postConstruct();
		congregations = (List<Congregation>) vulpe.cache().classes().get(
				Congregation.class.getSimpleName());
	}

	public void selecionarValidate() {
		if (entity.getCongregation() != null && entity.getCongregation().getId() != null) {
			for (final Congregation congregation : congregations) {
				if (congregation.getId().equals(entity.getCongregation().getId())) {
					ever.put(Core.SELECTED_CONGREGATION, congregation);
					final Group grupo = new Group();
					grupo.setCongregation(congregation);
					try {
						final List<Group> grupos = vulpe.service(CoreService.class)
								.readGroup(grupo);
						ever.put(Core.GROUPS_OF_SELECTED_CONGREGATION, grupos);
					} catch (VulpeApplicationException e) {
						LOG.error(e);
					}

					vulpe.controller().redirectTo("/core/Member/select", true);
				}
			}
		}
		final String currentLayout = ever.getSelf(View.CURRENT_LAYOUT);
		final String url = "FRONTEND".equals(currentLayout) ? "/frontend/Index" : "/backend/Index";
		vulpe.controller().redirectTo(url, true);
	}

	@ExecuteAlways
	public void init() {
		final Congregation congregation = ever.getSelf(Core.SELECTED_CONGREGATION);
		if (congregation != null) {
			entity = new Index();
			entity.setCongregation(congregation);
		}
	}

}
