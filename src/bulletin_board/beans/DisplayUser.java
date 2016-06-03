package bulletin_board.beans;

import java.io.Serializable;
import java.util.Date;

public class DisplayUser implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String account;
	private String name;
	private String password;
	private String branch_name;
	private String possition_name;
	private String status;
	private Date insertDate;
	private Date updateDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBranchName() {
		return branch_name;
	}

	public void setBranchName(String branch_name) {
		this.branch_name = branch_name;
	}

	public String getPossitionName() {
		return possition_name;
	}
	public void setPossitionName(String possition_name) {
		this.possition_name = possition_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}

