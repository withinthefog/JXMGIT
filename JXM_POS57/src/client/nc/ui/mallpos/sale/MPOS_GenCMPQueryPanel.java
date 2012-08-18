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
	 * 初始化
	 * 
	 */
	private void initialize()
	{
		// 设置表单内容
		setBillData(getThisBillData());
		initializeEX();
	}

	/**
	 * 初始化扩展<br>
	 */
	private void initializeEX()
	{

		try
		{
			MPOS_PubQueryItf iquery = (MPOS_PubQueryItf) NCLocator.getInstance().lookup(MPOS_PubQueryItf.class);
			UFDate prePaydate = null;// 上次付款日期
			MPOS_ArgConfigVO cvo = iquery.queryFirst();
			if (cvo == null)
				return;
			prePaydate = iquery.queryPrePayDate();
			if (prePaydate == null)// 首次付款
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
	 * 初始化表数据
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
	 * 得到表头项
	 * 
	 * @return BillItem[]
	 */
	private BillItem[] getThisHeadItems()
	{
		String sHeadItemKeys[] = new String[] { "custcode", "prepaydate" };
		String sHeadItemCaptions[] = new String[] { "商户", "上次付款日期" };
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