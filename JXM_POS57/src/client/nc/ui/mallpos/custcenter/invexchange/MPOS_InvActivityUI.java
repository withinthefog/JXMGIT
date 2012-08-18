package nc.ui.mallpos.custcenter.invexchange;

import nc.ui.trade.bill.AbstractManageController;
import nc.ui.trade.manage.BillManageUI;
import nc.vo.pub.CircularlyAccessibleValueObject;

public class MPOS_InvActivityUI extends BillManageUI {

	private static final long serialVersionUID = 6479183562757542109L;

	@Override
	protected AbstractManageController createController() {
		return new MPOS_InvActivityCtrl();
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

	}

}
