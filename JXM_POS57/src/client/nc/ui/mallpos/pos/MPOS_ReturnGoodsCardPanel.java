package nc.ui.mallpos.pos;

import nc.ui.mallpos.pub.BaseBillCardPanel;
import nc.ui.mallpos.pub.ref.MPOS_VipcardRefModel;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillItemNumberFormat;
import nc.vo.bd.ref.RefNodeNameConst;
import nc.vo.mallpos.pos.MPOS_SaleDetailBodyVO;
import nc.vo.mallpos.pos.MPOS_SaleDetailHeadVO;
import nc.vo.pub.lang.UFDouble;

/**
 * 退货面板
 * @author cj
 * @date 2012-8-12 下午10:37:56
 */
public class MPOS_ReturnGoodsCardPanel extends BaseBillCardPanel<MPOS_SaleDetailHeadVO, MPOS_SaleDetailBodyVO>{

	private static final long serialVersionUID = 1L;

	@Override
	public String getBodyCheckBoxKey() {
		return "select";
	}

	@Override
	public BillItem[] getThisBodyItems() {
		String sBodyItemKeys[] = new String[] {
				"select",
				"pk_saledetail_b",
				"pk_balatype",
				"balanname",
				"sale_datetime",
				"canreturnmoney",
				"received_money",
				"cardno",
				"systracdno",
				"nfee",
				"mall_money",
				"cust_money",
				"discash_grade",
				"brand",
				"exp_grade"};
		String sBodyItemCaptions[] = new String[] {
				"选择退货",
				"主键",
				"结算方式主键",
				"结算方式",
				"销售时间",
				"可退金额",
				"退货金额",
				"银行卡号",
				"系统流水号",
				"手续费",
				"商场承担金额",
				"商户承担金额",
				"折现积分",
				"POS机品牌",
				"消费积分"};
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
			if(sBodyItemKeys[i].equals("select"))
			{
				items[i].setEdit(true);
				items[i].setWidth(60);
				items[i].setDataType(BillItem.BOOLEAN);
			} else if (sBodyItemKeys[i].startsWith("pk_")
					|| "systracdno".equals(sBodyItemKeys[i])
					|| "brand".equals(sBodyItemKeys[i]))
				items[i].setShow(false);
			else if ("canreturnmoney".equals(sBodyItemKeys[i])
					|| "nfee".equals(sBodyItemKeys[i])
					|| "add_reduce_grade".equals(sBodyItemKeys[i])) {
				items[i].setDataType(BillItem.DECIMAL);
				items[i].setDecimalDigits(2);
				items[i].setTatol(true);
				items[i].setNumberFormat(format);
			} else if ("received_money".equals(sBodyItemKeys[i])) {
				items[i].setDataType(BillItem.DECIMAL);
				items[i].setDecimalDigits(2);
				items[i].setTatol(true);
				items[i].setNumberFormat(format);
			} 
		}
		return items;
	}

	@Override
	public BillItem[] getThisHeadItems() {
		String sHeadItemKeys[] = new String[] {
				"pk_saledetail_h",
				"sale_no",     
				"bill_date",
				"pk_cubasdoc",
				"ago_proof_billno",
				"proof_billno",
				"pk_vip_card",
				"pk_cashier",
				"memo",
				"istoday_return"};
		String sHeadItemCaptions[] = new String[] {
				"主键",
				"单据号",     
				"单据日期",
				"商户",				
				"原交款单号",
				"退款单号",
				"会员卡",
				"收银员",
				"备注",
				"是否当日退货"};
		BillItem items[] = new BillItem[sHeadItemKeys.length];
		for (int i = 0; i < sHeadItemKeys.length; i++) {
			items[i] = new BillItem();
			items[i].setKey(sHeadItemKeys[i]);
			items[i].setName(sHeadItemCaptions[i]);
			items[i].setShowOrder(i);
			items[i].setWidth(1);
			items[i].setEdit(false);
			items[i].setNull(false);
			if("pk_saledetail_h".equals(sHeadItemKeys[i]))
				items[i].setShow(false);
			if ("bill_date".equals(sHeadItemKeys[i])) {
				items[i].setDataType(BillItem.DATE);
			}else if ("pk_cubasdoc".equals(sHeadItemKeys[i])) {
				items[i].setDataType(BillItem.UFREF);
				items[i].setRefType(RefNodeNameConst.CUSTBASDOC);
			}else if ("proof_billno".equals(sHeadItemKeys[i])) {
				items[i].setEdit(true);
				items[i].setNull(true);
			}else if ("memo".equals(sHeadItemKeys[i])) {
				items[i].setEdit(true);
			}else if ("pk_vip_card".equals(sHeadItemKeys[i])) {
				items[i].setDataType(BillItem.UFREF);
				UIRefPane rp = new UIRefPane();
				rp.setRefModel(new MPOS_VipcardRefModel());
				items[i].setComponent(rp);
				items[i].setEdit(false);
			}else if ("pk_cashier".equals(sHeadItemKeys[i])) {
				items[i].setDataType(BillItem.UFREF);
				items[i].setRefType(RefNodeNameConst.OPERATOR);
			}else if("istoday_return".equals(sHeadItemKeys[i]))
			{
				items[i].setDataType(BillItem.BOOLEAN);
				items[i].setEdit(true);
			}
		}
		return items;
	}

	@Override
	public BillItem[] getThisTailItems() {
		return null;
	}

	@Override
	public void initializeEX() {
		this.addEditListener(this);
	}

	public void bodyRowChange(BillEditEvent arg0) {
		
	}
	
	@Override
	public void afterEdit(BillEditEvent arg0) {
		if (getBodyCheckBoxKey().equals(arg0.getKey()))
		{
			boolean ischecked = this.isRowChecked(arg0.getRow());
			if (ischecked)
			{
				this.getBillModel().setValueAt(this.getBillModel().getValueAt(arg0.getRow(), "canreturnmoney"), arg0.getRow(), "received_money");
			} else
			{
				this.getBillModel().setValueAt(null, arg0.getRow(), "received_money");
			}
			this.getBillModel().setCellEditable(arg0.getRow(), "received_money", ischecked);
		}else if("received_money".equals(arg0.getKey()))
		{
			if(this.isRowChecked(arg0.getRow()))
			{
				UFDouble received_money = new UFDouble(this.getBodyValueAt(arg0.getRow(), "received_money")==null?"0":this.getBodyValueAt(arg0.getRow(), "received_money").toString());
				UFDouble canreturnmoney = new UFDouble(this.getBodyValueAt(arg0.getRow(), "canreturnmoney")==null?"0":this.getBodyValueAt(arg0.getRow(), "canreturnmoney").toString());
				if(received_money.compareTo(new UFDouble(0))==0||received_money.compareTo(canreturnmoney)>0)
				{
					MessageDialog.showWarningDlg(this, "警告", "退货金额输入错误!");
					this.getBillModel().setValueAt(null, arg0.getRow(), "received_money");
				}
			}
		}
		super.afterEdit(arg0);
	}
}
