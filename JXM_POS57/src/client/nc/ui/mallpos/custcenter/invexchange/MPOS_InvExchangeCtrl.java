package nc.ui.mallpos.custcenter.invexchange;


import nc.ui.trade.bill.AbstractManageController;
import nc.ui.trade.businessaction.IBusinessActionType;
import nc.ui.trade.button.IBillButton;
import nc.vo.mallpos.invexchange.MPOS_InvExchangeBillVO;
import nc.vo.mallpos.invexchange.MPOS_InvExchangeItemVO;
import nc.vo.mallpos.invexchange.MPOS_InvExchangeSalesVO;
import nc.vo.mallpos.invexchange.MPOS_InvExchangeHeadVO;
import nc.vo.mallpos.pos.MPOS_SaleDetailHeadVO;
import nc.vo.mallpos.pub.MPOS_GlobalVariable;

/**
 * ÀñÆ·¶Ò»»¿ØÖÆÆ÷
 * @author cj
 * @date 2012-8-10 ÉÏÎç10:46:58
 */
public class MPOS_InvExchangeCtrl extends AbstractManageController{

	public String[] getCardBodyHideCol() {
		return null;
	}

	public int[] getCardButtonAry() {
		return new int[]{
				IBillButton.Add,
				IBillButton.Refresh,
				IBillButton.Edit,
				IBillButton.Line,
				IBillButton.Save,
				IBillButton.Cancel,				
				IBillButton.Return,
				IBillButton.Print
		};
	}

	public boolean isShowCardRowNo() {
		return false;
	}

	public boolean isShowCardTotal() {
		return false;
	}

	public String getBillType() {
		return MPOS_GlobalVariable.BILLTYPE_INVEXCHANGE;
	}

	public String[] getBillVoName() {
		return new String[] { MPOS_InvExchangeBillVO.class.getName(),
				MPOS_InvExchangeHeadVO.class.getName(),
				MPOS_InvExchangeItemVO.class.getName(),
				MPOS_InvExchangeSalesVO.class.getName() };
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
		return null;
	}

	public String getHeadZYXKey() {
		return null;
	}

	public String getPkField() {
		return new MPOS_SaleDetailHeadVO().getPrimaryKey();
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

	public String[] getListBodyHideCol() {
		return null;
	}

	public int[] getListButtonAry() {
		return new int[]{
				IBillButton.Query,
				IBillButton.Refresh,
				IBillButton.Add,
				IBillButton.Edit,
				IBillButton.Card
		};
	}

	public String[] getListHeadHideCol() {
		return null;
	}

	public boolean isShowListRowNo() {
		return true;
	}

	public boolean isShowListTotal() {
		return true;
	}
}
