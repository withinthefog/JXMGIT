/**
 * 
 */
package nc.vo.mallpos.vipcardgrade;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;

/**
 * @author chenguogang
 *
 */
public class MPOS_VipcardgradeVO extends SuperVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public MPOS_VipcardgradeVO() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see nc.vo.pub.SuperVO#getPKFieldName()
	 */
	@Override
	public String getPKFieldName() {
		// TODO Auto-generated method stub
		return "pk_vipcard_grade";
	}

	/* (non-Javadoc)
	 * @see nc.vo.pub.SuperVO#getParentPKFieldName()
	 */
	@Override
	public String getParentPKFieldName() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see nc.vo.pub.SuperVO#getTableName()
	 */
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "mpos_vipcard_grade";
	}
	//主键
	private String pk_vipcard_grade;
	//编码
	private String grade_code;
	//名称
	private String grade_name;
	//积分规则
	private UFDouble inl_rule;

	
	
	public String getPk_vipcard_grade() {
		return pk_vipcard_grade;
	}

	public void setPk_vipcard_grade(String pkVipcardGrade) {
		pk_vipcard_grade = pkVipcardGrade;
	}

	public String getGrade_code() {
		return grade_code;
	}

	public void setGrade_code(String gradeCode) {
		grade_code = gradeCode;
	}

	public String getGrade_name() {
		return grade_name;
	}

	public void setGrade_name(String gradeName) {
		grade_name = gradeName;
	}

	public UFDouble getInl_rule() {
		return inl_rule;
	}

	public void setInl_rule(UFDouble inlRule) {
		inl_rule = inlRule;
	}
	
	
}
