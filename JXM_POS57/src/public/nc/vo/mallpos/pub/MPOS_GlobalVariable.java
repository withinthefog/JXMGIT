package nc.vo.mallpos.pub;

import java.awt.Font;

/**
 * ȫ�ֱ���
 * 
 * @author chenjun
 * @date 2011-10-9 ����11:05:26
 */
public class MPOS_GlobalVariable {

	/**
	 * ���������
	 */
	public static final String PK_CNY = "00010000000000000001";

	/**
	 * �������㷽ʽ����
	 */
	public static final String PK_BALATYPE = "0001ZZ100000000013DU";

	/**
	 * ������������
	 */
	public static final String PK_PAYACCOUNT = "0001ZZ10000000003K3H";

	// -----------------------------------------����ƾ֤ʱ����Ŀ����
	/**
	 * �����̻�����
	 */
	public static final String ITEMCODE_DSFUND = "01";

	/**
	 * ������
	 */
	public static final String ITEMCODE_HANDFEE = "02";

	/**
	 * ���д��
	 */
	public static final String ITEMCODE_BANKDEPOSIT = "03";

	/**
	 * �ֽ�
	 */
	public static final String ITEMCODE_CASH = "04";

	// -----------------------------------------ʧ����־����
	/**
	 * ���ɸ����־
	 */
	public static final String LOGTYPE_PAYMANT = "0";

	/**
	 * �̻�ÿ�����ۻ���
	 */
	public static final String LOGTYPE_SALEDAYSUM = "1";

	// ******************����ģ�����*****************************

	/**
	 * ��������
	 */
	public static final String BILLTYPE_BD_AREACL = "POS001";

	/**
	 * ¥�̵���
	 */
	public static final String BILLTYPE_BD_HOUSEDOC = "POS002";

	/**
	 * POS��������Ϣ
	 */
	public static final String BILLTYPE_CONFIG_POS = "POS003";

	/**
	 * �������
	 */
	public static final String BILLTYPE_BD_INVDOC = "POS004";

	/**
	 * ���㷽ʽ����
	 */
	public static final String BILLTYPE_CONFIG_BALATYPE = "POS005";

	/**
	 * �
	 */
	public static final String BILLTYPE_ACTIVITY = "POS006";

	/**
	 * �����
	 */
	public static final String BILLTYPE_ACTIVITY_TYPE = "POS007";

	/**
	 * ��Ա���� ��������
	 */
	public static final String BILLTYPE_VIPDOC = "POS010";

	/**
	 * ��Ա�ȼ� ��������
	 */
	public static final String BILLTYPE_VIP_GRADE = "POS008";

	/**
	 * ��Ա������ ��������
	 */
	public static final String BILLTYPE_VIP_CARD = "POS009";

	/**
	 * ��Ʒ�һ�
	 */
	public static final String BILLTYPE_INVEXCHANGE = "POS011";

	/**
	 * ��Ʒ�˻�
	 */
	public static final String BILLTYPE_INVRETURN = "POS012";

	/**
	 * ���Ʒ�۸��
	 */
	public static final String BILLTYPE_INVACTIVITY = "POS013";

	/**
	 * ������ϸ
	 */
	public static final String BILLTYPE_SALEDETAIL = "POS100";

	/**
	 * ������ϸ����
	 */
	public static final String BILLTYPE_SALEDETAIL_PERFECT = "POS100A";

	// �״̬
	public static final int ACTIVITY_STATUS_INIT = 1;

	public static final int ACTIVITY_STATUS_ACTIVE = 2;

	public static final int ACTIVITY_STATUS_STOP = 3;

	// �ۿ۷�ʽ
	public static final String DISCOUNT_TYPE_CUT = "1"; // ����

	public static final String DISCOUNT_TYPE_DISCOUNT = "2"; // ����

	/**
	 * ����������
	 */
	public static final String PK_ACTIVITY_TYPE = "0001ZZ10000000002UP0";

	// ******************���㷽ʽ����*****************************

	/**
	 * �ֽ�
	 */
	public static final String BALANCETYPE_CASH = "01";

	/**
	 * ���п�
	 */
	public static final String BALANCETYPE_CARD = "02";

	/**
	 * �ֽ�ȯ
	 */
	public static final String BALANCETYPE_COUPON_CASH = "03";

	/**
	 * ��������
	 */
	public static final String BALANCETYPE_GRADE_DISCASH = "04";

	/**
	 * ֧Ʊ
	 */
	public static final String BALANCETYPE_GRADE_CHECK = "05";

	// ******************���п������Ѽ��㷽ʽ*****************************
	/**
	 * �ٷֱ�
	 */
	public static final int CARD_FEE_PERCENT = 0;

	/**
	 * �̶�ֵ
	 */
	public static final int CARD_FEE_DEFAULT = 1;

	// ******************�Զ������*****************************
	/**
	 * ��Ʒ����
	 */
	public static final String DEFDOC_GOODS_MAIN = "MPOS01";

	/**
	 * ��ƷС��
	 */
	public static final String DEFDOC_GOODS_SUB = "MPOS02";

	/**
	 * ����������
	 */
	public static final String POS_BILL_CODE = "SY";

	/**
	 * ������������
	 */
	public static final Font font = new Font("����", Font.PLAIN, 25);

	// ******************��������*****************************
	/**
	 * �ۿۼ���˳���ȼ����ۻ������ۺ��
	 */
	public static final String PARAM_CODE_DISCALSEQU = "MPOS01";

	/**
	 * �ȼ�����
	 */
	public static final String DISCOUNT_SEQU_FIRSTSUB = "�ȼ�����";

	/**
	 * ���ۺ��
	 */
	public static final String DISCOUNT_SEQU_FIRSTDIS = "���ۺ��";

	/**
	 * �������ֹ���
	 */
	public static final String PARAM_CODE_DISCASHRULE = "MPOS02";

	/**
	 * �˻�ʱ�Ƿ��ܿ��������Ʋ�������
	 */
	public static final String PARAM_CODE_ISCTRL = "MPOS10";

	/**
	 * �˻����������̻�����������ƵĲ���Ա���ò�������
	 */
	public static final String PARAM_CODE_NOCTRLOP = "MPOS11";

	// ******************���۷�ʽ*****************************
	public static final String SALETYPE_SALE = "����";

	public static final String SALETYPE_RETURN = "�˻�";

	public static final String SALETYPE_SHARE = "��̯";

	// ******************������ϸ�����*****************************
	/**
	 * �����ʼ״̬
	 */
	public static final int SALEDAYSUM_BILLSTATE_DEFAULT = 0;

	/**
	 * �����Ѵ���״̬
	 */
	public static final int SALEDAYSUM_BILLSTATE_HANDLED = 1;

	/**
	 * ƾ֤��ʼ״̬
	 */
	public static final int SALEDAYSUM_VOUCHER_DEFAULT = 0;

	/**
	 * ƾ֤�Ѵ���״̬
	 */
	public static final int SALEDAYSUM_VOUCHER_HANDLED = 1;

	// ******************��Ա��״̬*****************************
	/**
	 * δ����
	 */
	public static final int VIPCARD_STATUS_UNENABLED = 0;

	/**
	 * ������
	 */
	public static final int VIPCARD_STATUS_ENABLED = 1;

	/**
	 * ��ʧ
	 */
	public static final int VIPCARD_STATUS_LOSS = 2;

	/**
	 * ����
	 */
	public static final int VIPCARD_STATUS_CANCEL = 3;

}
