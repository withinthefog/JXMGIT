package nc.ui.mallpos.activity;

import nc.ui.trade.business.HYPubBO_Client;
import nc.ui.trade.controller.IControllerBase;
import nc.ui.trade.manage.BillManageUI;
import nc.ui.trade.manage.ManageEventHandler;
import nc.vo.mallpos.activity.MPOS_ActivityHeadVO;
import nc.vo.mallpos.activity.MPOS_ActivityVO;
import nc.vo.mallpos.pub.MPOS_ExtButton;
import nc.vo.mallpos.pub.MPOS_GlobalVariable;

public class ActivityEventHandler extends ManageEventHandler {

	public ActivityEventHandler(BillManageUI billUI, IControllerBase control) {
		super(billUI, control);
	}
	
	@Override
	protected void onBoElse(int intBtn) throws Exception {
		switch (intBtn) {
		case MPOS_ExtButton.ActivateActivity:
			onBoActivateActivity();
			break;
		case MPOS_ExtButton.DeactivateActivity:
			onBoDeactivateActivity();
			break;
		}
		super.onBoElse(intBtn);
	}

	private void onBoDeactivateActivity() throws Exception {
		MPOS_ActivityVO vo = (MPOS_ActivityVO) getBillUI().getBufferData().getCurrentVO();
		MPOS_ActivityHeadVO head = (MPOS_ActivityHeadVO) vo.getParentVO();
		switch (head.getStatusflag()) {
		case MPOS_GlobalVariable.ACTIVITY_STATUS_INIT:
			break;
		case MPOS_GlobalVariable.ACTIVITY_STATUS_ACTIVE:
			break;
		case MPOS_GlobalVariable.ACTIVITY_STATUS_STOP:
			getBillUI().showWarningMessage("活动已经终止");
			break;
		}
		head.setStatusflag(MPOS_GlobalVariable.ACTIVITY_STATUS_STOP);
		HYPubBO_Client.saveBD(vo, null);
		onBoRefresh();
	}

	private void onBoActivateActivity() throws Exception {
		MPOS_ActivityVO vo = (MPOS_ActivityVO) getBillUI().getBufferData().getCurrentVO();
		MPOS_ActivityHeadVO head = (MPOS_ActivityHeadVO) vo.getParentVO();
		switch (head.getStatusflag()) {
		case MPOS_GlobalVariable.ACTIVITY_STATUS_INIT:
			break;
		case MPOS_GlobalVariable.ACTIVITY_STATUS_ACTIVE:
			getBillUI().showWarningMessage("活动已经生效");
			break;
		case MPOS_GlobalVariable.ACTIVITY_STATUS_STOP:
			break;
		}
		head.setStatusflag(MPOS_GlobalVariable.ACTIVITY_STATUS_ACTIVE);
		HYPubBO_Client.saveBD(vo, null);
		onBoRefresh();
	}

}
