package ru.geekbrains.spring.winter.market.soapControllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.geekbrains.spring.winter.market.soap.GetAllProductsRequest;
import ru.geekbrains.spring.winter.market.soap.GetAllProductsResponse;

@Endpoint
@RequiredArgsConstructor
@Slf4j
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.flamexander.com/spring/ws/Products";
    private final ProductSoapService productSoapService;
    private final ProductSoapRepository productSoapRepository;

    /*
        Пример запроса: POST http://localhost:8080/ws
        Header -> Content-Type: text/xml

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:f="http://www.flamexander.com/spring/ws/products">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getAllProductsRequest/>
            </soapenv:Body>
        </soapenv:Envelope>
     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProducts(@RequestPayload GetAllProductsRequest request) {
        log.warn("Hello");
        GetAllProductsResponse response = new GetAllProductsResponse();
        productSoapService.getAllProducts().forEach(response.getProducts()::add);
        return response;
    }
}
