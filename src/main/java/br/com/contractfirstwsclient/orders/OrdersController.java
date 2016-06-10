package br.com.contractfirstwsclient.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.site.schema.order.ObjectFactory;
import com.site.schema.order.OrderInquiryResponseType;
import com.site.schema.order.OrderInquiryType;
import com.site.schema.order.OrderType;
import com.site.service.orders.Orders;

/**
 * Classe do controlador da pagina processOrderPlacement
 * 
 * @author demetrius.v.oliveira
 */
@Controller("/processOrderPlacement")
public class OrdersController {
	
	/**
	 * Interface Webservice
	 */
	@Autowired
	private Orders orders;
	private ObjectFactory factory;
	

	/**
	 * Construtor
	 */
	public OrdersController() {
		factory = new ObjectFactory();
	}

	/**
	 * Metodo controlador processOrderPlacement
	 * que chama as classes request e response
	 * @author demetrius.v.oliveira
	 * @param model - recebe valores a ser mostrados na tela
	 * @return - processa a pagina
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String processOrderPlacement(ModelMap model) {
		OrderInquiryType orderInquiry = factory.createOrderInquiryType();
		orderInquiry.setAccountId(1);
		orderInquiry.setEan13(2235456567L);
		orderInquiry.setOrderQuantity(2);
		orderInquiry.setUniqueOrderId(999);

		OrderInquiryResponseType response = orders.processOrderPlacement(orderInquiry);
		model.addAttribute("orderStatus", getStatus(response));

		return "processOrderPlacement";
	}
	
	private String getStatus(OrderInquiryResponseType response) {
		OrderType orderType = response.getOrder();
		return orderType.getOrderStatus();
	}

}
