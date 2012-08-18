package nc.ui.mallpos.config.balatype;



import nc.ui.mallpos.pub.BaseBillCardPanel;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillItemNumberFormat;
import nc.vo.bd.ref.RefNodeNameConst;
import nc.vo.mallpos.pub.MPOS_BalaTypeConfigVO;

/**
 * 结算方式配置面板
 * @author cj
 * @date 2012-8-16 下午12:56:42
 */
public class MPOS_BalaTypeBillPanel extends BaseBillCardPanel<MPOS_BalaTypeConfigVO, MPOS_BalaTypeConfigVO>{

	private static final long serialVersionUID = 1L;

	public MPOS_BalaTypeBillPanel() {
		super();
	}

	@Override
	public String getBodyCheckBoxKey() {
		return null;
	}

	@Override
	public BillItem[] getThisBodyItems() {
		String sBodyItemKeys[] = new String[] {"pk_balatype_config","pk_balatype","balatype_name","isenable_grade","isenable_return","iscallpos"};
		String sBodyItemCaptions[] = new String[] { "主键","结算方式主键","结算方式","是否能够积分","是否能够退还","是否调用POS机"};
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
			items[i].setEdit(true);
			items[i].setNull(false);
			if(sBodyItemKeys[i].startsWith("pk_"))
				items[i].setShow(false);
			if (sBodyItemKeys[i].startsWith("is")) {
				items[i].setDataType(BillItem.BOOLEAN);
			}else if("pk_balatype".equals(sBodyItemKeys[i]))
			{
				items[i].setLoadFormula(new String[]{"balatype_name->getColValue(bd_balatype,balanname , pk_balatype, pk_balatype)"});
			}else if("balatype_name".equals(sBodyItemKeys[i]))
			{
				items[i].setDataType(BillItem.UFREF);
				items[i].setRefType(RefNodeNameConst.BALANCETYPE);
				items[i].setIDColName("pk_balatype");
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
		
	}

	public void bodyRowChange(BillEditEvent arg0) {
		
	}

}
