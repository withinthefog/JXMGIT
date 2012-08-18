/**
 * 
 */
package nc.vo.mallpos.vipcard;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;

/**
 * @author chenguogang
 *
 */
public class MPOS_VipcardVO extends SuperVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public MPOS_VipcardVO() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see nc.vo.pub.SuperVO#getPKFieldName()
	 */
	@Override
	public String getPKFieldName() {
		// TODO Auto-generated method stub
		return "pk_vip_card";
	}

	/* (non-Javadoc)
	 * @see nc.vo.pub.SuperVO#getParentPKFieldName()
	 */
	@Override
	public String getParentPKFieldName() {
		// TODO Auto-generated method stub
		return "pk_vip_card";
	}

	/* (non-Javadoc)
	 * @see nc.vo.pub.SuperVO#getTableName()
	 */
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "mpos_vip_card";
	}
	//主键
	private String pk_vip_card;
	//内部编码
	private String inner_code;
	//印刷编码
	private String print_code;
	//状态
	private String wstatus;
	//会员卡等级
	private String pk_vipcard_grade;

	public String getPk_vip_card() {
		return pk_vip_card;
	}

	public void setPk_vip_card(String pkVipCard) {
		pk_vip_card = pkVipCard;
	}

	public String getInner_code() {
		return inner_code;
	}

	public void setInner_code(String innerCode) {
		inner_code = innerCode;
	}

	public String getPrint_code() {
		return print_code;
	}

	public void setPrint_code(String printCode) {
		print_code = printCode;
	}

	public String getWstatus() {
		return wstatus;
	}

	public void setWstatus(String wstatus) {
		this.wstatus = wstatus;
	}

	public String getPk_vipcard_grade() {
		return pk_vipcard_grade;
	}

	public void setPk_vipcard_grade(String pkVipcardGrade) {
		pk_vipcard_grade = pkVipcardGrade;
	}
	
}
