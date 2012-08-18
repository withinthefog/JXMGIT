package nc.vo.mallpos.pos;

import nc.dev.tools.BillTempletParams;
import nc.dev.tools.Param;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * ������ϸ��ͷ
 * 
 * @author chenjun
 * @date 2011-12-27 ����10:00:22
 */
public class MPOS_SaleDetailHeadVO extends SuperVO {

	private static final long serialVersionUID = 1L;

	/**
	 * ����
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "����"))
	private String pk_saledetail_h;

	/**
	 * ������ˮ��
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "������ˮ��"))
	private String sale_no;

	/**
	 * ��������
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "��������"))
	private UFDate bill_date;

	/**
	 * ����ƾ֤���ݺ�
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "�����"))
	private String proof_billno;

	/**
	 * ���۷�ʽ ���ۡ���؛
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "���۷�ʽ"))
	private String sale_type;

	/**
	 * �̻�������������
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "�̻�������������"))
	private String pk_cubasdoc;

	/**
	 * ����Ա����
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "����Ա����"))
	private String pk_cashier;

	/**
	 * ��λ��
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "��λ��"))
	private String bunkno;

	/**
	 * ��ע
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "��ע"))
	private String memo;

	/**
	 * �Ƿ����������������
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "�Ƿ��������"))
	private UFBoolean issend;

	/**
	 * ��������
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "��������"))
	private UFDouble bill_grade;

	/**
	 * ��Ʒ��������
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "��Ʒ��������"))
	private String pk_goods_main;

	/**
	 * ��ƷС������
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "��ƷС������"))
	private String pk_goods_sub;

	/**
	 * �ͻ�����
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "�ͻ�����"))
	private String cust_name;

	/**
	 * �ͻ��绰
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "�ͻ��绰"))
	private String cust_phone;

	/**
	 * �ͻ���ַ
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "�ͻ���ַ"))
	private String cust_address;

	/**
	 * �ͻ�¥������
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "�ͻ�¥������"))
	private String pk_houses;

	/**
	 * ��Ա������
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "��Ա������"))
	private String pk_vip_card;

	/**
	 * Ӧ���ܶ�
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "Ӧ���ܶ�"))
	private UFDouble receivable_amount;
	
	/**
	 * Դ��������(��Ҫȷ����̯��Դ����)
	 */
	private String spk_saledetail_h;
	
	/**
	 * ��˾����
	 */
	private String pk_corp;

	// -------------------------------
	private String custcode;

	private String custname;

	private String cashiercode;

	private String cashiername;

	@Override
	public String getPKFieldName() {
		return "pk_saledetail_h";
	}

	@Override
	public String getParentPKFieldName() {
		return null;
	}

	@Override
	public String getTableName() {
		return "mpos_saledetail_h";
	}

	/**
	 * ��ȡ��λ��
	 * 
	 * @return bunkno ��λ��
	 */
	public String getBunkno() {
		return bunkno;
	}

	/**
	 * ������λ��
	 * 
	 * @param bunkno
	 *            ��λ��
	 */
	public void setBunkno(String bunkno) {
		this.bunkno = bunkno;
	}

	/**
	 * ��ȡcashiercode
	 * 
	 * @return cashiercode cashiercode
	 */
	public String getCashiercode() {
		return cashiercode;
	}

	/**
	 * ����cashiercode
	 * 
	 * @param cashiercode
	 *            cashiercode
	 */
	public void setCashiercode(String cashiercode) {
		this.cashiercode = cashiercode;
	}

	/**
	 * ��ȡcashiername
	 * 
	 * @return cashiername cashiername
	 */
	public String getCashiername() {
		return cashiername;
	}

	/**
	 * ����cashiername
	 * 
	 * @param cashiername
	 *            cashiername
	 */
	public void setCashiername(String cashiername) {
		this.cashiername = cashiername;
	}

	/**
	 * ��ȡ-------------------
	 * 
	 * @return custcode -------------------
	 */
	public String getCustcode() {
		return custcode;
	}

	/**
	 * ����-------------------
	 * 
	 * @param custcode
	 *            -------------------
	 */
	public void setCustcode(String custcode) {
		this.custcode = custcode;
	}

