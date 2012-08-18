package nc.ui.mallpos.activity;

import nc.ui.bd.ref.AbstractRefModel;

public class ActivityTypeRefModel extends AbstractRefModel {

	public ActivityTypeRefModel() {
		super();
		m_strRefNodeName = "����Ͳ���";
		intialize();
	}

	private void intialize() {
		setRefTitle("����Ͳ���");
		// ������ʾ���ֶΣ����롢����
		setFieldCode(new String[] { "typecode", "typename", "pk_activity_type" });
		// �����ֶ�����
		setFieldName(new String[] { "���ͱ���", "��������", "����" });
		// �������ݿ������
		setTableName("mpos_activity_type");
		// ��������
		setPkFieldCode("pk_activity_type");
		// ���ñ���
		setRefCodeField("typecode");
		// ��������
		setRefNameField("typename");
		// ��������
		setOrderPart("typecode");
		// ���ù���������
		setWherePart("dr = 0 ");
	}
}
