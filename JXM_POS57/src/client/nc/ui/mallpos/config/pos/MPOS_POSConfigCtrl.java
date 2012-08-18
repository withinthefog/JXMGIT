package nc.ui.mallpos.config.pos;

import nc.ui.trade.bill.ICardController;
import nc.ui.trade.bill.ISingleController;
import nc.ui.trade.businessaction.IBusinessActionType;
import nc.ui.trade.button.IBillButton;
import nc.vo.mallpos.pub.MPOS_GlobalVariable;
import nc.vo.mallpos.pub.MPOS_POSConfigBillVO;
import nc.vo.mallpos.pub.MPOS_POSConfigVO;

/**
 * POS��������Ϣ������
 * 
 * @author cj
 * @date 2012-8-7 ����03:56:16
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
	 * �� �� true ʱ������Ϊ�����壻������ falseʱ������Ϊ����ͷ
	 */
	public boolean isSingleDetail() {
		return true;
	}

}