	/**
	 * ��ȡcustname
	 * 
	 * @return custname custname
	 */
	public String getCustname() {
		return custname;
	}

	/**
	 * ����custname
	 * 
	 * @param custname
	 *            custname
	 */
	public void setCustname(String custname) {
		this.custname = custname;
	}

	/**
	 * ��ȡ�Ƿ����������������
	 * 
	 * @return issend �Ƿ����������������
	 */
	public UFBoolean getIssend() {
		return issend;
	}

	/**
	 * �����Ƿ����������������
	 * 
	 * @param issend
	 *            �Ƿ����������������
	 */
	public void setIssend(UFBoolean issend) {
		this.issend = issend;
	}

	/**
	 * ��ȡ��ע
	 * 
	 * @return memo ��ע
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * ���ñ�ע
	 * 
	 * @param memo
	 *            ��ע
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * ��ȡ����Ա����
	 * 
	 * @return pk_cashier ����Ա����
	 */
	public String getPk_cashier() {
		return pk_cashier;
	}

	/**
	 * ��������Ա����
	 * 
	 * @param pk_cashier
	 *            ����Ա����
	 */
	public void setPk_cashier(String pk_cashier) {
		this.pk_cashier = pk_cashier;
	}

	/**
	 * ��ȡ�̻�������������
	 * 
	 * @return pk_cubasdoc �̻�������������
	 */
	public String getPk_cubasdoc() {
		return pk_cubasdoc;
	}

	/**
	 * �����̻�������������
	 * 
	 * @param pk_cubasdoc
	 *            �̻�������������
	 */
	public void setPk_cubasdoc(String pk_cubasdoc) {
		this.pk_cubasdoc = pk_cubasdoc;
	}

	/**
	 * ��ȡ����
	 * 
	 * @return pk_saledetail_h ����
	 */
	public String getPk_saledetail_h() {
		return pk_saledetail_h;
	}

	/**
	 * ��������
	 * 
	 * @param pk_saledetail_h
	 *            ����
	 */
	public void setPk_saledetail_h(String pk_saledetail_h) {
		this.pk_saledetail_h = pk_saledetail_h;
	}

	/**
	 * ��ȡ����ƾ֤���ݺ�
	 * 
	 * @return proof_billno ����ƾ֤���ݺ�
	 */
	public String getProof_billno() {
		return proof_billno;
	}

	/**
	 * ���ý���ƾ֤���ݺ�
	 * 
	 * @param proof_billno
	 *            ����ƾ֤���ݺ�
	 */
	public void setProof_billno(String proof_billno) {
		this.proof_billno = proof_billno;
	}

	/**
	 * ��ȡ������ˮ��
	 * 
	 * @return sale_no ������ˮ��
	 */
	public String getSale_no() {
		return sale_no;
	}

	/**
	 * ����������ˮ��
	 * 
	 * @param sale_no
	 *            ������ˮ��
	 */
	public void setSale_no(String sale_no) {
		this.sale_no = sale_no;
	}

	/**
	 * ��ȡ���۷�ʽ���ۡ���؛
	 * 
	 * @return sale_type ���۷�ʽ���ۡ���؛
	 */
	public String getSale_type() {
		return sale_type;
	}

	/**
	 * �������۷�ʽ���ۡ���؛
	 * 
	 * @param sale_type
	 *            ���۷�ʽ���ۡ���؛
	 */
	public void setSale_type(String sale_type) {
		this.sale_type = sale_type;
	}

	/**
	 * ��ȡ��������
	 * 
	 * @return bill_grade ��������
	 */
	public UFDouble getBill_grade() {
		return bill_grade;
	}

	/**
	 * ���ñ�������
	 * 
	 * @param bill_grade
	 *            ��������
	 */
	public void setBill_grade(UFDouble bill_grade) {
		this.bill_grade = bill_grade;
	}

	/**
	 * ��ȡ�ͻ���ַ
	 * 
	 * @return cust_address �ͻ���ַ
	 */
	public String getCust_address() {
		return cust_address;
	}

	/**
	 * ���ÿͻ���ַ
	 * 
	 * @param cust_address
	 *            �ͻ���ַ
	 */
	public void setCust_address(String cust_address) {
		this.cust_address = cust_address;
	}

