package org.katas.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address,
 * description, quantity, price and amount. It also calculates the sales tax @
 * 10% and prints as part of order. It computes the total order amount (amount
 * of individual lineItems + total sales tax) and prints it.
 */
public class OrderReceipt {
	private Order order;
	private StringBuilder output;

	public OrderReceipt(Order order) {
		this.order = order;
		this.output = new StringBuilder();
	}

	public String printReceipt() {
		printHeaders();
		printCustomerInfo();
		processLineItems();
		return output.toString();
	}

	private StringBuilder printHeaders() {
		output.append("======Printing Orders======\n");
		return output;
	}

	/**
     * 功能描述 : 打印顾客信息
     * @Param : []
     * @Return : java.lang.StringBuilder
     * @Author : 王辉
     * @Email : wanghui16@zybank.com.cn
     * @Date : 2019-08-28 00:06
     */
	private StringBuilder printCustomerInfo() {
		output.append(order.getCustomerName());
		output.append(order.getCustomerAddress());
		return output;
	}

	/**
     * 功能描述 : 打印订单总税额
     * @Param : [totSalesTx]
     * @Return : void
     * @Author : 王辉
     * @Email : wanghui16@zybank.com.cn
     * @Date : 2019-08-28 00:06
     */
	private void printSalesTax(double totSalesTx) {
		output.append("Sales Tax").append('\t').append(totSalesTx);

	}

    /**
     * 功能描述 : 处理订单所有产品信息
     * @Param : []
     * @Return : void
     * @Author : 王辉
     * @Email : wanghui16@zybank.com.cn
     * @Date : 2019-08-28 00:08
     */
	private void processLineItems() {
	    //全部商品的总税额
		double totalSalesTx = 0d;
		//全部商品加上税额以后的总价格
		double total = 0d;
		for (LineItem lineItem : order.getLineItems()) {
            printItemInfo(lineItem);

			double salesTax = calculateSalesTax(lineItem);

            totalSalesTx += salesTax;

            total += lineItem.totalAmount() + salesTax;
		}

        printSalesTax(totalSalesTx);

		printTotalAmount(total);
	}

	/**
     * 功能描述 : 计算商品的税额
     * @Param : [lineItem]
     * @Return : double
     * @Author : 王辉
     * @Email : wanghui16@zybank.com.cn
     * @Date : 2019-08-27 23:58
     */
	private double calculateSalesTax(LineItem lineItem) {
		double taxRate = .10;

		return lineItem.totalAmount() * taxRate;
	}

	/**
     * 功能描述 : 打印Item信息
     * @Param : [lineItem]
     * @Return : void
     * @Author : 王辉
     * @Email : wanghui16@zybank.com.cn
     * @Date : 2019-08-28 00:03
     */
	private void printItemInfo(LineItem lineItem){
        output.append(lineItem.getDesc());
        output.append('\t');
        output.append(lineItem.getPrice());
        output.append('\t');
        output.append(lineItem.getQuantity());
        output.append('\t');
        output.append(lineItem.totalAmount());
        output.append('\n');
    }

	/**
     * 功能描述 : 打印订单总额
     * @Param : [total]
     * @Return : void
     * @Author : 王辉
     * @Email : wanghui16@zybank.com.cn
     * @Date : 2019-08-28 00:01
     */
	private void printTotalAmount(double total) {
		output.append("Total Amount").append('\t').append(total);
	}

}