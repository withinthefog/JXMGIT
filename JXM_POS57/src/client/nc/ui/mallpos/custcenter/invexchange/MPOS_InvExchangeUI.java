package nc.ui.mallpos.custcenter.invexchange;

import nc.ui.mallpos.pub.MPOS_ClientQueryBO;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.trade.bill.AbstractManageController;
import nc.ui.trade.business.HYPubBO_Client;
import nc.ui.trade.manage.ManageEventHandler;
import nc.ui.trade.multichild.MultiChildBillManageUI;
import nc.vo.mallpos.invexchange.MPOS_InvActivityBodyVO;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFDouble;

/**
 * 礼品兑换主界面
 * 
 * @author cj
 * @date 2012-8-10 上午10:45:03
 */
public class MPOS_InvExchangeUI extends MultiChildBillManageUI {

	private static final long serialVersionUID = 1L;

	public MPOS_InvExchangeUI() {
		super();
		getBillCardPanel().addEditListener(this);
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
		return new MPOS_InvExchangeCtrl();
	}

	@Override
	protected ManageEventHandler createEventHandler() {
		return new MPOS_InvExchangeHandler(this, getUIControl());
	}

	@Override
	protected void initSelfData() {
		
	}

	@Override
	public void setDefaultData() throws Exception {
		getBillCardPanel().getTailItem("create_date").setValue(
				getClientEnvironment().getDate());
		getBillCardPanel().getHeadItem("bill_date").setValue(
				getClientEnvironment().getDate());
		getBillCardPanel().getTailItem("pk_creator").setValue(
				getClientEnvironment().getUser().getPrimaryKey());
	}
	
	@Override
	public void afterEdit(BillEditEvent e) {
		if("invname".equals(e.getKey())) {
			onInvEdit(e);
		}
		super.afterEdit(e);
	}

	/**
	 * 编辑礼品列表时将单价,积分,所属供应商带出来
	 */
	private void onInvEdit(BillEditEvent e) {
		int row = e.getRow();
		if(row < 0) return;

		MPOS_ClientQueryBO bo = new MPOS_ClientQueryBO();
		String pk_invdoc = (String) getBillCardPanel().getBillModel().getValueAt(row, "pk_invdoc");
		String pk_activity = (String) getBillCardPanel().getHeadItem("pk_activity").getValueObject();
		String pk_cubasdoc = null;
		UFDouble costprice = null;
		UFDouble cost_grade = null;
		
		try {
			MPOS_InvActivityBodyVO item =  bo.getActivityInvCust(pk_activity, pk_invdoc);
			pk_cubasdoc = item.getPk_cubasdoc();
			costprice = item.getCost_price();
			cost_grade = item.getCost_grade();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		getBillCardPanel().getBillModel().setValueAt(pk_cubasdoc, row, "pk_cubasdoc");
		getBillCardPanel().getBillModel().setValueAt(costprice, row, "cost_price");
		getBillCardPanel().getBillModel().setValueAt(cost_grade, row, "gradeprice");

		getBillCardPanel().getBillModel().execLoadFormulaByKey("pk_cubasdoc");
		getBillCardPanel().getBillModel().execEditFormulaByKey(row, "quantity");
	}
	
	@Override
	protected String getBillNo() throws Exception {
		return HYPubBO_Client.getBillNo(getUIControl().getBillType(),
				getCorpPrimaryKey(), null, null);
	}
}
