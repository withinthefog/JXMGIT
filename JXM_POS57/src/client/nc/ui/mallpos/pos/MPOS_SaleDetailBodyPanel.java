package nc.ui.mallpos.pos;

import javax.swing.ListSelectionModel;

import nc.ui.pub.ClientEnvironment;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillData;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillItemNumberFormat;
import nc.ui.pub.bill.BillModel;
import nc.vo.mallpos.pos.MPOS_SaleDetailBodyVO;

/**
 * 收银界面表体
 * 
 * @author chenjun
 * @date 2012-1-4 下午10:11:20
 */
public class MPOS_SaleDetailBodyPanel extends BillCardPanel {

	private static final long serialVersionUID = 1L;

	ClientEnvironment ce = ClientEnvironment.getInstance();

	public MPOS_SaleDetailBodyPanel() {
		super();
		this.initialize();
	}

	/**
	 * 初始化
	 * 
	 */
	public void initialize() {
		// 设置表单内容
		setBillData(getThisBillData());
		// 界面设置
		getBillTable().setColumnSelectionAllowed(false);
		getBodyPanel().getRendererVO().setShowThMark(true);
		getBodyPanel().setTotalRowShow(true);// 设置合计行
		getAddLineMenuItem().setVisible(false);
		getDelLineMenuItem().setVisible(false);
		getInsertLineMenuItem().setVisible(false);
		getCopyLineMenuItem().setVisible(false);
		getPasteLineMenuItem().setVisible(false);
		getPasteLineToTailMenuItem().setVisible(false);
		// 设置只能单选
		this.getBillTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	/**
	 * 初始化表数据
	 * 
	 * @return BillData
	 */
	public BillData getThisBillData() {
		BillData billdata = new BillData();
		billdata.setBodyItems(getThisBodyItems());
		return billdata;
	}

	/**
	 * 得到表体项
	 * 
	 * @return BillItem[]
	 */
	public BillItem[] getThisBodyItems() {
		String sBodyItemKeys[] = new String[] {"sale_datetime","pk_balatype","balanname","received_money","cardno","nfee","exp_grade","discash_grade"};
		String sBodyItemCaptions[] = new String[] { "销售时间","结算方式主键","结算方式","实收金额","银行卡号","手续费","消费积分","折现积分"};
		BillItem items[] = new BillItem[sBodyItemKeys.length];
		BillItemNumberFormat format = new BillItemNumberFormat();
		format.setShowThMark(true);
		format.setShowRed(true);
		for (int i = 0; i < sBodyItemKeys.length; i++) {
			items[i] = new BillItem();
			items[i].setKey(sBodyItemKeys[i]);
			items[i].setName(sBodyItemCaptions[i]);
			items[i].setShowOrder(i);
			items[i].setWidth(100);
			items[i].setEdit(false);
			items[i].setNull(false);
			if(sBodyItemKeys[i].startsWith("pk_"))
				items[i].setShow(false);
			if ("received_money".equals(sBodyItemKeys[i]) || "nfee".equals(sBodyItemKeys[i])|| "discash_grade".equals(sBodyItemKeys[i])) {
				items[i].setDataType(BillItem.DECIMAL);
				items[i].setDecimalDigits(2);
				items[i].setTatol(true);
				items[i].setNumberFormat(format);
			}else if ("exp_grade".equals(sBodyItemKeys[i])) {
				items[i].setDataType(BillItem.DECIMAL);
				items[i].setDecimalDigits(2);
				items[i].setTatol(true);
				items[i].setNumberFormat(format);
				items[i].setShow(false);
			}else if("sale_datetime".equals(sBodyItemKeys[i])||"balanname".equals(sBodyItemKeys[i])||"cardno".equals(sBodyItemKeys[i]))
			{
				items[i].setWidth(150);
			}
				
		}
		return items;
	}

	public void fillGrid(MPOS_SaleDetailBodyVO[] vos) {

		this.getBillModel().setBodyDataVO(vos);
		// 执行表体项加载公式
		this.getBillModel().execLoadFormula();

		if (vos != null && vos.length > 0) {
			// 设置表头数据第一行被选中
			this.getBillTable().setRowSelectionInterval(0, 0);
		}
	}

	/**
	 * 取得页面VO
	 * 
	 * @return KNPOS_SaleVO[]
	 */
	public MPOS_SaleDetailBodyVO[] getBodyValueVOs() {
		return (MPOS_SaleDetailBodyVO[]) getBillModel().getBodyValueVOs(MPOS_SaleDetailBodyVO.class.getName());
	}

	/**
	 * 取得页面选择的VO(鼠标选中)
	 * 
	 * @return KNPOS_SaleDetailVO[]
	 */
	public MPOS_SaleDetailBodyVO[] getBillSelectVOs() {
		int row_count = getRowCount();
		if (row_count <= 0)
			return null;
		for (int i = 0; i < row_count; i++) {
			getBillModel().setRowState(i, BillModel.NORMAL);
		}
		int[] rows = getBillTable().getSelectedRows();
		for (int xxx = 0; xxx < rows.length; xxx++) {
			getBillModel().setRowState(rows[xxx], BillModel.SELECTED);
		}
		MPOS_SaleDetailBodyVO[] vos = (MPOS_SaleDetailBodyVO[]) getBillModel().getBodySelectedVOs(MPOS_SaleDetailBodyVO.class.getName());
		return vos;
	}

	/**
	 * 追加数据
	 * 
	 * @param vo
	 */
	public void appendVO(MPOS_SaleDetailBodyVO vo) {
		this.addLine();
		this.getBillModel().setBodyRowVO(vo, this.getRowCount() - 1);
		// 执行表体项加载公式
		this.getBillModel().execLoadFormula();
	}

}
