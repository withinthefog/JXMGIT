package nc.ui.mallpos.sale;

import javax.swing.ListSelectionModel;

import nc.ui.pub.ClientEnvironment;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillData;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillItemNumberFormat;
import nc.ui.pub.bill.BillModel;
import nc.vo.mallpos.sale.MPOS_ReceivablesVO;

/**
 * 应收款项面板
 * 
 * @author chenjun
 * @date 2011-11-22 下午03:01:55
 */
public class MPOS_ReceBillPanel extends BillCardPanel
{

	private static final long serialVersionUID = 1L;

	ClientEnvironment ce = ClientEnvironment.getInstance();

	public MPOS_ReceBillPanel()
	{
		super();
		this.initialize();
	}

	/**
	 * 初始化
	 * 
	 */
	public void initialize()
	{
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
	public BillData getThisBillData()
	{
		BillData billdata = new BillData();
		billdata.setBodyItems(getThisBodyItems());
		return billdata;
	}

	/**
	 * 得到表体项
	 * 
	 * @return BillItem[]
	 */
	public BillItem[] getThisBodyItems()
	{
		String sBodyItemKeys[] = new String[] { "pk_receivables", "pk_cubasdoc", "custcode", "custname", "billdate", "amount", "billstatus", "strbillstatus", "pk_busibill" };
		String sBodyItemCaptions[] = new String[] { "主键", "商户主键", "商户编码", "商户名称", "单据日期", "应收金额", "状态", "状态", "付款单主键" };
		BillItem items[] = new BillItem[sBodyItemKeys.length];
		BillItemNumberFormat format = new BillItemNumberFormat();
		format.setNegativeSign(false);//不显示负号
		for (int i = 0; i < sBodyItemKeys.length; i++)
		{
			items[i] = new BillItem();
			items[i].setKey(sBodyItemKeys[i]);
			items[i].setName(sBodyItemCaptions[i]);
			items[i].setShowOrder(i);
			items[i].setWidth(100);
			items[i].setEdit(false);
			items[i].setNull(false);
			if ("pk_receivables".equals(sBodyItemKeys[i]) || "pk_cubasdoc".equals(sBodyItemKeys[i]) || "billstatus".equals(sBodyItemKeys[i]) || "pk_busibill".equals(sBodyItemKeys[i]))
			{
				items[i].setShow(false);
			} else if ("amount".equals(sBodyItemKeys[i]))
			{
				items[i].setDataType(BillItem.DECIMAL);
				items[i].setDecimalDigits(8);
				items[i].setWidth(150);
				items[i].setTatol(true);
				
				items[i].setNumberFormat(format);
			} else if ("custname".equals(sBodyItemKeys[i]))
			{
				items[i].setWidth(150);
			}
		}
		return items;
	}

	public void fillGrid(MPOS_ReceivablesVO[] vos)
	{

		this.getBillModel().setBodyDataVO(vos);
		// 执行表体项加载公式
		this.getBillModel().execLoadFormula();

		if (vos != null && vos.length > 0)
		{
			// 设置表头数据第一行被选中
			this.getBillTable().setRowSelectionInterval(0, 0);
		}
	}

	/**
	 * 取得页面VO
	 * 
	 * @return KNPOS_SaleVO[]
	 */
	public MPOS_ReceivablesVO[] getBodyValueVOs()
	{
		return (MPOS_ReceivablesVO[]) getBillModel().getBodyValueVOs(MPOS_ReceivablesVO.class.getName());
	}

	/**
	 * 取得页面选择的VO(鼠标选中)
	 * 
	 * @return KNPOS_SaleVO[]
	 */
	public MPOS_ReceivablesVO[] getBillSelectVOs()
	{
		int row_count = getRowCount();
		if (row_count <= 0)
			return null;
		for (int i = 0; i < row_count; i++)
		{
			getBillModel().setRowState(i, BillModel.NORMAL);
		}
		int[] rows = getBillTable().getSelectedRows();
		for (int xxx = 0; xxx < rows.length; xxx++)
		{
			getBillModel().setRowState(rows[xxx], BillModel.SELECTED);
		}
		MPOS_ReceivablesVO[] vos = (MPOS_ReceivablesVO[]) getBillModel().getBodySelectedVOs(MPOS_ReceivablesVO.class.getName());
		return vos;
	}

}
