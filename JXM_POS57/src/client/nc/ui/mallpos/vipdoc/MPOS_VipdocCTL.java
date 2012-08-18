/**
 * 
 */
package nc.ui.mallpos.vipdoc;

import nc.ui.trade.bill.AbstractManageController;
import nc.ui.trade.businessaction.IBusinessActionType;
import nc.ui.trade.button.IBillButton;
import nc.vo.mallpos.pub.MPOS_GlobalVariable;
import nc.vo.mallpos.vipdoc.MPOS_VipdocCardVO;
import nc.vo.mallpos.vipdoc.MPOS_VipdocBillVO;
import nc.vo.mallpos.vipdoc.MPOS_VipdocVO;

/**
 * @author chenguogang
 *
 */
public class MPOS_VipdocCTL extends AbstractManageController {

	/**
	 * 
	 */
	public MPOS_VipdocCTL() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see nc.ui.trade.bill.ICardController#getCardBodyHideCol()
	 */
	public String[] getCardBodyHideCol() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see nc.ui.trade.bill.ICardController#getCardButtonAry()
	 */
	public int[] getCardButtonAry() {
		// TODO Auto-generated method stub
		return new int[]{
				IBillButton.Add,
				IBillButton.Edit,
				IBillButton.Delete,
				IBillButton.Save,
				IBillButton.Cancel,
				IBillButton.Line,
				IBillButton.Refresh,
				IBillButton.Return
				
		};
	}

	/* (non-Javadoc)
	 * @see nc.ui.trade.bill.ICardController#isShowCardRowNo()
	 */
	public boolean isShowCardRowNo() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see nc.ui.trade.bill.ICardController#isShowCardTotal()
	 */
	public boolean isShowCardTotal() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see nc.ui.trade.controller.IControllerBase#getBillType()
	 */
	public String getBillType() {
		// TODO Auto-generated method stub
		return MPOS_GlobalVariable.BILLTYPE_VIPDOC;
	}

	/* (non-Javadoc)
	 * @see nc.ui.trade.controller.IControllerBase#getBillVoName()
	 */
	public String[] getBillVoName() {
		// TODO Auto-generated method stub
		return new String[]{
				MPOS_VipdocBillVO.class.getName(),
				MPOS_VipdocVO.class.getName(),
				MPOS_VipdocCardVO.class.getName()
		};
	}

	/* (non-Javadoc)
	 * @see nc.ui.trade.controller.IControllerBase#getBodyCondition()
	 */
	public String getBodyCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see nc.ui.trade.controller.IControllerBase#getBodyZYXKey()
	 */
	public String getBodyZYXKey() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see nc.ui.trade.controller.IControllerBase#getBusinessActionType()
	 */
	public int getBusinessActionType() {
		// TODO Auto-generated method stub
		return IBusinessActionType.BD;
	}

	/* (non-Javadoc)
	 * @see nc.ui.trade.controller.IControllerBase#getChildPkField()
	 */
	public String getChildPkField() {
		// TODO Auto-generated method stub
		return new MPOS_VipdocCardVO().getPKFieldName();
	}

	/* (non-Javadoc)
	 * @see nc.ui.trade.controller.IControllerBase#getHeadZYXKey()
	 */
	public String getHeadZYXKey() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see nc.ui.trade.controller.IControllerBase#getPkField()
	 */
	public String getPkField() {
		// TODO Auto-generated method stub
		return new MPOS_VipdocVO().getPKFieldName();
	}

	/* (non-Javadoc)
	 * @see nc.ui.trade.controller.IControllerBase#isEditInGoing()
	 */
	public Boolean isEditInGoing() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see nc.ui.trade.controller.IControllerBase#isExistBillStatus()
	 */
	public boolean isExistBillStatus() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see nc.ui.trade.controller.IControllerBase#isLoadCardFormula()
	 */
	public boolean isLoadCardFormula() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see nc.ui.trade.bill.IListController#getListBodyHideCol()
	 */
	public String[] getListBodyHideCol() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see nc.ui.trade.bill.IListController#getListButtonAry()
	 */
	public int[] getListButtonAry() {
		// TODO Auto-generated method stub
		return new int[]{
				IBillButton.Add,
				IBillButton.Edit,
				IBillButton.Delete,
				IBillButton.Query,
				IBillButton.Card,
		};
	}

	/* (non-Javadoc)
	 * @see nc.ui.trade.bill.IListController#getListHeadHideCol()
	 */
	public String[] getListHeadHideCol() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see nc.ui.trade.bill.IListController#isShowListRowNo()
	 */
	public boolean isShowListRowNo() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see nc.ui.trade.bill.IListController#isShowListTotal()
	 */
	public boolean isShowListTotal() {
		// TODO Auto-generated method stub
		return false;
	}

}
