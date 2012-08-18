package nc.ui.mallpos.pub.ref;

import nc.ui.bd.ref.AbstractRefModel;

/**
 * 销售单号参照
 * @author cj
 * @date 2012-8-17 下午03:14:34
 */
public class MPOS_SaleNORefModel extends AbstractRefModel{
	public MPOS_SaleNORefModel() {
		super();
		setFieldCode(new String[] { "pk_saledetail_h","sale_no","proof_billno"});
		setFieldName(new String[]{"主键","销售单号","交款单号"});
		setRefCodeField("sale_no");
		setRefNameField("sale_no");
		setTableName("mpos_saledetail_h");
		setPkFieldCode("pk_saledetail_h");
		setHiddenFieldCode(new String[]{"pk_saledetail_h"});
		setWherePart(" isnull(dr,0) = 0 ");
		setOrderPart("sale_no");
		setRefTitle("销售单号");
	}
}
