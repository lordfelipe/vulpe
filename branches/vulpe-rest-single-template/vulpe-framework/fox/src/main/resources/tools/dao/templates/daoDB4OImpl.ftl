<#include "macros.ftl"/>
<@forAllValidDAO ; type, dao>
Generating DAODB4OImpl: ${dao.daoPackageName}.impl.db4o.${dao.daoName}DB4OImpl
<@javaSource name="${dao.daoPackageName}.impl.db4o.${dao.daoName}DB4OImpl">
package ${dao.daoPackageName}.impl.db4o;

import ${dao.packageName}.${dao.name};
import ${dao.daoPackageName}.${dao.daoName};

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO DB4O implementation of ${dao.name}
 */
@Repository("${dao.daoName}")
@Transactional
<#if dao.daoSuperclassName??>
<#if dao.inheritance>
public class ${dao.daoName}DB4OImpl<ENTITY_CLASS extends ${dao.name}> extends ${dao.daoSuperclassPackageName}.impl.db4o.${dao.daoSuperclassSimpleName}DB4OImpl<ENTITY_CLASS> implements ${dao.daoName}<ENTITY_CLASS> {
<#else>
public class ${dao.daoName}DB4OImpl extends ${dao.daoPackageName}.impl.db4o.${dao.daoSuperclassSimpleName}DB4OImpl<${dao.name}> implements ${dao.daoName} {
</#if>
<#else>
<#if dao.inheritance>
public class ${dao.daoName}DB4OImpl<ENTITY_CLASS extends ${dao.name}> extends org.vulpe.model.dao.impl.db4o.VulpeBaseCRUDDAODB4OImpl<ENTITY_CLASS, ${dao.idType}> implements ${dao.daoName}<ENTITY_CLASS> {
<#else>
public class ${dao.daoName}DB4OImpl extends org.vulpe.model.dao.impl.db4o.VulpeBaseCRUDDAODB4OImpl<${dao.name}, ${dao.idType}> implements ${dao.daoName} {
</#if>
</#if>
	<#list dao.methods as method>
	public ${method.returnType} ${method.name}(
		<#list method.parameters as parameter>
		final ${parameter.type} ${parameter.name}<#if parameter_has_next>,</#if>
		</#list>) throws org.vulpe.exception.VulpeApplicationException {
	<#if method.parameters?has_content>
		final ${dao.name} entity = new ${dao.name}();
		<#list method.parameters as parameter>
		entity.set${parameter.name?cap_first}(${parameter.name});
		</#list>
	</#if>

	<#if method.returnType == dao.name>
		<#if method.parameters?has_content>
		return (java.util.List<${method.returnType}>) getObject(entity);
		<#else>
		return getObject(new ${method.returnType}());
		</#if>
	<#else>
		<#if method.parameters?has_content>
		return (${method.returnType}) getList(entity);
		<#else>
		return (${method.returnType}) getList(new ${method.returnType}());
		</#if>
	</#if>
	}
	</#list>
}</@javaSource></@forAllValidDAO>