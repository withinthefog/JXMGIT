/**
 * 
 */
package nc.vo.mallpos.vipdoc;

import nc.vo.pub.SuperVO;

/**
 * @author chenguogang
 *
 */
public class MPOS_VipdocCardVO extends SuperVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public MPOS_VipdocCardVO() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see nc.vo.pub.SuperVO#getPKFieldName()
	 */
	@Override
	public String getPKFieldName() {
		// TODO Auto-generated method stub
		return "pk_vipdoc_card";
	}

	/* (non-Javadoc)
	 * @see nc.vo.pub.SuperVO#getParentPKFieldName()
	 */
	@Override
	public String getParentPKFieldName() {
		// TODO Auto-generated method stub
		return "pk_vipdoc";
	}

	/* (non-Javadoc)
	 * @see nc.vo.pub.SuperVO#getTableName()
	 */
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "mpos_vipdoc_card";
	}
	//主键
	private String pk_vipdoc_card;
	//会员档案外键
	private String pk_vipdoc;
	//会员卡档案外键
	private String pk_vip_card;
	
	private String not_pk_vip_card;
	
	private String inner_code;
	
	private String print_code;
	
	private String wstatus;
	
	private String grade_name;

	public String getPk_vipdoc_card() {
		return pk_vipdoc_card;
	}

	public void setPk_vipdoc_card(String pkVipdocCard) {
		pk_vipdoc_card = pkVipdocCard;
	}

	public String getPk_vipdoc() {
		return pk_vipdoc;
	}

	public void setPk_vipdoc(String pkVipdoc) {
		pk_vipdoc = pkVipdoc;
	}

	public String getPk_vip_card() {
		return pk_vip_card;
	}

	public void setPk_vip_card(String pkVipCard) {
		pk_vip_card = pkVipCard;
	}


	public String getNot_pk_vip_card() {
		return not_pk_vip_card;
	}

	public void setNot_pk_vip_card(String notPkVipCard) {
		not_pk_vip_card = notPkVipCard;
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

	public String getGrade_name() {
		return grade_name;
	}

	public void setGrade_name(String gradeName) {
		grade_name = gradeName;
	}
	
}
