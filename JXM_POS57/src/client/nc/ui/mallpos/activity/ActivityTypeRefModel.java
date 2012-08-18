package nc.ui.mallpos.activity;

import nc.ui.bd.ref.AbstractRefModel;

public class ActivityTypeRefModel extends AbstractRefModel {

	public ActivityTypeRefModel() {
		super();
		m_strRefNodeName = "活动类型参照";
		intialize();
	}

	private void intialize() {
		setRefTitle("活动类型参照");
		// 设置显示的字段：编码、名称
		setFieldCode(new String[] { "typecode", "typename", "pk_activity_type" });
		// 设置字段名称
		setFieldName(new String[] { "类型编码", "类型名称", "主键" });
		// 设置数据库表名称
		setTableName("mpos_activity_type");
		// 设置主键
		setPkFieldCode("pk_activity_type");
		// 设置编码
		setRefCodeField("typecode");
		// 设置名称
		setRefNameField("typename");
		// 设置排序
		setOrderPart("typecode");
		// 设置过滤条件。
		setWherePart("dr = 0 ");
	}
}
