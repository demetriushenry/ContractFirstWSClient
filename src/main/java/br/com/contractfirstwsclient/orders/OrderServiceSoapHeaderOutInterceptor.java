package br.com.contractfirstwsclient.orders;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.binding.soap.interceptor.SoapOutInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.apache.cxf.phase.Phase;

/**
 * Classe OutInterceptor customizada CXF
 * 
 * @author demetrius.v.oliveira
 *
 */
public class OrderServiceSoapHeaderOutInterceptor extends AbstractSoapInterceptor {
	
	
	/**
	 * Construtor setando tipo de Phase
	 */
	public OrderServiceSoapHeaderOutInterceptor() {
		super(Phase.WRITE);
		this.addBefore(SoapOutInterceptor.class.getName());
	}

	/** 
	 * Metodo da interface
	 */
	@Override
	public void handleMessage(final SoapMessage message) throws Fault {
		final QName qName = new QName("http://www.site.com/service/Orders/", "apikey");
		final String apikey = "a1b2c3d4e5";
		
		try {
			final SoapHeader header = new SoapHeader(qName, apikey, new JAXBDataBinding(apikey.getClass()));
			message.getHeaders().add(header);
		} catch (JAXBException e) {
			throw new Fault(e);
		}
	}

}
