package nc.vo.mallpos.pub;

import nc.dev.tools.BillTempletParams;
import nc.dev.tools.Param;
import nc.vo.pub.*;
import nc.vo.pub.lang.*;

/**
 * POS机配置信息
 * 
 * @author cj
 * @date 2012-8-7 下午03:28:37
 */
public class MPOS_POSConfigVO extends SuperVO {
	private static final long serialVersionUID = 1L;

	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "手续费计算方式"))
	private Integer fee_caltype;

	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "最低限额"))
	private UFDouble lowestlimit_money;
	
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "最高限额"))
	private UFDouble toplimit_money;

	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "端口号"))
	private String comxx;

	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "POS机品牌"))
	private Integer brand;

	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "主键"))
	private String pk_posconfig;

	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "手续费"))
	private UFDouble fee;

	/**
	 * <p>
	 * 取得父VO主键字段.
	 * <p>
	 * 创建日期:2012-08-07 14:48:28
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getParentPKFieldName() {
		return null;
	}

	/**
	 * <p>
	 * 取得表主键.
	 * <p>
	 * 创建日期:2012-08-07 14:48:28
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPKFieldName() {
		return "pk_posconfig";
	}

	/**
	 * <p>
	 * 返回表名称.
	 * <p>
	 * 创建日期:2012-08-07 14:48:28
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getTableName() {
		return "mpos_posconfig";
	}

	/**
	 * 按照默认方式创建构造子.
	 * 
	 * 创建日期:2012-08-07 14:48:28
	 */
	public MPOS_POSConfigVO() {
		super();
	}

	/**
	 * 获取brand
	 * 
	 * @return brand brand
	 */
	public Integer getBrand() {
		return brand;
	}

	/**
	 * 设置brand
	 * 
	 * @param brand
	 *            brand
	 */
	public void setBrand(Integer brand) {
		this.brand = brand;
	}

	/**
	 * 获取comxx
	 * 
	 * @return comxx comxx
	 */
	public String getComxx() {
		return comxx;
	}

	/**
	 * 设置comxx
	 * 
	 * @param comxx
	 *            comxx
	 */
	public void setComxx(String comxx) {
		this.comxx = comxx;
	}

	/**
	 * 获取fee
	 * 
	 * @return fee fee
	 */
	public UFDouble getFee() {
		return fee;
	}

	/**
	 * 设置fee
	 * 
	 * @param fee
	 *            fee
	 */
	public void setFee(UFDouble fee) {
		this.fee = fee;
	}

	/**
	 * 获取fee_caltype
	 * 
	 * @return fee_caltype fee_caltype
	 */
	public Integer getFee_caltype() {
		return fee_caltype;
	}

	/**
	 * 设置fee_caltype
	 * 
	 * @param fee_caltype
	 *            fee_caltype
	 */
	public void setFee_caltype(Integer fee_caltype) {
		this.fee_caltype = fee_caltype;
	}

	/**
	 * 获取pk_posconfig
	 * 
	 * @return pk_posconfig pk_posconfig
	 */
	public String getPk_posconfig() {
		return pk_posconfig;
	}

	/**
	 * 设置pk_posconfig
	 * 
	 * @param pk_posconfig
	 *            pk_posconfig
	 */
	public void setPk_posconfig(String pk_posconfig) {
		this.pk_posconfig = pk_posconfig;
	}

	/**
	 * 获取toplimit_money
	 * 
	 * @return toplimit_money toplimit_money
	 */
	public UFDouble getToplimit_money() {
		return toplimit_money;
	}

	/**
	 * 设置toplimit_money
	 * 
	 * @param toplimit_money
	 *            toplimit_money
	 */
	public void setToplimit_money(UFDouble toplimit_money) {
		this.toplimit_money = toplimit_money;
	}

	/**
	 * 获取lowestlimit_money
	 * @return lowestlimit_money lowestlimit_money
	 */
	public UFDouble getLowestlimit_money() {
		return lowestlimit_money;
	}

	/**
	 * 设置lowestlimit_money
	 * @param lowestlimit_money lowestlimit_money
	 */
	public void setLowestlimit_money(UFDouble lowestlimit_money) {
		this.lowestlimit_money = lowestlimit_money;
	}
}
