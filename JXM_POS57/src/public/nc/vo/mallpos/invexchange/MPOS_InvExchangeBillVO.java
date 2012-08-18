package nc.vo.mallpos.invexchange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import nc.vo.mallpos.activity.MPOS_ActivityHeadVO;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.SuperVO;
import nc.vo.trade.pub.HYBillVO;
import nc.vo.trade.pub.IExAggVO;

/**
 * 礼品兑换聚合类
 * 
 * @author cj
 * @date 2012-8-10 下午02:39:11
 */
public class MPOS_InvExchangeBillVO extends HYBillVO implements IExAggVO {

	private static final long serialVersionUID = 1L;

	public static final String CHILD_TABLE_SALES = "mpos_invexchange_sales";
	public static final String CHILD_TABLE_DETAIL = "mpos_invexchange_detail";

	private HashMap<String, CircularlyAccessibleValueObject[]> childVOs;
	private String m_TableCodes[] = { CHILD_TABLE_DETAIL,CHILD_TABLE_SALES  };
	private String m_TableNames[];
	private String m_HeadClsName;
	private String m_ItemClsNames[];

	public MPOS_InvExchangeBillVO() {
		childVOs = new HashMap<String, CircularlyAccessibleValueObject[]>();
		m_TableNames = (new String[] { "订单明细", "礼品兑换明细" });
		m_HeadClsName = MPOS_ActivityHeadVO.class.getName();
		m_ItemClsNames = new String[] {
				MPOS_InvExchangeSalesVO.class.getName(),
				MPOS_InvExchangeItemVO.class.getName() };
	}

	public CircularlyAccessibleValueObject[] getAllChildrenVO() {
		ArrayList al = new ArrayList();
		for (int i = 0; i < getTableCodes().length; i++) {
			CircularlyAccessibleValueObject cvos[] = getTableVO(getTableCodes()[i]);
			if (cvos != null)
				al.addAll(Arrays.asList(cvos));
		}

		return (SuperVO[]) (SuperVO[]) al.toArray(new SuperVO[0]);
	}

	public SuperVO[] getChildVOsByParentId(String arg0, String arg1) {
		return null;
	}

	public String getDefaultTableCode() {
		return getTableCodes()[0];
	}

	public HashMap getHmEditingVOs() throws Exception {
		return null;
	}

	public String getParentId(SuperVO arg0) {
		return new MPOS_InvExchangeHeadVO().getPrimaryKey();
	}

	public String[] getTableCodes() {
		return m_TableCodes;
	}

	public String[] getTableNames() {
		return m_TableNames;
	}

	public CircularlyAccessibleValueObject[] getTableVO(String tableCode) {
		return childVOs.get(tableCode);
	}

	public void setParentId(SuperVO arg0, String arg1) {

	}

	public void setTableVO(String tableCode,
			CircularlyAccessibleValueObject[] values) {
		childVOs.put(tableCode, values);
	}

}
