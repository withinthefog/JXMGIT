/**
 * 
 */
package nc.ui.mallpos.vipcard;

import nc.bs.framework.common.NCLocator;
import nc.itf.uif.pub.IUifService;
import nc.ui.pub.FramePanel;
import nc.ui.trade.base.IBillOperate;
import nc.ui.trade.bill.AbstractManageController;
import nc.ui.trade.buffer.BillUIBuffer;
import nc.ui.trade.manage.BillManageUI;
import nc.ui.mallpos.vipcard.MPOS_VipcardHDL;
import nc.ui.trade.manage.ManageEventHandler;
import nc.vo.mallpos.vipcard.MPOS_VipcardVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;

/**
 * @author chenguogang
 *
 */
public class MPOS_VipcardUI extends BillManageUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public MPOS_VipcardUI() {
		// TODO Auto-generated constructor stub
		initData();
	}

	/**
	 * @param fp
	 */
	public MPOS_VipcardUI(FramePanel fp) {
		super(fp);
		initData();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param useBillSource
	 */
	public MPOS_VipcardUI(Boolean useBillSource) {
		super(useBillSource);
		initData();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @param arg4
	 */
	public MPOS_VipcardUI(String arg0, String arg1, String arg2, String arg3,
			String arg4) {
		super(arg0, arg1, arg2, arg3, arg4);
		initData();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see nc.ui.trade.manage.BillManageUI#createController()
	 */
	@Override
	protected AbstractManageController createController() {
		// TODO Auto-generated method stub
		return new MPOS_VipcardCTL();
	}
	
	

	@Override
	protected ManageEventHandler createEventHandler() {
		// TODO Auto-generated method stub
		return new MPOS_VipcardHDL(this, this.getUIControl());
	}

	/* (non-Javadoc)
	 * @see nc.ui.trade.manage.BillManageUI#setBodySpecialData(nc.vo.pub.CircularlyAccessibleValueObject[])
	 */
	@Override
	public void setBodySpecialData(CircularlyAccessibleValueObject[] arg0)
			throws Exception {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see nc.ui.trade.manage.BillManageUI#setHeadSpecialData(nc.vo.pub.CircularlyAccessibleValueObject, int)
	 */
	@Override
	protected void setHeadSpecialData(CircularlyAccessibleValueObject arg0,
			int arg1) throws Exception {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see nc.ui.trade.manage.BillManageUI#setTotalHeadSpecialData(nc.vo.pub.CircularlyAccessibleValueObject[])
	 */
	@Override
	protected void setTotalHeadSpecialData(
			CircularlyAccessibleValueObject[] arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see nc.ui.trade.base.AbstractBillUI#initSelfData()
	 */
	@Override
	protected void initSelfData() {
		// TODO Auto-generated method stub
	}
	/**
	 * 初始化时查询数据
	 */
	private void initData(){
		IUifService service = NCLocator.getInstance().lookup(IUifService.class);
		BillUIBuffer uiBuffer = this.getBufferData();
		try{
			uiBuffer.clear();
			MPOS_VipcardVO[] vos = (MPOS_VipcardVO[])service.queryByCondition(MPOS_VipcardVO.class, " isnull(dr,0)=0 ");
			for(int i=0;vos!=null&&i<vos.length;i++){
				AggregatedValueObject aVo = (AggregatedValueObject) Class.forName(
						this.getUIControl().getBillVoName()[0]).newInstance();
				aVo.setParentVO(vos[i]);
				uiBuffer.addVOToBuffer(aVo);
			}
			updateBuffer();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 刷新数据
	 * @throws Exception
	 */
	private void updateBuffer() throws Exception{
		if (getBufferData().getVOBufferSize() != 0) {

			setListHeadData(
					getBufferData().getAllHeadVOsFromBuffer());
			setBillOperate(IBillOperate.OP_NOTEDIT);
			getBufferData().setCurrentRow(0);
		} else {
			setListHeadData(null);
			setBillOperate(IBillOperate.OP_INIT);
			getBufferData().setCurrentRow(-1);
			showHintMessage(
					nc.ui.ml.NCLangRes.getInstance().getStrByID("uifactory",
							"UPPuifactory-000066")/* @res "没有查到任何满足条件的数据!" */);
		}
	}
	/* (non-Javadoc)
	 * @see nc.ui.trade.base.AbstractBillUI#setDefaultData()
	 */
	@Override
	public void setDefaultData() throws Exception {
		// TODO Auto-generated method stub

	}

}
