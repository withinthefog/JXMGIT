package nc.ui.mallpos.voucher;

import javax.swing.ListSelectionModel;

import nc.ui.pub.ClientEnvironment;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillData;
import nc.ui.pub.bill.BillItem;
import nc.vo.mallpos.voucher.MPOS_VoucherConfigVO;

public class MPOS_VoucherConfigCardPanel extends BillCardPanel
{

	private static final long serialVersionUID = 1L;

	ClientEnvironment ce = ClientEnvironment.getInstance();

	public MPOS_VoucherConfigCardPanel()
	{
		super();
		this.initialize();
		this.initializeEx();
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
		getBodyPanel().setTotalRowShow(false);// 设置合计行
		getAddLineMenuItem().setVisible(false);
		getDelLineMenuItem().setVisible(false);
		getInsertLineMenuItem().setVisible(false);
		getCopyLineMenuItem().setVisible(false);
		getPasteLineMenuItem().setVisible(false);
		getPasteLineToTailMenuItem().setVisible(false);
		// 固定表处理
		getBillTable().removeSortListener();
		// 设置只能单选
		this.getBillTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}

	public void initializeEx()
	{
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

	private BillItem[] getThisBodyItems()
	{
		String sBodyItemKeys[] = new String[] { "pk_voucherconfig", "itemcode", "itemname", "accsubjcode", "accsubjname" };
		String sBodyItemCaptions[] = new String[] { "主键", "项目编码", "项目名称", "科目编码", "科目名称" };
		BillItem items[] = new BillItem[sBodyItemKeys.length];
		for (int i = 0; i < sBodyItemKeys.length; i++)
		{
			items[i] = new BillItem();
			items[i].setKey(sBodyItemKeys[i]);
			items[i].setName(sBodyItemCaptions[i]);
			items[i].setShowOrder(i);
			items[i].setEdit(false);
			items[i].setNull(false);
			items[i].setWidth(100);

			if ("pk_voucherconfig".equals(sBodyItemKeys[i]))
			{
				items[i].setShow(false);
			} else if ("accsubjcode".equals(sBodyItemKeys[i]))
			{
				items[i].setNull(true);
				items[i].setEdit(true);
			} else if ("accsubjname".equals(sBodyItemKeys[i]))
			{
				items[i].setEdit(true);
			}
		}
		return items;
	}

	public void fillGrid(MPOS_VoucherConfigVO[] vos)
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
	 * 取得表体vos
	 * 
	 * @return KNPOS_SaleVO[]
	 */
	public MPOS_VoucherConfigVO[] getBodyValueVOs()
	{
		return (MPOS_VoucherConfigVO[]) getBillModel().getBodyValueVOs(MPOS_VoucherConfigVO.class.getName());
	}

	/**
	 * 得到表头VO
	 * 
	 * @return 根据当前表头项值设置到参数VO中，然后返回
	 */
	public MPOS_VoucherConfigVO getHeadVO()
	{
		MPOS_VoucherConfigVO vo = new MPOS_VoucherConfigVO();
		vo = this.getHeadVO(vo, getHeadItems());
		return vo;
	}

	/**
	 * 得到表头VO
	 * 
	 * @param vo
	 * @param items
	 *            数据项
	 * @return 根据当前表头项值设置到参数VO中，然后返回
	 */
	private MPOS_VoucherConfigVO getHeadVO(MPOS_VoucherConfigVO vo, BillItem[] items)
	{
		for (int xxx = 0; xxx < items.length; xxx++)
		{
			Object valueobject = null;
			try
			{
				valueobject = items[xxx].converType(items[xxx].getValueObject());
				// 设置VO属性值，如果Item的DataType与VO属性类型不一致会报：argument type
				// mismatch，因此必须保持一致。
				vo.setAttributeValue(items[xxx].getKey(), valueobject);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return vo;
	}

	/**
	 * 设置表头VO
	 * 
	 */
	public void setHeadVO(MPOS_VoucherConfigVO vo)
	{
		this.setHeadVO(vo, getHeadItems());
	}

	/**
	 * 设置表头VO
	 * 
	 * @param vo
	 *            VO对象
	 * @param items
	 *            数据项
	 */
	private void setHeadVO(MPOS_VoucherConfigVO vo, BillItem[] items)
	{
		if (vo == null || items == null)
		{
			return;
		}
		for (int xxx = 0; xxx < items.length; xxx++)
		{
			items[xxx].setValue(vo.getAttributeValue(items[xxx].getKey()));
		}
	}

}
