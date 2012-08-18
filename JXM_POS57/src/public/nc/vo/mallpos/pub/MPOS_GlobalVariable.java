package nc.vo.mallpos.pub;

import java.awt.Font;

/**
 * 全局变量
 * 
 * @author chenjun
 * @date 2011-10-9 上午11:05:26
 */
public class MPOS_GlobalVariable {

	/**
	 * 人民币主键
	 */
	public static final String PK_CNY = "00010000000000000001";

	/**
	 * 网银结算方式主键
	 */
	public static final String PK_BALATYPE = "0001ZZ100000000013DU";

	/**
	 * 付款银行主键
	 */
	public static final String PK_PAYACCOUNT = "0001ZZ10000000003K3H";

	// -----------------------------------------生成凭证时各项目编码
	/**
	 * 代收商户货款
	 */
	public static final String ITEMCODE_DSFUND = "01";

	/**
	 * 手续费
	 */
	public static final String ITEMCODE_HANDFEE = "02";

	/**
	 * 银行存款
	 */
	public static final String ITEMCODE_BANKDEPOSIT = "03";

	/**
	 * 现金
	 */
	public static final String ITEMCODE_CASH = "04";

	// -----------------------------------------失败日志类型
	/**
	 * 生成付款单日志
	 */
	public static final String LOGTYPE_PAYMANT = "0";

	/**
	 * 商户每天销售汇总
	 */
	public static final String LOGTYPE_SALEDAYSUM = "1";

	// ******************单据模板编码*****************************

	/**
	 * 地区分类
	 */
	public static final String BILLTYPE_BD_AREACL = "POS001";

	/**
	 * 楼盘档案
	 */
	public static final String BILLTYPE_BD_HOUSEDOC = "POS002";

	/**
	 * POS机配置信息
	 */
	public static final String BILLTYPE_CONFIG_POS = "POS003";

	/**
	 * 存货档案
	 */
	public static final String BILLTYPE_BD_INVDOC = "POS004";

	/**
	 * 结算方式配置
	 */
	public static final String BILLTYPE_CONFIG_BALATYPE = "POS005";

	/**
	 * 活动
	 */
	public static final String BILLTYPE_ACTIVITY = "POS006";

	/**
	 * 活动类型
	 */
	public static final String BILLTYPE_ACTIVITY_TYPE = "POS007";

	/**
	 * 会员管理 单据类型
	 */
	public static final String BILLTYPE_VIPDOC = "POS010";

	/**
	 * 会员等级 单据类型
	 */
	public static final String BILLTYPE_VIP_GRADE = "POS008";

	/**
	 * 会员卡管理 单据类型
	 */
	public static final String BILLTYPE_VIP_CARD = "POS009";

	/**
	 * 礼品兑换
	 */
	public static final String BILLTYPE_INVEXCHANGE = "POS011";

	/**
	 * 礼品退还
	 */
	public static final String BILLTYPE_INVRETURN = "POS012";

	/**
	 * 活动礼品价格表
	 */
	public static final String BILLTYPE_INVACTIVITY = "POS013";

	/**
	 * 销售明细
	 */
	public static final String BILLTYPE_SALEDETAIL = "POS100";

	/**
	 * 销售明细完善
	 */
	public static final String BILLTYPE_SALEDETAIL_PERFECT = "POS100A";

	// 活动状态
	public static final int ACTIVITY_STATUS_INIT = 1;

	public static final int ACTIVITY_STATUS_ACTIVE = 2;

	public static final int ACTIVITY_STATUS_STOP = 3;

	// 折扣方式
	public static final String DISCOUNT_TYPE_CUT = "1"; // 降价

	public static final String DISCOUNT_TYPE_DISCOUNT = "2"; // 打折

	/**
	 * 打折类活动主键
	 */
	public static final String PK_ACTIVITY_TYPE = "0001ZZ10000000002UP0";

	// ******************结算方式编码*****************************

	/**
	 * 现金
	 */
	public static final String BALANCETYPE_CASH = "01";

	/**
	 * 银行卡
	 */
	public static final String BALANCETYPE_CARD = "02";

	/**
	 * 现金券
	 */
	public static final String BALANCETYPE_COUPON_CASH = "03";

	/**
	 * 积分折现
	 */
	public static final String BALANCETYPE_GRADE_DISCASH = "04";

	/**
	 * 支票
	 */
	public static final String BALANCETYPE_GRADE_CHECK = "05";

	// ******************银行卡手续费计算方式*****************************
	/**
	 * 百分比
	 */
	public static final int CARD_FEE_PERCENT = 0;

	/**
	 * 固定值
	 */
	public static final int CARD_FEE_DEFAULT = 1;

	// ******************自定义项档案*****************************
	/**
	 * 商品大类
	 */
	public static final String DEFDOC_GOODS_MAIN = "MPOS01";

	/**
	 * 商品小类
	 */
	public static final String DEFDOC_GOODS_SUB = "MPOS02";

	/**
	 * 收银单编码
	 */
	public static final String POS_BILL_CODE = "SY";

	/**
	 * 收银界面字体
	 */
	public static final Font font = new Font("宋体", Font.PLAIN, 25);

	// ******************参数编码*****************************
	/**
	 * 折扣计算顺序：先减后折还是先折后减
	 */
	public static final String PARAM_CODE_DISCALSEQU = "MPOS01";

	/**
	 * 先减后折
	 */
	public static final String DISCOUNT_SEQU_FIRSTSUB = "先减后折";

	/**
	 * 先折后减
	 */
	public static final String DISCOUNT_SEQU_FIRSTDIS = "先折后减";

	/**
	 * 积分折现规则
	 */
	public static final String PARAM_CODE_DISCASHRULE = "MPOS02";

	/**
	 * 退货时是否受可用余额控制参数代码
	 */
	public static final String PARAM_CODE_ISCTRL = "MPOS10";

	/**
	 * 退货操作不受商户可用余额限制的操作员设置参数代码
	 */
	public static final String PARAM_CODE_NOCTRLOP = "MPOS11";

	// ******************销售方式*****************************
	public static final String SALETYPE_SALE = "销售";

	public static final String SALETYPE_RETURN = "退货";

	public static final String SALETYPE_SHARE = "分摊";

	// ******************销售明细天汇总*****************************
	/**
	 * 付款初始状态
	 */
	public static final int SALEDAYSUM_BILLSTATE_DEFAULT = 0;

	/**
	 * 付款已处理状态
	 */
	public static final int SALEDAYSUM_BILLSTATE_HANDLED = 1;

	/**
	 * 凭证初始状态
	 */
	public static final int SALEDAYSUM_VOUCHER_DEFAULT = 0;

	/**
	 * 凭证已处理状态
	 */
	public static final int SALEDAYSUM_VOUCHER_HANDLED = 1;

	// ******************会员卡状态*****************************
	/**
	 * 未启用
	 */
	public static final int VIPCARD_STATUS_UNENABLED = 0;

	/**
	 * 已启用
	 */
	public static final int VIPCARD_STATUS_ENABLED = 1;

	/**
	 * 挂失
	 */
	public static final int VIPCARD_STATUS_LOSS = 2;

	/**
	 * 作废
	 */
	public static final int VIPCARD_STATUS_CANCEL = 3;

}
