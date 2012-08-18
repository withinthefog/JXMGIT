package nc.ui.mallpos.pub.ref;

import nc.ui.bd.ref.AbstractRefModel;

/**
 * 存货(礼品)档案列表参照模型
 * @author cj
 * @date 2012-8-9 下午12:49:30
 */
public class MPOS_InvdocRefModel extends AbstractRefModel{

	public MPOS_InvdocRefModel() {
		super();
		setFieldCode(new String[] { "pk_invdoc","inv_code","inv_name","spectype"});
		setFieldName(new String[]{"主键","编码","名称","规格型号"});
		setRefCodeField("inv_code");
		setRefNameField("inv_name");
		setTableName("mpos_invdoc");
		setPkFieldCode("pk_invdoc");
		setHiddenFieldCode(new String[]{"pk_invdoc"});
		setWherePart(" isnull(dr,0) = 0 ");
		setOrderPart("inv_code");
		
		setShownColumns(new int[]{1,2,3,4});
		
		setRefTitle("礼品档案");
	}
	
}
