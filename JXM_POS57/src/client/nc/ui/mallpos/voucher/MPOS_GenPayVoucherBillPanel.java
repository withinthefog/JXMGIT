package nc.ui.mallpos.voucher;

import javax.swing.ListSelectionModel;

import nc.ui.pub.ClientEnvironment;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillData;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillModel;
import nc.vo.mallpos.sale.MPOS_PaymentVO;

/**
 * ����ƾ֤���������
 * 
 * @author chenjun
 * @date 2011-12-21 ����02:53:01
 */
public class MPOS_GenPayVoucherBillPanel extends BillCardPanel
{

	private static final long serialVersionUID = 1L;

	ClientEnvironment ce = ClientEnvironment.getInstance();

	public MPOS_GenPayVoucherBillPanel()
	{
		super();
		this.initialize();
	}

	/**
	 * ��ʼ��
	 * 
	 */
	public void initialize()
	{
		// ���ñ�����
		setBillData(getThisBillData());
		// ��������
		getBillTable().setColumnSelectionAllowed(false);
		getBodyPanel().getRendererVO().setShowThMark(true);
		getBodyPanel().setTotalRowShow(true);// ���úϼ���
		getAddLineMenuItem().setVisible(false);
		getDelLineMenuItem().setVisible(false);
		getInsertLineMenuItem().setVisible(false);
		getCopyLineMenuItem().setVisible(false);
		getPasteLineMenuItem().setVisible(false);
		getPasteLineToTailMenuItem().setVisible(false);
		// ����ֻ�ܵ�ѡ
		this.getBillTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}

	/**
	 * ��ʼ��������
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
	 * �õ�������
	 * 
	 * @return BillItem[]
	 */
	public BillItem[] getThisBodyItems()
	{
		String sBodyItemKeys[] = new String[] { "vouchid", "djbh", "paydate", "custcode", "custname", "nmoney","zyx2" };
		String sBodyItemCaptions[] = new String[] { "������㵥����", "���ݱ��", "��������", "�̻�����", "�̻�����", "������","ƾ֤����״̬" };
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
			if ("vouchid".equals(sBodyItemKeys[i]))
			{
				items[i].setShow(false);
			} else if ("nmoney".equals(sBodyItemKeys[i]))
			{
				items[i].setDataType(BillItem.DECIMAL);
				items[i].setDecimalDigits(2);
				items[i].setWidth(150);
				items[i].setTatol(true);
			} else if ("custname".equals(sBodyItemKeys[i]))
			{
				items[i].setWidth(150);
			} else if ("djbh".equals(sBodyItemKeys[i]))
			{
				items[i].setWidth(120);
			}
		}
		return items;
	}

	public void fillGrid(MPOS_PaymentVO[] vos)
	{

		this.getBillModel().setBodyDataVO(vos);
		// ִ�б�������ع�ʽ
		this.getBillModel().execLoadFormula();

		if (vos != null && vos.length > 0)
		{
			// ���ñ�ͷ���ݵ�һ�б�ѡ��
			this.getBillTable().setRowSelectionInterval(0, 0);
		}
	}

	/**
	 * ȡ��ҳ��VO
	 * 
	 * @return KNPOS_PaymentVO[]
	 */
	public MPOS_PaymentVO[] getBodyValueVOs()
	{
		return (MPOS_PaymentVO[]) getBillModel().getBodyValueVOs(MPOS_PaymentVO.class.getName());
	}

	/**
	 * ȡ��ҳ��ѡ���VO(���ѡ��)
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
