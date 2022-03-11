package com.amf.registration.internal.security.permission.resource.definition;

import com.amf.registration.constants.RegistrationConstants;
import com.amf.registration.model.RegistrationLog;
import com.amf.registration.service.RegistrationLogLocalService;
import com.amf.registration.service.RegistrationLogLocalServiceUtil;
import com.liferay.exportimport.kernel.staging.permission.StagingPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionLogic;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.StagedModelPermissionLogic;
import com.liferay.portal.kernel.security.permission.resource.definition.ModelResourcePermissionDefinition;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.workflow.permission.WorkflowPermission;

import java.util.function.Consumer;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author liferay
 */
@Component(immediate = true, service = ModelResourcePermissionDefinition.class)
public class RegistrationLogModelResourcePermissionDefinition
		implements ModelResourcePermissionDefinition<RegistrationLog> {

	@Override
	public PortletResourcePermission getPortletResourcePermission() {
		return _portletResourcePermission;
	}

	@Override
	public RegistrationLog getModel(long primaryKey) throws PortalException {
		return _registrationLogLocalService.getRegistrationLog(primaryKey);
	}

	@Override
	public Class<RegistrationLog> getModelClass() {
		return RegistrationLog.class;
	}

	@Override
	public long getPrimaryKey(RegistrationLog t) {
		return t.getRegistrationLogId();
	}

	@Override
	public void registerModelResourcePermissionLogics(ModelResourcePermission<RegistrationLog> modelResourcePermission,
			Consumer<ModelResourcePermissionLogic<RegistrationLog>> modelResourcePermissionLogicConsumer) {
		modelResourcePermissionLogicConsumer.accept(new StagedModelPermissionLogic<>(_stagingPermission,
				"com_amf_web_registration_portlet_AdminMonitorPortlet", RegistrationLog::getRegistrationLogId));

	}

	@Reference
	private RegistrationLogLocalService _registrationLogLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference(target = "(resource.name=" + RegistrationConstants.RESOURCE_NAME + ")")
	private PortletResourcePermission _portletResourcePermission;

	@Reference
	private StagingPermission _stagingPermission;
	@Reference
	private WorkflowPermission _workflowPermission;

}