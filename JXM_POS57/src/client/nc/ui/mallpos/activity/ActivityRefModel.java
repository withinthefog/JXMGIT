package nc.ui.mallpos.activity;

import nc.ui.bd.ref.AbstractRefModel;

public class ActivityRefModel extends AbstractRefModel {

	public ActivityRefModel() {
		super();
		m_strRefNodeName = "活动参照";
		intialize();
	}

	private void intialize() {
		setRefTitle("活动参照");
		// 设置显示的字段：编码、名称
		setFieldCode(new String[] { "activitycode", "activityname", "pk_activity" });
		// 设置字段名称
		setFieldName(new String[] { "类型编码", "类型名称", "主键" });
		// 设置数据库表名称
		setTableName("mpos_activity");
		// 设置主键
		setPkFieldCode("pk_activity");
		// 设置编码
		setRefCodeField("activitycode");
		// 设置名称
		setRefNameField("activityname");
		// 设置排序
		setOrderPart("activitycode");
		// 设置过滤条件。
		setWherePart("isnull(dr,0) = 0 ");
	}
}
