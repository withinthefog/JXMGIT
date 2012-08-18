package nc.ui.mallpos.pub.ref;

import nc.ui.bd.ref.AbstractRefModel;
import nc.vo.mallpos.pub.MPOS_GlobalVariable;

/**
 * ��Ա���б����ģ��
 * @author chenguogang
 * @date 2012-8-9 ����15:51:30
 */
public class MPOS_VipcardRefModel extends AbstractRefModel{

	public MPOS_VipcardRefModel() {
		super();
		//�б��ֶ�
		setFieldCode(new String[] {"mpos_vip_card.inner_code","mpos_vip_card.print_code","mpos_vipdoc.vname","mpos_vipdoc.grade"});
		//�б��ֶζ�Ӧ������
		setFieldName(new String[]{"�ڲ�����","ӡˢ����","��Ա����"});
		//���ݿ����
		setTableName("mpos_vip_card inner join mpos_vipdoc_card on mpos_vip_card.pk_vip_card = mpos_vipdoc_card.pk_vip_card inner join mpos_vipdoc on mpos_vipdoc_card.pk_vipdoc = mpos_vipdoc.pk_vipdoc");
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
		setWherePart(" isnull(mpos_vip_card.dr,0) = 0 and mpos_vip_card.wstatus ="+MPOS_GlobalVariable.VIPCARD_STATUS_ENABLED);
	}
	
}
