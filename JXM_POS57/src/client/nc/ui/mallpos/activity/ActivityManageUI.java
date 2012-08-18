package nc.ui.mallpos.activity;

import nc.ui.pub.ClientEnvironment;
import nc.ui.trade.bill.AbstractManageController;
import nc.ui.trade.bsdelegate.BusinessDelegator;
import nc.ui.trade.manage.ManageEventHandler;
import nc.ui.trade.multichild.MultiChildBillManageUI;
import nc.vo.mallpos.pub.MPOS_GlobalVariable;
import nc.vo.pub.CircularlyAccessibleValueObject;

public class ActivityManageUI extends MultiChildBillManageUI {

	private static final long serialVersionUID = 6577940044046864968L;

	@Override
	protected AbstractManageController createController() {
		return new ActivityController();
	}

	@Override
	public void setBodySpecialData(CircularlyAccessibleValueObject[] arg0)
			throws Exception {

	}

	@Override
	protected void setHeadSpecialData(CircularlyAccessibleValueObject arg0,
			int arg1) throws Exception {

	}

	@Override
	protected void setTotalHeadSpecialData(
			CircularlyAccessibleValueObject[] arg0) throws Exception {

	}

	@Override
	protected void initSelfData() {

	}

	@Override
	public void setDefaultData() throws Exception {
		getBillCardPanel().getHeadItem("billdate").setValue(
				ClientEnvironment.getInstance().getBusinessDate());
		getBillCardPanel().getHeadItem("statusflag").setValue(
				MPOS_GlobalVariable.ACTIVITY_STATUS_INIT);
	}
	
	@Override
	protected BusinessDelegator createBusinessDelegator() {
		return new ActivityBusinessDelegator();
	}
	
	@Override
	protected void initPrivateButton() {
		addPrivateButton(new ActivateActivityBtnVO().getButtonVO());
		addPrivateButton(new DeactivateActivityBtnVO().getButtonVO());
		super.initPrivateButton();
	}
	
	@Override
	protected ManageEventHandler createEventHandler() {
		return new ActivityEventHandler(this,getUIControl());
	}

}
