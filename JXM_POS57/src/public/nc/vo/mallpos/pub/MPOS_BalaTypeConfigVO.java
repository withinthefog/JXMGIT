package nc.vo.mallpos.pub;

import nc.dev.tools.BillTempletParams;
import nc.dev.tools.Param;
import nc.vo.pub.*;
import nc.vo.pub.lang.*;

/**
 * 结算方式配置信息
 * 
 * @author cj
 * @date 2012-8-7 下午03:28:37
 */
public class MPOS_BalaTypeConfigVO extends SuperVO {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "主键"))
	private String pk_balatype_config;

	/**
	 * 结算方式主键
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "结算方式"))
	private String pk_balatype;

	/**
	 * 是否能够积分
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "是否能够积分"))
	private UFBoolean isenable_grade;

	/**
	 * 是否能够退还
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "是否能够退还"))
	private UFBoolean isenable_return;

	/**
	 * 是否调用POS机
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "是否调用POS机"))
	private UFBoolean iscallpos;

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
		return "pk_balatype_config";
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
		return "mpos_balatype_config";
	}

	/**
	 * 获取是否调用POS机
	 * 
	 * @return iscallpos 是否调用POS机
	 */
	public UFBoolean getIscallpos() {
		return iscallpos;
	}

	/**
	 * 设置是否调用POS机
	 * 
	 * @param iscallpos
	 *            是否调用POS机
	 */
	public void setIscallpos(UFBoolean iscallpos) {
		this.iscallpos = iscallpos;
	}

	/**
	 * 获取是否能够积分
	 * 
	 * @return isenable_grade 是否能够积分
	 */
	public UFBoolean getIsenable_grade() {
		return isenable_grade;
	}

	/**
	 * 设置是否能够积分
	 * 
	 * @param isenable_grade
	 *            是否能够积分
	 */
	public void setIsenable_grade(UFBoolean isenable_grade) {
		this.isenable_grade = isenable_grade;
	}

	/**
	 * 获取是否能够退还
	 * 
	 * @return isenable_return 是否能够退还
	 */
	public UFBoolean getIsenable_return() {
		return isenable_return;
	}

	/**
	 * 设置是否能够退还
	 * 
	 * @param isenable_return
	 *            是否能够退还
	 */
	public void setIsenable_return(UFBoolean isenable_return) {
		this.isenable_return = isenable_return;
	}

	/**
	 * 获取结算方式主键
	 * 
	 * @return pk_balatype 结算方式主键
	 */
	public String getPk_balatype() {
		return pk_balatype;
	}

	/**
	 * 设置结算方式主键
	 * 
	 * @param pk_balatype
	 *            结算方式主键
	 */
	public void setPk_balatype(String pk_balatype) {
		this.pk_balatype = pk_balatype;
	}

	/**
	 * 获取主键
	 * 
	 * @return pk_balatype_config 主键
	 */
	public String getPk_balatype_config() {
		return pk_balatype_config;
	}

	/**
	 * 设置主键
	 * 
	 * @param pk_balatype_config
	 *            主键
	 */
	public void setPk_balatype_config(String pk_balatype_config) {
		this.pk_balatype_config = pk_balatype_config;
	}

}
