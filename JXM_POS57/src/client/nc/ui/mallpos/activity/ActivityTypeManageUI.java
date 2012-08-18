package nc.ui.mallpos.activity;

import nc.ui.trade.bill.AbstractManageController;
import nc.ui.trade.manage.BillManageUI;
import nc.vo.pub.CircularlyAccessibleValueObject;

public class ActivityTypeManageUI extends BillManageUI {

	private static final long serialVersionUID = 6145310640637477054L;

	@Override
	protected AbstractManageController createController() {
		return new ActivityTypeController();
	}

	@Override
	public void setBodySpecialData(
			CircularlyAccessibleValueObject[] acircularlyaccessiblevalueobject)
			throws Exception {
		
	}

	@Override
	protected void setHeadSpecialData(
			CircularlyAccessibleValueObject circularlyaccessiblevalueobject,
			int i) throws Exception {

	}

	@Override
	protected void setTotalHeadSpecialData(
			CircularlyAccessibleValueObject[] acircularlyaccessiblevalueobject)
			throws Exception {

	}

	@Override
	protected void initSelfData() {

	}

	@Override
	public void setDefaultData() throws Exception {

	}

}
