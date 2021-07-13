package ar.com.plug.examen.domain.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;



/**
 *
 * @author Joan Rey
 */
@Data
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String reference;
    
    
    public Transaction() {
    	
    }
    /**
     * 
     * Declaro constructor 
     * 
     * @param id
     * @param reference
     * @param ibanAccount
     * @param date
     * @param amount
     * @param fee
     * @param description
     * @param status
     * @param channel
     */
    public Transaction(long id, String reference, String ibanAccount, LocalDateTime date, double amount, double fee,
			String description, String status, String channel) {
		super();
		this.id = id;
		this.reference = reference;
		this.ibanAccount = ibanAccount;
		this.date = date;
		this.amount = amount;
		this.fee = fee;
		this.description = description;
		this.status = status;
		this.channel = channel;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getIbanAccount() {
		return ibanAccount;
	}
	public void setIbanAccount(String ibanAccount) {
		this.ibanAccount = ibanAccount;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	private String ibanAccount;
    private LocalDateTime date;
    private double amount ;
    private double fee;
    private String description;
    private String status;
    private String channel;
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}

}
