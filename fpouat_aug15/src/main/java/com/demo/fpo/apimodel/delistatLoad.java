package com.demo.fpo.apimodel;
import javax.persistence.Column;
	import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
	import javax.persistence.SequenceGenerator;
	import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

//	import com.fasterxml.jackson.annotation.JsonIgnore;
	import com.fasterxml.jackson.annotation.JsonProperty;
	@Entity
	@Table(name ="FPO_DELISTAT_REQ")
public class delistatLoad {
	
	

		@Id
	    //@GeneratedValue(strategy = GenerationType.IDENTITY)
	//	@SequenceGenerator(name="fromtodtfpo", sequenceName="ARR_FPO")
		@GenericGenerator(name = "FPO_DELISTAT_REQ_ID", strategy = "com.seq.gen.FPO_DELISTAT_REQ_SEQIdGenerator")
		@GeneratedValue(generator = "FPO_DELISTAT_REQ_ID")
		
		@JsonProperty(value = "ArticleNo")
		@Column(name = "SPEEDPOST_NO")
		    private String id;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}
}
