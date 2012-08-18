package nc.vo.mallpos.pos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.SuperVO;
import nc.vo.trade.pub.HYBillVO;
import nc.vo.trade.pub.IExAggVO;

/**
 * 交易明细聚合类(多子表)
 * 
 * @author cj
 * @date 2012-8-16 下午10:35:13
 */
public class MPOS_SaleDetailBillVO extends HYBillVO implements IExAggVO {
	private static final long serialVersionUID = 1L;

	public static final String CHILD_TABLE_DETAIL = "mpos_saledetail_b";

	public static final String CHILD_TABLE_SHARE = "mpos_sale_share";

	private HashMap<String, CircularlyAccessibleValueObject[]> childVOs;

	private String m_TableCodes[] = { CHILD_TABLE_DETAIL, CHILD_TABLE_SHARE };

	private String m_TableNames[] = { "收款明细", "折扣明细" };

	public MPOS_SaleDetailBillVO() {
		childVOs = new HashMap<String, CircularlyAccessibleValueObject[]>();
	}

	@SuppressWarnings("unchecked")
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
		return new MPOS_SaleDetailHeadVO().getPrimaryKey();
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
