package nc.ui.mallpos.config.pos;

import nc.ui.trade.bill.ICardController;
import nc.ui.trade.card.BillCardUI;
import nc.ui.trade.card.CardEventHandler;

/**
 * POS机配置信息主界面
 * 
 * @author cj
 * @date 2012-8-7 下午03:54:12
 */
public class MPOS_POSConfigUI extends BillCardUI {

	private static final long serialVersionUID = 1L;
	

	public MPOS_POSConfigUI() {
		super();
		initBodyData();
	}

	@Override
	protected ICardController createController() {
		return new MPOS_POSConfigCtrl();
	}
	
	@Override
	protected CardEventHandler createEventHandler() {
		return new MPOS_POSConfigHandler(this,getUIControl());
	}

	@Override
	public String getRefBillType() {
		return null;
	}

	@Override
	protected void initSelfData() {
	}

	@Override
	public void setDefaultData() throws Exception {
		
	}
	
	/**
	 * 初始化表体数据
	 *
	 */
	private void initBodyData() {
		try {
			((MPOS_POSConfigHandler) getCardEventHandler())
					.queryBodyData(getUIControl().getBodyCondition());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
