package nc.ui.mallpos.pub.ref;

import nc.ui.bd.ref.AbstractRefModel;

/**
 * 为会员提供会员卡列表参照模型
 * 关联查询出会员卡等级
 * 过滤掉已关联的 已挂失的 已作废的
 * @author chenguogang
 * @date 2012-8-9 下午15:51:30
 */
public class MPOS_Vipcard4VipdocRefModel extends AbstractRefModel{

	public MPOS_Vipcard4VipdocRefModel() {
		super();
		//列表字段
		setFieldCode(new String[] {"mpos_vip_card.inner_code","mpos_vip_card.print_code","mpos_vipcard_grade.grade_name"});
		//列表字段对应的名称
		setFieldName(new String[]{"内部编码","印刷编码","会员等级"});
		//数据库表名
		setTableName("mpos_vip_card left join mpos_vipcard_grade on mpos_vipcard_grade.pk_vipcard_grade = mpos_vip_card.pk_vipcard_grade ");
		//主键编码
		setPkFieldCode("mpos_vip_card.pk_vip_card");
		// 设置编码
		setRefCodeField("mpos_vip_card.inner_code");
		// 设置名称
		setRefNameField("mpos_vip_card.print_code");
		//未显示的隐藏字段
		setHiddenFieldCode(new String[]{"mpos_vip_card.pk_vip_card"});
		//标题
		this.setRefTitle("会员卡参照");
		// 设置排序
		setOrderPart(" mpos_vip_card.inner_code");
		// 设置过滤条件。
		setWherePart(" isnull(mpos_vip_card.dr,0) = 0 and  mpos_vip_card.pk_vip_card not in ("+
        			   "select mpos_vipdoc_card.pk_vip_card from mpos_vipdoc left join mpos_vipdoc_card on mpos_vipdoc_card.pk_vipdoc=mpos_vipdoc.pk_vipdoc where isnull(mpos_vipdoc.dr,0)=0 and mpos_vipdoc_card.pk_vip_card is not null)  and mpos_vip_card.wstatus='0'");
	}
	
}
