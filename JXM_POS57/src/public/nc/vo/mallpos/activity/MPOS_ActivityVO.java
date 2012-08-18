package nc.vo.mallpos.activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.SuperVO;
import nc.vo.trade.pub.HYBillVO;
import nc.vo.trade.pub.IExAggVO;

public class MPOS_ActivityVO extends HYBillVO implements IExAggVO {

	private static final long serialVersionUID = -7218587696114623204L;
	
	public static final String CHILD_TABLE_RULE = "mpos_activity_rules";
	public static final String CHILD_TABLE_CUSTS = "mpos_activity_custs";

	private HashMap<String, CircularlyAccessibleValueObject[]> childVOs;
	private String m_TableCodes[] = { CHILD_TABLE_RULE, CHILD_TABLE_CUSTS };
	private String m_TableNames[];
	private String m_HeadClsName;
	private String m_ItemClsNames[];

	public MPOS_ActivityVO() {
		childVOs = new HashMap<String, CircularlyAccessibleValueObject[]>();
		m_TableNames = (new String[] { "", "" });
		m_HeadClsName = MPOS_ActivityHeadVO.class.getName();
		m_ItemClsNames = new String[] {};
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
		return new MPOS_ActivityHeadVO().getPrimaryKey();
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
