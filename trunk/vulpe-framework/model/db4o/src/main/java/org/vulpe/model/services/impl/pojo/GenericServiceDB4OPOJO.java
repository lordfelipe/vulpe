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
package org.vulpe.model.services.impl.pojo;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vulpe.exception.VulpeApplicationException;
import org.vulpe.model.dao.impl.db4o.VulpeBaseDAODB4O;
import org.vulpe.model.entity.VulpeEntity;
import org.vulpe.model.entity.impl.AbstractVulpeBaseEntity;
import org.vulpe.model.services.GenericService;

/**
 * 
 * @author <a href="mailto:felipe@vulpe.org">Geraldo Felipe</a>
 */
@SuppressWarnings( { "unchecked" })
@Service("GenericService")
@Transactional
public class GenericServiceDB4OPOJO<ENTITY extends AbstractVulpeBaseEntity<ID>, ID extends Serializable & Comparable>
		implements GenericService {

	private static final Logger LOG = Logger.getLogger(GenericServiceDB4OPOJO.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.vulpe.model.services.GenericService#getList(
	 * org.vulpe.model.entity.VulpeEntity)
	 */
	public <T extends VulpeEntity<?>> List<T> getList(final T entity) {
		List<T> list = null;
		try {
			final VulpeBaseDAODB4O<ENTITY, ID> dao = new VulpeBaseDAODB4O<ENTITY, ID>();
			dao.setEntityClass((Class<ENTITY>) entity.getClass());
			list = (List<T>) dao.read((ENTITY) entity);
		} catch (VulpeApplicationException e) {
			LOG.error(e);
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.vulpe.model.services.GenericService#exists(org.vulpe.model.entity
	 * .VulpeEntity)
	 */
	@Override
	public <T extends VulpeEntity<?>> boolean exists(T entity) {
		try {
			final VulpeBaseDAODB4O<ENTITY, ID> dao = new VulpeBaseDAODB4O<ENTITY, ID>();
			dao.setEntityClass((Class<ENTITY>) entity.getClass());
			return dao.exists((ENTITY) entity);
		} catch (VulpeApplicationException e) {
			LOG.error(e);
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.vulpe.model.services.GenericService#notExistEquals(org.vulpe.model
	 * .entity.VulpeEntity)
	 */
	@Override
	public <T extends VulpeEntity<?>> boolean notExistEquals(T entity) {
		try {
			final VulpeBaseDAODB4O<ENTITY, ID> dao = new VulpeBaseDAODB4O<ENTITY, ID>();
			dao.setEntityClass((Class<ENTITY>) entity.getClass());
			return dao.notExistEquals((ENTITY) entity);
		} catch (VulpeApplicationException e) {
			LOG.error(e);
		}
		return false;
	}

}