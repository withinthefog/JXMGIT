package nc.ui.mallpos.bd.housedoc;

import nc.ui.trade.controller.IControllerBase;
import nc.ui.trade.manage.BillManageUI;
import nc.ui.trade.treemanage.TreeManageEventHandler;

public class MPOS_HousedocHandler extends TreeManageEventHandler {

	public MPOS_HousedocHandler(BillManageUI arg0, IControllerBase arg1) {
		super(arg0, arg1);
	}

	protected void onBoSave() throws Exception {
		// ±Ìµ•∑«ø’≈–∂œ
		getBillCardPanelWrapper().getBillCardPanel().getBillData()
				.dataNotNullValidate();
		super.onBoSave();
	}
}
