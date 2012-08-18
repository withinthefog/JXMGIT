package nc.ui.mallpos.pub.ref;

import nc.ui.bd.ref.AbstractRefModel;

/**
 * Ϊ��Ա�ṩ��Ա���б����ģ��
 * ������ѯ����Ա���ȼ�
 * ���˵��ѹ����� �ѹ�ʧ�� �����ϵ�
 * @author chenguogang
 * @date 2012-8-9 ����15:51:30
 */
public class MPOS_Vipcard4VipdocRefModel extends AbstractRefModel{

	public MPOS_Vipcard4VipdocRefModel() {
		super();
		//�б��ֶ�
		setFieldCode(new String[] {"mpos_vip_card.inner_code","mpos_vip_card.print_code","mpos_vipcard_grade.grade_name"});
		//�б��ֶζ�Ӧ������
		setFieldName(new String[]{"�ڲ�����","ӡˢ����","��Ա�ȼ�"});
		//���ݿ����
		setTableName("mpos_vip_card left join mpos_vipcard_grade on mpos_vipcard_grade.pk_vipcard_grade = mpos_vip_card.pk_vipcard_grade ");
		//��������
		setPkFieldCode("mpos_vip_card.pk_vip_card");
		// ���ñ���
		setRefCodeField("mpos_vip_card.inner_code");
		// ��������
		setRefNameField("mpos_vip_card.print_code");
		//δ��ʾ�������ֶ�
		setHiddenFieldCode(new String[]{"mpos_vip_card.pk_vip_card"});
		//����
		this.setRefTitle("��Ա������");
		// ��������
		setOrderPart(" mpos_vip_card.inner_code");
		// ���ù���������
		setWherePart(" isnull(mpos_vip_card.dr,0) = 0 and  mpos_vip_card.pk_vip_card not in ("+
        			   "select mpos_vipdoc_card.pk_vip_card from mpos_vipdoc left join mpos_vipdoc_card on mpos_vipdoc_card.pk_vipdoc=mpos_vipdoc.pk_vipdoc where isnull(mpos_vipdoc.dr,0)=0 and mpos_vipdoc_card.pk_vip_card is not null)  and mpos_vip_card.wstatus='0'");
	}
	
}
