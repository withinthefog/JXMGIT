package nc.ui.mallpos.sale;

import nc.ui.mallpos.pub.BaseBillCardPanel;
import nc.ui.pub.ClientEnvironment;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillItemNumberFormat;
import nc.vo.mallpos.sale.MPOS_SaleDaySumVO;

/**
 * 销售记录天汇总面板
 * 
 * @author cj
 * @date 2012-8-17 下午10:44:32
 */
public class MPOS_SaleDaySumBillPanel extends
		BaseBillCardPanel<MPOS_SaleDaySumVO, MPOS_SaleDaySumVO> {

	private static final long serialVersionUID = 1L;

	ClientEnvironment ce = ClientEnvironment.getInstance();

	/**
	 * 得到表体项
	 * 
	 * @return BillItem[]
	 */
	public BillItem[] getThisBodyItems() {
		String sBodyItemKeys[] = new String[] { "pk_sale_daysum",
				"pk_cubasdoc", "custcode", "custname", "billdate",
				"pk_balatype", "balanname", "nmoney", "nfee", "pk_cashier",
				"usercode", "username", "billstate", "strbillstate", "djbh",
				"strpay", "voucher", "strvoucher" };
		String sBodyItemCaptions[] = new String[] { "主键", "商户主键", "商户编码",
				"商户名称", "单据日期", "结算方式主键", "结算方式", "营业额", "手续费", "收银员主键",
				"收银员编码", "收银员名称", "单据状态", "单据状态", "付款单号", "付款状态", "凭证生成状态",
				"凭证生成状态" };
		BillItem items[] = new BillItem[sBodyItemKeys.length];
		BillItemNumberFormat format = new BillItemNumberFormat();
		format.setShowThMark(true);
		format.setShowRed(true);
		for (int i = 0; i < sBodyItemKeys.length; i++) {
			items[i] = new BillItem();
			items[i].setKey(sBodyItemKeys[i]);
			items[i].setName(sBodyItemCaptions[i]);
			items[i].setShowOrder(i);
			items[i].setWidth(80);
			items[i].setEdit(false);
			items[i].setNull(false);
			if (sBodyItemKeys[i].startsWith("pk_")) {
				items[i].setShow(false);
			} else if ("nmoney".equals(sBodyItemKeys[i])
					|| "nfee".equals(sBodyItemKeys[i])) {
				items[i].setDataType(BillItem.DECIMAL);
				items[i].setDecimalDigits(2);
				items[i].setTatol(true);
				items[i].setNumberFormat(format);
			} else if ("billstate".equals(sBodyItemKeys[i])) {
				items[i].setShow(false);
			} else if ("voucher".equals(sBodyItemKeys[i])) {
				items[i].setShow(false);
			}
		}
		return items;
	}

	@Override
	public String getBodyCheckBoxKey() {
		return null;
	}

	@Override
	public BillItem[] getThisHeadItems() {
		return null;
	}

	@Override
	public BillItem[] getThisTailItems() {
		return null;
	}

	@Override
	public void initializeEX() {

	}

	public void bodyRowChange(BillEditEvent arg0) {

	}

}
