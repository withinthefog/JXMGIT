package nc.ui.mallpos.activity;

import nc.ui.bd.ref.AbstractRefModel;

public class ActivityRefModel extends AbstractRefModel {

	public ActivityRefModel() {
		super();
		m_strRefNodeName = "�����";
		intialize();
	}

	private void intialize() {
		setRefTitle("�����");
		// ������ʾ���ֶΣ����롢����
		setFieldCode(new String[] { "activitycode", "activityname", "pk_activity" });
		// �����ֶ�����
		setFieldName(new String[] { "���ͱ���", "��������", "����" });
		// �������ݿ������
		setTableName("mpos_activity");
		// ��������
		setPkFieldCode("pk_activity");
		// ���ñ���
		setRefCodeField("activitycode");
		// ��������
		setRefNameField("activityname");
		// ��������
		setOrderPart("activitycode");
		// ���ù���������
		setWherePart("isnull(dr,0) = 0 ");
	}
}
