package nc.ui.mallpos.bd.invdoc;

import nc.ui.trade.bill.AbstractManageController;
import nc.ui.trade.manage.BillManageUI;
import nc.ui.trade.manage.ManageEventHandler;
import nc.vo.pub.CircularlyAccessibleValueObject;

/**
 * 存货档案管理主界面
 * 
 * @author cj
 * @date 2012-8-7 下午03:54:12
 */
public class MPOS_InvdocUI extends BillManageUI {

	private static final long serialVersionUID = 1L;

//	public MPOS_InvdocUI() {
//		super();
//		//initBodyData();
//	}

	/**
	 * 初始化表体数据
	 * 
	 */
//	private void initBodyData() {
//		try {
//			((MPOS_InvdocHandler) getManageEventHandler())
//					.queryBodyData(getUIControl().getBodyCondition());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

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
	protected AbstractManageController createController() {
		return new MPOS_InvdocCtrl();
	}

	@Override
	protected ManageEventHandler createEventHandler() {
		return new MPOS_InvdocHandler(this,getUIControl());
	}

	@Override
	protected void initSelfData() {

	}

	@Override
	public void setDefaultData() throws Exception {

	}

}
