package nc.vo.mallpos.pos;

import nc.dev.tools.BillTempletParams;
import nc.dev.tools.Param;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;

/**
 * ������ϸ����
 * 
 * @author chenjun
 * @date 2011-12-27 ����10:00:22
 */
public class MPOS_SaleDetailBodyVO extends SuperVO {

	private static final long serialVersionUID = 1L;

	/**
	 * ����
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "����"))
	private String pk_saledetail_b;

	/**
	 * ��������
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "��������"))
	private String pk_saledetail_h;

	/**
	 * ����ʱ��
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "����ʱ��"))
	private UFDateTime sale_datetime;

	/**
	 * ���㷽ʽ
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "���㷽ʽ"))
	private String pk_balatype;

	/**
	 * ʵ�ս��
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "ʵ�ս��"))
	private UFDouble received_money;

	/**
	 * �̳��е����
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "�̳��е����"))
	private UFDouble mall_money;

	/**
	 * �̻��е����
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "�̻��е����"))
	private UFDouble cust_money;

	/**
	 * �����
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "�����"))
	private String cardno;

	/**
	 * �˻�ԭʼ������
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "�˻�ԭʼ������"))
	private String rt_pk_saledetail;

	/**
	 * ˢ��-ϵ�y��ˮ��-���ڳ���
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "ˢ��ϵ�y��ˮ��"))
	private String systracdno;

	/**
	 * ������
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "������"))
	private UFDouble nfee;

	/**
	 * ���ֻ���
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "���ֻ���"))
	private UFDouble discash_grade;

	/**
	 * ���ѻ���
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "���ѻ���"))
	private UFDouble exp_grade;
	
	/**
	 * POS��Ʒ��,�����˻�
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "POS��Ʒ��"))
	private Integer brand;
	
	/**
	 * ����ϵ��,�����˻�
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "����ϵ��"))
	private UFDouble grade_cofit;

	// -------------------------------
	/**
	 * �˻�ԭʼ����
	 */
	private String returngoods_no;

	/**
	 * ���˻�����Ҫ�����˻�����
	 */
	private UFDouble canreturnmoney;

	/**
	 * ���㷽ʽ����
	 */
	private String balanname;

	@Override
	public String getPKFieldName() {
		return "pk_saledetail_b";
	}

	@Override
	public String getParentPKFieldName() {
		return "pk_saledetail_h";
	}

	@Override
	public String getTableName() {
		return "mpos_saledetail_b";
	}

	/**
	 * ��ȡ���˻�����Ҫ�����˻�����
	 * 
	 * @return canreturnmoney ���˻�����Ҫ�����˻�����
	 */
	public UFDouble getCanreturnmoney() {
		return canreturnmoney;
	}

	/**
	 * ���ÿ��˻�����Ҫ�����˻�����
	 * 
	 * @param canreturnmoney
	 *            ���˻�����Ҫ�����˻�����
	 */
	public void setCanreturnmoney(UFDouble canreturnmoney) {
		this.canreturnmoney = canreturnmoney;
	}

	/**
	 * ��ȡ�����
	 * 
	 * @return cardno �����
	 */
	public String getCardno() {
		return cardno;
	}

	/**
	 * ���ø����
	 * 
	 * @param cardno
	 *            �����
	 */
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	/**
	 * ��ȡ������
	 * 
	 * @return nfee ������
	 */
	public UFDouble getNfee() {
		return nfee;
	}

	/**
	 * ����������
	 * 
	 * @param nfee
	 *            ������
	 */
	public void setNfee(UFDouble nfee) {
		this.nfee = nfee;
	}

	/**
	 * ��ȡ����
	 * 
	 * @return pk_saledetail_b ����
	 */
	public String getPk_saledetail_b() {
		return pk_saledetail_b;
	}

	/**
	 * ��������
	 * 
	 * @param pk_saledetail_b
	 *            ����
	 */
	public void setPk_saledetail_b(String pk_saledetail_b) {
		this.pk_saledetail_b = pk_saledetail_b;
	}

	/**
	 * ��ȡ��������
	 * 
	 * @return pk_saledetail_h ��������
	 */
	public String getPk_saledetail_h() {
		return pk_saledetail_h;
	}

	/**
	 * ������������
	 * 
	 * @param pk_saledetail_h
	 *            ��������
	 */
	public void setPk_saledetail_h(String pk_saledetail_h) {
		this.pk_saledetail_h = pk_saledetail_h;
	}

	/**
	 * ��ȡ-------------------
	 * 
	 * @return returngoods_no -------------------
	 */
	public String getReturngoods_no() {
		return returngoods_no;
	}

	/**
	 * ����-------------------
	 * 
	 * @param returngoods_no
	 *            -------------------
	 */
	public void setReturngoods_no(String returngoods_no) {
		this.returngoods_no = returngoods_no;
	}

	/**
	 * ��ȡ�˻�ԭʼ������
	 * 
	 * @return rt_pk_saledetail �˻�ԭʼ������
	 */
	public String getRt_pk_saledetail() {
		return rt_pk_saledetail;
	}

