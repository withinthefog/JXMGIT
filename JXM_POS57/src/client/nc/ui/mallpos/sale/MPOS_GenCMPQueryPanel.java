package nc.ui.mallpos.sale;

import nc.bs.framework.common.NCLocator;
import nc.itf.mallpos.pub.MPOS_PubQueryItf;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillData;
import nc.ui.pub.bill.BillItem;
import nc.vo.bd.ref.RefNodeNameConst;
import nc.vo.mallpos.pub.MPOS_ArgConfigVO;
import nc.vo.pub.lang.UFDate;

public class MPOS_GenCMPQueryPanel extends BillCardPanel
{

	private static final long serialVersionUID = 1L;

	public MPOS_GenCMPQueryPanel()
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

		try
		{
			MPOS_PubQueryItf iquery = (MPOS_PubQueryItf) NCLocator.getInstance().lookup(MPOS_PubQueryItf.class);
			UFDate prePaydate = null;// �ϴθ�������
			MPOS_ArgConfigVO cvo = iquery.queryFirst();
			if (cvo == null)
				return;
			prePaydate = iquery.queryPrePayDate();
			if (prePaydate == null)// �״θ���
			{
				prePaydate = cvo.getFirst_paydate().getDateBefore(cvo.getPay_cycle());
			}
			this.getHeadItem("prepaydate").setValue(prePaydate);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
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
		String sHeadItemKeys[] = new String[] { "custcode", "prepaydate" };
		String sHeadItemCaptions[] = new String[] { "�̻�", "�ϴθ�������" };
		BillItem items[] = new BillItem[sHeadItemKeys.length];
		for (int i = 0; i < sHeadItemKeys.length; i++)
		{
			items[i] = new BillItem();
			items[i].setKey(sHeadItemKeys[i]);
			items[i].setName(sHeadItemCaptions[i]);
			items[i].setShowOrder(i);
			items[i].setWidth(1);
			items[i].setEdit(true);
			items[i].setNull(true);
			if ("custcode".equals(sHeadItemKeys[i]))
			{
				items[i].setDataType(BillItem.UFREF);
				items[i].setRefType(RefNodeNameConst.CUSTBASDOC);
			} else if ("prepaydate".equals(sHeadItemKeys[i]))
			{
				items[i].setDataType(BillItem.DATE);
			}
		}
		return items;
	}

}