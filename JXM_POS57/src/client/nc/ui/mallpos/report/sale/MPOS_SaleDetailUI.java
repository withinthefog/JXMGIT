package nc.ui.mallpos.report.sale;

import nc.ui.trade.bill.AbstractManageController;
import nc.ui.trade.manage.BillManageUI;
import nc.ui.trade.manage.ManageEventHandler;
import nc.vo.pub.CircularlyAccessibleValueObject;

/**
 * 销售明细报表主界面
 * 
 * @author cj
 * @date 2012-8-10 上午10:45:03
 */
public class MPOS_SaleDetailUI extends BillManageUI {

	private static final long serialVersionUID = 1L;

	public MPOS_SaleDetailUI() {
		super();
		initHeadData();
	}

	/**
	 * 初始化表体数据
	 * 
	 */
	private void initHeadData() {
		try {
			((MPOS_SaleDetaiHandler) getManageEventHandler()).initHeadData();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	protected AbstractManageController createController() {
		return new MPOS_SaleDetaiCtrl();
	}

	@Override
	protected ManageEventHandler createEventHandler() {
		return new MPOS_SaleDetaiHandler(this, getUIControl());
	}

	@Override
	protected void initSelfData() {

	}

	@Override
	public void setDefaultData() throws Exception {

	}

}
