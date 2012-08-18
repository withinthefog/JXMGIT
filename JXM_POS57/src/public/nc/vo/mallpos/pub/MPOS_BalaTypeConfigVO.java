package nc.vo.mallpos.pub;

import nc.dev.tools.BillTempletParams;
import nc.dev.tools.Param;
import nc.vo.pub.*;
import nc.vo.pub.lang.*;

/**
 * ���㷽ʽ������Ϣ
 * 
 * @author cj
 * @date 2012-8-7 ����03:28:37
 */
public class MPOS_BalaTypeConfigVO extends SuperVO {
	private static final long serialVersionUID = 1L;

	/**
	 * ����
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "����"))
	private String pk_balatype_config;

	/**
	 * ���㷽ʽ����
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "���㷽ʽ"))
	private String pk_balatype;

	/**
	 * �Ƿ��ܹ�����
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "�Ƿ��ܹ�����"))
	private UFBoolean isenable_grade;

	/**
	 * �Ƿ��ܹ��˻�
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "�Ƿ��ܹ��˻�"))
	private UFBoolean isenable_return;

	/**
	 * �Ƿ����POS��
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "�Ƿ����POS��"))
	private UFBoolean iscallpos;

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
		return "pk_balatype_config";
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
		return "mpos_balatype_config";
	}

	/**
	 * ��ȡ�Ƿ����POS��
	 * 
	 * @return iscallpos �Ƿ����POS��
	 */
	public UFBoolean getIscallpos() {
		return iscallpos;
	}

	/**
	 * �����Ƿ����POS��
	 * 
	 * @param iscallpos
	 *            �Ƿ����POS��
	 */
	public void setIscallpos(UFBoolean iscallpos) {
		this.iscallpos = iscallpos;
	}

	/**
	 * ��ȡ�Ƿ��ܹ�����
	 * 
	 * @return isenable_grade �Ƿ��ܹ�����
	 */
	public UFBoolean getIsenable_grade() {
		return isenable_grade;
	}

	/**
	 * �����Ƿ��ܹ�����
	 * 
	 * @param isenable_grade
	 *            �Ƿ��ܹ�����
	 */
	public void setIsenable_grade(UFBoolean isenable_grade) {
		this.isenable_grade = isenable_grade;
	}

	/**
	 * ��ȡ�Ƿ��ܹ��˻�
	 * 
	 * @return isenable_return �Ƿ��ܹ��˻�
	 */
	public UFBoolean getIsenable_return() {
		return isenable_return;
	}

	/**
	 * �����Ƿ��ܹ��˻�
	 * 
	 * @param isenable_return
	 *            �Ƿ��ܹ��˻�
	 */
	public void setIsenable_return(UFBoolean isenable_return) {
		this.isenable_return = isenable_return;
	}

	/**
	 * ��ȡ���㷽ʽ����
	 * 
	 * @return pk_balatype ���㷽ʽ����
	 */
	public String getPk_balatype() {
		return pk_balatype;
	}

	/**
	 * ���ý��㷽ʽ����
	 * 
	 * @param pk_balatype
	 *            ���㷽ʽ����
	 */
	public void setPk_balatype(String pk_balatype) {
		this.pk_balatype = pk_balatype;
	}

	/**
	 * ��ȡ����
	 * 
	 * @return pk_balatype_config ����
	 */
	public String getPk_balatype_config() {
		return pk_balatype_config;
	}

	/**
	 * ��������
	 * 
	 * @param pk_balatype_config
	 *            ����
	 */
	public void setPk_balatype_config(String pk_balatype_config) {
		this.pk_balatype_config = pk_balatype_config;
	}

}
