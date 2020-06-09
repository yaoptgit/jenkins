package com.gomro.pojo;

public class ParticipateInAQuoteData {
	// UserName(用户名) Password(密码) UntaxedUnitPrice(未税金额) Delivery(货期)
	// DeliveryAddress(发货地址)
	private String userName;
	private String password;
	private String untaxedUnitPrice;
	private String delivery;
	private String deliveryAddress;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUntaxedUnitPrice() {
		return untaxedUnitPrice;
	}

	public void setUntaxedUnitPrice(String untaxedUnitPrice) {
		this.untaxedUnitPrice = untaxedUnitPrice;
	}

	public String getDelivery() {
		return delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	@Override
	public String toString() {
		return "Participate_In_A_QuoteData [userName=" + userName + ", password=" + password + ", untaxedUnitPrice="
				+ untaxedUnitPrice + ", delivery=" + delivery + ", deliveryAddress=" + deliveryAddress + "]";
	}

}
