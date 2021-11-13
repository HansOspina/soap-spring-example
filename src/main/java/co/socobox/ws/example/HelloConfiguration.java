package co.socobox.ws.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

@Configuration
public class HelloConfiguration {

  @Bean
  public Jaxb2Marshaller marshaller() {
    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    // this ContextPath(i.e. package) must match the value of <generatePackage> specified in pom.xml

    marshaller.setContextPath("co.socobox.ws.codegen");
    return marshaller;
  }

//  @Bean
//  public WebServiceTemplate webServiceTemplate() {
//    WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
//    webServiceTemplate.setMarshaller(marshaller());
//    webServiceTemplate.setUnmarshaller(marshaller());
//    webServiceTemplate.setDefaultUri("http://www.learnwebservices.com/services/hello");
//    // set a HttpComponentsMessageSender which provides support for basic authentication
//    //webServiceTemplate.setMessageSender(httpComponentsMessageSender());
//
//    return webServiceTemplate;
//  }




  @Bean
  public HelloClient helloClient(Jaxb2Marshaller marshaller) {
    HelloClient client = new HelloClient();
//		http://127.0.0.1:8080/ws is refer to "soap:address location" from wsdl file as below
//		<wsdl:service name="CountriesPortService">
//		<wsdl:port binding="tns:CountriesPortSoap11" name="CountriesPortSoap11">
//		<soap:address location="http://127.0.0.1:8080/ws"/>
    client.setDefaultUri("https://www.learnwebservices.com/services/hello");
    client.setMarshaller(marshaller);
    client.setUnmarshaller(marshaller);
    return client;

  }
}