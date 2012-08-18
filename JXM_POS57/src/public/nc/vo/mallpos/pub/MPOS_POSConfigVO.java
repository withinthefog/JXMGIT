package nc.vo.mallpos.pub;

import nc.dev.tools.BillTempletParams;
import nc.dev.tools.Param;
import nc.vo.pub.*;
import nc.vo.pub.lang.*;

/**
 * POS��������Ϣ
 * 
 * @author cj
 * @date 2012-8-7 ����03:28:37
 */
public class MPOS_POSConfigVO extends SuperVO {
	private static final long serialVersionUID = 1L;

	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "�����Ѽ��㷽ʽ"))
	private Integer fee_caltype;

	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "����޶�"))
	private UFDouble lowestlimit_money;
	
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "����޶�"))
	private UFDouble toplimit_money;

	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "�˿ں�"))
	private String comxx;

	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "POS��Ʒ��"))
	private Integer brand;

	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "����"))
	private String pk_posconfig;

	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "������"))
	private UFDouble fee;

	/**
	 * <p>
	 * ȡ�ø�VO�����ֶ�.
	 * <p>
	 * ��������:2012-08-07 14:48:28
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getParentPKFieldName() {
		return null;
	}

	/**
	 * <p>
	 * ȡ�ñ�����.
	 * <p>
	 * ��������:2012-08-07 14:48:28
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPKFieldName() {
		return "pk_posconfig";
	}

	/**
	 * <p>
	 * ���ر�����.
	 * <p>
	 * ��������:2012-08-07 14:48:28
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getTableName() {
		return "mpos_posconfig";
	}

	/**
	 * ����Ĭ�Ϸ�ʽ����������.
	 * 
	 * ��������:2012-08-07 14:48:28
	 */
	public MPOS_POSConfigVO() {
		super();
	}

	/**
	 * ��ȡbrand
	 * 
	 * @return brand brand
	 */
	public Integer getBrand() {
		return brand;
	}

	/**
	 * ����brand
	 * 
	 * @param brand
	 *            brand
	 */
	public void setBrand(Integer brand) {
		this.brand = brand;
	}

	/**
	 * ��ȡcomxx
	 * 
	 * @return comxx comxx
	 */
	public String getComxx() {
		return comxx;
	}

	/**
	 * ����comxx
	 * 
	 * @param comxx
	 *            comxx
	 */
	public void setComxx(String comxx) {
		this.comxx = comxx;
	}

	/**
	 * ��ȡfee
	 * 
	 * @return fee fee
	 */
	public UFDouble getFee() {
		return fee;
	}

	/**
	 * ����fee
	 * 
	 * @param fee
	 *            fee
	 */
	public void setFee(UFDouble fee) {
		this.fee = fee;
	}

	/**
	 * ��ȡfee_caltype
	 * 
	 * @return fee_caltype fee_caltype
	 */
	public Integer getFee_caltype() {
		return fee_caltype;
	}

	/**
	 * ����fee_caltype
	 * 
	 * @param fee_caltype
	 *            fee_caltype
	 */
	public void setFee_caltype(Integer fee_caltype) {
		this.fee_caltype = fee_caltype;
	}

	/**
	 * ��ȡpk_posconfig
	 * 
	 * @return pk_posconfig pk_posconfig
	 */
	public String getPk_posconfig() {
		return pk_posconfig;
	}

	/**
	 * ����pk_posconfig
	 * 
	 * @param pk_posconfig
	 *            pk_posconfig
	 */
	public void setPk_posconfig(String pk_posconfig) {
		this.pk_posconfig = pk_posconfig;
	}

	/**
	 * ��ȡtoplimit_money
	 * 
	 * @return toplimit_money toplimit_money
	 */
	public UFDouble getToplimit_money() {
		return toplimit_money;
	}

	/**
	 * ����toplimit_money
	 * 
	 * @param toplimit_money
	 *            toplimit_money
	 */
	public void setToplimit_money(UFDouble toplimit_money) {
		this.toplimit_money = toplimit_money;
	}

	/**
	 * ��ȡlowestlimit_money
	 * @return lowestlimit_money lowestlimit_money
	 */
	public UFDouble getLowestlimit_money() {
		return lowestlimit_money;
	}

	/**
	 * ����lowestlimit_money
	 * @param lowestlimit_money lowestlimit_money
	 */
	public void setLowestlimit_money(UFDouble lowestlimit_money) {
		this.lowestlimit_money = lowestlimit_money;
	}
}
