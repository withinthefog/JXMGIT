/**
 * 
 */
package nc.ui.mallpos.vipdoc;

import nc.bs.framework.common.NCLocator;
import nc.dev.tools.UtilTools;
import nc.itf.uif.pub.IUifService;
import nc.ui.trade.controller.IControllerBase;
import nc.ui.trade.manage.BillManageUI;
import nc.ui.trade.manage.ManageEventHandler;
import nc.vo.mallpos.vipcard.MPOS_VipcardVO;
import nc.vo.mallpos.vipdoc.MPOS_VipdocCardVO;
import nc.vo.pub.AggregatedValueObject;

/**
 * @author chenguogang
 *
 */
public class MPOS_VipdocHDL extends ManageEventHandler {

	/**
	 * @param billUi
	 * @param control
	 */
	public MPOS_VipdocHDL(BillManageUI billUi, IControllerBase control) {
		super(billUi, control);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onBoRefresh() throws Exception {
		// TODO Auto-generated method stub
		super.onBoRefresh();
	}

	@Override
	protected void onBoSave() throws Exception {
		// TODO Auto-generated method stub
		AggregatedValueObject billVO = getBillUI().getVOFromUI();
		super.onBoSave();
		if(billVO.getChildrenVO()!=null){
			MPOS_VipdocCardVO[] vipdocCardVOs = (MPOS_VipdocCardVO[])billVO.getChildrenVO();
			IUifService service = (IUifService) NCLocator.getInstance().lookup(IUifService.class.getName());
			String[] vip_card_pks = new String[vipdocCardVOs.length];
			int i=0;
			for(MPOS_VipdocCardVO vipdocCardVO:vipdocCardVOs){
				String pk_vip_card = vipdocCardVO.getPk_vip_card();
				vip_card_pks[i]=pk_vip_card;
				i++;
			}
			String condition = "pk_vip_card in ("+UtilTools.getPkString(vip_card_pks)+")";
			MPOS_VipcardVO[] vipCardVOs = (MPOS_VipcardVO[])service.queryByCondition(MPOS_VipcardVO.class, condition);
			for(MPOS_VipcardVO vipCardVO:vipCardVOs){
				vipCardVO.setWstatus(""+1);
			}
			service.updateAry(vipCardVOs);
		}
		super.onBoRefresh();
	}
	
	

}
