package nc.ui.mallpos.sale;

import nc.ui.pub.beans.UIComboBox;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillData;
import nc.ui.pub.bill.BillItem;
import nc.vo.bd.ref.RefNodeNameConst;

public class MPOS_ReceQueryPanel extends BillCardPanel
{

	private static final long serialVersionUID = 1L;

	public MPOS_ReceQueryPanel()
	{
		super();
		this.initialize();
	}

	/**
	 * ��ʼ��
	 * 
	 */
	private void initialize()
	{
		// ���ñ�����
		setBillData(getThisBillData());
		initializeEX();
	}

	/**
	 * ��ʼ����չ<br>
	 */
	private void initializeEX()
	{
		/* ��ʼ״̬ */
		UIComboBox m_BoxStatusv = (UIComboBox) getHeadItem("billstatus").getComponent();
		m_BoxStatusv.removeAllItems();
		String[] status = { "", "δ����", "�Ѵ���" };
		m_BoxStatusv.addItems(status);
	}

	/**
	 * ��ʼ��������
	 * 
	 * @return BillData
	 */
	public BillData getThisBillData()
	{
		BillData billdata = new BillData();
		billdata.setHeadItems(getThisHeadItems());
		return billdata;
	}

	/**
	 * �õ���ͷ��
	 * 
	 * @return BillItem[]
	 */
	private BillItem[] getThisHeadItems()
	{
		String sHeadItemKeys[] = new String[] { "pk_cubasdoc", "billdate", "billstatus" };
		String sHeadItemCaptions[] = new String[] { "�̻�", "��������", "״̬" };
		BillItem items[] = new BillItem[sHeadItemKeys.length];
		for (int i = 0; i < sHeadItemKeys.length; i++)
		{
			items[i] = new BillItem();
			items[i].setKey(sHeadItemKeys[i]);
			items[i].setName(sHeadItemCaptions[i]);
			items[i].setShowOrder(i);
			items[i].setWidth(1);
			items[i].setEdit(true);
			items[i].setNull(false);
			if ("pk_cubasdoc".equals(sHeadItemKeys[i]))
			{
				items[i].setDataType(BillItem.UFREF);
				items[i].setRefType(RefNodeNameConst.CUSTBASDOC);
			} else if ("billstatus".equals(sHeadItemKeys[i]))
			{
				items[i].setDataType(BillItem.COMBO);
			} else if ("billdate".equals(sHeadItemKeys[i]))
			{
				items[i].setDataType(BillItem.DATE);
			}
		}
		return items;
	}

}