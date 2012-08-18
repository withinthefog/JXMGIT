package nc.ui.mallpos.custcenter.sdperfect;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.trade.bill.AbstractManageController;
import nc.ui.trade.manage.BillManageUI;
import nc.ui.trade.manage.ManageEventHandler;
import nc.vo.mallpos.pub.MPOS_GlobalVariable;
import nc.vo.pub.CircularlyAccessibleValueObject;

/**
 * 销售明细数据完善主界面
 * 
 * @author cj
 * @date 2012-8-10 上午10:45:03
 */
public class MPOS_SDPerfectUI extends BillManageUI {

	private static final long serialVersionUID = 1L;

	public MPOS_SDPerfectUI() {
		super();
		initHeadData();
	}

	/**
	 * 初始化表体数据
	 * 
	 */
	private void initHeadData() {
		try {
			((MPOS_SDPerfectHandler) getManageEventHandler()).initHeadData();
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
		return new MPOS_SDPerfectCtrl();
	}

	@Override
	protected ManageEventHandler createEventHandler() {
		return new MPOS_SDPerfectHandler(this, getUIControl());
	}

	@Override
	protected void initSelfData() {
		//设置商品大小类的参照条件
		UIRefPane goodsMain = (UIRefPane)this.getBillCardPanel().getHeadItem("pk_goods_main").getComponent();
		goodsMain.setWhereString(" bd_defdoc.pk_defdoclist = (select l.pk_defdoclist from bd_defdoclist l where l.doclistcode = '"+MPOS_GlobalVariable.DEFDOC_GOODS_MAIN+"') ");
		
		UIRefPane goodsSub = (UIRefPane)this.getBillCardPanel().getHeadItem("pk_goods_sub").getComponent();
		goodsSub.setWhereString(" bd_defdoc.pk_defdoclist = (select l.pk_defdoclist from bd_defdoclist l where l.doclistcode = '"+MPOS_GlobalVariable.DEFDOC_GOODS_SUB+"') ");
	}

	@Override
	public void setDefaultData() throws Exception {

	}

}
