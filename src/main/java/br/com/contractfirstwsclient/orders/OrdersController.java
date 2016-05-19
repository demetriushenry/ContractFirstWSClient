package br.com.contractfirstwsclient.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.site.schema.order.ObjectFactory;
import com.site.schema.order.OrderInquiryResponseType;
import com.site.schema.order.OrderInquiryType;
import com.site.service.orders.Orders;

@Controller("/processOrderPlacement")
public class OrdersController {
	
	@Autowired
	private Orders orders;
	
	@RequestMapping(method = RequestMethod.GET)
	public String processOrderPlacement(ModelMap model) throws Exception {
		OrderInquiryType orderInquiry = new ObjectFactory().createOrderInquiryType();
		orderInquiry.setAccountId(1);
		orderInquiry.setEan13(2235456567L);
		orderInquiry.setOrderQuantity(2);
		orderInquiry.setUniqueOrderId(999);
		
		OrderInquiryResponseType response = orders.processOrderPlacement(orderInquiry);
		model.addAttribute("orderStatus", response.getOrder().getOrderStatus());
		
		return "processOrderPlacement";
	}

}
