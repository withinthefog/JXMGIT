package nc.ui.mallpos.pub.ref;

import nc.ui.bd.ref.AbstractRefModel;

/**
 * ���۵��Ų���
 * @author cj
 * @date 2012-8-17 ����03:14:34
 */
public class MPOS_SaleNORefModel extends AbstractRefModel{
	public MPOS_SaleNORefModel() {
		super();
		setFieldCode(new String[] { "pk_saledetail_h","sale_no","proof_billno"});
		setFieldName(new String[]{"����","���۵���","�����"});
		setRefCodeField("sale_no");
		setRefNameField("sale_no");
		setTableName("mpos_saledetail_h");
		setPkFieldCode("pk_saledetail_h");
		setHiddenFieldCode(new String[]{"pk_saledetail_h"});
		setWherePart(" isnull(dr,0) = 0 ");
		setOrderPart("sale_no");
		setRefTitle("���۵���");
	}
}
