package nc.ui.mallpos.pos;

import nc.ui.mallpos.pub.BaseBillCardPanel;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillEditListener2;
import nc.ui.pub.bill.BillItem;
import nc.vo.mallpos.pos.MPOS_DiscountTempVO;

public class MPOS_SaleDiscountBillPanel extends
		BaseBillCardPanel<MPOS_DiscountTempVO, MPOS_DiscountTempVO> implements
		BillEditListener2 {

	private static final long serialVersionUID = 1L;

	@Override
	public String getBodyCheckBoxKey() {
		return "select";
	}

	@Override
	public BillItem[] getThisBodyItems() {
		String sBodyItemKeys[] = new String[] { "select", "pk_activity",
				"pk_activity_type", "activityname", "discounttype",
				"discounttype_name", "beginmny", "endmny", "discountnum" };
		String sBodyItemCaptions[] = new String[] { "选择", "活动主键", "活动规则主键",
				"活动名称", "打折类型", "打折类型", "beginmny", "endmny", "折扣" };
		BillItem items[] = new BillItem[sBodyItemKeys.length];
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
			if ("select".equals(sBodyItemKeys[i])) {
				items[i].setWidth(40);
				items[i].setEdit(true);
				items[i].setDataType(BillItem.BOOLEAN);
			} else if ("beginmny".equals(sBodyItemKeys[i])
					|| "endmny".equals(sBodyItemKeys[i])) {
				items[i].setDataType(BillItem.DECIMAL);
				items[i].setDecimalDigits(2);
				items[i].setShow(false);
			} else if ("discountnum".equals(sBodyItemKeys[i])) {
				items[i].setDataType(BillItem.DECIMAL);
				items[i].setDecimalDigits(2);
				items[i].setWidth(80);
			} else if ("discounttype".equals(sBodyItemKeys[i])) {
				items[i].setShow(false);
			}
		}
		return items;
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
		this.addBodyEditListener2(this);
	}

	public void bodyRowChange(BillEditEvent arg0) {

	}

	public boolean beforeEdit(BillEditEvent arg0) {
		// this.setRowCheckStatus(false);
		return true;
	}

}
