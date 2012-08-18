package nc.ui.mallpos.config.pos;

import nc.ui.trade.bill.ICardController;
import nc.ui.trade.bill.ISingleController;
import nc.ui.trade.businessaction.IBusinessActionType;
import nc.ui.trade.button.IBillButton;
import nc.vo.mallpos.pub.MPOS_GlobalVariable;
import nc.vo.mallpos.pub.MPOS_POSConfigBillVO;
import nc.vo.mallpos.pub.MPOS_POSConfigVO;

/**
 * POS机配置信息控制类
 * 
 * @author cj
 * @date 2012-8-7 下午03:56:16
 */
public class MPOS_POSConfigCtrl implements ICardController,ISingleController{

	public String[] getCardBodyHideCol() {
		return null;
	}

	public int[] getCardButtonAry() {
		return new int[]{
				IBillButton.Edit,
				//IBillButton.Line,
				IBillButton.Save,
				IBillButton.Cancel
		};
	}

	public boolean isShowCardRowNo() {
		return false;
	}

	public boolean isShowCardTotal() {
		return false;
	}

	public String getBillType() {
		return MPOS_GlobalVariable.BILLTYPE_CONFIG_POS;
	}

	public String[] getBillVoName() {
		return new String[] { MPOS_POSConfigBillVO.class.getName(),
				MPOS_POSConfigVO.class.getName(),
				MPOS_POSConfigVO.class.getName() };
	}

	public String getBodyCondition() {
		return null;
	}

	public String getBodyZYXKey() {
		return null;
	}

	public int getBusinessActionType() {
		return IBusinessActionType.BD;
	}

	public String getChildPkField() {
		return new MPOS_POSConfigVO().getPrimaryKey();
	}

	public String getHeadZYXKey() {
		return null;
	}

	public String getPkField() {
		return new MPOS_POSConfigVO().getPrimaryKey();
	}

	public Boolean isEditInGoing() throws Exception {
		return null;
	}

	public boolean isExistBillStatus() {
		return false;
	}

	public boolean isLoadCardFormula() {
		return false;
	}

	/**
	 * 返 回 true 时，单据为单表体；当返回 false时，单据为单表头
	 */
	public boolean isSingleDetail() {
		return true;
	}

}
