package com.hfahimi.currencyconverterrest;

import com.hfahimi.currencyconverterrest.convert.ConvertCredential;
import com.hfahimi.currencyconverterrest.convert.ConvertResponse;
import com.hfahimi.currencyconverterrest.convert.ConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ApiController {
    private final ConverterService converterService;

    @Autowired
    public ApiController(ConverterService converterService) {
        this.converterService = converterService;
    }

    @GetMapping(value = "/convert", produces = MediaType.APPLICATION_JSON_VALUE)
    public EntityModel<ConvertResponse> convertUrl (
            @RequestParam(value = "source", defaultValue = "EUR") String source,
            @RequestParam(value = "target", defaultValue = "USD") String target,
            @RequestParam(value = "amount", defaultValue = "1") float amount
            ) throws ResponseStatusException {

        ConvertCredential convertCredential = new ConvertCredential(source.toLowerCase(), target.toLowerCase(), amount);
        if(amount > Float.MAX_VALUE) {
            throw new ResponseStatusException(500, "amount exceeded max number", null);
        }
        ConvertResponse convertResponse;
        try {
            convertResponse = converterService.convert(convertCredential);
        } catch (Exception e) {
            throw new ResponseStatusException(500, e.getMessage(), null);
        }

        return EntityModel.of(
                convertResponse,
                linkTo(
                        methodOn(ApiController.class)
                                .convertUrl(source, target, amount)
                ).withSelfRel()
        );
    }

}