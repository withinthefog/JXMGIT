package nc.ui.mallpos.pub.ref;

import nc.ui.bd.ref.AbstractRefModel;

/**
 * 会员等级列表参照模型
 * @author chenguogang
 * @date 2012-8-9 下午15:51:30
 */
public class MPOS_VipgradeRefModel extends AbstractRefModel{

	public MPOS_VipgradeRefModel() {
		super();
		//列表字段
		setFieldCode(new String[] {"grade_code","grade_name","inl_rule"});
		//列表字段对应的名称
		setFieldName(new String[]{"等级编码","等级名称","积分规则"});
		//数据库表名
		setTableName("MPOS_VIP_GRADE");
		//主键编码
		setPkFieldCode("PK_VIP_GRADE");
		// 设置编码
		setRefCodeField("grade_code");
		// 设置名称
		setRefNameField("grade_name");
		//未显示的隐藏字段
		setHiddenFieldCode(new String[]{"PK_VIP_GRADE"});
		//标题
		this.setRefTitle("会员等级参照");
		// 设置排序
		setOrderPart(" grade_code");
		// 设置过滤条件。
		setWherePart(" isnull(dr,0) = 0 ");
	}
	
}
