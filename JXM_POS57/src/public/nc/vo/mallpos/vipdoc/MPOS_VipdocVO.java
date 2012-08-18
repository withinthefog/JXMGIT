/**
 * 
 */
package nc.vo.mallpos.vipdoc;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;

/**
 * @author chenguogang
 *
 */
public class MPOS_VipdocVO extends SuperVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public MPOS_VipdocVO() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see nc.vo.pub.SuperVO#getPKFieldName()
	 */
	@Override
	public String getPKFieldName() {
		// TODO Auto-generated method stub
		return "pk_vipdoc";
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
		return "mpos_vipdoc";
	}
	//����
	private String pk_vipdoc;
	//����
	private String vname;
	//�ƶ��绰
	private String mobilephone;
	//��ַ
	private String address;
	//���֤��
	private String idno;
	//¥������
	private String pk_houses;
	//������
	private String pk_creator;
	//����ʱ��
	private String create_date;
	//��ͥ�绰
	private String homephone;
	//�칫�绰
	private String officephone;
	//�ʼ�
	private String email;
	//��������
	private String zip;
	//��Ա����
	private UFDouble grade;

	public String getPk_vipdoc() {
		return pk_vipdoc;
	}

	public void setPk_vipdoc(String pkVipdoc) {
		pk_vipdoc = pkVipdoc;
	}

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIdno() {
		return idno;
	}

	public void setIdno(String idno) {
		this.idno = idno;
	}

	public String getPk_houses() {
		return pk_houses;
	}

	public void setPk_houses(String pkHouses) {
		pk_houses = pkHouses;
	}

	public String getPk_creator() {
		return pk_creator;
	}

	public void setPk_creator(String pkCreator) {
		pk_creator = pkCreator;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String createDate) {
		create_date = createDate;
	}

	public String getHomephone() {
		return homephone;
	}

	public void setHomephone(String homephone) {
		this.homephone = homephone;
	}

	public String getOfficephone() {
		return officephone;
	}

	public void setOfficephone(String officephone) {
		this.officephone = officephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public UFDouble getGrade() {
		return grade;
	}

	public void setGrade(UFDouble grade) {
		this.grade = grade;
	}
	
}
