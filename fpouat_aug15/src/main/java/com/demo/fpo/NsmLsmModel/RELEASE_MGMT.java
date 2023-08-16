package com.demo.fpo.NsmLsmModel;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RELEASE_MGMT")
public class RELEASE_MGMT {
  @Id
  
  @Column(name = "APPNAME")
  private String AppName;
  
  @Column(name = "MODNAME")
  private String ModName;
  
  @Column(name = "ROLENAME")
  private String RoleName;
  
  @Column(name = "YEAR")
  private int Year;
  
  @Column(name = "MONTH")
  private int Month;
  
  @Column(name = "VERSION")
  private String Version;
  
  @Column(name = "UPD_DATA")
  private String Upd_Data;
  
  @Column(name = "UPD_DT")
  private Date Upd_DT;

public String getAppName() {
	return AppName;
}

public void setAppName(String appName) {
	AppName = appName;
}

public String getModName() {
	return ModName;
}

public void setModName(String modName) {
	ModName = modName;
}

public String getRoleName() {
	return RoleName;
}

public void setRoleName(String roleName) {
	RoleName = roleName;
}

public int getYear() {
	return Year;
}

public void setYear(int year) {
	Year = year;
}

public int getMonth() {
	return Month;
}

public void setMonth(int month) {
	Month = month;
}

public String getVersion() {
	return Version;
}

public void setVersion(String version) {
	Version = version;
}

public String getUpd_Data() {
	return Upd_Data;
}

public void setUpd_Data(String upd_Data) {
	Upd_Data = upd_Data;
}

public Date getUpd_DT() {
	return Upd_DT;
}

public void setUpd_DT(Date upd_DT) {
	Upd_DT = upd_DT;
}
  
  
}
