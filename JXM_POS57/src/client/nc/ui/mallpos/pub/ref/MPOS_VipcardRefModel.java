package nc.ui.mallpos.pub.ref;

import nc.ui.bd.ref.AbstractRefModel;
import nc.vo.mallpos.pub.MPOS_GlobalVariable;

/**
 * 会员卡列表参照模型
 * @author chenguogang
 * @date 2012-8-9 下午15:51:30
 */
public class MPOS_VipcardRefModel extends AbstractRefModel{

	public MPOS_VipcardRefModel() {
		super();
		//列表字段
		setFieldCode(new String[] {"mpos_vip_card.inner_code","mpos_vip_card.print_code","mpos_vipdoc.vname","mpos_vipdoc.grade"});
		//列表字段对应的名称
		setFieldName(new String[]{"内部编码","印刷编码","会员姓名"});
		//数据库表名
		setTableName("mpos_vip_card inner join mpos_vipdoc_card on mpos_vip_card.pk_vip_card = mpos_vipdoc_card.pk_vip_card inner join mpos_vipdoc on mpos_vipdoc_card.pk_vipdoc = mpos_vipdoc.pk_vipdoc");
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
		setWherePart(" isnull(mpos_vip_card.dr,0) = 0 and mpos_vip_card.wstatus ="+MPOS_GlobalVariable.VIPCARD_STATUS_ENABLED);
	}
	
}
