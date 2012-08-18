package nc.ui.mallpos.pub.ref;

import nc.ui.bd.ref.AbstractRefGridTreeModel;

/**
 * 楼盘档案树表参照模型
 * 
 * @author cj
 * @date 2012-8-9 上午09:45:13
 */
public class MPOS_HouseRefModel extends AbstractRefGridTreeModel {

	public MPOS_HouseRefModel() {
	
		super();
		setClassTableName("mpos_areacl");
		setClassFieldCode(new String[]{"areacl_code","areacl_name","pk_fareacl","pk_areacl"});
		setClassJoinField("pk_areacl");
		setFatherField("pk_fareacl");
		setRootName("地区分类");
		setClassWherePart(" nvl(mpos_areacl.dr,0)=0 ");
		
		setTableName("mpos_houses");
		setPkFieldCode("pk_houses");
		setRefCodeField("house_code");
		setRefNameField("house_name");
		setDocJoinField("pk_areacl");
		setFieldCode(new String[]{"house_code","house_name","address","phone","pk_houses","pk_areacl"});
		setFieldName(new String[]{"楼盘编码","楼盘名称","楼盘地址","联系电话","主键","地区分类"});
		setShownColumns(new int[]{0,1,2,3});
		setWherePart(" isnull(mpos_houses.dr,0)=0");
		setRefTitle("楼盘档案");
	}

}