	/**
	 * �����˻�ԭʼ������
	 * 
	 * @param rt_pk_saledetail
	 *            �˻�ԭʼ������
	 */
	public void setRt_pk_saledetail(String rt_pk_saledetail) {
		this.rt_pk_saledetail = rt_pk_saledetail;
	}

	/**
	 * ��ȡ����ʱ��
	 * 
	 * @return sale_datetime ����ʱ��
	 */
	public UFDateTime getSale_datetime() {
		return sale_datetime;
	}

	/**
	 * ��������ʱ��
	 * 
	 * @param sale_datetime
	 *            ����ʱ��
	 */
	public void setSale_datetime(UFDateTime sale_datetime) {
		this.sale_datetime = sale_datetime;
	}

	/**
	 * ��ȡˢ��-ϵ�y��ˮ��-���ڳ���
	 * 
	 * @return systracdno ˢ��-ϵ�y��ˮ��-���ڳ���
	 */
	public String getSystracdno() {
		return systracdno;
	}

	/**
	 * ����ˢ��-ϵ�y��ˮ��-���ڳ���
	 * 
	 * @param systracdno
	 *            ˢ��-ϵ�y��ˮ��-���ڳ���
	 */
	public void setSystracdno(String systracdno) {
		this.systracdno = systracdno;
	}

	/**
	 * ��ȡ���㷽ʽ
	 * 
	 * @return pk_balatype ���㷽ʽ
	 */
	public String getPk_balatype() {
		return pk_balatype;
	}

	/**
	 * ���ý��㷽ʽ
	 * 
	 * @param pk_balatype
	 *            ���㷽ʽ
	 */
	public void setPk_balatype(String pk_balatype) {
		this.pk_balatype = pk_balatype;
	}

	/**
	 * ��ȡ�̻��е����
	 * 
	 * @return cust_money �̻��е����
	 */
	public UFDouble getCust_money() {
		return cust_money;
	}

	/**
	 * �����̻��е����
	 * 
	 * @param cust_money
	 *            �̻��е����
	 */
	public void setCust_money(UFDouble cust_money) {
		this.cust_money = cust_money;
	}

	/**
	 * ��ȡ�̳��е����
	 * 
	 * @return mall_money �̳��е����
	 */
	public UFDouble getMall_money() {
		return mall_money;
	}

	/**
	 * �����̳��е����
	 * 
	 * @param mall_money
	 *            �̳��е����
	 */
	public void setMall_money(UFDouble mall_money) {
		this.mall_money = mall_money;
	}

	/**
	 * ��ȡʵ�ս��
	 * 
	 * @return received_money ʵ�ս��
	 */
	public UFDouble getReceived_money() {
		return received_money;
	}

	/**
	 * ����ʵ�ս��
	 * 
	 * @param received_money
	 *            ʵ�ս��
	 */
	public void setReceived_money(UFDouble received_money) {
		this.received_money = received_money;
	}

	/**
	 * ��ȡ���㷽ʽ����
	 * 
	 * @return balanname ���㷽ʽ����
	 */
	public String getBalanname() {
		return balanname;
	}

	/**
	 * ���ý��㷽ʽ����
	 * 
	 * @param balanname
	 *            ���㷽ʽ����
	 */
	public void setBalanname(String balanname) {
		this.balanname = balanname;
	}

	/**
	 * ��ȡbrand
	 * 
	 * @return brand brand
	 */
	public Integer getBrand() {
		return brand;
	}

	/**
	 * ����brand
	 * 
	 * @param brand
	 *            brand
	 */
	public void setBrand(Integer brand) {
		this.brand = brand;
	}

	/**
	 * ��ȡ���ֻ���
	 * 
	 * @return discash_grade ���ֻ���
	 */
	public UFDouble getDiscash_grade() {
		return discash_grade;
	}

	/**
	 * �������ֻ���
	 * 
	 * @param discash_grade
	 *            ���ֻ���
	 */
	public void setDiscash_grade(UFDouble discash_grade) {
		this.discash_grade = discash_grade;
	}

	/**
	 * ��ȡ���ѻ���
	 * 
	 * @return exp_grade ���ѻ���
	 */
	public UFDouble getExp_grade() {
		return exp_grade;
	}

	/**
	 * �������ѻ���
	 * 
	 * @param exp_grade
	 *            ���ѻ���
	 */
	public void setExp_grade(UFDouble exp_grade) {
		this.exp_grade = exp_grade;
	}

	/**
	 * ��ȡ����ϵ�������˻�
	 * @return grade_cofit ����ϵ�������˻�
	 */
	public UFDouble getGrade_cofit() {
		return grade_cofit;
	}

	/**
	 * ���û���ϵ�������˻�
	 * @param grade_cofit ����ϵ�������˻�
	 */
	public void setGrade_cofit(UFDouble grade_cofit) {
		this.grade_cofit = grade_cofit;
	}

}
