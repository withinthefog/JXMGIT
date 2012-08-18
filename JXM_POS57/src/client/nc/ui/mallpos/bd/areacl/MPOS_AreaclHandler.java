package nc.ui.mallpos.bd.areacl;

import nc.ui.trade.bill.ICardController;
import nc.ui.trade.treecard.BillTreeCardUI;
import nc.ui.trade.treecard.TreeCardEventHandler;

public class MPOS_AreaclHandler extends TreeCardEventHandler {

	public MPOS_AreaclHandler(BillTreeCardUI arg0, ICardController arg1) {
		super(arg0, arg1);
	}

	protected void onBoSave() throws Exception {
		// ±Ìµ•∑«ø’≈–∂œ
		getBillCardPanelWrapper().getBillCardPanel().getBillData()
				.dataNotNullValidate();
		super.onBoSave();
	}

}
