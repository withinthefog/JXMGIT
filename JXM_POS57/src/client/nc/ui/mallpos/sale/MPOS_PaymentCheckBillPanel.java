package nc.ui.mallpos.sale;

import javax.swing.ListSelectionModel;

import nc.ui.pub.ClientEnvironment;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillData;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillModel;
import nc.vo.mallpos.sale.MPOS_PaymentVO;

/**
 * 付款核对面板
 * 
 * @author chenjun
 * @date 2011-12-11 下午04:40:47
 */
public class MPOS_PaymentCheckBillPanel extends BillCardPanel
{

	private static final long serialVersionUID = 1L;

	ClientEnvironment ce = ClientEnvironment.getInstance();

	public MPOS_PaymentCheckBillPanel()
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
		String sBodyItemKeys[] = new String[] { "paydate", "custcode", "custname", "nmoney", "result" };
		String sBodyItemCaptions[] = new String[] { "付款日期", "商户编码", "商户名称", "付款金额", "核对结果" };
		BillItem items[] = new BillItem[sBodyItemKeys.length];
		for (int i = 0; i < sBodyItemKeys.length; i++)
		{
			items[i] = new BillItem();
			items[i].setKey(sBodyItemKeys[i]);
			items[i].setName(sBodyItemCaptions[i]);
			items[i].setShowOrder(i);
			items[i].setWidth(100);
			items[i].setEdit(false);
			items[i].setNull(false);
			if ("nmoney".equals(sBodyItemKeys[i]))
			{
				items[i].setDataType(BillItem.DECIMAL);
				items[i].setDecimalDigits(2);
				items[i].setWidth(150);
				items[i].setTatol(true);
			} else if ("custname".equals(sBodyItemKeys[i]))
			{
				items[i].setWidth(150);
			} else if ("result".equals(sBodyItemKeys[i]))
			{
				items[i].setWidth(250);
			}
		}
		return items;
	}

	public void fillGrid(MPOS_PaymentVO[] vos)
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
	 * @return KNPOS_PaymentVO[]
	 */
	public MPOS_PaymentVO[] getBodyValueVOs()
	{
		return (MPOS_PaymentVO[]) getBillModel().getBodyValueVOs(MPOS_PaymentVO.class.getName());
	}

	/**
	 * 取得页面选择的VO(鼠标选中)
	 * 
	 * @return KNPOS_PaymentVO[]
	 */
	public MPOS_PaymentVO[] getBillSelectVOs()
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
		MPOS_PaymentVO[] vos = (MPOS_PaymentVO[]) getBillModel().getBodySelectedVOs(MPOS_PaymentVO.class.getName());
		return vos;
	}

}
