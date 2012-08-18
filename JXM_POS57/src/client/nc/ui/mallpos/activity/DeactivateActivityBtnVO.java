package nc.ui.mallpos.activity;

import nc.ui.trade.base.IBillOperate;
import nc.ui.trade.button.IBillButtonVO;
import nc.vo.mallpos.pub.MPOS_ExtButton;
import nc.vo.trade.button.ButtonVO;

public class DeactivateActivityBtnVO implements IBillButtonVO {

private static final String BUTTON_NAME = "取消生效";
	
	public DeactivateActivityBtnVO() {
		super();
	}

	public ButtonVO getButtonVO() {
		ButtonVO btnVO = new ButtonVO();
		btnVO.setBtnNo(MPOS_ExtButton.DeactivateActivity);
		btnVO.setBtnName(BUTTON_NAME);
		btnVO.setBtnChinaName(BUTTON_NAME);
		btnVO.setHintStr(BUTTON_NAME);
		btnVO.setOperateStatus(new int[] {IBillOperate.OP_NOTEDIT,IBillOperate.OP_NOADD_NOTEDIT});
		btnVO.setBusinessStatus(null);
		return btnVO;
	}

}
