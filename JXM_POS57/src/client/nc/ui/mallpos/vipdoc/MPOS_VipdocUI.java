/**
 * 
 */
package nc.ui.mallpos.vipdoc;

import nc.bs.framework.common.NCLocator;
import nc.itf.uif.pub.IUifService;
import nc.ui.pub.ClientEnvironment;
import nc.ui.pub.FramePanel;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.trade.base.IBillOperate;
import nc.ui.trade.bill.AbstractManageController;
import nc.ui.trade.bsdelegate.BusinessDelegator;
import nc.ui.trade.buffer.BillUIBuffer;
import nc.ui.trade.manage.BillManageUI;
import nc.ui.trade.manage.ManageEventHandler;
import nc.vo.mallpos.vipcard.MPOS_VipcardVO;
import nc.vo.mallpos.vipcardgrade.MPOS_VipcardgradeVO;
import nc.vo.mallpos.vipdoc.MPOS_VipdocBillVO;
import nc.vo.mallpos.vipdoc.MPOS_VipdocCardVO;
import nc.vo.mallpos.vipdoc.MPOS_VipdocVO;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFDate;

/**
 * @author chenguogang
 *
 */
public class MPOS_VipdocUI extends BillManageUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public MPOS_VipdocUI() {
		// TODO Auto-generated constructor stub
		initData();
	}

	/**
	 * @param fp
	 */
	public MPOS_VipdocUI(FramePanel fp) {
		super(fp);
		initData();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param useBillSource
	 */
	public MPOS_VipdocUI(Boolean useBillSource) {
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
	public MPOS_VipdocUI(String arg0, String arg1, String arg2, String arg3,
			String arg4) {
		super(arg0, arg1, arg2, arg3, arg4);
		initData();
		// TODO Auto-generated constructor stub
	}

	private void initData(){
		IUifService service = NCLocator.getInstance().lookup(IUifService.class);
		try{
			MPOS_VipdocVO[] vos = (MPOS_VipdocVO[])service.queryByCondition(MPOS_VipdocVO.class," isnull(dr,0)=0 ");
			BillUIBuffer uiBuffer = this.getBufferData();
			if(vos!=null){
				for(MPOS_VipdocVO vo:vos){
					MPOS_VipdocBillVO billVo =new MPOS_VipdocBillVO();
					billVo.setParentVO(vo);
					uiBuffer.addVOToBuffer(billVo);
				}
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
	 * @see nc.ui.trade.manage.BillManageUI#createController()
	 */
	@Override
	protected AbstractManageController createController() {
		// TODO Auto-generated method stub
		return new MPOS_VipdocCTL();
	}
	
	

	@Override
	protected ManageEventHandler createEventHandler() {
		// TODO Auto-generated method stub
		return new MPOS_VipdocHDL(this, this.getUIControl());
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

	/* (non-Javadoc)
	 * @see nc.ui.trade.base.AbstractBillUI#setDefaultData()
	 */
	@Override
	public void setDefaultData() throws Exception {
		// TODO Auto-generated method stub
		ClientEnvironment ce = ClientEnvironment.getInstance();
		String userpk = ce.getUser().getPrimaryKey();
		UFDate currdate = ce.getDate();
		getBillCardPanel().setHeadItem("pk_creator", userpk);
		getBillCardPanel().setHeadItem("create_date", currdate);
	}

	@Override
	public void afterEdit(BillEditEvent e) {
		// TODO Auto-generated method stub
		if(e.getKey().trim().equals("not_pk_vip_card")){//选择了会员卡 则将会员卡的其他信息都带出来
			int row = e.getRow();
			String pk_vip_card = (String)getBillCardPanel().getBodyValueAt(row, "pk_vip_card");
			//遍历本条记录是否已有关联了相同的会员卡信息
			int rows = getBillCardPanel().getRowCount();
			for(int _row=0;_row<rows&&pk_vip_card!=null&&!pk_vip_card.trim().equals("");_row++){
				if(_row!=row){
					String _pk_vip_card = (String)getBillCardPanel().getBodyValueAt(_row, "pk_vip_card");
					if(_pk_vip_card!=null&&_pk_vip_card.equals(pk_vip_card)){
						this.showErrorMessage("该会员卡已关联了该会员，不允许重复关联！");
						getBillCardPanel().setBodyValueAt(null, row, "pk_vip_card");
						getBillCardPanel().setBodyValueAt(null, row, "not_pk_vip_card");
						getBillCardPanel().setBodyValueAt(null, row, "inner_code");
						getBillCardPanel().setBodyValueAt(null, row, "print_code");
						getBillCardPanel().setBodyValueAt(null, row, "wstatus");
						getBillCardPanel().setBodyValueAt(null, row, "grade_name");
						return;
					}
				}
			}
			IUifService service = NCLocator.getInstance().lookup(IUifService.class);
			MPOS_VipcardVO vipCardVo = new MPOS_VipcardVO();
			MPOS_VipcardgradeVO vipcardGradeVO = new MPOS_VipcardgradeVO();
			if(pk_vip_card!=null&&!pk_vip_card.trim().equals("")){
				try {
					vipCardVo = (MPOS_VipcardVO)service.queryByPrimaryKey(MPOS_VipcardVO.class, pk_vip_card);
					String pk_vipcard_grade = vipCardVo.getPk_vipcard_grade();
					vipcardGradeVO = (MPOS_VipcardgradeVO)service.queryByPrimaryKey(MPOS_VipcardgradeVO.class, pk_vipcard_grade);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			getBillCardPanel().setBodyValueAt(vipCardVo.getInner_code(), row, "inner_code");
			getBillCardPanel().setBodyValueAt(vipCardVo.getPrint_code(), row, "print_code");
			getBillCardPanel().setBodyValueAt(vipCardVo.getWstatus(), row, "wstatus");
			getBillCardPanel().setBodyValueAt(vipcardGradeVO.getGrade_name(), row, "grade_name");
		}
		super.afterEdit(e);
	}

	@Override
	protected BusinessDelegator createBusinessDelegator() {
		// TODO Auto-generated method stub
		return new MPOS_VipdocBusinessDelegator();
	}
	
	

}
