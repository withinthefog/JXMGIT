package nc.ui.mallpos.pub.ref;

import nc.ui.bd.ref.AbstractRefModel;

/**
 * ��Ա�ȼ��б����ģ��
 * @author chenguogang
 * @date 2012-8-9 ����15:51:30
 */
public class MPOS_VipcardgradeRefModel extends AbstractRefModel{

	public MPOS_VipcardgradeRefModel() {
		super();
		//�б��ֶ�
		setFieldCode(new String[] {"grade_code","grade_name","inl_rule"});
		//�б��ֶζ�Ӧ������
		setFieldName(new String[]{"�ȼ�����","�ȼ�����","���ֹ���"});
		//���ݿ����
		setTableName("MPOS_VIPCARD_GRADE");
		//��������
		setPkFieldCode("pk_vipcard_grade");
		// ���ñ���
		setRefCodeField("grade_code");
		// ��������
		setRefNameField("grade_name");
		//δ��ʾ�������ֶ�
		setHiddenFieldCode(new String[]{"pk_vipcard_grade"});
		//����
		this.setRefTitle("��Ա���ȼ�����");
		// ��������
		setOrderPart(" grade_code");
		// ���ù���������
		setWherePart(" isnull(dr,0) = 0 ");
	}
	
}
