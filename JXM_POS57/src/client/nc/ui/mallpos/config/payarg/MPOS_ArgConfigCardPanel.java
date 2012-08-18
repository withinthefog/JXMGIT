package nc.ui.mallpos.config.payarg;

import javax.swing.ListSelectionModel;

import nc.ui.pub.ClientEnvironment;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillData;
import nc.ui.pub.bill.BillItem;
import nc.vo.bd.ref.RefNodeNameConst;
import nc.vo.mallpos.pub.MPOS_ArgConfigVO;
public class MPOS_ArgConfigCardPanel extends BillCardPanel {

	private static final long serialVersionUID = 1L;

	ClientEnvironment ce = ClientEnvironment.getInstance();

	public MPOS_ArgConfigCardPanel() {
		super();
		this.initialize();
		this.initializeEx();
	}

	public void initialize() {
		setBillData(getThisBillData());
		getBillTable().setColumnSelectionAllowed(false);
		getBodyPanel().getRendererVO().setShowThMark(true);
		getBodyPanel().setTotalRowShow(false);
		getAddLineMenuItem().setVisible(false);
		getDelLineMenuItem().setVisible(false);
		getInsertLineMenuItem().setVisible(false);
		getCopyLineMenuItem().setVisible(false);
		getPasteLineMenuItem().setVisible(false);
		getPasteLineToTailMenuItem().setVisible(false);
		getBillTable().removeSortListener();
		this.getBillTable().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);

	}

	public void initializeEx() {
	}

	public BillData getThisBillData() {
		BillData billdata = new BillData();
		billdata.setHeadItems(getThisHeadItems());
		billdata.setBodyItems(getThisBodyItems());
		return billdata;
	}

	private BillItem[] getThisHeadItems() {
		String sHeadItemKeys[] = new String[] { "pk_argconfig", "pay_cycle",
				"first_paydate", "pk_userid" };
		String sHeadItemCaptions[] = new String[] {"主键","付款周期","首次付款日期","默认制单人"};
		BillItem items[] = new BillItem[sHeadItemKeys.length];
		for (int i = 0; i < sHeadItemKeys.length; i++) {
			items[i] = new BillItem();
			items[i].setKey(sHeadItemKeys[i]);
			items[i].setName(sHeadItemCaptions[i]);
			items[i].setShowOrder(i);
			items[i].setEdit(false);
			items[i].setNull(false);
			items[i].setWidth(1);

			if ("pk_argconfig".equals(sHeadItemKeys[i])) {
				items[i].setShow(false);
			} else if ("pay_cycle".equals(sHeadItemKeys[i])) {
				items[i].setDataType(BillItem.INTEGER);
				items[i].setNull(true);
			} else if ("first_paydate".equals(sHeadItemKeys[i])) {
				items[i].setDataType(BillItem.DATE);
				items[i].setNull(true);
			} else if ("pk_userid".equals(sHeadItemKeys[i])) {
				items[i].setDataType(BillItem.UFREF);
				items[i].setRefType(RefNodeNameConst.OPERATOR);
				items[i].setNull(true);
			}
		}
		return items;
	}

	private BillItem[] getThisBodyItems() {
		String sBodyItemKeys[] = new String[] { "paydate" };
		String sBodyItemCaptions[] = new String[] {"上次付款日期"};
		BillItem items[] = new BillItem[sBodyItemKeys.length];
		for (int i = 0; i < sBodyItemKeys.length; i++) {
			items[i] = new BillItem();
			items[i].setKey(sBodyItemKeys[i]);
			items[i].setName(sBodyItemCaptions[i]);
			items[i].setShowOrder(i);
			items[i].setEdit(false);
			items[i].setNull(false);
			items[i].setWidth(100);
		}
		return items;
	}

	public void fillGrid(MPOS_ArgConfigVO[] vos) {

		this.getBillModel().setBodyDataVO(vos);
		this.getBillModel().execLoadFormula();

		if (vos != null && vos.length > 0) {
			this.getBillTable().setRowSelectionInterval(0, 0);
		}
	}

	public MPOS_ArgConfigVO[] getBodyValueVOs() {
		return (MPOS_ArgConfigVO[]) getBillModel().getBodyValueVOs(
				MPOS_ArgConfigVO.class.getName());
	}

	public MPOS_ArgConfigVO getHeadVO() {
		MPOS_ArgConfigVO vo = new MPOS_ArgConfigVO();
		vo = this.getHeadVO(vo, getHeadItems());
		return vo;
	}
	private MPOS_ArgConfigVO getHeadVO(MPOS_ArgConfigVO vo, BillItem[] items) {
		for (int xxx = 0; xxx < items.length; xxx++) {
			Object valueobject = null;
			try {
				valueobject = items[xxx]
						.converType(items[xxx].getValueObject());
				vo.setAttributeValue(items[xxx].getKey(), valueobject);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return vo;
	}
	public void setHeadVO(MPOS_ArgConfigVO vo) {
		this.setHeadVO(vo, getHeadItems());
	}
	private void setHeadVO(MPOS_ArgConfigVO vo, BillItem[] items) {
		if (vo == null || items == null) {
			return;
		}
		for (int xxx = 0; xxx < items.length; xxx++) {
			items[xxx].setValue(vo.getAttributeValue(items[xxx].getKey()));
		}
	}

}