	/**
	 * ��ȡ�ͻ�����
	 * 
	 * @return cust_name �ͻ�����
	 */
	public String getCust_name() {
		return cust_name;
	}

	/**
	 * ���ÿͻ�����
	 * 
	 * @param cust_name
	 *            �ͻ�����
	 */
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	/**
	 * ��ȡ�ͻ��绰
	 * 
	 * @return cust_phone �ͻ��绰
	 */
	public String getCust_phone() {
		return cust_phone;
	}

	/**
	 * ���ÿͻ��绰
	 * 
	 * @param cust_phone
	 *            �ͻ��绰
	 */
	public void setCust_phone(String cust_phone) {
		this.cust_phone = cust_phone;
	}

	/**
	 * ��ȡ��Ʒ��������
	 * 
	 * @return pk_goods_main ��Ʒ��������
	 */
	public String getPk_goods_main() {
		return pk_goods_main;
	}

	/**
	 * ������Ʒ��������
	 * 
	 * @param pk_goods_main
	 *            ��Ʒ��������
	 */
	public void setPk_goods_main(String pk_goods_main) {
		this.pk_goods_main = pk_goods_main;
	}

	/**
	 * ��ȡ��ƷС������
	 * 
	 * @return pk_goods_sub ��ƷС������
	 */
	public String getPk_goods_sub() {
		return pk_goods_sub;
	}

	/**
	 * ������ƷС������
	 * 
	 * @param pk_goods_sub
	 *            ��ƷС������
	 */
	public void setPk_goods_sub(String pk_goods_sub) {
		this.pk_goods_sub = pk_goods_sub;
	}

	/**
	 * ��ȡ�ͻ�¥������
	 * 
	 * @return pk_houses �ͻ�¥������
	 */
	public String getPk_houses() {
		return pk_houses;
	}

	/**
	 * ���ÿͻ�¥������
	 * 
	 * @param pk_houses
	 *            �ͻ�¥������
	 */
	public void setPk_houses(String pk_houses) {
		this.pk_houses = pk_houses;
	}

	/**
	 * ��ȡ��Ա������
	 * 
	 * @return pk_vip_card ��Ա������
	 */
	public String getPk_vip_card() {
		return pk_vip_card;
	}

	/**
	 * ���û�Ա������
	 * 
	 * @param pk_vip_card
	 *            ��Ա������
	 */
	public void setPk_vip_card(String pk_vip_card) {
		this.pk_vip_card = pk_vip_card;
	}

	/**
	 * ��ȡ��������
	 * 
	 * @return bill_date ��������
	 */
	public UFDate getBill_date() {
		return bill_date;
	}

	/**
	 * ���õ�������
	 * 
	 * @param bill_date
	 *            ��������
	 */
	public void setBill_date(UFDate bill_date) {
		this.bill_date = bill_date;
	}

	/**
	 * ��ȡӦ���ܶ�
	 * 
	 * @return receivable_amount Ӧ���ܶ�
	 */
	public UFDouble getReceivable_amount() {
		return receivable_amount;
	}

	/**
	 * ����Ӧ���ܶ�
	 * 
	 * @param receivable_amount
	 *            Ӧ���ܶ�
	 */
	public void setReceivable_amount(UFDouble receivable_amount) {
		this.receivable_amount = receivable_amount;
	}

	/**
	 * ��ȡԴ��������
	 * @return spk_saledetail_h Դ��������
	 */
	public String getSpk_saledetail_h() {
		return spk_saledetail_h;
	}

	/**
	 * ����Դ��������
	 * @param spk_saledetail_h Դ��������
	 */
	public void setSpk_saledetail_h(String spk_saledetail_h) {
		this.spk_saledetail_h = spk_saledetail_h;
	}

	/**
	 * ��ȡ��˾����
	 * @return pk_corp ��˾����
	 */
	public String getPk_corp() {
		return pk_corp;
	}

	/**
	 * ���ù�˾����
	 * @param pk_corp ��˾����
	 */
	public void setPk_corp(String pk_corp) {
		this.pk_corp = pk_corp;
	}

}
